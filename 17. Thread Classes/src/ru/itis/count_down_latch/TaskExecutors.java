package ru.itis.count_down_latch;

import java.util.concurrent.CountDownLatch;

public class TaskExecutors {
    public static TaskExecutor threadPool(int threadsCount, CountDownLatch countDownLatch) {
        return new ThreadPool(threadsCount, countDownLatch);
    }
}
