import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;

/**
 *
 * @author Benni
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String text;
        
        if (args.length != 2) {
            System.out.println("Incorrect Parameters. Please use <Hostname> <ClientChatName>");
            return;
        }
        // extract param infos
        String serverName = args[0];
        String clientName = args[1];
                
        try {
            // get our chatserver from rmi
            ChatServer server = (ChatServer) Naming.lookup("rmi://"+ serverName +":1090/ChatServer");
            // create our chatclient
            ChatClient client = new ChatClientImpl(clientName);
            // publish our client to the server
            if (server.addClient(client)) {
                System.out.println("Client signed in to Server");
            }
            else {
                System.out.println("Nickname already in use");
                return;
            }
            // create buffered reader for chat input
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            // get chat input
            while ((text = consoleInput.readLine()) != null) {
                server.sendMessage(client.getName(), text);
            }
        }
        catch (Exception e) {
            System.out.println("Can't conntect to Chatserver");
        }
    }
    
}
