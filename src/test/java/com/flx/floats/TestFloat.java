package com.flx.floats;

import java.util.Arrays;

public class TestFloat {

    public static void main(String[] args) {
        float f = 20.5f;
        int bits = Float.floatToIntBits(f);
        System.out.println("Float.floatToIntBits(f): " + bits);
        System.out.println("Float.floatToRawIntBits(f): " + Float.floatToRawIntBits(f));

        byte[] bytes = new byte[4];
        bytes[0] = (byte) (bits >> 24);
        bytes[1] = (byte) (bits >> 16);
        bytes[2] = (byte) (bits >> 8);
        bytes[3] = (byte) (bits >> 0);
        System.out.println("bytes: " + Arrays.toString(bytes));

        int num = 0;
        num = num | (bytes[0] & 0xff) << 24;
        num = num | (bytes[1] & 0xff) << 16;
        num = num | (bytes[2] & 0xff) << 8;
        num = num | (bytes[3] & 0xff) << 0;
        System.out.println("num: " + num);


    }


}
