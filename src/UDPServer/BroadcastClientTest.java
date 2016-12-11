package UDPServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}