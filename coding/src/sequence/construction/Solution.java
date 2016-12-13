package sequence.construction;

import java.util.*;

/**
 * Created by jun.ouyang on 11/3/16.
 */
public class Solution {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        Map<Integer, Set<Integer>> precedents = new HashMap<>();
        Map<Integer, Set<Integer>> successors = new HashMap<>();
        for (int[] seq : seqs) {
            for (int i = 0; i < seq.length; i++) {
                int n = seq[i];
                Set<Integer> sets = new HashSet();
                if (i > 0) sets.add(seq[i - 1]);
                precedents.merge(n, sets, (oldValue, newValue) -> {
                    oldValue.addAll(newValue);
                    return oldValue;
                });
                Set<Integer> sets2 = new HashSet();
                if (i < seq.length - 1) {
                    sets2.add(seq[i + 1]);
                }
                successors.merge(n, sets2, (oldValue, newValue) -> {
                    oldValue.addAll(newValue);
                    return oldValue;
                });
            }
        }
        if (precedents.size() != org.length) return false;

        Integer next = null, head = null;
        int index = 0;
        if (head == null) {
            Iterator<Map.Entry<Integer, Set<Integer>>> iterator = precedents.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Set<Integer>> entry = iterator.next();
                Set<Integer> value = entry.getValue();
                if (value.isEmpty()) {
                    if (head != null) return false;
                    head = entry.getKey();
                }
            }
        }
        while (head != null && index < org.length && head == org[index]) {
            precedents.remove(head);
            for (Integer i : successors.get(head)) {
                Set<Integer> precedentsOfSuccessor = precedents.get(i);
                precedentsOfSuccessor.remove(head);
                if (precedentsOfSuccessor.size() == 0) {
                    if (next != null) return false;
                    next = i;
                }
            }
            head = next;
            next = null;
            index++;
        }
        return index == org.length;
    }
}