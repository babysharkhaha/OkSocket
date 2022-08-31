package com.xuhao.didi.oksocket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.core.protocol.IReaderProtocol;
import com.xuhao.didi.oksocket.adapter.LogAdapter;
import com.xuhao.didi.oksocket.data.DefaultSendBean;
import com.xuhao.didi.oksocket.data.LogBean;
import com.xuhao.didi.oksocket.data.MsgDataBean;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

import static android.widget.Toast.LENGTH_SHORT;

import org.json.JSONObject;

/**
 * Created by Tony on 2017/10/24.
 */

public class SimpleDemoActivity extends AppCompatActivity {


    private Button mConnect;

    private EditText mIPET;
    private EditText mPortET;

    private EditText mSendET;

    private Button mClearLog;
    private Button mSendBtn;

    private RecyclerView mSendList;
    private RecyclerView mReceList;
    private ConnectionInfo mInfo;
    private OkSocketOptions mOkOptions;
    private IConnectionManager mManager;
    private LogAdapter mSendLogAdapter = new LogAdapter();
    private LogAdapter mReceLogAdapter = new LogAdapter();

    private SocketActionAdapter adapter = new SocketActionAdapter() {

        @Override
        public void onSocketConnectionSuccess(ConnectionInfo info, String action) {
            mManager.send(new DefaultSendBean());
            mConnect.setText("DisConnect");
            mIPET.setEnabled(false);
            mPortET.setEnabled(false);
        }

        @Override
        public void onSocketDisconnection(ConnectionInfo info, String action, Exception e) {
            if (e != null) {
                logSend("异常断开(Disconnected with exception):" + e.getMessage());
            } else {
                logSend("正常断开(Disconnect Manually)");
            }
            mConnect.setText("Connect");
            mIPET.setEnabled(true);
            mPortET.setEnabled(true);
        }

        @Override
        public void onSocketConnectionFailed(ConnectionInfo info, String action, Exception e) {
            logSend("连接失败(Connecting Failed)");
            mConnect.setText("Connect");
            mIPET.setEnabled(true);
            mPortET.setEnabled(true);
        }

        @Override
        public void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data) {
            String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));
            logRece(str);
            Logger.i("onSocketReadResponse "+str);
        }

        @Override
        public void onSocketWriteResponse(ConnectionInfo info, String action, ISendable data) {
            String str = new String(data.parse(), Charset.forName("utf-8"));
            logSend(str);
            Logger.i("onSocketWriteResponse  "+str);
        }

        @Override
        public void onPulseSend(ConnectionInfo info, IPulseSendable data) {
            String str = new String(data.parse(), Charset.forName("utf-8"));
            logSend(str);
            Logger.i("onPulseSend "+str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        findViews();
        initData();
        setListener();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void findViews() {
        mSendList = findViewById(R.id.send_list);
        mReceList = findViewById(R.id.rece_list);
        mIPET = findViewById(R.id.ip);
        mPortET = findViewById(R.id.port);
        mClearLog = findViewById(R.id.clear_log);
        mConnect = findViewById(R.id.connect);
        mSendET = findViewById(R.id.send_et);
        mSendBtn = findViewById(R.id.send_btn);
    }

    private void initData() {
        LinearLayoutManager manager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSendList.setLayoutManager(manager1);
        mSendList.setAdapter(mSendLogAdapter);

        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mReceList.setLayoutManager(manager2);
        mReceList.setAdapter(mReceLogAdapter);

        initManager();
    }

    private void initManager() {
        final Handler handler = new Handler();
        mInfo = new ConnectionInfo(mIPET.getText().toString(), Integer.parseInt(mPortET.getText().toString()));
        mOkOptions = new OkSocketOptions.Builder()
                .setReconnectionManager(new NoneReconnect())
                .setConnectTimeoutSecond(10)
                .setCallbackThreadModeToken(new OkSocketOptions.ThreadModeToken() {
                    @Override
                    public void handleCallbackEvent(ActionDispatcher.ActionRunnable runnable) {
                        handler.post(runnable);
                    }
                })
                .setReaderProtocol(new IReaderProtocol() {
                    @Override
                    public int getHeaderLength() {
                        return 22;
                    }

                    @Override
                    public int getBodyLength(byte[] header, ByteOrder byteOrder) {
                        if (header == null || header.length < getHeaderLength()) {
                            return 0;
                        }
                        ByteBuffer bb = ByteBuffer.wrap(header);
                        bb.order(byteOrder);
                        return bb.getInt()-22;
                    }
                })
                .build();
        mManager = OkSocket.open(mInfo).option(mOkOptions);
        mManager.registerReceiver(adapter);
    }


    private void setListener() {
        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManager == null) {
                    return;
                }
                if (!mManager.isConnect()) {
                    initManager();
                    mManager.connect();
                    mIPET.setEnabled(false);
                    mPortET.setEnabled(false);
                } else {
                    mConnect.setText("Disconnecting");
                    mManager.disconnect();
                }
            }
        });
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManager == null) {
                    return;
                }
                if (!mManager.isConnect()) {
                    Toast.makeText(getApplicationContext(), "Unconnected", LENGTH_SHORT).show();
                } else {
                    String msg = mSendET.getText().toString();
                    if (TextUtils.isEmpty(msg.trim())) {
                        return;
                    }
//                  MsgDataBean msgDataBean = new MsgDataBean(msg);
                    //CONTRACT_SUBSCRIBE_SYMBOL_THUMB 58901合约
                    //SUBSCRIBE_SYMBOL_THUMB  28901行情
                    //SEND_CHAT((short) 20034), 28902聊天
                    MsgDataBean msgDataBean = new MsgDataBean(buildBodyJson(msg).toString(),ISocket.CMD.SEND_CHAT.getCode());
                    mManager.send(msgDataBean);
                    mSendET.setText("");
                }
            }
        });
        mClearLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReceLogAdapter.getDataList().clear();
                mSendLogAdapter.getDataList().clear();
                mReceLogAdapter.notifyDataSetChanged();
                mSendLogAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * {
     *   "content": "???????",
     *   "messageType": "NORMAL_CHAT",
     *   "nameFrom": "13791233920",
     *   "nameTo": "0822647054",
     *   "orderId": "381566914771550208",
     *   "sendTime": 1661180283519,
     *   "sendTimeStr": "2022-08-22 14:58:03",
     *   "uidFrom": "1",
     *   "uidTo": "600809"
     * }
     * @param content
     * @return
     */

    private JSONObject buildBodyJson(String content) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("orderId", "381566914771550208");
            //obj.put("uid", orderDetial.getMyId());
            obj.put("uidFrom", "600809");
            obj.put("uidTo", "1");
            obj.put("nameTo", "13791233920");
            obj.put("nameFrom", "0822647054");
            obj.put("messageType", 1);
            obj.put("avatar", "");
            if (!TextUtils.isEmpty(content)) obj.put("content", content);
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }

    private void logSend(final String log) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            LogBean logBean = new LogBean(System.currentTimeMillis(), log);
            mSendLogAdapter.getDataList().add(0, logBean);
            mSendLogAdapter.notifyDataSetChanged();
        } else {
            final String threadName = Thread.currentThread().getName();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    logSend(threadName + " 线程打印(In Thread):" + log);
                }
            });
        }
    }

    private void logRece(final String log) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            LogBean logBean = new LogBean(System.currentTimeMillis(), log);
            mReceLogAdapter.getDataList().add(0, logBean);
            mReceLogAdapter.notifyDataSetChanged();
        } else {
            final String threadName = Thread.currentThread().getName();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    logRece(threadName + " 线程打印(In Thread):" + log);
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mManager != null) {
            mManager.disconnect();
            mManager.unRegisterReceiver(adapter);
        }
    }
}
