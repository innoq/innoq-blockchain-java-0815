package com.innoq.blockchain0815;

import org.junit.Test;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class BlockSerializerTest {

    @Test
    public void toJson_forTransaction_shouldReturnValidJson() {
        Transaction transaction =
            new Transaction("b3c973e2-db05-4eb5-9668-3e81c7389a6d", 0, "I am Heribert Innoq");

        String json = BlockSerializer.toJson(transaction);

        assertThat(json)
            .isEqualTo("{\"id\":\"b3c973e2-db05-4eb5-9668-3e81c7389a6d\",\"timestamp\":0,\"payload\":\"I am Heribert Innoq\"}");
    }

    @Test
    public void toJson_forTransactions_shouldReturnValidJson() {
        List<Transaction> transactions = asList(
            new Transaction("b3c973e2-db05-4eb5-9668-3e81c7389a6d", 0, "I am Heribert Innoq"),
            new Transaction("b3c973e2-db05-4eb5-9668-3e81c7389a6d", 0, "I am Heribert Innoq")
        );

        String json = BlockSerializer.toJson(transactions);

        assertThat(json)
            .isEqualTo("[{\"id\":\"b3c973e2-db05-4eb5-9668-3e81c7389a6d\",\"timestamp\":0,\"payload\":\"I am Heribert Innoq\"},{\"id\":\"b3c973e2-db05-4eb5-9668-3e81c7389a6d\",\"timestamp\":0,\"payload\":\"I am Heribert Innoq\"}]");
    }

    @Test
    public void serialize_forGenesisBlock_shouldReturnValidJson() {
        BlockSerializer sut = new BlockSerializer(Block.GENESIS);

        byte[] result = sut.serialize();

        assertThat(result)
            .isEqualTo("{\"index\":1,\"timestamp\":0,\"proof\":1917336,\"transactions\":[{\"id\":\"b3c973e2-db05-4eb5-9668-3e81c7389a6d\",\"timestamp\":0,\"payload\":\"I am Heribert Innoq\"}],\"previousBlockHash\":\"0\"}".getBytes(UTF_8));
    }
}
