package com.xuhao.didi.oksocket.data;


import android.os.Build;
import android.support.annotation.RequiresApi;

import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PulseBean implements IPulseSendable {
    private String str = "";

    public PulseBean() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd", 14);
            str = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public byte[] parse() {
        byte[] body = str.getBytes(StandardCharsets.UTF_8);
        int length = body == null ? 26 : (26 + body.length);
        ByteBuffer bb = ByteBuffer.allocate(length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(length);
        bb.putLong(sequenceId);
        bb.putShort((short) 11004);
        bb.putInt(version);
        bb.put(terminal.getBytes());
        bb.putInt(requestid);
        if (body != null) bb.put(body);
        return bb.array();
    }
}