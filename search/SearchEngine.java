package search;

import search.strategies.SearchAll;
import search.strategies.SearchAny;
import search.strategies.SearchContext;
import search.strategies.SearchNone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {

    private static Scanner scanner = new Scanner(System.in);

    private static List<String> linesList = new ArrayList<>();
    private static Map<String, Set<Integer>> invertedIndex = new LinkedHashMap<>();

    private String pathToFile;

    private static String mainMenu = "=== Menu ===\n" +
            "1. Find a person\n" +
            "2. Print all people\n" +
            "0. Exit";

    public SearchEngine(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    private void searchOptions() {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String option = scanner.nextLine().trim().toUpperCase();

        SearchContext sc = new SearchContext();

        switch (option) {
            case "ALL":
                sc.setSearchStrategy(new SearchAll());
                sc.search(linesList, invertedIndex);
                break;
            case "ANY":
                sc.setSearchStrategy(new SearchAny());
                sc.search(linesList, invertedIndex);
                break;
            case "NONE":
                sc.setSearchStrategy(new SearchNone());
                sc.search(linesList, invertedIndex);
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    private void fillInvertedIndex() {
        for (String line : linesList) {

            int lineIndex = linesList.indexOf(line);
            List<String> words = List.of(line.split("\\s+"));

            for (String word : words) {
                if (invertedIndex.containsKey(word.toLowerCase())) {
                    invertedIndex.get(word.toLowerCase()).add(lineIndex);
                } else {
                    invertedIndex.put(word.toLowerCase(), new LinkedHashSet<>(Set.of(lineIndex)));
                }
            }
        }
    }

    private void readFile() {
        File file = new File(pathToFile);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                linesList.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }
    }

    private void printAllPeople() {
        System.out.println("=== List of people ===");
        linesList.forEach(x -> System.out.println(x));
    }

    private List<String> getAllLinesThatContain(List<String> list, String word) {
        return list.stream()
                .filter(x -> x.toLowerCase().contains(word.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void start() {
        readFile();
        fillInvertedIndex();

        for (;;) {
            System.out.println(mainMenu);
            String input = scanner.nextLine().trim();

            int selectedOption = 0;
            try {
                selectedOption = Integer.parseInt(input);

                switch (selectedOption) {
                    case 1:
                        searchOptions();
                        break;
                    case 2:
                        printAllPeople();
                        break;
                    case 0:
                        System.out.println("\nBye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect option! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect option! Try again.\n");
            }
        }
    }

}
