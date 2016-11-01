import java.awt.*;
import java.util.Arrays;

class ColorProcessor {

    private final Color[] colors;

    ColorProcessor(Color[] colors) {
        this.colors = colors;
    }

    int getAverageRed() {
        int[] R = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            R[i] = colors[i].getRed();
        }

        Arrays.sort(R);

        return R[colors.length / 2];
    }

    int getAverageBlue() {
        int[] B = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            B[i] = colors[i].getBlue();
        }

        Arrays.sort(B);

        return B[colors.length / 2];
    }

    int getAverageGreen() {
        int[] G = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            G[i] = colors[i].getGreen();
        }

        Arrays.sort(G);

        return G[colors.length / 2];
    }
}
