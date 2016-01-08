import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benni
 */
public class Server {
    
    public static void main(String[] args) {
        
        try {
            ChatServerImpl chatServer = new ChatServerImpl();
            // using standard rmi port
            LocateRegistry.createRegistry(1090);
            // bind server to localhost on standard port
            Naming.rebind("//localhost:1090/ChatServer", chatServer);
            System.out.println("Server started on " + "localhost:1090/ChatServer");
        } catch (RemoteException | MalformedURLException ex) {
            System.out.println("Binding Error");
        }
    }
}
