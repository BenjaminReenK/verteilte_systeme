/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung3;


/**
 * Parker Class
 * Represents a parking car in a carpark
 * @author Benni
 */

public class Parker extends Thread{
        
    private int number;
    // random travel time to carpark
    private int travelTime;
    // random idle stayting time
    private int idleTime;
    private Parkhaus carpark;
    
    /**
     * Constructor
     * @param p carpark
     * @param number carnumber
     */
    public Parker(Parkhaus p, int number) {
        this.carpark = p;
        this.number = number;
        //this.travelTime = (int) (Math.random() * 1000);
        this.travelTime = 1000;
        this.idleTime = (int) (Math.random() * 3000);
    }
    
    /**
     * Thread.Run Function. 
     * Drive to carpark, get parkticket, park, idle some time and leave
     */
    public void run() {
        
        // drive to carpark, this takes some time...
        try {
                sleep(this.travelTime);
            } catch (InterruptedException ex) {
        }
        // get a parking ticket
        this.carpark.getParkTicket(this);
            
        // check for position in parking queue, 
        // wait if car isn't the next one in queue
        while (this.number != this.carpark.getNextParker()) {
            try {
                sleep((int)(Math.random() * 1000));
            } catch (InterruptedException ex) {
            }
        }

        // try to park
        while(!this.carpark.driveIn()) {
            try {
                sleep((int)(Math.random() * 1000));
            } catch (InterruptedException ex) {
            }
        }
        // parked    
        System.out.println("drivein (" + this.number + ")");
        // idle in carpark
        try {
                sleep(this.idleTime);
            } catch (InterruptedException ex) {
        }
        // leave carpark
        this.carpark.driveOut();
        System.out.println("driveout (" + this.number + ")");
        
    }
    /**
     * This function returns the id number of the car
     * @return 
     */
    public int getNumber() {
        return this.number;
    }

}