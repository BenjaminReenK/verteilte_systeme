/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

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
