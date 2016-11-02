import java.awt.*;

class ColorProcessor {

    private final Color[][] colors;

    ColorProcessor(Color[][] colors) {
        this.colors = colors;
    }

    int getAverageRgb() {
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        for (Color[] row : colors) {
            for (Color col : row) {
                sumR += col.getRed();
                sumG += col.getGreen();
                sumB += col.getBlue();
            }
        }

        int n2 = colors.length * colors.length;

        return new Color(sumR / n2, sumG / n2, sumB / n2).getRGB();
    }
}
