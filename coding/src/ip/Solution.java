package ip;

import java.util.regex.Pattern;

/**
 * Created by jun.ouyang on 12/31/16.
 */
public class Solution {
    public String validIPAddress(String IP) {
        Pattern ipv6Pattern = Pattern.compile("(?:[a-fA-F0-9]{1,4}:){7}[a-fA-F0-9]{1,4}");
        Pattern ipv4Pattern = Pattern.compile("(?:(?:0|1[0-9]{0,2}|2|2[0-9]|2[0-4][0-9]|25[0-5])\\.){3}(?:0|1[0-9]{0,2}|2|2[0-9]|2[0-4][0-9]|25[0-5])");

        if( IP == null ) return "Neither";
        if( ipv6Pattern.matcher(IP).matches() ) return "IPv6";
        if (ipv4Pattern.matcher(IP).matches()) return "IPv4";
        return "Neither";
    }
}