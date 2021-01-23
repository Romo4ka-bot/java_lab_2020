package ru.itis.javalab;

import com.beust.jcommander.JCommander;

public class MainServer {
    public static void main(String[] argv) {
        ServerArgs serverArgs = new ServerArgs();

        JCommander.newBuilder()
                .addObject(serverArgs)
                .build()
                .parse(argv);

        new Server(serverArgs.port);
    }
}
