import java.util.Scanner;

public class GameOfLifeMain {

	/**
	 * Method which takes a 2D array containing values of "C" and "X" to run Game Of
	 * Life
	 * 
	 * @param gameBoard
	 */
	public static void gameOfLife(String[][] gameBoard) {

		boolean validBoardCheck = checkValidGameBoard(gameBoard, "C", "X");

		// While there are cells in the gameBoard
		while (checkForCells(gameBoard, "C") == true && validBoardCheck == true) {

			System.out.println(printGameBoard(gameBoard));

			// Create empty gameBoard to be filled in
			String[][] updatedGameBoard = new String[gameBoard.length][gameBoard[0].length];

			for (int i = 0; i < gameBoard.length; i++) {
				for (int j = 0; j < gameBoard[i].length; j++) {

					// Check if current position falls on an edge
					boolean topEdge = false;
					boolean leftEdge = false;
					boolean rightEdge = false;
					boolean bottomEdge = false;

					// Top edge
					if (i == 0) {
						topEdge = true;
					}
					// Left edge
					if (j == 0) {
						leftEdge = true;
					}
					// Right edge
					if (j == gameBoard[i].length - 1) {
						rightEdge = true;
					}
					// Bottom edge
					if (i == gameBoard.length - 1) {
						bottomEdge = true;
					}

					// Search around current position for cells
					String[][] searchNetwork = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
					searchNetwork[1][1] = "Current position";

					// Check top edge if current position is not top edge
					if (topEdge == false) {
						if (leftEdge == false) {
							searchNetwork[0][0] = gameBoard[i - 1][j - 1];
						}

						searchNetwork[0][1] = gameBoard[i - 1][j];

						if (rightEdge == false) {
							searchNetwork[0][2] = gameBoard[i - 1][j + 1];
						}
					}

					// Check left edge if current position is not left edge
					if (leftEdge == false) {
						searchNetwork[1][0] = gameBoard[i][j - 1];
					}

					// Check right edge if current position is not right edge
					if (rightEdge == false) {
						searchNetwork[1][2] = gameBoard[i][j + 1];
					}

					// Check bottom edge if current position is not bottom edge
					if (bottomEdge == false) {
						if (leftEdge == false) {
							searchNetwork[2][0] = gameBoard[i + 1][j - 1];
						}

						searchNetwork[2][1] = gameBoard[i + 1][j];

						if (rightEdge == false) {
							searchNetwork[2][2] = gameBoard[i + 1][j + 1];
						}
					}

					// Count the number of instances of "C" surrounding the current position
					int cellCounter = 0;
					for (int x = 0; x < searchNetwork.length; x++) {
						for (int y = 0; y < searchNetwork[x].length; y++) {
							if (searchNetwork[x][y].equals("C")) {
								cellCounter++;
							}
						}
					}
					// System.out.println("Cells surrounding (" + i + ", " + j + ") = " +
					// cellCounter);

					// If a cell is found, perform the relevant scenarios and add to
					// updatedGameBoard
					if (gameBoard[i][j].equals("C")) {
						// System.out.println("HIT! Cell found at: (" + i + ", " + j + ")");

						// Scenario 1 - Underpopulation
						// When a live cell has fewer than two neighbours
						// Then this cell dies

						if (cellCounter < 2) {
							// System.out.println("Scenario 1 - Underpopulation");
							updatedGameBoard[i][j] = "X";
						}

						// Scenario 2 - Overcrowding
						// When a live cell has more than three neighbours
						// Then this cell dies

						if (cellCounter > 3) {
							// System.out.println("Scenario 2 - Overcrowding");
							updatedGameBoard[i][j] = "X";
						}
						// Scenario 3 - Survival
						// When a live cell has two or three neighbours
						// Then this cell stays alive
						if (cellCounter == 2 || cellCounter == 3) {
							// System.out.println("Scenario 3 - Survival");
							updatedGameBoard[i][j] = "C";
						}

					}
					// If an empty position is found, perform relevant scenario and add to
					// updatedGameBoard
					else {
						// System.out.println("No Cell found at : (" + i + ", " + j + ")");
						// Scenario 4 - Creation of Life
						// When an empty position has exactly three neighbouring cells
						// Then a cell is created in this position

						if (cellCounter == 3) {
							// System.out.println("Scenario 4 - Creation of Life");

							updatedGameBoard[i][j] = "C";
						}

						else {
							updatedGameBoard[i][j] = "X";
						}
					}
				}
			}
			System.out.println("");
			// gameBoard is given values of updatedGameBoard after a single iteration
			gameBoard = updatedGameBoard;
		}

		if (validBoardCheck == false) {
			System.out.println("Invalid input");
		} else {
			System.out.println("No cells remaining");
		}
	}

	/**
	 * Helper method to check a 2D array for a particular input
	 * 
	 * @param ar
	 * @param input
	 * @return
	 */
	public static boolean checkForCells(String[][] ar, String input) {

		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar[i].length; j++) {
				if (ar[i][j] == input) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check gameBoard contains correct parameters
	 * 
	 * @param ar
	 * @param cell
	 * @param empty
	 * @return
	 */
	public static boolean checkValidGameBoard(String[][] ar, String cell, String empty) {

		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar[i].length; j++) {
				if (ar[i][j] != cell && ar[i][j] != empty) {
					return false;
				}
			}
		}
		return true;

	}

	/**
	 * Helper method to print a 2D array in a matrix
	 * 
	 * @param arr
	 * @return
	 */
	public static String printGameBoard(String[][] arr) {
		String toPrint = "";
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				toPrint += arr[i][j] + " ";
			}
			toPrint += "\n";
		}
		return toPrint;
	}

	/**
	 * Method which creates a random gameBoard to user's requirements
	 * 
	 * width = how wide user wants gameBoard height = how high user wants gameBoard
	 * cellProportion = approximate percentage of places to hold a cell
	 * 
	 * @param width
	 * @param height
	 * @param cellProportion
	 * @return
	 */
	public static String[][] createRandomGameBoard(int height, int width, double cellProportion) {
		String[][] board = new String[height][width];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				double random = Math.random();
				if (random < cellProportion) {
					board[i][j] = "C";
				} else {
					board[i][j] = "X";
				}
			}
		}
		return board;
	}

	/**
	 * Acquire user input from console and run program
	 */
	public static void runProgram() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the height of the grid: ");
		int height = sc.nextInt();
		System.out.println("Please enter the width of the grid: ");
		int width = sc.nextInt();
		System.out.println("Please enter the percentage of cells: ");
		int cellPercentage = sc.nextInt();
		double cellPercentageDouble = (double) cellPercentage / 100;
		System.out.println("");
		sc.close();

		gameOfLife(createRandomGameBoard(height, width, cellPercentageDouble));

	}

	public static void main(String[] args) {

		runProgram();

	}

}
