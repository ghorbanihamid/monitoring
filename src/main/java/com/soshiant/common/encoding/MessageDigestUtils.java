package com.soshiant.common.encoding;

import java.security.MessageDigest;

public class MessageDigestUtils {
    private static final byte[] md5Digest(byte[] input) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(input);
            byte[] messageDigest = algorithm.digest();

            return messageDigest;
        } catch (Exception exception) {

        }
        return null;
    }


    private static final String md5DigestToHexString(String text) {
        try {
            byte[] messageDigest = md5Digest(text.getBytes("UTF-8"));

            return toHexString(messageDigest);
        } catch (Exception exception) {

        }
        return null;
    }


    private static final byte[] sha2Digest(byte[] input) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            algorithm.reset();
            algorithm.update(input);
            byte[] messageDigest = algorithm.digest();

            return messageDigest;
        } catch (Exception exception) {

        }
        return null;
    }


    private static final String sha2DigestToHexString(String text) {
        try {
            byte[] messageDigest = sha2Digest(text.getBytes("UTF-8"));

            return toHexString(messageDigest);
        } catch (Exception exception) {

        }
        return null;
    }


    public static final String sha2AndMd5DigestToHexString(String text) {
        try {
            byte[] md5DigestBytes = md5Digest(text.getBytes("UTF-8"));

            final String md5DigestHexString = toHexString(md5DigestBytes);

            return sha2DigestToHexString(md5DigestHexString);
        } catch (Exception exception) {

        }
        return null;
    }


    private static String toHexString(byte[] messageDigest) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < messageDigest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        }

        return hexString.toString();
    }
}
