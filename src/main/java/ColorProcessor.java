import java.awt.*;
import java.util.Arrays;

class ColorProcessor {

    private final Color[][] colors;

    ColorProcessor(Color[][] colors) {
        this.colors = colors;
    }

    Color getAverageColor() {
        int[] R = new int[colors.length * colors.length];
        int[] G = new int[colors.length * colors.length];
        int[] B = new int[colors.length * colors.length];

        int index = 0;
        for (Color[] color : colors) {
            for (Color aColor : color) {
                R[index] = aColor.getRed();
                G[index] = aColor.getGreen();
                B[index] = aColor.getBlue();
                index++;
            }
        }

        Arrays.sort(R);
        Arrays.sort(G);
        Arrays.sort(B);

        return new Color(R[R.length / 2], G[G.length / 2], B[B.length / 2]);
    }
}
