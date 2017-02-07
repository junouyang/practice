package sum;

/**
 * Created by jun.ouyang on 1/18/17.
 */
public class Solution {
    public static void main(String[] args ) {
        System.out.println(new Solution().getSum(1, 1));
    }

    public int getSum(int a, int b) {
        if(b==0) return a;
        return getSum(a ^ b, (a&b)<<1);
    }
}