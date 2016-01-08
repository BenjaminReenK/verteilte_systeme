/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Benni
 */
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer{
    // client list with reference to all clients
    private ArrayList<ChatClient> clientList;
    
    public ChatServerImpl() throws RemoteException {
        clientList = new ArrayList<>();
    }
    
    // add client to clientlist
    @Override
    public synchronized boolean addClient(ChatClient objRef) throws RemoteException {
        String clientName = objRef.getName();
        // loop through clientlist
        for (Iterator<ChatClient> iterator = clientList.iterator(); iterator.hasNext();) {
            ChatClient tempClient = iterator.next();
            try {
                // check if we already have this client in our list by checking the name
                if (tempClient.getName().equals(clientName)) {
                    System.out.println("Client already in list: " + objRef.getName());
                    return false;
                }
            }
            // remove client from list if there is an exception
            catch (RemoteException e) {
                iterator.remove();
                System.out.println("There was a problem with the client while adding, removing it");
            }
        }
        // if we didn't find the client in our list, add it
        clientList.add(objRef);
        System.out.println("Client added: " + objRef.getName());
        return true;
    }
    
    // remove client from clientlist
    @Override
    public synchronized void removeClient(ChatClient objRef) throws RemoteException {
        clientList.remove(objRef);
        System.out.println("Client removed: " + objRef.getName());
    }
    
    // spread message to all clients
    @Override
    public synchronized void sendMessage(String name, String msg) throws RemoteException {
        System.out.println("Incoming message from " + name + ": " + msg);
        // loop through clientlist
        for (Iterator<ChatClient> iterator = clientList.iterator(); iterator.hasNext();) {
           ChatClient tempClient = iterator.next();
            try {
                // get current time
                long now = System.currentTimeMillis();
                Date d = new Date(now);
                // send our msg to the client
                tempClient.print("[" + d.getHours() + ":" + d.getMinutes() 
                        + ":" + d.getSeconds() + "] " + name + ": " + msg);
            }
            // remove client if there is an exception
            catch  (RemoteException e) {
                iterator.remove();
                System.out.println("There was a problem with the client while message sending, removing it");
            }
        } 
    }
    
}
