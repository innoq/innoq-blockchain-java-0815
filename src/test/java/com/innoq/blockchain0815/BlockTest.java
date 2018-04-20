package com.innoq.blockchain0815;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockTest {

    @Test
    public void toJson_shouldWork() {
        Block block = Block.GENESIS;

        String json = block.toJson();

        assertThat(json)
            .isEqualTo("{\"index\":1,\"timestamp\":0,\"proof\":1917336,\"transactions\":[{\"id\":\"b3c973e2-db05-4eb5-9668-3e81c7389a6d\",\"timestamp\":0,\"payload\":\"I am Heribert Innoq\"}],\"previousBlockHash\":\"0\"}");
    }

    @Test
    public void toHash_shouldWork() {
        Block block = Block.GENESIS;

        String hash = block.hash();

        assertThat(hash)
            .isEqualTo("000000b642b67d8bea7cffed1ec990719a3f7837de5ef0f8ede36537e91cdc0e");
    }
}
