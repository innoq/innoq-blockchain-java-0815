package com.innoq.blockchain0815.hash;

import java.math.BigInteger;
import java.util.function.Function;

public final class Hash {

    private final byte[] bytes;

    public Hash(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String asString() {
        return GUAVA.apply(bytes);
    }

    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    static final Function<byte[], String> GUAVA =
        bytes -> {
            final StringBuilder sb = new StringBuilder(2 * bytes.length);
            for (byte b : bytes) {
                sb.append(HEX_DIGITS[(b >> 4) & 0xf]).append(HEX_DIGITS[b & 0xf]);
            }
            return sb.toString();
    };

    static final Function<byte[], String> FORMAT =
        bytes -> String.format("%064x", new BigInteger(1, bytes));
}
