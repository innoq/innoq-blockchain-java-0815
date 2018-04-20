package com.innoq.blockchain0815;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MinerTest {

    @Test
    public void mine_shouldFindGenesis() throws Exception {
        Miner miner = new Miner(ProofOfWork.SIX_LEADING_ZEROS);

        Stopwatch sw = Stopwatch.createStarted();

        final Block genesis = miner.mine(
            Block.GENESIS.getIndex(),
            Block.GENESIS.getTimestamp(),
            Block.GENESIS.getTransactions(),
            Block.GENESIS.getPreviousBlockHash());

        System.out.println("Finding proof for genesis took: " + sw.stop());

        assertThat(genesis.getProof())
            .isEqualTo(Block.GENESIS.getProof());
    }
}
