package com.innoq.blockchain0815;

import java.util.function.Predicate;

public enum ProofOfWork implements Predicate<BlockHasher> {
    SIX_LEADING_ZEROS() {
        @Override
        public boolean test(BlockHasher blockHasher) {
            return blockHasher.hash().asString().startsWith("000000");
        }
    }
}
