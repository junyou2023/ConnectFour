import java.util.Arrays;

public class Grid {
    private char[][] grid;

    public Grid(){
        this.grid = new char[6][7];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col]= ' ';
            }
        }
    }

    public boolean isWinningMove(int col, char symbol) {
        // 获取刚放置的棋子所在的行
        int row = -1;
        for(int i = 0; i < grid.length; i++){
            if(grid[i][col] != ' '){
                row = i;
                break;
            }
        }

        if (row == -1){
            throw new IllegalStateException("No piece has been placed in the column: " + col);
        }

        // 检查当前行有无4个连续一样的
        int count = 0;
        for (char c : grid[row]) {
            count = (c == symbol) ? (count + 1) : 0;
            if (count >= 4) return true;
        }

        // 检查当前列有无4个连续一样的
        count = 0;
        for (int r = 0; r < grid.length; r++) {
            count = (grid[r][col] == symbol) ? (count + 1) : 0;
            if (count >= 4) return true;
        }

        // 检查主对角线有无4个连续一样的
        count = 0;
        for (int r = row, c = col; r < grid.length && c < grid[0].length; r++, c++) {
            count = (grid[r][c] == symbol) ? (count + 1) : 0;
            if (count >= 4) return true;
        }

        // 检查次对角线有无4个连续一样的
        count = 0;
        for (int r = row, c = col; r < grid.length && c >= 0; r++, c--) {
            count = (grid[r][c] == symbol) ? (count + 1) : 0;
            if (count >= 4) return true;
        }

        return false;
    }

    public boolean makeMove(char symbol, int col) {
        for (int row = grid.length - 1; row >= 0; row--) {
            if (grid[row][col] == ' ') {
                grid[row][col] = symbol;
                if(isWinningMove(col, symbol)){
                    System.out.println("Player " + symbol + " wins!");
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkColumnFull(int col) throws ColumnFullException {
        if (grid[0][col] != ' ') {  // 如果顶部位置不为空，说明这一列已满
            throw new ColumnFullException("Column " + col+1 + " is full.");
        }
        return false;  // 如果未满，返回 false
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                sb.append(String.format("| %c ", grid[row][col]));
            }
            sb.append("|\n");
            sb.append("-----------------------------\n");
        }

        return sb.toString();
    }

}
