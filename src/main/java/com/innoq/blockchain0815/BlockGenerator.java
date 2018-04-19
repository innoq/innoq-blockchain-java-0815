package com.innoq.blockchain0815;

import java.util.function.Predicate;

import static java.util.Collections.emptyList;

public final class BlockGenerator {

    private final Predicate<String> validator;

    public BlockGenerator(String zeros) {
        validator = s -> s.startsWith(zeros);
    }

    public Block generateSuccessorFor(Block block) {
        final String previousHash = block.hash();
        final long timestamp = System.currentTimeMillis();
        int proof = 0;
        Block next = new Block(block.getIndex(), timestamp, proof, emptyList(), previousHash);
        while (next.isInvalid(validator)) {
            next = new Block(block.getIndex(), timestamp, ++proof, emptyList(), previousHash);
        }
        return next;
    }

}
