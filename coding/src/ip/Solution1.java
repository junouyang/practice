package ip;

/**
 * Created by jun.ouyang on 12/31/16.
 */
public class Solution1 {
    public String validIPAddress(String IP) {
        if( IP == null ) return "Neither";
        int len = IP.length();
        if( len < 7 || len > 15 && len != 39 ) {
            return "Neither";
        }
        if( len == 39 ) {
            return isIPv6(IP) ? "IPv6" : "Neither";
        } else if (isIPv4(IP)) return "IPv4";
        return "Neither";
    }

    private boolean isIPv6( String ip ) {
        String[] groups = ip.split(":");
        if( groups.length == 8 ) {
            for( String group : groups ) {
                if( group.length() > 4 || group.length() == 0 ) return false;
                for( char c : group.toLowerCase().toCharArray() ) {
                    if( !(c >= '0' && c <='9') && !(c >='a' && c <='f') ) return false;
                }
            }
        }
        return true;
    }

    private boolean isIPv4(String ip) {
        String[] groups = ip.split("\\.");
        if( groups.length == 4 ) {
            for( String group : groups ) {
                if( group.length() > 1 && group.startsWith("0") ) return false;
                int value = -1;
                try {
                    value = Integer.parseInt(group);
                } catch (Exception ignored) {
                }
                return value >= 0 && value < 256;
            }
        }
        return false;
    }
}