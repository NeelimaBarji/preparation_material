import java.util.*;
import java.io.*;

public class setMatrixZeroes {
    public static void setZeroes(int[][] matrix) {
        int Rows = matrix.length,
                Cols = matrix[0].length;
        boolean rowBool = false,
                oneRow = false;
        for (int row = 0; row < Rows; row++) {
            for (int column = 0; column < Cols; column++) {
                if (matrix[row][column] == 0) {
                    matrix[0][column] = 0;
                    if (row > 0) {
                        rowBool = true;
                    } else {
                        oneRow = true;
                    }
                }
            }
            if (rowBool) {
                rowBool = false;
                Arrays.fill(matrix[row], 0);
            }
        }
        for (int column = 0; column < Cols; column++) {
            if (matrix[0][column] == 0) {
                for (int row = 0; row < Rows; row++) {
                    matrix[row][column] = 0;
                }
            }
        }
        if (oneRow) {
            Arrays.fill(matrix[0], 0);
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

    }

    public static void main(String[] args) {
        int[][] mat = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        setZeroes(mat);
    }
}