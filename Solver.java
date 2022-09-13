
public class Solver {
	
	private static final int GRID_SIZE = 9;
	
 public static void main(String[] args) {
	 
	 //Use by finding an unsolved suduko puzzle and inputting the numbers from the puzzle into this array
	 int[][] board = {
			 {0,0,0,7,0,0,4,2,0},
			 {0,1,0,0,0,2,0,0,0},
			 {0,0,0,0,4,0,0,5,6},
			 {0,9,0,0,5,1,0,0,0},
			 {0,0,0,2,0,0,0,0,0},
			 {0,3,0,0,0,7,0,1,0},
			 {7,5,0,0,2,0,0,0,0},
			 {9,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,2,0,0},
	 };
					
	//print statments
	 if (solveBoard(board)) {
		 System.out.println("Solved successfully!");
	 }
	 else {
		 System.out.println("Unsolveable board");
	 }
	 printBoard(board);

}
	//generate the board for printing
 private static void printBoard(int[][] board) {
	// TODO Auto-generated method stub
	for(int row = 0; row< GRID_SIZE; row++) {
		if( row % 3 == 0 && row != 0) {
			System.out.println("  ---------------");
		}
		for(int column = 0; column< GRID_SIZE; column++) {
			if(column % 3 == 0 && column != 0) {
				System.out.print("|");
			}
			System.out.print(board[row][column] + " ");
		}
		System.out.println();
	}
}
	//check to see if there is the same number where it's being placed  inside the row
private static boolean isNumberInRow(int[][] board, int number, int row) {
	 for(int i = 0; i < GRID_SIZE; i++) {
		if(board[row][i] == number) {
			return true;
		}
	 }
	 return false;
 }
	//check to see if there is the same number where it's being placed  inside the column
 private static boolean isNumberInColumn(int[][] board, int number, int column) {
	 for(int i = 0; i < GRID_SIZE; i++) {
		if(board[i][column] == number) {
			return true;
		}
	 }
	 return false;
 }
	//check to see if there is the same number where it's being placed  inside the box
 private static boolean isNumberInBox(int[][] board, int number, int column, int row) {
	 int localBoxRow = row - row % 3;
	 int localBoxColumn = column - column % 3;
	 
	 for (int i = localBoxRow; i < localBoxRow + 3; i++) {
		 for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
			 if (board[i][j] == number) {
				 return true;
			 }
		 }
	 }
	 return false;
 }
 //checks if the placement is valid
 private static boolean isValidPlacement(int board[][], int number, int row, int column) {
	 return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) && !isNumberInBox(board, number, column,row);
 }
 //recursivly calls solve board inside itself to place a number and keep placing numbers until the boardstate is not valid then erases and goes back over till it
	//either ends or finds a working boardstate.
 private static boolean solveBoard(int[][] board) {
	 for (int row = 0; row < GRID_SIZE; row++) {
		 for(int column = 0; column < GRID_SIZE; column++) {
			 if(board[row][column] == 0) {
				 for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
					 if(isValidPlacement(board, numberToTry, row, column)) {
						 board[row][column] = numberToTry;
						 
						 if(solveBoard(board)) {
							 return true; 
						 }
						 else {
							 board[row][column] = 0;
						 }
					 }
				 }
				 return false;
			 }
		 }
	 }
	 return true;
 }
}
