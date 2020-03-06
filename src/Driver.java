import Network.Server.Client;
import Network.Server.ClientHandler;
import UI.Board;

import java.io.IOException;

public class Driver {
    public static void main(String[] args){
//          Board boardGUI = new Board();
        try {
            Client client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
