package ru.itis.count_down_latch;

public interface TaskExecutor {
    void submit(Runnable task);
}
