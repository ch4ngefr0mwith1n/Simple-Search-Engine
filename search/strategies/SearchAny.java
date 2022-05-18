package search.strategies;

import java.util.*;

public class SearchAny implements Search {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void search(List<String> lines, Map<String, Set<Integer>> invertedIndex) {

        System.out.println("Enter a name or email to search all suitable people.");
        List<String> searchTerms = List.of(scanner.nextLine().trim().split("\\s+"));

        Set<Integer> indexes = new LinkedHashSet<>();

        invertedIndex.keySet().forEach(key -> {
            if (searchTerms.contains(key)) {
                indexes.addAll(invertedIndex.get(key));
            }
        });

        if (indexes.isEmpty()) {
            System.out.println("No matching people found.\n");
            return;
        }

        System.out.printf("\n%d persons found:\n", indexes.size());
        indexes.forEach(index -> System.out.println(lines.get(index)));
        System.out.println();
    }
}
