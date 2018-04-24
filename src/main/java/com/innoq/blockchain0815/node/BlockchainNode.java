package com.innoq.blockchain0815.node;

import com.innoq.blockchain0815.Blockchain;
import com.innoq.blockchain0815.Blockchain.MiningResult;
import com.innoq.blockchain0815.Miner;
import com.innoq.blockchain0815.ProofOfWork;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.util.UUID;

public final class BlockchainNode {

    private final UUID id = UUID.randomUUID();
    private final Blockchain blockchain;

    public BlockchainNode(Miner miner) {
        blockchain = new Blockchain(miner);
    }

    public void start(int port) throws IOException {
        final HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/", this::root);
        httpServer.createContext("/blocks", this::blocks);
        httpServer.createContext("/mine", this::mine);
        httpServer.start();
        System.out.println("Started blockchain on port: " + port);

    }

    public void root(HttpExchange exchange) throws IOException {
        if (!"/".equals(exchange.getRequestURI().toString())) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.getResponseHeaders().add("Allow", "GET");
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, 0);
        try (PrintStream out = new PrintStream(exchange.getResponseBody())) {
            out.println("{");
            out.println("  \"nodeId\": \"" + id.toString() + "\",");
            out.println("  \"currentBlockHeight\": " + blockchain.getCurrentBlockHeight());
            out.println("}");
        }
    }

    public void blocks(HttpExchange exchange) throws IOException {
        if (!"/blocks".equals(exchange.getRequestURI().toString())) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.getResponseHeaders().add("Allow", "GET");
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, 0);
        try (PrintStream out = new PrintStream(exchange.getResponseBody())) {
            out.println(blockchain.toJson());
        }
    }

    public void mine(HttpExchange exchange) throws IOException {
        if (!"/mine".equals(exchange.getRequestURI().toString())) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.getResponseHeaders().add("Allow", "GET");
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        try {
            final MiningResult result = blockchain.mine();

            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, 0);
            try (PrintStream out = new PrintStream(exchange.getResponseBody())) {
                out.println(result.toJson());
            }

        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1);
        }
    }

    public static void main(String[] args) throws IOException {
        final Miner miner = new Miner(ProofOfWork.SIX_LEADING_ZEROS);
        new BlockchainNode(miner).start(3000);
    }
}
