package island.parameter;

/**
 * Created by jun.ouyang on 1/1/17.
 */
public class Solution {
    public int islandPerimeter(int[][] grid) {
        int total = 0;
        for( int i = 0; i < grid.length; i++) {
            for( int j = 0; j < grid[0].length; j++ ) {
                if( grid[i][j] ==  1 ) {
                    total += getEdge(grid, i-1, j);
                    total += getEdge(grid, i+1, j);
                    total += getEdge(grid, i, j-1);
                    total += getEdge(grid, i, j+1);
                }
            }
        }
        return total;
    }

    private int getEdge( int[][] grid, int i, int j) {
        if( i < 0 || i > grid.length || j < 0 || j > grid[0].length|| grid[i][j] == 0) return 1;
        return 0;
    }
}