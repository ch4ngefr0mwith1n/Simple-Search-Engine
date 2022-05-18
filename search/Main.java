package search;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> argsList = List.of(args);
        String fileName = argsList.get(argsList.indexOf("--data") + 1);

        SearchEngine se = new SearchEngine(fileName);
        se.start();
    }
}