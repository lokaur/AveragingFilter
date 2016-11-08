import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class AveragingFilter {

    private BufferedImage img;
    private int frameSide;
    private int halfFrame;
    private int squareFrame;

    AveragingFilter(String in, String number) {
        try {
            frameSide = Integer.parseInt(number);

            if (frameSide % 2 == 0) {
                System.out.println("N must be odd number!");
                System.exit(0);
            }

            halfFrame = frameSide / 2;
            squareFrame = frameSide * frameSide;
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
        Runtime rt = Runtime.getRuntime();
        long memoryAtStart = rt.freeMemory();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                img.setRGB(i, j, getAverageRgb(i, j));
            }
        }

        long endTime = System.currentTimeMillis();
        long free = rt.freeMemory();

        System.out.println("Filtering done!");
        System.out.println("Working time: " + (endTime - startTime) + "ms");
        System.out.println("Allocated memory: " + ((memoryAtStart - free) / 1024) + " kbs");
    }

    private int getAverageRgb(int x, int y) {
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;

        for (int i = 0; i < frameSide; i++) {
            for (int j = 0; j < frameSide; j++) {
                int color = 0xff000000 | img.getRGB(getPos(x, i, img.getWidth()), getPos(y, j, img.getHeight()));
                sumR += color >> 16 & 0xFF;
                sumG += color >> 8 & 0xFF;
                sumB += color & 0xFF;
            }
        }

        return  0xff000000 |
                (sumR / squareFrame & 0xFF) << 16 |
                (sumG / squareFrame & 0xFF) << 8 |
                sumB / squareFrame & 0xFF;
    }

    private int getPos(int origin, int current, int maxSize) {
        int diff = halfFrame - current;
        int target = origin - diff;

        if (target < 0 || target >= maxSize) {
            return origin + diff;
        } else {
            return target;
        }
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
