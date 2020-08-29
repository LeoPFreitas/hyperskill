package searchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class PeopleSearchEngine {
    private List<String> data;
    private Scanner sc;
    private Map<String, List<Integer>> invertedIndex;

   private SearchStrategy searchStrategy = new SearchStrategy();

    public PeopleSearchEngine() {
        this.data = new ArrayList<>();
        this.sc = new Scanner(System.in);
        this.invertedIndex = new HashMap<>();

    }

    public void start() {
        String menu =
                "=== Menu ===\n" +
                        "1. Find a person\n" +
                        "2. Print all people\n" +
                        "0. Exit";

        boolean exit = false;
        while (!exit) {
            System.out.println(menu);
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = sc.nextLine();

                    // setStrategy
                    if ("NONE".equals(strategy.toUpperCase())) {
                        searchStrategy.setSearchStrategy(new NoneSearchStrategy());
                    } else if ("ANY".equals(strategy.toUpperCase())) {
                        searchStrategy.setSearchStrategy(new AnySearchStrategy());
                    } else if ("ALL".equals(strategy.toUpperCase())) {
                        searchStrategy.setSearchStrategy(new AllSearchStrategy());
                    }

                    searchStrategy.search(invertedIndex, data);
                    break;
                case "2":
                    printData();
                    break;
                case "0":
                    exit = true;
                    System.out.println("\nBye!");
                    break;
                default:
                    System.out.println("\nIncorrect option! Try again.");
                    break;
            }
        }
    }

    public void printData() {
        System.out.println("\n=== List of people ===");
        for (String result : data)
            System.out.println(result);
    }

    public void loadData(String[] src) {
        if (!dataSourceValid(src))
            return;

        String filePath = src[1];
        try (Scanner reader = new Scanner(new File(filePath))) {
            int lineNumber = 0;
            while (reader.hasNextLine()) {
                String record = reader.nextLine();
                data.add(record);
                String[] recordWords = record.toLowerCase().split("\\s+");
                for (String word : recordWords) {
                    invertedIndex.computeIfAbsent(word, k -> new ArrayList<>());
                    invertedIndex.get(word).add(lineNumber);

                }
                lineNumber++;
            }
            System.out.println(lineNumber);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file at " + filePath + " not found");
        }

    }

    private boolean dataSourceValid(String[] src) {
        if (src != null && src.length == 2 && src[0].equals("--data"))
            return true;
        System.out.println("No external data source");
        return false;
    }
}