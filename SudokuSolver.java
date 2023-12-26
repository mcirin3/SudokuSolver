/* 
 
  Program Title: Sudoku Command Line Solver 
  Author: Mark Cirineo 
  System: Eclipse IDE 


*/




import java.util.*; 


public class SudokuSolver  { 

static int N = 9; 

/* Function takes in a partially filled grid and checks if there is a possible solution to the 9x9 grid. 
It attempts to assign values to unassigned areas to meet the requirements of solving the Sudoku puzzle. */ 

static boolean solveSudoku(int grid[][], int row,
int column) { 
// if we reach the 8th row and 9th column positions, we return true to avoid any backtracking. 

if(row == N - 1 && col == N) 
	return true; 
/* Check if the column value becomes 9. If so then we move to next row 
and start from 0 again. */ 

if(col == N) { 
	row++; 
	col = 0; 
}

/* Then check if the grid already contains a value greater than 0, 
we move to the next column by iterating throw the grid.  */ 

if(grid[row][column] != 0 )
	return solveSudoku(grid, row, col + 1); 

for(int num = 1; num < 10; num++) { 
	
	if(isSafe(grid, row, column)) { 
		
		grid[row][column] = num; 
		
		//Check for the next possible value for the next column 
		if(solveSudoku(grid, row, col + 1)) 
			return true; 
		
	}
	/* removing the assigned num , since our
    assumption was wrong , and we go for next
    assumption with diff num value   */
 grid[row][col] = 0;
}
return false;
}

/* A utility function to print grid */
static void print(int[][] grid)
{
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            System.out.print(grid[i][j] + " ");
        System.out.println();
    }
}






//Check whether it will be legal
// to assign num to the
// given row, col
static boolean isSafe(int[][] grid, int row, int col,
                      int num)
{

    // Check if we find the same num
    // in the similar row , we
    // return false
    for (int x = 0; x <= 8; x++)
        if (grid[row][x] == num)
            return false;

    // Check if we find the same num
    // in the similar column ,
    // we return false
    for (int x = 0; x <= 8; x++)
        if (grid[x][col] == num)
            return false;

    // Check if we find the same num
    // in the particular 3*3
    // matrix, we return false
    int startRow = row - row % 3, startCol
                                  = col - col % 3;
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (grid[i + startRow][j + startCol] == num)
                return false;

    return true;
}

// Driver Code
public static void main(String[] args)
{
    int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                     { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                     { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                     { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                     { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                     { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                     { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                     { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                     { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

    if (solveSudoku(grid, 0, 0))
        print(grid);
    else
        System.out.println("No Solution exists");
}
}


}
