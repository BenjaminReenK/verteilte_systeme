/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung4;

import java.net.SocketException;
import java.net.UnknownHostException;



/**
 *
 * @author Benni
 */
public class ParkhausVerwaltung {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException {
        
        Parkhaus carPark = new Parkhaus(5, 8000);
        carPark.startParkhausServer();
    }
    
}