import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class AveragingFilter {

    private BufferedImage img;
    private int n;
    private int centerPoint;

    AveragingFilter(String in, String number) {
        try {
            n = Integer.parseInt(number);
            centerPoint = n / 2;
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
                img.setRGB(i, j, processor.getAverageRgb());
            }
        }

        System.out.println("Filtering done!");
    }

    private Color[][] getSubMatrix(int x, int y) {
        Color[][] matrix = new Color[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = new Color(img.getRGB(getPos(x, i), getPos(y, j)));

        return matrix;
    }

    private int getPos(int origin, int current) {
        int diff = centerPoint - current;
        int target = origin - diff;
        int pos;

        if (target < 0 || target >= img.getHeight()) {
            pos = origin + diff;
        } else {
            pos = origin - diff;
        }

        return pos;
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
