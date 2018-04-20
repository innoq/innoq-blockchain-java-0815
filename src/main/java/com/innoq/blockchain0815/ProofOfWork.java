package com.innoq.blockchain0815;

import java.util.function.Predicate;

public enum ProofOfWork implements Predicate<Block> {
    SIX_LEADING_ZEROS() {
        @Override
        public boolean test(Block block) {
            return block.hash().startsWith("000000");
        }
    }
}
