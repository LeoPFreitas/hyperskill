package searchEngine;

import java.util.List;
import java.util.Map;

public interface ISearchStrategy {
    void search(Map<String, List<Integer>> invertedIndex, List<String> data);
}
