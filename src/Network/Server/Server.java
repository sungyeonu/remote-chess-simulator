package Network.Server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A fairly generic server class that listens on port 9000; waits for clients to connect, then
 * passes the client's socket off to a ClientHandler object in its own thread
 */
public class Server {
    private static final int PORT = 9222;
    private static Thread connection = null;
    /**
     * Runs the server; establishes the server socket then indefinitely listens on it for clients
     * to connect, passing them off to handlers as it does so
     */
    public static void main(String args[]) {
        //when a client connects trying to create a new game, he is given an ID while he waits;
        //making this call will establish all of the possible IDs for waiting clients (of which
        //there are finitely many)
        ServerSocket socket;
        try {
            socket = new ServerSocket(PORT);
            System.out.println("Server Started on \"LocalHost, Port: " + PORT + "\"");
        }
        catch (IOException e) {
            System.out.println("Couldn't create socket on port " + PORT);
            return;
        }

        while (connection == null) {
            Socket clientSocket;
            try {
                clientSocket = socket.accept();
                connection = new Thread(new ClientHandler(clientSocket));
                connection.start();
                while(true){
                }
            } catch (IOException e) {
                System.out.println("Error accepting connection on socket.");
                return;
            }
        }
    }
}