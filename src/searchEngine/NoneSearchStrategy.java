package searchEngine;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NoneSearchStrategy implements ISearchStrategy {
    @Override
    public void search(Map<String, List<Integer>> invertedIndex, List<String> data) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] values = sc.nextLine().toLowerCase().split(" ");

        List<Integer> list = Stream.iterate(0, n -> n + 1)
                .limit(data.size())
                .collect(Collectors.toList());

        for (String val : values) {
            if (invertedIndex.containsKey(val)) {
                list.removeAll(invertedIndex.get(val));
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
