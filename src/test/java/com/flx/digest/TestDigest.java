package com.flx.digest;

import com.flx.utils.DigestSm3Utils;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

public class TestDigest {

    @Test
    public void testDigestSm3() throws Exception {
        byte[] bytes = "Hello test".getBytes(StandardCharsets.UTF_8);
        long start = System.currentTimeMillis();
        System.out.println(DatatypeConverter.printHexBinary(DigestSm3Utils.digest(bytes)));
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println(DatatypeConverter.printHexBinary(DigestSm3Utils.digest(bytes, "SM3")));
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println(DatatypeConverter.printHexBinary(DigestSm3Utils.digestByKey(bytes, "key".getBytes(StandardCharsets.UTF_8))));
        System.out.println(System.currentTimeMillis() - start);
    }

}
