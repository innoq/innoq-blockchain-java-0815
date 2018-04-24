package com.innoq.blockchain0815;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class BlockHasher {

    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    private final MessageDigest digest = sha256();
    private final BlockSerializer serializer;

    public BlockHasher(BlockSerializer serializer) {
        this.serializer = serializer;
    }

    // TODO: byte[] maybe faster than String
    public String hash() {
        digest.reset();
        digest.update(serializer.getPreProof());
        digest.update(serializer.getProof());
        digest.update(serializer.getPostProof());
        return toString(digest.digest());
    }

    private static String toString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            sb.append(HEX_DIGITS[(b >> 4) & 0xf]).append(HEX_DIGITS[b & 0xf]);
        }
        return sb.toString();
    }

    private static MessageDigest sha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
