package UDPClient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    @Test
    void DOM_addVirtualCharacter() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;
        System.setOut(out);
        updateServer.DOM_addVirtualCharacter("Jason");
        String output = outputStream.toString().trim();
        System.setOut(oldOutput);
        assertEquals("Add Virtual Character Jason", output);
    }

    @Test
    void DOM_updateVirtualCharacter() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;
        System.setOut(out);
        updateServer.DOM_updateVirtualCharacter("Jason");
        String output = outputStream.toString().trim();
        System.setOut(oldOutput);
        assertEquals("Update Virtual Character Jason", output);
    }

    @Test
    void DOM_addItem() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;
        System.setOut(out);
        updateServer.DOM_addItem("Bomb");
        String output = outputStream.toString().trim();
        System.setOut(oldOutput);
        assertEquals("Add Item Bomb", output);
    }

    @Test
    void DOM_updateItem() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;
        System.setOut(out);
        updateServer.DOM_updateItem("Bomb");
        String output = outputStream.toString().trim();
        System.setOut(oldOutput);
        assertEquals("Update Item Bomb", output);
    }

}