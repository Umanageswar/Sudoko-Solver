/*import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = 9;
        int col = 9;
        char[][] board = new char[row][col];

        for (int i = 0; i < board.length; i++) {
            String input = sc.next();
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Enter solve to complete the sudoku board.");
        String solve = sc.next();

        System.out.println("Sudoku completed.");
    }
}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = 9;
        int col = 9;
        char[][] board = new char[row][col];

        System.out.println("Enter Sudoku puzzle row by row (use '.' or '0' for empty cells):");

        for (int i = 0; i < row; i++) {
            String input = sc.nextLine();
            for (int j = 0; j < col; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }

    // Solver function using backtracking
    public static boolean solveSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.' || board[row][col] == '0') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = '.'; // backtrack
                        }
                    }
                    return false; // no valid number found
                }
            }
        }
        return true; // board solved
    }

    // Check if it's safe to place num
    public static boolean isSafe(char[][] board, int row, int col, char num) {
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                    board[3*(row/3) + x/3][3*(col/3) + x%3] == num) {
                return false;
            }
        }
        return true;
    }

    // Print the board
    public static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private JButton solveButton = new JButton("Solve");

    public Main() {
        setTitle("Sudoku Solver");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(SIZE, SIZE));
        Font font = new Font("SansSerif", Font.BOLD, 20);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(font);
                panel.add(cells[row][col]);
            }
        }

        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[][] board = new char[SIZE][SIZE];
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        String text = cells[i][j].getText();
                        if (text.isEmpty() || text.equals(".") || text.equals("0")) {
                            board[i][j] = '.';
                        } else {
                            char c = text.charAt(0);
                            if (c >= '1' && c <= '9') {
                                board[i][j] = c;
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid input at (" + (i+1) + "," + (j+1) + ")");
                                return;
                            }
                        }

                    }
                }

                if (solveSudoku(board)) {
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++) {
                            cells[i][j].setText(String.valueOf(board[i][j]));
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No solution exists!");
                }
            }
        });

        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);
        container.add(solveButton, BorderLayout.SOUTH);

        add(container);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Backtracking solver
    public static boolean solveSudoku(char[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == '.' || board[row][col] == '0') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) return true;
                            board[row][col] = '.'; // backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSafe(char[][] board, int row, int col, char num) {
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                    board[3 * (row / 3) + x / 3][3 * (col / 3) + x % 3] == num)
                return false;
        }
        return true;
    }
}

