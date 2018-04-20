package com.innoq.blockchain0815;

import com.google.common.hash.HashCode;

import static com.google.common.hash.Hashing.sha256;

public final class BlockHasher {

    private final BlockSerializer serializer;

    public BlockHasher(BlockSerializer serializer) {
        this.serializer = serializer;
    }

    public HashCode hash() {
        return sha256().hashBytes(serializer.serialize());
    }
}
