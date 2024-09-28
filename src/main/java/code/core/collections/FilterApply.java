package code.core.collections;

public class FilterApply implements Filter {
    @Override
    public Object apply(Object word) {
        if (word == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (!(word instanceof String str)) {
            throw new IllegalArgumentException("Input must be a string");
        }
        return str.replaceAll("а", "").replaceAll("А", "");
    }
}
