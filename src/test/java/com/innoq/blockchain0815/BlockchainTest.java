package com.innoq.blockchain0815;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockchainTest {

    private Blockchain blockchain =
        new Blockchain(new Miner(ProofOfWork.SIX_LEADING_ZEROS));

    @Test
    public void toJson_shouldWork() {
        String json = blockchain.toJson();

        assertThat(json)
            .isEqualTo("{\"blocks\":[{\"index\":1,\"timestamp\":0,\"proof\":1917336,\"transactions\":[{\"id\":\"b3c973e2-db05-4eb5-9668-3e81c7389a6d\",\"timestamp\":0,\"payload\":\"I am Heribert Innoq\"}],\"previousBlockHash\":\"0\"}],\"blockHeight\":1}");
    }

    @Test
    public void getCurrentBlockHeight_forNewBlockchain_returns1() {
        int currentBlockHeight = blockchain.getCurrentBlockHeight();

        assertThat(currentBlockHeight)
            .isEqualTo(1);
    }

    @Test
    public void mine_shouldAddNewBlock() throws Exception {
        blockchain.mine();

        assertThat(blockchain.getCurrentBlockHeight())
            .isEqualTo(2);
    }
}
