package Network.Server;

import ChessGame.ColorEnum;
import UI.Board;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.Queue;


/**
 * After a client connects to the server, this class can be run in its in thread to handle all of
 * its needs
 */
public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private ColorEnum player;
    /**
     * Creates a new ClientHandler object with the given socket for the client. Throws an
     * IOException if there are problems with the clientSocket
     * @param clientSocket the Socket associated with the client being handled
     */
    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.socketOut = new ObjectOutputStream(clientSocket.getOutputStream());
        this.socketIn = new ObjectInputStream(clientSocket.getInputStream());
    }

    /**
     * The run method for the Runnable interface; handle all facets of getting the client set up
     * with his opponent for the chess game
     */

    private void startGame(){
        Board board = new Board(player, socketIn, socketOut);

    }

    @Override
    public void run() {
        System.out.println("Received a connection!");
        player = ColorEnum.BLUE;
        startGame();
//        try {
//            readWriteLoop();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        cleanExit();
//        try {
//            gameID = socketIn.readInt();
//        }
//        catch (IOException e) {
//            System.out.println("Error: couldn't read from socket.");
//            cleanExit();
//            return;
//        }
//        System.out.println("The received game ID is : " + gameID);
//
//        try {
//            if (gameID < 0) {
//                //treat the "gameID" as the time limit and try to establish the new game
//                if (!establishNewGame()) {
//                    //not possible to establish a new game, indicate that to the client and then
//                    //exit
//                    socketOut.writeInt(-1);
//                    socketOut.flush();
//                    cleanExit();
//                    System.out.println("Not enough room. ClientHandler exiting.");
//                    return;
//                }
//            }
//            else {
//                if (joinExistingGame(gameID)) {
//                    //indicate that the connection was successful to the client
//                    socketOut.flush();
//                    //allow the peer thread to continue
//                    peer.peerSemaphore.release();
//                }
//                else {
//                    System.out.println("Game doesn't exist.");
//                    socketOut.writeInt(-1);
//                    socketOut.flush();
//                    cleanExit();
//                    System.out.println("Exiting.");
//                    return;
//                }
//            }
//        }
//        catch (InterruptedException e) {
//            System.out.println("Error: thread interrupted.");
//            cleanExit();
//            return;
//        }
//        catch (IOException e) {
//            System.out.println("Error: couldn't write the gameID.");
//            cleanExit();
//            return;
//        }
//
//        try {
//            readWriteLoop();
//        }
//        catch (ClassNotFoundException e) {
//            System.out.println("Error: couldn't find class.");
//            cleanExit();
//            return;
//        }
//        catch (IOException e) {
//            System.out.println("Error: couldn't read from or write to socket.");
//            cleanExit();
//            return;
//        }
//
//
//        cleanExit();
    }

    /**
     * Tries to establish a new game for the client, returning true if it was possible, false if
     * not
     * @return true if it was possible to create a new game, false if it was not
     */
//    private boolean establishNewGame() throws IOException, InterruptedException {
//        Integer gameID = ids.poll();
//        if (gameID == null) {
//            return false;
//        }
//
//        waitingClients.put(gameID, this);
//        socketOut.writeInt(gameID);
//        socketOut.flush();
//
//        peerSemaphore.acquire();
//        socketOut.writeInt(0);
//        socketOut.flush();
//
//        return true;
//    }

    /**
     * Tries to let client join an existing game with the given ID
     * @param gameID the ID of the game which is to be joined
     * @return true if it was possible to join the game, false otherwise (i.e., no game with ID)
     */
//    private boolean joinExistingGame(int gameID) throws IOException {
//        //get the peer based on the gameID; remove the peer from the list of waiting clients and put
//        //the game id back on the available list of ids
//        peer = waitingClients.get(gameID);
//        if (peer == null) {
//            return false;
//        }
//
//        waitingClients.remove(gameID);
//        ids.add(gameID);
//
//        //set the initial time to the peer's initial time
//
//        //set the peer's output socket to...the peer's output socket
//        peerOut = peer.socketOut;
//
//        //set the peer's peer to this, and the peer's peer's output socket to the current output
//        //socket
//        peer.peer = this;
//        peer.peerOut = socketOut;
//
//        return true;
//    }

    /**
     * Continuously reads Objects from the client's input stream and then immediately writes them
     * to the corresponding opponent's output stream. Should never throw a ClassNotFoundException,
     * but might throw an IOException if an error with either socket occurs
     */
    private void readWriteLoop() throws ClassNotFoundException, IOException {
        Object obj = socketIn.readObject();
        while (obj != null) {
            System.out.println("Writing receieved object.");
            socketOut.writeObject(obj);
            socketOut.flush();
            obj = socketIn.readObject();
        }
    }

    private void cleanExit() {
        try {
            clientSocket.close();
        }
        catch (IOException e) {
            System.out.println("Error: couldn't close clientSocket.");
            return;
        }
    }
}