/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benni
 */
public class Parker extends Thread{
    public static final int DRIVEIN = 0; 
    public static final int DRIVEOUT = 1;
    
    private int driveDirection;
    private Parkhaus carpark;
    
    public Parker(Parkhaus p, int driveDirection) {
        this.carpark = p;
        this.driveDirection = driveDirection;
    }
    
    public void run() {
        // Car enters carpark
        if (this.driveDirection == DRIVEIN) {
                      
            this.carpark.driveIn();

            System.out.println("drivein -> free slots: " + 
                    this.carpark.getFreeSlots() +
                    " parked cars: " + this.carpark.getParkedCars());


            try {
                    sleep((int)(Math.random() * 1000));
                } catch (InterruptedException ex) {
            }
        }
        
        // car exits carpark
        if (this.driveDirection == DRIVEOUT) {
            this.carpark.driveOut();
            System.out.println("driveout -> free slots: " + 
                    this.carpark.getFreeSlots() +
                    " parked cars: " + this.carpark.getParkedCars());
            try {
                    sleep((int)(Math.random() * 1000));
                } catch (InterruptedException ex) { 
            }
        }
    }
}