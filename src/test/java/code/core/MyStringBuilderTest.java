package code.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStringBuilderTest {
    private MyStringBuilder usb;

    @BeforeEach
    public void setUp() {
        usb = new MyStringBuilder();
    }

    @Test
    public void testAppend() {
        usb.append("Hello");
        usb.append(", World!");
        assertEquals("Hello, World!", usb.toString());
    }

    @Test
    public void testDelete() {
        usb.append("Hello, World!");
        usb.delete(5, 7); // удаляем ", World"
        assertEquals("Hello!", usb.toString());
    }

    @Test
    public void testUndoAppend() {
        usb.append("Hello");
        usb.undo(); // отменяем добавление "Hello"
        assertEquals("", usb.toString());
    }

    @Test
    public void testUndoDelete() {
        usb.append("Hello, World!");
        usb.delete(5, 7); // удаляем ", World"
        usb.undo(); // откат к "Hello, World!"
        assertEquals("Hello, World!", usb.toString());
    }

    @Test
    public void testMultipleUndo() {
        usb.append("Testing");
        usb.append(" the undo");
        usb.delete(12, 4);
        assertEquals("Testing the ", usb.toString());
        usb.undo();
        assertEquals("Testing the undo", usb.toString());
    }

    @Test
    public void testUndoNoAction() {
        usb.undo(); // ничего не должно произойти
        assertEquals("", usb.toString());
    }

    @Test
    public void testDeleteOutOfBounds() {
        usb.append("Hello");
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            usb.delete(0, 10); // удаляем больше, чем строка содержит
        });
    }

    @Test
    public void testUndoAfterDeleteOutOfBounds() {
        MyStringBuilder usb = new MyStringBuilder();
        usb.append("Hello");
        assertThrows(StringIndexOutOfBoundsException.class, ()
                -> usb.delete(0, 10));
        assertEquals("Hello", usb.toString());
        usb.undo();
        assertEquals("Hello", usb.toString());
    }
}

