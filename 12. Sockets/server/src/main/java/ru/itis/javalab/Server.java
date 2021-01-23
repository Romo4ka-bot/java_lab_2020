package ru.itis.javalab;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<ClientHandler> clients;

    Server(int port) {
        clients = new ArrayList<>();
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            while (true) {
                Socket client = server.accept();
                System.out.println("Accept new client");
                ClientHandler clientHandler = new ClientHandler(client, this);
                clientHandler.start();
                clients.add(clientHandler);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public void broadcast(String message) {
        clients.forEach(client -> client.sendMessage(message));
    }
}
