package searchEngine;

import java.util.*;
import java.util.stream.Collectors;

public class AllSearchStrategy implements ISearchStrategy {
    @Override
    public void search(Map<String, List<Integer>> invertedIndex, List<String> data) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] values = sc.nextLine().toLowerCase().split(" ");

        List<Integer> list = new ArrayList<>();

        for (String val : values) {
            if (invertedIndex.containsKey(val)) {
                // void list
                if (list.size() == 0) {
                    list.addAll(invertedIndex.get(val));
                }

                // checking intersection
                list = list.stream()
                        .distinct()
                        .filter(invertedIndex.get(val)::contains)
                        .collect(Collectors.toList());

            }
        }

        if (list.size() == 0) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(list.size() + " persons found:");
            list.forEach(i -> System.out.println(data.get(i)));
        }
    }
}
