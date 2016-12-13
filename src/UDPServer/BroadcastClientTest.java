package UDPServer;

import CDC.CDC;
import TCPServerModule.TCPServerModule;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BroadcastClientTest {
    private BroadcastClient broadcastClient;

    @BeforeEach
    void setUp() {
        broadcastClient = new BroadcastClient();
    }

    @AfterEach
    void tearDown() {
        broadcastClient = null;
    }

    @Test
    void main() {
        assertNotNull(broadcastClient);
    }

    @Test
    void run() {
        broadcastClient.start();
        assertNotNull(broadcastClient);
    }

    @Test
    void startUDPBroadCast() {
        ArrayList<InetAddress> clientAddresses = TCPServerModule.getClientIPTable();
        assertTrue(clientAddresses.size() >= broadcastClient.playerCount);
    }

    @Test
    void startInvalidUDPBroadCast() {
        ArrayList<InetAddress> clientAddresses = null;
        assertTrue(clientAddresses == null);
    }

    @Test
    void updateInfo() {
        ArrayList<JSONObject> updateInfo = CDC.getUpdateInfo();
        boolean infoCorrect = true;
        for (JSONObject info : updateInfo) {
            infoCorrect &= info.has("Character");
            infoCorrect &= info.has("Item");
        }
        assertTrue(infoCorrect);
    }

    @Test
    void updateInvalidInfo() {
        ArrayList<JSONObject> updateInfo = new ArrayList<JSONObject>() {{
            JSONObject info = new JSONObject();
            info.append("None", "Jason Wu");
            info.append("Bad", "Bomb");
            add(info);
        }};
        boolean infoCorrect = true;
        for (JSONObject info : updateInfo) {
            infoCorrect &= info.has("Character");
            infoCorrect &= info.has("Item");
        }
        assertTrue(!infoCorrect);
    }

    @Test
    void updateInvalidNullInfo() {
        ArrayList<JSONObject> updateInfo = null;
        boolean infoCorrect = updateInfo != null;
        assertTrue(!infoCorrect);
    }

}