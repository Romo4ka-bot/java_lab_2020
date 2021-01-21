package ru.itis.semaphore;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Semaphore;

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
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Semaphore semaphore = new Semaphore(scanner.nextInt());
        BufferedReader reader = new BufferedReader(new FileReader("links.txt"));
        String fileName = reader.readLine();

        while (fileName != null) {
            final String finalFileName = fileName;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                saveFile(finalFileName);
                System.out.println(Thread.currentThread().getName() + " скачано");
                semaphore.release();
            }).start();

            fileName = reader.readLine();
        }
    }
}
