package code.core.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FilterApplyTest {

    private final Filter filter = new FilterApply();

    @Test
    public void testApply_NullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> filter.apply(null));

        assertEquals("Input cannot be null", exception.getMessage());
    }

    @Test
    public void testApply_NonStringInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> filter.apply(123));

        assertEquals("Input must be a string", exception.getMessage());
    }

    @Test
    public void testApply_EmptyString() {
        Object result = filter.apply("");
        assertEquals("", result);
    }

    @Test
    public void testApply_NoTargetLetters() {
        Object result = filter.apply("программирование");
        assertEquals("прогрммировние", result);
    }

    @Test
    public void testApply_OnlyTargetLetters() {
        Object result = filter.apply("аааа");
        assertEquals("", result);
    }

    @Test
    public void testApply_MixedStringWithLowercaseA() {
        Object result = filter.apply("какао");
        assertEquals("кко", result);
    }

    @Test
    public void testApply_MixedStringWithUppercaseA() {
        Object result = filter.apply("Африка");
        assertEquals("фрик", result);
    }

    @Test
    public void testApply_MixedStringWithBothCases() {
        Object result = filter.apply("Карта аргентов");
        assertEquals("Крт ргентов", result);
    }

    @Test
    public void testApply_StringWithSpacesAndSpecialCharacters() {
        Object result = filter.apply("Привет, как дела? А я живу тут.");
        assertEquals("Привет, кк дел?  я живу тут.", result);
    }
}

