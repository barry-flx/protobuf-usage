package com.flx.utils;

import java.util.SortedMap;
import java.util.TreeMap;

public class UrlBuilder {

    public static String buildUrl(String url, String secretKey) {
        return buildUrl(url, secretKey, null);
    }

    public static String buildUrl(String url, String secretKey, SortedMap<String, Object> signatureParameters) {
        return buildUrl(url, secretKey, signatureParameters, null);
    }

    public static String buildUrl(String url, String secretKey, SortedMap<String, Object> signatureParameters, SortedMap<String, Object> urlParameters) {
        String timestamp = UrlBuilder.buildTimestamp();
        if (signatureParameters == null) {
            signatureParameters = new TreeMap<>();
        }
        signatureParameters.put("timestamp", timestamp);
        String queryString = ParametersBuilder.appendParameters(signatureParameters);
        String signature = SignatureUtils.getSignature(queryString, secretKey);

        if (urlParameters == null) {
            urlParameters = new TreeMap<>();
        }
        urlParameters.put("timestamp", timestamp);
        String fullUrl = UrlBuilder.buildFullUrl(url, "", urlParameters, signature);
        System.out.println(fullUrl);

        return fullUrl;
    }

    public static String buildFullUrl(String baseUrl, SortedMap<String, Object> parameters, String signature) {
        return buildFullUrl(baseUrl, "", parameters, signature);
    }

    public static String buildFullUrl(String baseUrl, String urlPath, SortedMap<String, Object> parameters, String signature) {
        if (parameters != null && !parameters.isEmpty()) {
            StringBuilder sb = new StringBuilder(baseUrl);
            sb.append(urlPath).append('?');
            ParametersBuilder.appendParameters(sb, parameters);
            if (null != signature) {
                sb.append("&signature=").append(signature);
            }

            return sb.toString();
        } else {
            return baseUrl + urlPath;
        }
    }

    public static String buildTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

}
