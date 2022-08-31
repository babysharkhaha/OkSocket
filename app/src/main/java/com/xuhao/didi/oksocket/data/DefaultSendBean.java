package com.xuhao.didi.oksocket.data;


import android.os.Build;
import android.support.annotation.RequiresApi;

import com.xuhao.didi.core.iocore.interfaces.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by didi on 2018/6/4.
 */

public class DefaultSendBean implements ISendable {
    protected String content = "";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public final byte[] parse() {
        byte[] body = content.getBytes(StandardCharsets.UTF_8);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
