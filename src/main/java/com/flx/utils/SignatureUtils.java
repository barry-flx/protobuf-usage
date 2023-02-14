package com.flx.utils;

import cn.hutool.core.util.HexUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author flx
 */
public class SignatureUtils {

    /**
     * 签名算法
     *
     * @param signatureStr
     * @param key
     * @return
     */
    public static String getSignature(String signatureStr, String key) {
        byte[] hmacSha256;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(signatureStr.getBytes());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to calculate hmac-sha256", ex);
        }

        return HexUtil.encodeHexStr(hmacSha256);
    }

    /**
     * 验签
     *
     * @param signatureStr
     * @param secretKey
     * @param signature
     * @return
     */
    public static boolean validateSignature(String signatureStr, String secretKey, String signature) {
        String checkSignature;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac hmac = Mac.getInstance("HmacSHA256");
            hmac.init(secretKeySpec);
            byte[] result = hmac.doFinal(signatureStr.getBytes());
            checkSignature = HexUtil.encodeHexStr(result);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to validate signature", ex);
        }

        return signature.equals(checkSignature);
    }

}

