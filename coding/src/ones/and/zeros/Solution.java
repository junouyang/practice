package ones.and.zeros;

/**
 * Created by jun.ouyang on 12/14/16.
 */
public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0 || m < 0 || n < 0 || (m == 0 && n == 0)) return 0;
        int[][] count = new int[m + 1][n + 1];
        for (String str : strs) {
            if (str.length() > m + n) continue;
            int c0 = 0, c1 = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') c0++;
                else if (c == '1') c1++;
            }
            if (c0 <= m && c1 <= n)
                count[c0][c1]++;
        }

        int c0 = m, c1 = n;
        int result = 0;
        for (int limit = 0; limit <= c0 || limit <= c1; limit++) {
            for (int index = 0; index <= limit; index++) {
                if (index <= n) {
                    for (int i = 0; i <= index && i <= c0; i++) {
                        int j = index;
                        if (i == 0 && j == 0 || count[i][j] == 0) continue;
                        int adding = count[i][j];
                        if (i != 0) {
                            adding = Math.min(adding, c0 / i);
                        }
                        if (j != 0) {
                            adding = Math.min(adding, c1 / j);
                        }
                        result += adding;
                        c0 -= i * adding;
                        c1 -= j * adding;
                    }
                }
                if (index <= m) {
                    for (int j = 0; j <= index && j <= c1; j++) {
                        int i = index;
                        if (i == 0 && j == 0 || count[i][j] == 0) continue;
                        int adding = count[i][j];
                        if (i != 0) {
                            adding = Math.min(adding, c0 / i);
                        }
                        if (j != 0) {
                            adding = Math.min(adding, c1 / j);
                        }
                        result += adding;
                        c0 -= i * adding;
                        c1 -= j * adding;
                    }
                }
            }
        }
        return result;
    }
}