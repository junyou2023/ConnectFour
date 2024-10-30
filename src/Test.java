import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println(grid);
        Player player1 = new Player("Eric", 'X');
        Player player2 = new Player("Tony", 'O');
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (playTurn(player1, grid, sc)) {
                break;
            }
            if (playTurn(player2, grid, sc)) {
                break;
            }
        }
    }

    private static boolean playTurn(Player player, Grid grid, Scanner sc) {
        while (true) {  // 持续循环，直到玩家输入有效的列
            try {
                System.out.println("Player " + player.getName() +
                        " (" + player.getSymbol() + "), please enter the column where you want to place your piece:");
                int col = sc.nextInt() - 1;  // 用户输入列号

                // 检查列是否已满，如果已满会抛出异常
                grid.checkColumnFull(col);

                // 将棋子放入指定列
                player.takeTurn(grid, col);

                // 显示当前网格状态
                System.out.println(grid);

                // 检查是否胜利
                if (grid.isWinningMove(col, player.getSymbol())) {
                    System.out.println("Player " + player.getName() + " wins!");
                    return true;  // 游戏结束
                }

                return false;  // 正常结束回合
            } catch (ColumnFullException e) {
                System.out.println(e.getMessage());  // 提示玩家列已满
                // 继续当前玩家的回合，不退出循环
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid column number.");
                sc.next();  // 清除无效输入，继续当前玩家的回合
            }
        }
    }
}

