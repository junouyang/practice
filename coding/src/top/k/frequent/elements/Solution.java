package top.k.frequent.elements;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by jun.ouyang on 1/29/17.
 */
public class Solution {

    public static void main(String[] args) {
        int[] array = {1, 1, 2, 2, 2, 3};
        Arrays.stream(array).boxed().collect(groupingBy(identity(), counting())).entrySet().stream().collect(Collectors.toMap(entry -> entry.getValue(), ));
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        TreeMap<Integer, List<Integer>> c2n = new TreeMap<>(Comparator.reverseOrder());
        for(int n : nums) {
            counts.compute(n, (key, v) -> v == null ? 1 : v + 1 );
        }
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            c2n.compute(entry.getValue(), (count, numbers) -> {
                if( numbers == null ) {
                    numbers = new LinkedList<>();
                }
                numbers.add(entry.getKey());
                return numbers;
            });
        }
        List<Integer> result = new LinkedList<Integer>();
        while( result.size() < k ) {
            List<Integer> list = c2n.pollFirstEntry().getValue();
            result.addAll(list.subList(0, Math.min(list.size(), k - result.size())));
        }
        return result;
    }
}
