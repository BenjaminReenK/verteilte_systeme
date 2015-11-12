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
        // TODO code application logic here
        Parkhaus carPark = new Parkhaus(2);
        
        for (int i = 0; i < 10; i++) {
            Parker carIn = new Parker(carPark, Parker.DRIVEIN);
            Parker carOut = new Parker(carPark, Parker.DRIVEOUT);
            carIn.start();
            carOut.start();
        }
    }
    
}
