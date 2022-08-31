package com.aa.lib33;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;

public class ByteBufferApp {
    public static void main(String[] args) {
        testBuffer();
    }

    public static void testBuffer() {
        // 初始化一个大小为6的ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        print(buffer);  // 初始状态：position: 0, limit: 6, capacity: 6

        // 往buffer中写入3个字节的数据
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        print(buffer.put((byte) 3));
        buffer.put("a".getBytes());
        buffer.put("b".getBytes());
        buffer.put("c".getBytes());
        buffer.put("在".getBytes());
        print(buffer);  // 写入之后的状态：position: 3, limit: 6, capacity: 6

        System.out.println("************** after flip **************");
        buffer.flip();
        System.out.println(Arrays.toString(buffer.array()));
//        while (buffer.hasRemaining()) {
//            System.out.println( (char) buffer.get());
//        }

//        print(buffer);  // 切换为读取模式之后的状态：position: 0, limit: 3, capacity: 6
//
//        System.out.println(buffer.get());
//        System.out.println(buffer.get());
//        System.out.println(buffer.get());
//        print(buffer);  // 读取两个数据之后的状态：position: 2, limit: 3, capacity: 6
    }

    private static void print(ByteBuffer buffer) {
        System.out.printf("position: %d, limit: %d, capacity: %d\n",
                buffer.position(), buffer.limit(), buffer.capacity());
    }
}