package heaters;

import java.util.Arrays;

/**
 * Created by jun.ouyang on 12/12/16.
 */
public class Solution {

    public static void main(String[] args) {
        int[] houses = new int[]{282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923};
        int[] heaters = new int[]{823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612};

        System.out.println( isAscending(houses));
        System.out.println(isAscending(heaters));

        System.out.println(new Solution().findRadius(houses, heaters));

    }

    private static boolean isAscending( int[] a ) {
        int previous = Integer.MIN_VALUE;
        for( int v : a ) {
            if( v < previous ) return false;
            previous = v;
        }
        return true;
    }

    public int findRadius(int[] houses, int[] heaters) {
        if( houses == null || houses.length == 0 || heaters == null || heaters.length == 0 ) return 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int j = 0, result = 0;

        for( int i = 0; i < houses.length; i++ ) {
            while( j + 1 < heaters.length && houses[i] >= heaters[j+1] ) j++;
            int minRadius = Math.abs(heaters[j] - houses[i]);
            if(j + 1 < houses.length) {
                minRadius = Math.min(minRadius, Math.abs(heaters[j+1] - houses[i]));
            }
            result = Math.max(result, minRadius);
        }
        return result;
    }
}