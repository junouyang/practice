package random.of.list;

import java.util.Random;

/**
 * Created by jun.ouyang on 1/16/17.
 */
public class Solution {
    public static void main(String[] args) {
        Random random = new Random();
        for ( int i = 0; i < 20; i++ ) {
            System.out.println( random.nextInt(2));
        }
    }
}
