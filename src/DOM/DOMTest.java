package DOM;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DOMTest {

    @Test
    void addVirtualCharacter() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;
        System.setOut(out);
        DOM.addVirtualCharacter("Jason");
        String output = outputStream.toString().trim();
        System.setOut(oldOutput);
        assertEquals("Add Virtual Character Jason", output);
    }

    @Test
    void updateVirtualCharacter() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;
        System.setOut(out);
        DOM.updateVirtualCharacter("Jason");
        String output = outputStream.toString().trim();
        System.setOut(oldOutput);
        assertEquals("Update Virtual Character Jason", output);
    }

    @Test
    void addItem() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;
        System.setOut(out);
        DOM.addItem("Bomb");
        String output = outputStream.toString().trim();
        System.setOut(oldOutput);
        assertEquals("Add Item Bomb", output);
    }

    @Test
    void updateItem() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;
        System.setOut(out);
        DOM.updateItem("Bomb");
        String output = outputStream.toString().trim();
        System.setOut(oldOutput);
        assertEquals("Update Item Bomb", output);
    }

}