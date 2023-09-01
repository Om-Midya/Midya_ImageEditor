package Test;

import java.util.Scanner;

public class BlurTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = sc.nextInt();
        System.out.println("Enter the number of columns: ");
        int columns = sc.nextInt();
        int[][] matrix = new int[rows][columns];
        System.out.println("Enter the matrix: ");
        for (int i = 0; i < rows; i++) {
            System.out.println("Enter the row " + (i + 1) + ": ");
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter the blur radius: ");
        int blurRadius = sc.nextInt();
        int[][] blurredMatrix = blur(matrix, blurRadius);
        System.out.println("The blurred matrix is: ");
        for (int i = 0; i < blurredMatrix.length; i++) {
            for (int j = 0; j < blurredMatrix[i].length; j++) {
                System.out.print(blurredMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    /*static int[][] blur(int[][] matrix, int blurRadius) {
        // create an empty image to hold the result
        int[][] blurredMatrix = new int[matrix.length][matrix[0].length];  //initialized a matrix for storing the blurred values

        // calculate the blurred value for each pixel of the original image
        for (int i = 0; i < blurredMatrix.length; i++) {
            for (int j = 0; j < blurredMatrix[i].length; j++) {

                // the blurred value of this pixel the avg of all pixels in a blurRadiusxblurRadius window
                int sum = 0;
                int count = 0;
                for (int k = i - blurRadius; k <= i + blurRadius; k++) { //loop for iterating through the rows in the blurRadius
                    for (int l = j - blurRadius; l <= j + blurRadius; l++) { //loop for iterating through the columns in the blurRadius
                        if (k >= 0 && k < matrix.length && l >= 0 && l < matrix[i].length) { //condition for checking if the index is within the matrix
                            sum += matrix[k][l];
                            count++;
                        }
                    }
                }
                blurredMatrix[i][j] = sum / count; //storing the average of the elements in the blurRadius in the blurredMatrix
            }
        }
        return blurredMatrix;
    }*/
    static int[][] blur(int[][] matrix, int blurRadius) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] blurredMatrix = new int[rows][cols];

        // Calculate the initial sum for the first window
        int sum = 0;
        for (int i = 0; i <= blurRadius; i++) {
            for (int j = 0; j <= blurRadius; j++) {
                sum += matrix[i][j];
            }
        }

        // Iterate through the image to calculate the blurred values
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Store the average in the blurredMatrix
                blurredMatrix[i][j] = sum / ((Math.min(i + blurRadius, rows - 1) - Math.max(i - blurRadius, 0) + 1) *
                        (Math.min(j + blurRadius, cols - 1) - Math.max(j - blurRadius, 0) + 1));

                // Update sum for the next window
                if (j + blurRadius + 1 < cols) {
                    for (int k = Math.max(i - blurRadius, 0); k <= Math.min(i + blurRadius, rows - 1); k++) {
                        sum += matrix[k][j + blurRadius + 1];
                    }
                }

                // Remove elements from the previous window
                if (j - blurRadius >= 0) {
                    for (int k = Math.max(i - blurRadius, 0); k <= Math.min(i + blurRadius, rows - 1); k++) {
                        sum -= matrix[k][j - blurRadius];
                    }
                }
            }
        }

        return blurredMatrix;
    }


}
