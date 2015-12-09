/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parker Class Represents a parking car in a carpark
 *
 * @author Benni
 */
public class Parker extends Thread {

    private int number;
    // random travel time to carpark
    private int travelTime;
    // random idle stayting time
    private int idleTime;
    private DatagramSocket aSocket;
    private byte[] commandBytes;
    private InetAddress aHost;
    private int serverPort;
    private boolean parkStatus;
    private boolean waitingStatus;
    private String parkticket;

    /**
     * Constructor
     *
     * @param p carpark
     * @param number carnumber
     */
    public Parker(int parkhausServerPort, int aNumber) throws SocketException, UnknownHostException {
        serverPort = parkhausServerPort;
        aSocket = new DatagramSocket();
        commandBytes = "".getBytes();
        aHost = InetAddress.getByName("localhost");
        number = aNumber;
        travelTime = (int) (Math.random() * 1000);
        idleTime = (int) (Math.random() * 3000);
        parkStatus = false;
        waitingStatus = true;
        parkticket = "0";
    }

    /**
     * Thread.Run Function. Drive to carpark, get parkticket, park, idle some
     * time and leave
     */
    public void run() {

        // drive to carpark, this takes some time...
        try {
            sleep(this.travelTime);
        } catch (InterruptedException ex) {
        }
        // get a parking ticket
        try {
            parkticket = sendRequest("neuesTicket");
                        System.out.println(parkticket);
            System.out.println("Car " + number + " got ticket " + parkticket);
        } catch (InterruptedException e) {
            System.out.println("IE: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOE: " + e.getMessage());
        }
        // check for position in parking queue, 
        // wait if car isn't the next one in queue
        while (waitingStatus) {
            try {
                String currentTicket = sendRequest("nextInQueue");
                if (currentTicket != parkticket) {
                    sleep((int) (Math.random() * 1000));
                } else {
                    waitingStatus = false;
                }
            } catch (InterruptedException e) {
                System.out.println("IE: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IOE: " + e.getMessage());
            }
        }

        // try to park
        while (!parkStatus) {
            try {
                String replyString = sendRequest("einparken");
                if (replyString == "success") {
                    System.out.println("Car " + number + " now parking");
                    parkStatus = true;
                } // parked
                else {
                    System.out.println("No Free Slot for Car " + number + " waiting...");
                    sleep((int) (Math.random() * 1000));
                }
            } catch (InterruptedException e) {
                System.out.println("IE: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IOE: " + e.getMessage());
            }
        }
        // idle in carpark
        try {
            sleep(idleTime);
        } catch (InterruptedException ex) {
        }
        // leave carpark
        try {
            String replyString = sendRequest("ausparken");
            if (replyString == "success") {
                System.out.println("Car " + number + " driven out");
            } else {
                System.out.println("Something went wrong by driving out");
            }
        } catch (InterruptedException e) {
            System.out.println("IE: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOE: " + e.getMessage());
        }
        System.out.println("driveout (" + number + ")");

    }

    /**
     * This function returns the id number of the car
     *
     * @return
     */
    public int getNumber() {
        return number;
    }

    private String sendRequest(String aRequest) throws IOException, InterruptedException {
        commandBytes = aRequest.getBytes();
        DatagramPacket request = new DatagramPacket(commandBytes, commandBytes.length, aHost, serverPort);
        aSocket.send(request);
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(reply);
        String replyString = new String(reply.getData());
       // aSocket.close();
        return replyString;
    }
}
