package com.innoq.blockchain0815.support;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public final class Stopwatch {

    private boolean running = false;
    private long start;
    private long elapsedNanos;

    private Stopwatch() {
    }

    private boolean isRunning() {
        return running;
    }

    private boolean isStopped() {
        return !isRunning();
    }

    public Stopwatch start() {
        if (isRunning()) {
            throw new IllegalStateException("Stopwatch already running");
        }
        running = true;
        start = System.nanoTime();
        return this;
    }

    public Stopwatch stop() {
        final long stop = System.nanoTime();
        if (isStopped()) {
            throw new IllegalStateException("Stopwatch already stopped");
        }
        running = false;
        final long duration = stop - start;
        elapsedNanos += duration;
        return this;
    }

    private long elapsedNanos() {
        if (isRunning()) {
            return elapsedNanos + (System.nanoTime() - start);
        } else {
            return elapsedNanos;
        }
    }

    public Duration elapsed() {
        return Duration.ofNanos(elapsedNanos());
    }

    public long elapsed(TimeUnit unit) {
        return unit.convert(elapsedNanos(), NANOSECONDS);
    }

    public static Stopwatch started() {
        return new Stopwatch().start();
    }

}
