package code.core.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class MyCollection {

    private <T> HashMap<T, Integer> arrayToMap(Collection<T> collection) {
        HashMap<T, Integer> result = new HashMap<>();
        if (collection.isEmpty()) {
            throw new NoSuchElementException("Collection is empty");
        }
        collection.forEach(element -> result.put(element, result.getOrDefault(element, 0) + 1));
        return result;
    }

    public <T> HashMap<T, Integer> getArrayToMap(Collection<T> collection) {
        return arrayToMap(collection);
    }
}