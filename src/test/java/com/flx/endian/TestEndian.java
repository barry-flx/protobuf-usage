package com.flx.endian;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.xml.bind.DatatypeConverter;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

public class TestEndian {

    @Test
    public void testUnsigned() {
        String value = "ffffffffffffffff";
        long d = Long.parseUnsignedLong(value, 16);
        System.out.println(d);
        byte[] bytes = long2Bytes(d, ByteOrder.LITTLE_ENDIAN);
        System.out.println(Hex.toHexString(bytes));
    }

    @Test
    public void testByteBuffer() {
        long d = 0x0000000000000001;
        byte[] v = long2Bytes(d, ByteOrder.LITTLE_ENDIAN);
        System.out.println(DatatypeConverter.printHexBinary(v));
        System.out.println(ByteBuffer.wrap(v).order(ByteOrder.LITTLE_ENDIAN).getInt());

        byte[] bytes = new byte[]{0x47, 0x55, 0x43, 0x65};
        int value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
        System.out.println(value);
        System.out.println(Integer.toHexString(value));

        byte[] nBytes = int2Bytes(value, ByteOrder.LITTLE_ENDIAN);
        System.out.println(DatatypeConverter.printHexBinary(nBytes));
    }

    public static byte[] long2Bytes(long value, ByteOrder order) {
        return ByteBuffer.allocate(8).order(order).putLong(value).array();
    }

    public static byte[] int2Bytes(int value, ByteOrder order) {
        return ByteBuffer.allocate(4).order(order).putInt(value).array();
    }

}
