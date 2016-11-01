import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class AveragingFilter {

    private BufferedImage img;
    private int n;

    AveragingFilter(String in, String number) {
        try {
            n = Integer.parseInt(number);
            if (n % 2 == 0) {
                System.out.println("N must be odd number!");
                System.exit(0);
            }

            img = ImageIO.read(new File(in));
        } catch (NumberFormatException e) {
            System.out.println("Error parsing string: " + number);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error opening file at: " + in);
            System.exit(0);
        }
    }

    void filter() {
        System.out.println("Filtering...");

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color[][] matrix = getSubMatrix(i, j);
                ColorProcessor processor = new ColorProcessor(matrix);
                img.setRGB(i, j, new Color(processor.getAverageRed(),
                        processor.getAverageBlue(), processor.getAverageGreen()).getRGB());
            }
        }

        System.out.println("Filtering done!");
    }

    private Color[][] getSubMatrix(int x, int y) {
        Color[][] matrix = new Color[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = new Color(img.getRGB(getPosX(x, i), getPosY(y, j)));

        return matrix;
    }

    private int getPosX(int originX, int currentX) {
        int centerPoint = n / 2;
        int diffX = centerPoint - currentX;

        if (originX - diffX < 0)
            return originX + diffX;

        if (originX - diffX >= img.getWidth())
            return originX + diffX;

        return originX - diffX;
    }

    private int getPosY(int originY, int currentY) {
        int centerPoint = n / 2;
        int diffY = centerPoint - currentY;

        if (originY - diffY < 0)
            return originY + diffY;

        if (originY - diffY >= img.getHeight())
            return originY + diffY;

        return originY - diffY;
    }

    void writeImg(String output) {
        try {
            ImageIO.write(img, "jpg", new File(output));
        } catch (IOException e) {
            System.out.println("Can't write to file: " + output);
            System.exit(0);
        }
    }
}
