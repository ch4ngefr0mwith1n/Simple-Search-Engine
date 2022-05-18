package search.strategies;

import java.util.*;

public class SearchContext {

    private Search searchStrategy;

    public void setSearchStrategy(Search searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void search(List<String> lines, Map<String, Set<Integer>> invertedIndex) {
        this.searchStrategy.search(lines, invertedIndex);
    }
}
