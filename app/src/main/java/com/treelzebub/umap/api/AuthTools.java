package com.treelzebub.umap.api;

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

    private AuthTools() {
    }
}
