package ru.itis.images.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader {

    public void downloadImg(String mode, int count, List<String> files, String folder) {

        if (mode.equals("one-thread")) {
            count = 1;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(count);

        int i = 1;

        for (String file : files) {

            int finalI = i;

            Runnable task = () -> {

                try (InputStream inputStream = new URL(file).openStream()) {
                    Files.copy(inputStream, Paths.get(folder + "/" + finalI + ".jpg"));

                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            };

            executorService.submit(task);
            i++;
        }
    }
}