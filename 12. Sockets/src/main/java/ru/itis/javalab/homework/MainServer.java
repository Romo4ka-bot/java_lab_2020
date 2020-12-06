package ru.itis.javalab.homework;

import com.beust.jcommander.JCommander;

public class MainServer {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        new Server(args.port);
    }
}
