package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private static final Logger Logger = LogManager.getLogger(Server.class);

    public Server() {

        Logger.info("test");
        System.out.println("Test");
        try {
            serverSocket = new ServerSocket(8888);
            Logger.info("Server Socket configured");
        } catch (IOException e) {
            Logger.error("Unable to configure server socket");
            Logger.error(e);
            throw new RuntimeException(e);

        }

        Logger.info("The server has started.");
        while (true) {

            try {
                connectionSocket = serverSocket.accept();
                Logger.info("New client connected to the server.");
            } catch (IOException e) {
                Logger.error("There was an error when the client tried to connect to the server");
                Logger.error(e);
            }

            Logger.info("New client thread started.");

            ClientHandler clientHandler = new ClientHandler(connectionSocket);

            Thread clientThread = new Thread(clientHandler);
            clientThread.start();

        }
    }
}
