package min.move.to.equal.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jun.ouyang on 1/1/17.
 */
public class Solution {
    int[][] adjs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return Collections.emptyList();
        int h = matrix.length, w = matrix[0].length;
        int[][] flags = new int[h][w];
        for (int i = 0; i < h; i++) {
            matrix[i][0] |= 1;
            mark(matrix, i, 0, 1);
            matrix[i][w - 1] |= 2;
            mark(matrix, i, w - 1, 2);
        }
        for (int j = 0; j < w; j++) {
            matrix[0][j] |= 1;
            mark(matrix, 0, j, 1);
            matrix[h - 1][j] |= 2;
            mark(matrix, h - 1, j, 2);
        }
        List<int[]> result = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] == 3) result.add(new int[]{i, j});
            }
        }
        return result;
    }

    public void mark(int[][] matrix, int i, int j, int value) {

        for (int[] adj : adjs) {
            int i1 = i + adj[0], j1 = j + adj[1];
            if (i1 >= 0 && i1 <= matrix.length && j1 >= 0 && j1 <= matrix[0].length && matrix[i1][j1] >= matrix[i][j] && (matrix[i1][j1] & value) == 0) {
                matrix[i1][j1] |= value;
                mark(matrix, i1, j1, value);
            }
        }
    }
}