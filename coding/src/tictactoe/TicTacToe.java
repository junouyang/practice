package tictactoe;

/**
 * Created by jun.ouyang on 1/29/17.
 */
public class TicTacToe {

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(3);
        System.out.println(ticTacToe.move(0, 0, 1));
    }

    int[][] result;
    int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        result = new int[n][n];
        this.n = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        result[row][col] = player;
        int h = 0, v = 0, d = 0, rd= 0;
        for( int i = 0; i < n; i++ ) {
            if( compare(row, i, player) ) h++;
            if( compare(i, col, player) ) v++;
            if( compare(i, i + col - row, player) ) d++;
            if( compare(i, col + row - i, player) ) rd++;
        }
        return (h == n || v == n || d == n || rd == n) ? player : 0;
    }

    private boolean compare(int i, int j, int player ) {
        if(i < 0 || i >= n || j < 0 || j >= n ) return false;
        return result[i][j] == player;
    }
}