package com.innoq.blockchain0815;

import com.innoq.blockchain0815.support.Stopwatch;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.assertThat;

public class MinerTest {

    @Test
    public void mine_shouldFindGenesis() throws Exception {
        Miner miner = new Miner(ProofOfWork.SIX_LEADING_ZEROS);

        Stopwatch sw = Stopwatch.started();

        final Block genesis = miner.mine(
            Block.GENESIS.index,
            Block.GENESIS.timestamp,
            Block.GENESIS.transactions,
            Block.GENESIS.previousBlockHash);

        sw.stop();
        System.out.println("Finding proof for genesis took: " + sw.elapsed(MILLISECONDS) + "ms");

        assertThat(genesis.proof)
            .isEqualTo(Block.GENESIS.proof);
    }
}
