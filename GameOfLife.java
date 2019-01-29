
public class GameOfLife {

	public static void cells(String[][] gameBoard) {

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {

				if (gameBoard[i][j].equals("C")) {
					System.out.println("Cell found at: (" + i +", "+ j + ")");
				
					boolean topEdge = false;
					boolean leftEdge = false;
					boolean rightEdge = false;
					boolean bottomEdge = false;
					
					// Top edge
					if (i > 0) {
						topEdge = true;
					}
					// Left edge
					if (j > 0) {
						leftEdge = true;
					}
					// Right edge
					if (j == gameBoard[i].length) {
						rightEdge = true;
					}
					// Bottom edge
					if (i == gameBoard.length) {
						bottomEdge = true;
					}
				
					
				
				}
			}
		}

	}

	public static void main(String[] args) {

		String[][] testArr = { { "", "", "" }, { "", "C", "" }, { "C", "", "" }, };

		cells(testArr);

	}

}
