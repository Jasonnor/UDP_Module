package UDPServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void run() {
        broadcastClient.start();
    }

}