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
    static int[][] blur(int[][] matrix, int blurRadius) {
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
    }


}
