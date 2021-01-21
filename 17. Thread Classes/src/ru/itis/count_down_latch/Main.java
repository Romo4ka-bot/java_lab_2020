package ru.itis.count_down_latch;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void saveFile(String fileName) {
        try {
            URL url = new URL(fileName);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            String newFileName = Thread.currentThread().getName() + "_" + UUID.randomUUID().toString() + ".png";
            FileOutputStream fos = new FileOutputStream("images\\" + newFileName);
            fos.write(response);
            fos.close();
            System.out.println(Thread.currentThread().getName() + " завершил скачивание");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        CountDownLatch tasksCount = new CountDownLatch(30);
        TaskExecutor executor = TaskExecutors.threadPool(20, tasksCount);
        BufferedReader reader = new BufferedReader(new FileReader("links.txt"));
        for (int i = 0; i < 100; i++) {
            final String finalFileName = reader.readLine();
            executor.submit(() -> saveFile(finalFileName));
            System.out.println("Из main была направлена задача №" + i);
        }
        tasksCount.await(); // main будет ждать пока tasksCount не станет равен 0
        System.out.println("Скачано 30 файлов");
        System.exit(0);
    }
}
