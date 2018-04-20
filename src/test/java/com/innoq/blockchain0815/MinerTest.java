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
            Block.GENESIS.index,
            Block.GENESIS.timestamp,
            Block.GENESIS.transactions,
            Block.GENESIS.previousBlockHash);

        System.out.println("Finding proof for genesis took: " + sw.stop());

        assertThat(genesis.proof)
            .isEqualTo(Block.GENESIS.proof);
    }
}
