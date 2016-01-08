/**
 *
 * @author Benni
 */
public interface ChatClient extends java.rmi.Remote{
    
    /**
     * Get the Client Name
     * @return Client Name
     * @throws java.rmi.RemoteException 
     */
    public String getName() throws java.rmi.RemoteException;
    
    /**
     * Print the incoming message on screen
     * @param msg message content
     * @throws java.rmi.RemoteException 
     */
    public void print(String msg) throws java.rmi.RemoteException;
}
