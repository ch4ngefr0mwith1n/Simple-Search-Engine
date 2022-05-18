package search.strategies;

import com.google.common.collect.Sets;

import java.util.*;

public class SearchNone implements Search{

    Scanner scanner = new Scanner(System.in);

    @Override
    public void search(List<String> lines, Map<String, Set<Integer>> invertedIndex) {
        System.out.println("Enter a name or email to search all suitable people.");
        List<String> searchTerms = List.of(scanner.nextLine().trim().split("\\s+"));

        Set<Integer> indexesForRemoving = new LinkedHashSet<>();
        searchTerms.forEach(term -> {
            if (invertedIndex.containsKey(term)) {
                indexesForRemoving.addAll(invertedIndex.get(term));
            }
        });

        if (indexesForRemoving.isEmpty()) {
            System.out.println("No matching people found.\n");
            return;
        }

        Set<Integer> indexesToPrint = new LinkedHashSet<>();
        invertedIndex.keySet().forEach(key -> {
            if (Sets.intersection(invertedIndex.get(key), indexesForRemoving).isEmpty()) {
                indexesToPrint.addAll(invertedIndex.get(key));
            }
        });

        System.out.printf("\n%d persons found:\n", indexesToPrint.size());
        indexesToPrint.forEach(index -> System.out.println(lines.get(index)));
        System.out.println();
    }
}
