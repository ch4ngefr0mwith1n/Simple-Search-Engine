package search.strategies;

import java.util.*;

/*
    - implementiranje "Strategy" patterna
 */
public interface Search {
    void search(List<String> lines, Map<String,Set<Integer>> invertedIndex);
}
