package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jun.ouyang on 11/16/16.
 */
public class Test {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("<test>[^<]*</test>|<test/>");
        Matcher matcher = pattern.matcher("<test/>");
        System.out.println( matcher.matches());
        System.out.println(pattern.matcher("<test>kakaka</test>").matches());
    }
}
