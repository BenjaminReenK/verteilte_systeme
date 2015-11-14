/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung3;



/**
 *
 * @author Benni
 */
public class ParkhausVerwaltung {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Parkhaus carPark = new Parkhaus(2);
        // create cars
        for (int i = 0; i < 25; i++) {
            Parker car = new Parker(carPark, i);
            car.start();
        }
    }
    
}
