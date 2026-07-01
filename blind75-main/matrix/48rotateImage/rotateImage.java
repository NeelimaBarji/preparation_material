import java.util.*;

public class rotateImage {
    public static int[][] rotate(int[][] matrix) {
        int matrixLength = matrix.length;
        for (int row = 0; row < matrixLength; row++) {
            for (int col = row + 1; col < matrixLength; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
        for (int row = 0; row < matrixLength; row++) {
            int l = 0,
                    r = matrixLength - 1;
            while (l < r) {
                int temp = matrix[row][l];
                matrix[row][l] = matrix[row][r];
                matrix[row][r] = temp;
                l++;
                r--;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        matrix = rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}