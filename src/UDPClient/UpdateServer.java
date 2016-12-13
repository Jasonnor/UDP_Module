package UDPClient;

import DOM.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UpdateServer extends Thread {

    int port = 5566;

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
            JSONArray messages = new JSONArray(receiveMessage);
            for (int i = 0; i < messages.length(); i++) {
                JSONObject message = new JSONObject(messages.get(i).toString());
                Object command = ((JSONArray) message.get("Command")).get(0);
                if (command.equals("ADD")) {
                    DOM.addVirtualCharacter(message.get("Character"));
                    DOM.addItem(message.get("Item"));
                } else if (command.equals("UPDATE")) {
                    DOM.updateVirtualCharacter(message.get("Character"));
                    DOM.updateItem(message.get("Item"));
                }
            }
            socket.close();
        }
    }
}
