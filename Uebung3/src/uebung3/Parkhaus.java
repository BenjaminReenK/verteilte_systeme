/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung3;

import java.util.ArrayList;

/**
 * carpark class
 * @author Benni
 */
public class Parkhaus {
    private int freeSlots;
    private int currentTicket;
    private ArrayList<Parker> parkList;
   
    /**
     * Constructor
     * @param freeSlots Number of free parking slots
     */
    public Parkhaus(int freeSlots) {
        this.freeSlots = freeSlots;
        this.parkList = new ArrayList<>();
        this.currentTicket = 0;
    }
    
    /**
     * Method to get a Parking ticket 
     * @param p car, carnumber gets stored in queue for fair parking
     */
    public synchronized void getParkTicket(Parker p) {
        this.parkList.add(p);
    }
    
    /**
     * Car enters the carpark
     * @return if parking was successful
     */
    public synchronized boolean driveIn() {
        // put thread to sleep, if no free parking slots are aviable
        while (this.freeSlots == 0) {
            try {
                System.out.println("no free slots");
                wait();
                return false;
            } catch (InterruptedException ex) {         
            }
        }
                
        this.freeSlots--;
        this.currentTicket++;
        notifyAll();
        return true;
    }
    
    /**
     * car leaves carpark
     * make room for new cars
     */
    public synchronized void driveOut() {
        this.freeSlots++;
        notifyAll();
    }
    
    /**
     * For fair parking...
     * @return carnumber of the next car in queue
     */
    public synchronized int getNextParker() {
        int result = this.parkList.get(this.currentTicket).getNumber();
        
        return result;
    }
    
}
