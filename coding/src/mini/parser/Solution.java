package mini.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jun.ouyang on 1/14/17.
 */

public class Solution {
    private static Solution solution = new Solution();

    public static void main(String[] args) {

//        output(solution.deserialize("[-9999,888,[999,[-999],-999,999]]"), "");
//        output(solution.deserialize("-889"), "");
        output(solution.deserialize("[[],[[[[]],-4],[[[]],[0],[[]]]]]"), "");
    }

    private static void output(NestedInteger value, String intent ) {

        if( value.isInteger ) {
            System.out.println(intent + "true:" + value.getInteger());
        } else {
            System.out.println(intent + "false : [");
            for( NestedInteger value1 : value.getList() ) {
                output( value1, intent + "    ");
            }
            System.out.println(intent + "]");
        }
    }

    public NestedInteger deserialize(String s) {
        if(s == null || s.length() == 0 ) return null;
        Stack<NestedInteger> stack = new Stack<>();
        boolean negative = false;
        boolean isEmpty = true;
        for( char c : s.toCharArray()) {
            if( c == '[') {
                stack.push(new NestedInteger());
                isEmpty = true;
            } else if( c >='0' && c <='9' || c == '-' ) {
                NestedInteger value;
                if( !stack.isEmpty() && stack.peek().isInteger()) {
                    value = stack.peek();
                } else {
                    value = new NestedInteger(0);
                    stack.push(value);
                }
                if(c=='-') negative = true;
                else value.setInteger(value.getInteger() * 10 + c - '0');
            } else {
                if(!isEmpty || stack.peek().isInteger ) {
                    NestedInteger top = stack.pop();
                    if (negative) {
                        negative = false;
                        top.setInteger(-top.getInteger());
                    }
                    stack.peek().add(top);
                }
                isEmpty = false;
            }
        }
        NestedInteger top = stack.pop();
        if(negative) {
            top.setInteger(-top.getInteger());
        }
        return top;
    }


    // This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
    public class NestedInteger {
        private boolean isInteger = false;
        private int value;
        private List<NestedInteger> values = new LinkedList<>();
        // Constructor initializes an empty nested list.
        public NestedInteger() {

        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
            isInteger = true;
            this.value = value;
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger(){
            return isInteger;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return value;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            isInteger = true;
            this.value = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            values.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return values;
        }
    }

}