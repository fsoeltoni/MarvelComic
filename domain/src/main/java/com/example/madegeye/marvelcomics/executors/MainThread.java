package com.example.madegeye.marvelcomics.executors;

import io.reactivex.Scheduler;

/**
 * Executor that runs on the main thread
 */

public interface MainThread {
    Scheduler getScheduler();
}
