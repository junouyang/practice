package perfect.squre;

/**
 * Created by jun.ouyang on 1/19/17.
 */
public class Solution {
    public static void main(String[] args ) {
//        System.out.println(new Solution().isPerfectSquare(169));
        System.out.println(new Solution().isPerfectSquare(808201));
        System.out.println(Math.sqrt(808201));
    }

    public boolean isPerfectSquare(int num) {
        int start = 1, end = num;
        while( start < end ) {
            int middle = start + (end - start)/2;
            long square = (long)middle * middle;
            if( num == square ) return true;
            if( num > square ) start = middle + 1;
            else end = middle;
        }
        return start == end && num == start * start;
    }
}