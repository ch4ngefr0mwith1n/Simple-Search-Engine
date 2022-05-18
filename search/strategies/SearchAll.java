package search.strategies;

import com.google.common.collect.Sets;

import java.util.*;

public class SearchAll implements Search {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void search(List<String> lines, Map<String,Set<Integer>> invertedIndex) {

        System.out.println("Enter a name or email to search all suitable people.");
        List<String> searchTerms = List.of(scanner.nextLine().trim().split("\\s+"));

        List<Set<Integer>> linesWithTerms = new ArrayList<>();

        searchTerms.forEach(term -> {
            if (invertedIndex.containsKey(term)) {
                linesWithTerms.add(invertedIndex.get(term));
            }
        });

        if (linesWithTerms.isEmpty()) {
            System.out.println("No matching people found.\n");
            return;
        }

        Set<Integer> indexesIntersection = getIntersection(linesWithTerms);

        System.out.printf("\n%d persons found:\n", indexesIntersection.size());
        indexesIntersection.forEach(index -> System.out.println(lines.get(index)));
        System.out.println();

    }

    // metoda za pronalazenje presjeka setova (koristi se "Sets.intersection()" iz Guave):
    public Set<Integer> getIntersection(List<Set<Integer>> list) {

        Set<Integer> result = new LinkedHashSet<>(list.get(0));

        for (Set<Integer> indexes : list) {
            result = Sets.intersection(result, Sets.newHashSet(indexes));
        }

        return result;
    }

}
