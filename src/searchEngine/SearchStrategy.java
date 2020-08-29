package searchEngine;

import java.util.List;
import java.util.Map;

class SearchStrategy {
    private ISearchStrategy searchStrategy;

    public void setSearchStrategy(ISearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void search(Map<String, List<Integer>> invertedIndex, List<String> data){
        this.searchStrategy.search(invertedIndex, data);
    }
}
