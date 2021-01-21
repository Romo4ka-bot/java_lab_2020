package ru.itis.count_down_latch;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// wait, notify, synchronized
public class ThreadPool implements TaskExecutor {
    // очередь задач
    private final Deque<Runnable> tasks;

    // пул потоков
    private final PoolWorker threads[];

    private static final Lock lock = new ReentrantLock();

    // счетчик, который будет считать - сколько потоков уже готово к работе?
    private final CountDownLatch readyCountDownLatch;

    // счетчик, который будет считать - сколько задач было завершено?
    private final CountDownLatch completeCountDownLatch;

    public ThreadPool(int threadsCount, CountDownLatch completeCountDownLatch) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.threads = new PoolWorker[threadsCount];

        readyCountDownLatch = new CountDownLatch(threadsCount);
        this.completeCountDownLatch = completeCountDownLatch;
        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new PoolWorker();
            this.threads[i].start();
        }
    }

    public void submit(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            lock.lock();
            // уменьшить количество потоков, которые я ожидаю
            readyCountDownLatch.countDown(); // i--
            System.out.println(Thread.currentThread().getName() + " Готово потоков - " + (threads.length - readyCountDownLatch.getCount()));
            try {
                System.out.println(Thread.currentThread().getName() + " ушел в ожидание");
                lock.unlock();
                // мой поток теперь будет ждать, пока readyCountDownLatch не будет равен 0
                readyCountDownLatch.await();
            } catch (InterruptedException e) {
                throw new IllegalArgumentException(e);
            }
            System.out.println(Thread.currentThread().getName() + " вышел из ожидания");
            Runnable task;
            while(true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
                    task = tasks.poll();
                }
                System.out.println(Thread.currentThread().getName() + " начата загрузка файла");
                task.run();
                completeCountDownLatch.countDown();
            }
        }
    }
}
