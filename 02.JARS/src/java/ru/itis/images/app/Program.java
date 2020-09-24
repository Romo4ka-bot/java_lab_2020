package ru.itis.images.app;

import com.beust.jcommander.JCommander;
import ru.itis.images.utils.Downloader;

public class Program {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Downloader downloader = new Downloader();
        downloader.downloadImg(args.mode, args.count, args.files, args.folder);
    }
}
