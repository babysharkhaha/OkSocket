package com.xuhao.didi.oksocket.data;


import com.xuhao.didi.core.iocore.interfaces.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * Created by Tony on 2017/10/24.
 */

public class MsgDataBean implements ISendable {
    private String content = "";
    private short cmd = 20034;
    public MsgDataBean(String content) {
        this.content = content;
    }
    public MsgDataBean(String content,short cmd) {
        this.content = content;
        this.cmd = cmd;
    }

    @Override
    public byte[] parse() {
        byte[] body = null;
        if(null!=content){
            body = content.getBytes(Charset.defaultCharset());
        }
        int length = body == null ? 26 : (26 + body.length);
        ByteBuffer bb = ByteBuffer.allocate(length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(length);
        bb.putLong(sequenceId);
        bb.putShort(cmd);
        bb.putInt(version);
        bb.put(terminal.getBytes());
        bb.putInt(requestid);
        if (body != null) bb.put(body);
        return bb.array();
    }
}
