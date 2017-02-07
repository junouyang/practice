package eliminate.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jun.ouyang on 1/15/17.
 */
public class Solution {
    static Solution solution = new Solution();

    public static void main(String[] args) {
//        test(9);
//        test(1);
        test(16);
//        test(n);
    }

    private static void test(int n) {
        System.out.println(expectedRenain(n));
        System.out.println(solution.lastRemaining(n));
    }

    public static int expectedRenain( int n ) {
        LinkedList<Integer> nums = new LinkedList<>();
        for( int i = 1; i <= n; i++ ) {
            nums.add(i);
        }
        boolean fromRight = false;
        int time = 1, diff = 0;
        while(nums.size() > 1) {
            int expected = fromRight ? ((nums.getLast() + diff)/time) & 1 : 1;
            Iterator<Integer> iterator = nums.iterator();
            while(iterator.hasNext()) {
                if( (((iterator.next() + diff)/time) & 1 ) == expected ) iterator.remove();
            }
            diff += time * (1-expected);
            time *= 2;
            fromRight = !fromRight;
        }
        return nums.get(0);
    }


    public int lastRemaining(int n) {
        boolean fromRight = false;
        int time = 1, diff = 0;
        while(n > 1) {
            if( fromRight && (n & 1) == 0 ) {
                diff -= time;
                n = (n + 1)/2;
            } else {
                n /= 2;
            }
            time *= 2;
            fromRight = !fromRight;
        }
        return time + diff;
    }
}