package Test;

import java.util.Scanner;

public class BlurTest1 {
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
        int[][] blurredMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < blurredMatrix.length; i+=blurRadius) {
            for (int j = 0; j < blurredMatrix[i].length; j+=blurRadius) {
                float sum = 0;
                for (int k = i; k < i+blurRadius; k++) {
                    for (int l = j; l < j+blurRadius; l++) {
                            sum += matrix[k][l]/(float)(blurRadius*blurRadius);
                    }
                }
                System.out.println(i+" "+j+" "+sum);
                for (int k = 0; k < blurRadius; k++) {
                    for (int l = 0; l < blurRadius; l++) {
                        if(i + k < matrix.length && j + l < matrix[i].length) {
                            blurredMatrix[i + k][j + l] = (int)sum;

                        }
                    }
                }

            }
        }
        return blurredMatrix;
    }
}
