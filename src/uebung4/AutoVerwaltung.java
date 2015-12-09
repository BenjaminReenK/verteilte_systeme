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
 * @author René
 */
public class AutoVerwaltung {
     public static void main(String[] args) throws SocketException, UnknownHostException {
        // create cars
        for (int i = 0; i < 10; i++) {
            Parker car = new Parker(8000, i+1);
            car.start();
        }
    }
}
