package ru.itis.javalab;

import com.beust.jcommander.JCommander;

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
