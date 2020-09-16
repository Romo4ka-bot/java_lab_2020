package ru.itis.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
        ThreadPool threadPool = ThreadPool.newPool(1);
        Runnable task1 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " A");
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " B");
            }
        };

        Runnable task3 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " C");
            }
        };

//        executorService.submit(task1);
//        executorService.submit(task2);
//        executorService.submit(task3);
//    }


        threadPool.submit(task1);
        threadPool.submit(task2);
        threadPool.submit(task3);

    }
}

