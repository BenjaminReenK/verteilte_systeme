import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Benni
 */
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient{
    
    // client name
    private String name;
    
    public ChatClientImpl(String name) throws RemoteException {
        this.name = name;
    }
    
    // get client name
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    // print the incoming message
    @Override
    public void print(String msg) throws RemoteException {
        System.out.println(msg);
    }
    
}
