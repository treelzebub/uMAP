package com.treelzebub.umap.api;

import android.util.Base64;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Tre Murillo on 3/29/15
 */
public class AuthTools {
    private static SecureRandom random = new SecureRandom();

    public static String getNonce() {
        return new BigInteger(130, random).toString(32);
    }

    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static String encodeBasicAuthBase64(String rawStr) {
        return "Basic " + Base64.encodeToString(rawStr.getBytes(), Base64.NO_WRAP);
    }

    private AuthTools() {
    }
}
