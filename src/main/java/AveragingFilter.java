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
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color[] subPixels = getSubPixels(i, j);
                ColorProcessor processor = new ColorProcessor(subPixels);
                img.setRGB(i, j, new Color(processor.getAverageRed(),
                        processor.getAverageBlue(), processor.getAverageGreen()).getRGB());
            }
        }
    }

    private Color[] getSubPixels(int i, int j) {
        Color[] subPixels = new Color[n * n];
        int iMinus1 = i - 1 < 0 ? i + 1 : i - 1;
        int jMinus1 = j - 1 < 0 ? j + 1 : j - 1;
        int iPlus1 = i + 1 >= img.getWidth() ? i - 1 : i + 1;
        int jPlus1 = j + 1 >= img.getHeight() ? j - 1 : j + 1;

        subPixels[0] = new Color(img.getRGB(iMinus1, jMinus1));
        subPixels[1] = new Color(img.getRGB(iMinus1, j));
        subPixels[2] = new Color(img.getRGB(iMinus1, jPlus1));
        subPixels[3] = new Color(img.getRGB(i, jPlus1));
        subPixels[4] = new Color(img.getRGB(iPlus1, jPlus1));
        subPixels[5] = new Color(img.getRGB(iPlus1, j));
        subPixels[6] = new Color(img.getRGB(iPlus1, jMinus1));
        subPixels[7] = new Color(img.getRGB(i, jMinus1));
        subPixels[8] = new Color(img.getRGB(i, j));

        return subPixels;
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
