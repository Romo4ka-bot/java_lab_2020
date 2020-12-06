package ru.itis.javalab.homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Server server;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Socket client, Server server) {
        this.server = server;
        try {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = in.readUTF();
                System.out.println("from client: " + msg);
                server.broadcast(msg);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
