package searchEngine;

public class Main {
    public static void main(String[] args) {
        PeopleSearchEngine peopleSearchEngine = new PeopleSearchEngine();
        peopleSearchEngine.loadData(args);
        peopleSearchEngine.start();
    }
}