package code.core.collections;

import java.util.ArrayList;
import java.util.List;

public class CollectionsFilter {
    private List<String> symbolAYouShallNotPass(List<String> list, FilterApply filterApply) {
        if (list == null || filterApply == null) {
            throw new IllegalArgumentException("List and FilterApply cannot be null");
        }
        List<String> result = new ArrayList<>();
        for (String word : list) {
            result.add((String) filterApply.apply(word));
        }
        return result;
    }
    public List<String> getSymbolAYouShallNotPass(List<String> list, FilterApply filterApply) {
        return symbolAYouShallNotPass(list, filterApply);
    }
}

