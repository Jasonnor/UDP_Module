package UDPClient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        updateServer.start();
        assertNotNull(updateServer);
    }

}