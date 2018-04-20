package com.innoq.blockchain0815;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProofOfWorkTest {

    @Test
    public void sixLeadingZeros_shouldDetectValidBlock() {
        Block block = Block.GENESIS;

        boolean result = ProofOfWork.SIX_LEADING_ZEROS.test(block);

        assertThat(result)
            .isTrue();
    }
}
