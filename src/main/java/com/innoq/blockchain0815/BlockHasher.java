package com.innoq.blockchain0815;

import com.innoq.blockchain0815.hash.Hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class BlockHasher {

    private final MessageDigest digest = sha256();
    private final BlockSerializer serializer;

    public BlockHasher(BlockSerializer serializer) {
        this.serializer = serializer;
    }

    // TODO: byte[] maybe faster than String
    public Hash hash() {
        digest.reset();
        digest.update(serializer.getPreProof());
        digest.update(serializer.getProof());
        digest.update(serializer.getPostProof());
        return new Hash(digest.digest());
    }

    private static MessageDigest sha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
