import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gobang {
	public static int p = 1;// current player: 1 means black while 2 means
							// white;

	public static void main(String[] args) {
		// Set GUI
		JFrame frame = new JFrame("Gobang - Powered by David                     | Black's round |");
		JPanel chessboard = new JPanel();
		ImageIcon line = new ImageIcon("chessboardline.png");
		ImageIcon white = new ImageIcon("whitechess.png");
		ImageIcon black = new ImageIcon("blackchess.png");
		chessboard.setLayout(new GridLayout(15, 15));
		int[][] chessStatus = new int[15][15];
		for (int i = 0; i < 225; i++) {
			int x = i % 15;
			int y = i / 15;
			JButton chess = new JButton("", line);// Add button
			chess.setBorderPainted(false); // Set the border line invisible to
											// make the frame more beautiful.
			chess.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (chessStatus[x][y] == 0) // Judge and change the current
												// player and the position.
						if (p == 1) {
							chess.setIcon(black);
							chessStatus[x][y] = p;
							judge(chessStatus, x, y);
							frame.setTitle("Gobang - Powered by David                     | White's round |");
							p = 2;
						} else {
							chess.setIcon(white);
							chessStatus[x][y] = p;
							judge(chessStatus, x, y);
							frame.setTitle("Gobang - Powered by David                     | Black's round |");
							p = 1;
						}
					else
						JOptionPane.showMessageDialog(null, "You cannot put a chess here!", "A chess already here",
								JOptionPane.ERROR_MESSAGE);
				}
			});
			chessboard.add(chess);
		}
		frame.add(chessboard);
		frame.setSize(750, 750); // 15*50 each side
		frame.setResizable(false); // If it is true, the size of panel will
									// change as the windows size together.
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	// Logical Part
	public static void judge(int chessStatus[][], int x, int y) {
		String player;
		if (p == 1) {
			player = "Black";
		} else {
			player = "White";
		}
		if (ifRow(chessStatus, y) || ifColumn(chessStatus, x) || ifDiagonal(chessStatus, x, y)) {
			JOptionPane.showConfirmDialog(null, "Player " + player + " is the winner!!\nPress OK to exit.", "Game over",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		} else if (ifDraw(chessStatus)) {
			JOptionPane.showConfirmDialog(null, "Draw!!\nPress OK to exit.", "Game over", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

	}

	public static boolean ifRow(int chessStatus[][], int y) {
		int sum = 0;
		//count from the beginning of the row
		for (int i = 1; i < 15; i++) {
			if (chessStatus[i][y] == chessStatus[i - 1][y] && chessStatus[i][y] == p)
				sum += 1;
			else
				sum = 0;
			if (sum == 4)
				return true;
		}
		return false;
	}

	public static boolean ifColumn(int chessStatus[][], int x) {
		int sum = 0;
		//count from the beginning of the column
		for (int i = 1; i < 15; i++) {
			if (chessStatus[x][i] == chessStatus[x][i - 1] && chessStatus[x][i] == p)
				sum += 1;
			else
				sum = 0;
			if (sum == 4)
				return true;
		}
		return false;
	}

	public static boolean ifDiagonal(int[][] chessStatus, int x, int y) {
		int sum1 = 0;
		int sum2 = 0;
		//to top left corner and bottom right corner
		for (int i = x, j = y; j < 14 && i < 14; i++, j++) {
			if (chessStatus[i][j] == chessStatus[i + 1][j + 1] && chessStatus[i][j] == p)
				sum1 += 1;
			else
				break;
		}
		for (int i = x, j = y; j >= 1 && i >= 1; j--, i--) {
			if (chessStatus[i][j] == chessStatus[i - 1][j - 1] && chessStatus[i][j] == p)
				sum1 += 1;
			else
				break;
		}
		if (sum1 == 4)
			return true;
		//to top right corner and bottom left corner
		for (int i = x, j = y; i >= 1 && j < 14; i--, j++) {
			if (chessStatus[i][j] == chessStatus[i - 1][j + 1] && chessStatus[i][j] == p)
				sum2 += 1;
			else
				break;
		}
		for (int i = x, j = y; i < 14 && j >= 1; i++, j--) {
			if (chessStatus[i][j] == chessStatus[i + 1][j - 1] && chessStatus[i][j] == p)
				sum2 += 1;
			else
				break;
		}
		if (sum2 == 4)
			return true;
		return false;
	}

	public static boolean ifDraw(int[][] chessStatus) {
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				if (chessStatus[i][j] == 0)
					return false;
		return true;
	}

}
