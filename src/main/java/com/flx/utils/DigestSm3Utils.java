package com.flx.utils;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

public class DigestSm3Utils {

    public static byte[] digest(byte[] source) {
        if (source == null || source.length == 0) {
            return new byte[0];
        }
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(source, 0, source.length);
        byte[] digest = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(digest, 0);

        return digest;
    }

    public static byte[] digestByKey(byte[] source, byte[] key) {
        if (source == null || source.length == 0
                || key == null || key.length == 0) {
            return new byte[0];
        }
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest sm3Digest = new SM3Digest();
        HMac hMac = new HMac(sm3Digest);
        hMac.init(keyParameter);
        hMac.update(source, 0, source.length);
        byte[] digest = new byte[hMac.getMacSize()];
        hMac.doFinal(digest, 0);

        return digest;
    }

    public static byte[] digest(byte[] source, String algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        if (source == null || source.length == 0
                || algorithm == null || "".equals(algorithm)) {
            return new byte[0];
        }
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm, BouncyCastleProvider.PROVIDER_NAME);
        return messageDigest.digest(source);
    }

}
