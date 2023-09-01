//import Necessary packages for image processing
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Define File Path for input image
        File inputFile = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/src/image.jpg");
        try {
            BufferedImage inputImage = ImageIO.read(inputFile);
//            printPixelValues(inputImage);
            Scanner sc = new Scanner(System.in);
            //Menu for choosing the operation to be performed
            System.out.println("Choose the operation you want to perform");
            System.out.println("1. Brightness");
            System.out.println("2. Grayscale");
            System.out.println("3. Negative");
            System.out.println("4. Rotate Clockwise by 90 degrees");
            System.out.println("5. Rotate Anti-clockwise by 90 degrees");
            System.out.println("6. Rotate by 180 degrees");
            System.out.println("7. Flip horizontally");
            System.out.println("8. Blur image(Gaussian blur)");
            System.out.println("9. Pixelated blur");
            System.out.println("0. Exit");
            //Loop for continuosly taking input from user
            while(true){
                System.out.println("Enter your choice");
                int choice = sc.nextInt();
                switch (choice){
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("Enter the brightness value");
                        double brightness = sc.nextDouble();
                        BufferedImage brightImage = brigthnessConfig(inputImage,brightness);
                        File BrightIMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/brightImage.jpg");
                        ImageIO.write(brightImage,"jpg",BrightIMG);
                        System.out.println("Brightness changed");
                        break;
                    case 2:
                        BufferedImage grayScaleImage = convertToGrayScale(inputImage);
                        File GrayscaleIMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/grayScaleImage.jpg");
                        ImageIO.write(grayScaleImage,"jpg",GrayscaleIMG);
                        System.out.println("Grayscale image created");
                        break;
                    case 3:
                        BufferedImage negativeImage = convertToNegative(inputImage);
                        File NegativeIMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/negativeImage.jpg");
                        ImageIO.write(negativeImage,"jpg",NegativeIMG);
                        System.out.println("Negative image created");
                        break;
                    case 4:
                        BufferedImage rotateClockwise90 = RotateClockwise90(inputImage);
                        File RotateClockwise90IMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/rotateClockwise90.jpg");
                        ImageIO.write(rotateClockwise90,"jpg",RotateClockwise90IMG);
                        System.out.println("Image rotated clockwise by 90 degrees");
                        break;
                    case 5:
                        BufferedImage rotateAntiClockwise90 = RotateAntiClockwise90(inputImage);
                        File RotateAntiClockwise90IMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/rotateAntiClockwise90.jpg");
                        ImageIO.write(rotateAntiClockwise90,"jpg",RotateAntiClockwise90IMG);
                        System.out.println("Image rotated anti-clockwise by 90 degrees");
                        break;
                    case 6:
                        BufferedImage rotate180 = Rotate180(inputImage);
                        File Rotate180IMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/rotate180.jpg");
                        ImageIO.write(rotate180,"jpg",Rotate180IMG);
                        System.out.println("Image rotated by 180 degrees");
                        break;
                    case 7:
                        BufferedImage flipHorizontal = FlipHorizontal(inputImage);
                        File FlipHorizontalIMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/flipHorizontal.jpg");
                        ImageIO.write(flipHorizontal,"jpg",FlipHorizontalIMG);
                        System.out.println("Image flipped horizontally");
                        break;
                    case 8:
                        System.out.println("Enter the blur radius");
                        int blur = sc.nextInt();
                        BufferedImage blurImage = GaussianBlurImage(inputImage,blur);
                        File BlurIMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/blurImage.jpg");
                        ImageIO.write(blurImage,"jpg",BlurIMG);
                        System.out.println("Image blurred");
                        break;
                    case 9:
                        System.out.println("Enter the blur radius");
                        int blur1 = sc.nextInt();
                        BufferedImage pixelatedBlurImage = PixelatedBlurImage(inputImage,blur1);
                        File PixelatedBlurIMG = new File("/media/om7521/Om_MidyaExt/CS_All/@SST/Lecs/Kshitij_Java/Image_editor/outputImage/pixelatedBlurImage.jpg");
                        ImageIO.write(pixelatedBlurImage,"jpg",PixelatedBlurIMG);
                        System.out.println("Image pixelated blurred");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
//          e.printStackTrace();
        }
    }
    public static BufferedImage convertToGrayScale(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                Color pixel = new Color(inputImage.getRGB(j,i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                int gray = (red+green+blue)/3;
                Color grayPixel = new Color(gray,gray,gray);
                outputImage.setRGB(j,i,grayPixel.getRGB());
            }
        }
        return outputImage;
    }
    public static BufferedImage convertToNegative(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                Color pixel = new Color(inputImage.getRGB(j,i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                Color negativePixel = new Color(255-red,255-green,255-blue);
                outputImage.setRGB(j,i,negativePixel.getRGB());
            }
        }
        return outputImage;
    }
    public static BufferedImage brigthnessConfig(BufferedImage inputImage,double brightness){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        brightness =  brightness/100;
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                Color pixel = new Color(inputImage.getRGB(j,i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                int newRed = (int) (red+(red*brightness));
                int newGreen = (int) (green+(green*brightness));
                int newBlue = (int) (blue+(blue*brightness));
                if(newRed>255){
                    newRed = 255;
                }
                if(newGreen>255){
                    newGreen = 255;
                }
                if(newBlue>255){
                    newBlue = 255;
                }
                if(newRed<0){
                    newRed = 0;
                }
                if(newGreen<0){
                    newGreen = 0;
                }
                if(newBlue<0){
                    newBlue = 0;
                }
                Color newPixel = new Color(newRed,newGreen,newBlue);
                outputImage.setRGB(j,i,newPixel.getRGB());
            }
        }
        return outputImage;
    }
    //Unoptimized code for Gaussian blur
    public static BufferedImage GaussianBlurImage(BufferedImage inputImage, int blur){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j,i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                int sumRed = 0;
                int sumGreen = 0;
                int sumBlue = 0;
                int count = 0;
                for (int k = i-blur; k <= i+blur; k++) {
                    for (int l = j-blur; l <= j+blur; l++) {
                        if(k>=0 && k<height && l>=0 && l<width){
                            Color pixel1 = new Color(inputImage.getRGB(l,k));
                            sumRed += pixel1.getRed();
                            sumGreen += pixel1.getGreen();
                            sumBlue += pixel1.getBlue();
                            count++;
                        }
                    }
                }
                int newRed = sumRed/count;
                int newGreen = sumGreen/count;
                int newBlue = sumBlue/count;
                Color newPixel = new Color(newRed,newGreen,newBlue);
                outputImage.setRGB(j,i,newPixel.getRGB());
            }
        }
        return outputImage;
    }
   /* public static BufferedImage SharpenImage(BufferedImage inputImage, int sharpen){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

    }
    public static BufferedImage EdgeDetection(BufferedImage inputImage, int edge){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);


    }*/
    //Optimised code for Gaussian blur using sliding window algorithm
   /* public static BufferedImage GaussianBlurImage(BufferedImage inputImage, int blur) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Loop through each pixel in the input image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int sumRed = 0;
                int sumGreen = 0;
                int sumBlue = 0;
                int count = 0;

                // Define the sliding window
                for (int x = -blur; x <= blur; x++) {
                    for (int y = -blur; y <= blur; y++) {
                        int pixelX = j + x;
                        int pixelY = i + y;

                        // Check if the window is within image bounds
                        if (pixelX >= 0 && pixelX < width && pixelY >= 0 && pixelY < height) {
                            Color pixel = new Color(inputImage.getRGB(pixelX, pixelY));
                            sumRed += pixel.getRed();
                            sumGreen += pixel.getGreen();
                            sumBlue += pixel.getBlue();
                            count++;
                        }
                    }
                }

                // Calculate the average color value
                int avgRed = Math.min(255, Math.max(0, sumRed / count));
                int avgGreen = Math.min(255, Math.max(0, sumGreen / count));
                int avgBlue = Math.min(255, Math.max(0, sumBlue / count));

                // Set the average color as the pixel value in the output image
                outputImage.setRGB(j, i, new Color(avgRed, avgGreen, avgBlue).getRGB());
            }
        }
        return outputImage;
    }*/



    public static BufferedImage RotateClockwise90(BufferedImage inputImage) {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            outputImage.setRGB(height-i-1, j, inputImage.getRGB(j, i));
        }
    }
    return outputImage;
}

    public static BufferedImage RotateAntiClockwise90(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                outputImage.setRGB(i, width-1-j, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }
    public static BufferedImage Rotate180(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                outputImage.setRGB(width-1-j, height-1-i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }
    public static BufferedImage FlipHorizontal(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                outputImage.setRGB(width-1-j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }
    public static void printPixelValues(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
               // System.out.print(inputImage.getRGB(j,i)+" ");
                Color pixel = new Color(inputImage.getRGB(j,i));
                System.out.print("("+pixel.getRed()+" "+pixel.getGreen()+" "+ pixel.getBlue()+")");
            }
            System.out.println();
        }

    }
    public static BufferedImage PixelatedBlurImage(BufferedImage inputImage, int blur){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i+=blur) {
            for (int j = 0; j < width; j+=blur) {
                float sumRed = 0;
                float sumGreen = 0;
                float sumBlue = 0;
                int count = 0;
                for (int k = i; k < i+blur; k++) {
                    for (int l = j; l < j+blur; l++) {
                        if(k<height && l<width){
                            Color pixel = new Color(inputImage.getRGB(l,k));
                            sumRed += pixel.getRed();
                            sumGreen += pixel.getGreen();
                            sumBlue += pixel.getBlue();
                            count++;
                        }
                    }
                }
                float newRed = sumRed/count;
                float newGreen = sumGreen/count;
                float newBlue = sumBlue/count;
                for (int k = 0; k < blur; k++) {
                    for (int l = 0; l < blur; l++) {
                        if(i+k<height && j+l<width){
                            Color newPixel = new Color((int)newRed,(int)newGreen,(int)newBlue);
                            outputImage.setRGB(j+l,i+k,newPixel.getRGB());
                        }
                    }
                }
            }
        }
        return outputImage;
    }
}