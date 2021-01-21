package ru.itis.cyclic_barrier;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static double saveFile(String fileName) {
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
            return response.length;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        List<Double> fileSizes = Collections.synchronizedList(new ArrayList<>());
        BufferedReader reader = new BufferedReader(new FileReader("links.txt"));
        Scanner scanner = new Scanner(System.in);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(scanner.nextInt(), () -> {
            double sum = fileSizes.stream().mapToDouble(value -> value).sum();
            System.out.println("Скачано файлов на общий размер - " + sum);
        });

        String fileName = reader.readLine();
        while (fileName != null) {
            final String finalFileName = fileName;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " начал скачивание");
                double fileSize = saveFile(finalFileName);
                fileSizes.add(fileSize);
                System.out.println(Thread.currentThread().getName() + " завершил скачивание");
                // уходим в ожидание, пока не наберется нужное количество завершенных потоков
                try {
                    System.out.println(Thread.currentThread().getName() + " ушел в ожидание");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " вышел из ожидания");
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }).start();

            fileName = reader.readLine();
        }
    }
}
