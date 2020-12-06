package ru.itis.javalab.homework;

import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        try {
            Socket socket = new Socket(args.serverIp, args.port);
            new Client(socket);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
