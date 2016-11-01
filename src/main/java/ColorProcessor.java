import java.awt.*;
import java.util.Arrays;

class ColorProcessor {

    private final Color[][] colors;

    ColorProcessor(Color[][] colors) {
        this.colors = colors;
    }

    int getAverageRed() {
        int[] R = new int[colors.length * colors.length];
        int indexR = 0;
        for (Color[] color : colors)
            for (Color aColor : color)
                R[indexR++] = aColor.getRed();

        Arrays.sort(R);

        return R[R.length / 2];
    }

    int getAverageBlue() {
        int[] B = new int[colors.length * colors.length];
        int indexR = 0;
        for (Color[] color : colors)
            for (Color aColor : color)
                B[indexR++] = aColor.getBlue();

        Arrays.sort(B);

        return B[B.length / 2];
    }

    int getAverageGreen() {
        int[] G = new int[colors.length * colors.length];
        int indexR = 0;
        for (Color[] color : colors)
            for (Color aColor : color)
                G[indexR++] = aColor.getGreen();

        Arrays.sort(G);

        return G[G.length / 2];
    }
}
