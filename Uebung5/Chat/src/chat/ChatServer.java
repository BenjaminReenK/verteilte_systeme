/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.*;


/**
 *
 * @author Benni
 */
public interface ChatServer extends Remote{
    
    /**
     * Add a client to client list
     * @param objRef Client Reference
     * @return successful y / n
     * @throws RemoteException 
     */
    public boolean addClient(ChatClient objRef) throws RemoteException;
    
    /**
     * Removes a client from the client list
     * @param objRef Client Reference
     * @throws RemoteException 
     */
    public void removeClient(ChatClient objRef) throws RemoteException;
    
    /**
     * Send incoming message to all known clients
     * @param name name of the message sender
     * @param msg message content
     * @throws RemoteException 
     */
    public void sendMessage(String name, String msg) throws RemoteException;
}
