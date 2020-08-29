package searchEngine;

import java.util.*;

public class AnySearchStrategy implements ISearchStrategy {
    @Override
    public void search(Map<String, List<Integer>> invertedIndex, List<String> data) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] values = sc.nextLine().toLowerCase().split(" ");

        Set<Integer> set = new HashSet<>();
        for (String val : values) {
            if (invertedIndex.containsKey(val)) {
                set.addAll(invertedIndex.get(val));
            }
        }

        if (set.size() == 0) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(set.size() + " persons found:");
            set.forEach(i -> System.out.println(data.get(i)));
        }
    }
}
