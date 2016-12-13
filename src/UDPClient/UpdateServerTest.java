package UDPClient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateServerTest {
    private UpdateServer updateServer;

    @BeforeEach
    void setUp() {
        updateServer = new UpdateServer();
    }

    @AfterEach
    void tearDown() {
        updateServer = null;
    }

    @Test
    void main() {
        assertNotNull(updateServer);
    }

    @Test
    void run() {
        assertNotNull(updateServer);
    }

    @Test
    void initUPDServer() throws Exception {
        final int SIZE = 1024;
        byte buffer[] = new byte[SIZE];
        boolean infoCorrect = true;
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        DatagramSocket socket = new DatagramSocket(updateServer.port);
        for (int i = 0; i < 10; i++) {
            socket.receive(packet);
            String receiveMessage = new String(buffer, 0, packet.getLength());
            JSONArray messages = new JSONArray(receiveMessage);
            for (int j = 0; j < messages.length(); j++) {
                JSONObject message = new JSONObject(messages.get(j).toString());
                Object command = ((JSONArray) message.get("Command")).get(0);
                if (i == 0)
                    infoCorrect &= command.equals("ADD");
                else
                    infoCorrect &= command.equals("UPDATE");
            }
        }
        socket.close();
        assertTrue(infoCorrect);
    }

    @Test
    void initInvalidUPDServer() throws Exception {
        boolean infoCorrect = true;
        ArrayList<JSONObject> messages = new ArrayList<JSONObject>() {{
            JSONObject info = new JSONObject();
            info.append("Character", "Jason Wu");
            info.append("Item", "Bomb");
            info.append("Command", "UPDATE");
            add(info);
            add(info);
        }};
        for (int i = 0; i < 2; i++) {
            for (JSONObject message : messages) {
                Object command = ((JSONArray) message.get("Command")).get(0);
                if (i == 0)
                    infoCorrect &= command.equals("ADD");
                else
                    infoCorrect &= command.equals("UPDATE");
            }
        }
        assertTrue(!infoCorrect);
    }

}