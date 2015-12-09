/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * carpark class
 *
 * @author Benni, René
 */
public class Parkhaus {

    private int freeParkingSlots;
    private int currentTicket;
    private int parkList;
    private int socket;

    public synchronized void startParkhausServer() throws SocketException {
        DatagramSocket aSocket = new DatagramSocket(socket);
        byte[] buffer = new byte[1000];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        String s = "";
        DatagramPacket reply = null;
        try {
            while (true) {
                aSocket.receive(request);
                byte[] data = request.getData();
                s = new String(data, 0, request.getLength());
                switch (s) {
                    case "einparken":
                        // put thread to sleep, if no free parking slots are aviable
                        if (freeParkingSlots == 0) {
                            System.out.println("no free slots");
                            s = "error";
                            reply = new DatagramPacket(s.getBytes(), s.getBytes().length, request.getAddress(), request.getPort());
                            aSocket.send(reply);
                        } else {
                            driveIn();
                            s = "success";
                            reply = new DatagramPacket(s.getBytes(), s.getBytes().length, request.getAddress(), request.getPort());
                            aSocket.send(reply);
                        }
                        break;
                    case "ausparken":
                        driveOut();
                        s = "success";
                        reply = new DatagramPacket(s.getBytes(), s.getBytes().length, request.getAddress(), request.getPort());
                        aSocket.send(reply);
                        break;
                    case "neuesTicket":
                        s = Integer.toString(parkList + 1);
                        parkList++;
                        reply = new DatagramPacket(s.getBytes(), s.getBytes().length, request.getAddress(), request.getPort());
                        aSocket.send(reply);
                    case "nextInQueue":
                        s = Integer.toString(currentTicket);
                        reply = new DatagramPacket(s.getBytes(), s.getBytes().length, request.getAddress(), request.getPort());
                        aSocket.send(reply);
                    default:
                        break;
                }
                s = "Success " + s;
                reply = new DatagramPacket(s.getBytes(), s.getBytes().length, request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    /**
     * Constructor
     *
     * @param freeSlots Number of free parking slots
     */
    public Parkhaus(int freeSlots, int serverSocket) {
        freeParkingSlots = freeSlots;
        parkList = 0;
        currentTicket = 0;
        socket = serverSocket;
        System.out.println("Parkhaus " + socket + " started");
    }

    /**
     * Car enters the carpark
     *
     * @return if parking was successful
     */
    public boolean driveIn() {

        freeParkingSlots--;
        currentTicket++;
        //notifyAll();
        return true;
    }

    /**
     * car leaves carpark make room for new cars
     */
    public void driveOut() {
        freeParkingSlots++;
        notifyAll();
    }
}
