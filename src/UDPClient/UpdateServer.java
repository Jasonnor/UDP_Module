package UDPClient;

import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UpdateServer extends Thread {

    private int port = 5566;

    public static void main(String[] args) throws Exception {
        UpdateServer updateServer = new UpdateServer();
        updateServer.run();
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
            String msg = new String(buffer, 0, packet.getLength());
            JSONObject newInfo = new JSONObject();
            System.out.println(msg);
            socket.close();
        }
    }
}
