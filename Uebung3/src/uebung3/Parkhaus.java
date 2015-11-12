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
public class Parkhaus {
    private int freeSlots;
    private int parkedCars;
    
    public Parkhaus(int freeSlots) {
        this.freeSlots = freeSlots;
        this.parkedCars = 0;
    }
    
    public synchronized void driveIn() {
        while (this.freeSlots == 0) {
            try {
                System.out.println("no free slots");
                wait();
            } catch (InterruptedException ex) {         
            }
        }
       
        this.freeSlots--;
        this.parkedCars++;
        notifyAll();
    }
    
    public synchronized void driveOut() {
        while (this.parkedCars == 0) {
            try {
                System.out.println("no parked cars");
                wait();
            } catch (InterruptedException ex) {         
            }
        }
        
        this.freeSlots++;
        this.parkedCars--;
        notifyAll();
    }
    
    public int getFreeSlots() {
        return this.freeSlots;
    }
    
    public int getParkedCars() {
        return this.parkedCars;
    }
}
