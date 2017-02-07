package can.i.win;

import java.util.*;

/**
 * Created by jun.ouyang on 1/1/17.
 */
public class Solution {
    static Solution solution = new Solution();
    public static void main(String[] args) {
        for( int i = 90; i < 110; i++)
        System.out.println(solution.lexicalOrder(i));
    }

    public List<Integer> lexicalOrder(int n) {
        if( n <= 0 ) return Collections.emptyList();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        List<Integer> result = new LinkedList<Integer>();
        result.add(1);
        while(result.size() < n ) {
            if( stack.peek() * 10 <= n ) {
                int peek = stack.peek() * 10;
                result.add( peek );
                stack.push( peek );
            } else {
                while(stack.peek() % 10 == 9 || stack.peek() + 1 > n) {
                    stack.pop();
                }
                int peek = stack.pop() + 1;
                result.add(peek);
                stack.push(peek);
            }
        }
        return result;
    }
}