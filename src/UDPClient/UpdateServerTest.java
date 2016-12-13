package UDPClient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
        assertNotNull(updateServer);
    }

    @Test
    void initUPDServer() throws Exception {
        final int SIZE = 1024;
        byte buffer[] = new byte[SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        DatagramSocket socket = new DatagramSocket(updateServer.port);
        socket.receive(packet);
        String receiveMessage = new String(buffer, 0, packet.getLength());
        JSONArray messages = new JSONArray(receiveMessage);
        boolean infoCorrect = true;
        for (int i = 0; i < messages.length(); i++) {
            JSONObject message = new JSONObject(messages.get(i).toString());
            Object command = ((JSONArray) message.get("Command")).get(0);
            infoCorrect &= command.equals("ADD") || command.equals("UPDATE");
        }
        socket.close();
        assertTrue(infoCorrect);
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