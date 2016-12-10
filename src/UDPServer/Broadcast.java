package UDPServer;

import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Broadcast extends Thread {
    private ArrayList<InetAddress> clientAddresses;
    private ArrayList<JSONObject> encodeInfo;
    private int port = 5566;

    Broadcast(ArrayList<InetAddress> clientAddresses, ArrayList<JSONObject> encodeInfo) {
        this.clientAddresses = clientAddresses;
        this.encodeInfo = encodeInfo;
    }

    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket();
            String message = encodeInfo.toString();
            byte buffer[] = message.getBytes();
            for (InetAddress clientAddress : clientAddresses) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, port);
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}