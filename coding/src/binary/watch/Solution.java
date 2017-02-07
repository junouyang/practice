package binary.watch;

import java.util.*;

/**
 * Created by jun.ouyang on 1/16/17.
 */
public class Solution {
    public static void main(String[] args) {
        String[] array = {"4:16", "2:08", "8:32", "0:02", "0:03", "0:04", "0:05", "0:06", "2:16", "0:08", "0:09", "0:10", "0:12", "4:32", "0:16", "0:17", "0:18", "0:20", "2:32", "0:24", "9:00", "0:32", "0:33", "0:34", "0:36", "0:40", "5:00", "0:48", "3:00", "1:01", "1:02", "1:04", "1:08", "1:16", "12:00", "10:00", "1:32", "8:00", "8:01", "8:02", "8:04", "6:00", "8:08", "4:00", "8:16", "4:01", "4:02", "4:04", "4:08", "2:00", "2:01", "2:02", "2:04"};
        String[] array2 = {"0:03", "0:05", "0:06", "0:09", "0:10", "0:12", "0:17", "0:18", "0:20", "0:24", "0:33", "0:34", "0:36", "0:40", "0:48", "1:01", "1:02", "1:04", "1:08", "1:16", "1:32", "2:01", "2:02", "2:04", "2:08", "2:16", "2:32", "3:00", "4:01", "4:02", "4:04", "4:08", "4:16", "4:32", "5:00", "6:00", "8:01", "8:02", "8:04", "8:08", "8:16", "8:32", "9:00", "10:00"};
        Arrays.sort(array);
        Set<String> rights = new HashSet<>(Arrays.asList(array2));
        Arrays.stream(array).filter( s -> !rights.contains(s)).forEach(s -> System.out.println(s));
    }

    public List<String> readBinaryWatch(int num) {
        if( num > 10 || num < 0 ) return Collections.emptyList();
        final int[] counts = {8 * 60, 4 * 60, 2 * 60, 1 * 60, 32, 16, 8, 4, 2, 1};
        Set<Integer> values = new HashSet<>();
        final Set<Integer> nextValues = new HashSet<>();
        values.add(0);
        for( int i = 0; i < num; i++ ) {
            nextValues.clear();
            for( int j = i; j < counts.length; j++ ) {
                int mins = counts[j];
                values.stream().forEach(v -> nextValues.add(v + mins));
            }
            values = new HashSet<>(nextValues);
        }
        List<String> result = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for( int v : values ) {
            int hour = v / 60, min = v % 60;
            builder.append(String.valueOf(hour));
            builder.append(':');
            builder.append( min > 9 ? "" : '0');
            builder.append(String.valueOf(min));
            result.add(builder.toString());
            builder.setLength(0);
        }
        return result;
    }
}
