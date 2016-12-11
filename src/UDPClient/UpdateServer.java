package UDPClient;

import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UpdateServer extends Thread {

    private int port = 5566;

    public static void main(String[] args) throws Exception {
        UpdateServer updateServer = new UpdateServer();
        updateServer.start();
    }

    public void run() {
        try {
            initUPDServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUPDServer() throws Exception {
        final int SIZE = 1024;
        byte buffer[] = new byte[SIZE];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            DatagramSocket socket = new DatagramSocket(port);
            socket.receive(packet);
            String receiveMessage = new String(buffer, 0, packet.getLength());
            JSONObject message = new JSONObject(receiveMessage);
            if (message.get("Command").equals("ADD")) {
                DOM_addVirutalCharacter(message.getJSONObject("Character"));
                DOM_addItem(message.getJSONObject("Item"));
            } else if (message.get("Command").equals("UPDATE")) {
                DOM_updateVirutalCharacter(message.getJSONObject("Character"));
                DOM_updateItem(message.getJSONObject("Item"));
            }
            socket.close();
        }
    }

    // Fake methods
    private void DOM_addVirutalCharacter(JSONObject character) {
        System.out.println("Add Virutal Character " + character);
    }

    private void DOM_updateVirutalCharacter(JSONObject character) {
        System.out.println("Update Virutal Character " + character);
    }

    private void DOM_addItem(JSONObject item) {
        System.out.println("Add Item " + item);
    }

    private void DOM_updateItem(JSONObject item) {
        System.out.println("Update Item " + item);
    }
}
