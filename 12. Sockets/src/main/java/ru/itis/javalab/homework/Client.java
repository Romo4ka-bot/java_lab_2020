package ru.itis.javalab.homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    Socket client;
    DataInputStream in;
    DataOutputStream out;

    public Client(Socket client) {

        this.client = client;

        try {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        Runnable writeMsg = () -> {
            while (true) {
                String message;
                try {
                    message = in.readUTF();
                    System.out.println("from server: " + message);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
        new Thread(writeMsg).start();
    }

}
