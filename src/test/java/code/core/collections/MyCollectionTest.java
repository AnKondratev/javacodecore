package code.core.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class MyCollectionTest {

    private final MyCollection myCollection = new MyCollection();

    @Test
    public void testArrayToMapWithIntegerCollection() {
        Collection<Integer> input = Arrays.asList(1, 2, 2, 3, 3, 3);
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 1);
        expected.put(2, 2);
        expected.put(3, 3);

        HashMap<Integer, Integer> result = myCollection.getArrayToMap(input);
        assertEquals(expected, result);
    }

    @Test
    public void testArrayToMapWithStringCollection() {
        Collection<String> input = Arrays.asList("apple", "banana", "apple");
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("apple", 2);
        expected.put("banana", 1);

        HashMap<String, Integer> result = myCollection.getArrayToMap(input);
        assertEquals(expected, result);
    }

    @Test
    public void testArrayToMapWithMixedTypes() {
        Collection<Object> input = Arrays.asList("string", 1, 1.5, "string", 1);
        HashMap<Object, Integer> expected = new HashMap<>();
        expected.put("string", 2);
        expected.put(1, 2);
        expected.put(1.5, 1);

        HashMap<Object, Integer> result = myCollection.getArrayToMap(input);
        assertEquals(expected, result);
    }

    @Test
    public void testArrayToMapWithEmptyCollection() {
        Collection<Object> input = List.of();
        assertThrows(NoSuchElementException.class, ()
                -> myCollection.getArrayToMap(input));
    }
}

