package matchsticks.to.square;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by jun.ouyang on 12/31/16.
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{5,5,5,5,4,4,4,4,3,3,3,3};
        System.out.println(new Solution().makesquare(nums));
    }

    public boolean makesquare(int[] nums) {
        LinkedList<Integer> numList = new LinkedList<>();
        int sum = 0;
        for( int num : nums ) {
            numList.add(num);
            sum += num;
        }
        if( (sum % 4) != 0 ) return false;
        Collections.sort( numList );
        int edge = sum /4;
        return makeRect(numList, new int[]{edge, edge, edge, edge});
    }

    private boolean makeRect(LinkedList<Integer> nums, int[] lens) {
        if( nums.isEmpty() ) return Arrays.stream(lens).allMatch( len -> len == 0 );
        int num = nums.removeLast();
        if(Arrays.stream(lens).allMatch( len -> num > len) ) {
            nums.addLast(num);
            return false;
        }
        for( int i = 0; i < lens.length; i++ ) {
            if( lens[i] < num ) continue;
            lens[i] -= num;
            if( makeRect(nums, lens) ) return true;
            lens[i] += num;
        }
        nums.addLast(num);
        return false;
    }
}