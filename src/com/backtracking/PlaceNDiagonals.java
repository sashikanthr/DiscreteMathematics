package com.backtracking;

public class PlaceNDiagonals {

    private static int size = 5;

    private static int[][] board = new int[size][size];

    private static int noOfDiagonals = 16;

    private final static int empty = 0;

    private final static int diagonalBRtoTL = 1;

    private final static int diagonalBLtoTR = -1;

    public static void main(String[] args) {

        processCell(0,0);
    }

   public static boolean processCell(int i, int j) {

        boolean isSolutionComplete = false;
        boolean isDiagonalBRtoTLSet = false;
        boolean isDiagonalBLtoTRSet = false;
        boolean isEmptySet = false;
        int currentValue = -2;
        int newValue = -2;
        while (!isSolutionComplete) {

            switch (board[i][j]) {

                case empty:
                    if (isDiagonalBRtoTLFeasible(i, j) && !isDiagonalBRtoTLSet) {
                        setDiagonalBRtoTL(i, j);
                        isDiagonalBRtoTLSet = true;

                    } else if (isDiagonalBLtoTRFeasible(i, j) && !isDiagonalBLtoTRSet) {
                        setDiagonalBLtoTR(i, j);
                        isDiagonalBLtoTRSet = true;

                    }
                    break;

                case diagonalBRtoTL:
                    if (isDiagonalBLtoTRFeasible(i, j) && !isDiagonalBLtoTRSet) {
                        setDiagonalBLtoTR(i, j);
                        isDiagonalBLtoTRSet = true;

                    } else if(!isEmptySet) {
                        setEmpty(i, j);
                        isEmptySet = true;

                    }
                    break;

                case diagonalBLtoTR:
                    if (isDiagonalBRtoTLFeasible(i, j) && !isDiagonalBRtoTLSet) {
                        setDiagonalBRtoTL(i, j);
                        isDiagonalBRtoTLSet = true;

                    } else if(!isEmptySet){
                        setEmpty(i, j);
                        isEmptySet = true;

                    }

                    break;

            }
            if(currentValue==-2) {
                currentValue = board[i][j];
            } else {
                newValue = board[i][j];
            }

            if(currentValue==newValue) {
                return false;
            } else if(newValue!=-2){
                currentValue = newValue;
            }

            if (i + 1 >= size && j + 1 >= size ) {

                boolean solutionComplete = isSolutionComplete();
                if (!solutionComplete) {
                    board[i][j] = empty;
                    return false;
                } else {
                    return true;
                }

            } else {
                if(j==size-1) {
                    isSolutionComplete = processCell(i + 1, 0);
                } else {
                    isSolutionComplete = processCell(i , j+1);
                }
            }
        }

        if (isSolutionComplete) {
            printSolution();
            System.out.println("-----------------------------------------------------------");
        }
        return false;
    }

    public static void setEmpty(int i, int j) {

        board[i][j] = empty;
    }

    public static void setDiagonalBRtoTL(int i, int j) {

        board[i][j] = diagonalBRtoTL;
    }

    public static void setDiagonalBLtoTR(int i, int j) {

        board[i][j] = diagonalBLtoTR;
    }

    public static boolean isDiagonalBRtoTLFeasible(int i, int j) {


        boolean bottom_right_diagonal = true;
        boolean right_cell = true;
        boolean bottom_cell = true;

        if (i - 1 >= 0 && j - 1 >= 0) {
            bottom_right_diagonal = board[i - 1][j - 1] != 1;
        }

        if (i - 1 >= 0) {
            right_cell = board[i - 1][j] != -1;
        }

        if (j - 1 >= 0) {
            bottom_cell = board[i][j - 1] != -1;
        }
        return (bottom_right_diagonal && right_cell && bottom_cell);


    }

    public static boolean isDiagonalBLtoTRFeasible(int i, int j) {

        boolean top_right_diagonal = true;
        boolean bottom_cell = true;
        boolean right_cell = true;

        if (j - 1 >= 0) {
            bottom_cell = board[i][j - 1] != 1;
        }
        if (i - 1 >= 0) {
            if (j + 1 <= size - 1) {
                top_right_diagonal = board[i - 1][j + 1] != -1;
            }
            right_cell = board[i - 1][j] != 1;
        }




        return (top_right_diagonal && bottom_cell && right_cell);

    }

    public static boolean isSolutionComplete() {

        int count = 0;

        for (int[] i : board) {

            for (int j : i) {

                if (j != 0) {

                    ++count;
                }
            }

        }

        return count == noOfDiagonals ? true : false;
    }

    public static void printSolution() {

        StringBuilder solution  = new StringBuilder();
        solution.append("{");
        for (int i = 0; i <= size - 1; i++) {
            solution.append("[");
            for (int j = 0; j <= size - 1; j++) {

                solution.append(board[i][j]);
                if(j!=size-1) {
                    solution.append(",");
                }

            }
            solution.append("]");
        }
        solution.append("}");
        System.out.println(solution.toString());
    }
}
