package ternary.expression.parser;

/**
 * Created by jun.ouyang on 11/3/16.
 */
public class Solution {
    public String parseTernary(String expression) {
        if (expression.length() < 5) return null;
        if (expression.charAt(1) == '?') {
            if (expression.charAt(0) == 'T') {
                return parseTernary(expression.substring(2));
            } else {
                int i = 3, depth = 1;
                do {
                    char c = expression.charAt(i);
                    if (c == '?') {
                        depth++;
                    } else if (c == ':') {
                        depth--;
                    }
                    i += 2;
                } while (depth > 0);
                return parseTernary(expression.substring(i + 1));
            }
        }
        return expression.substring(0, 1);
    }
}