package UDPClient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        updateServer.start();
    }

}