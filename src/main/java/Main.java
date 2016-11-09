import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: [source] [destination] [n]");
            System.exit(0);
        }

        AveragingFilter averagingFilter = new AveragingFilter(args[0], args[2]);
        averagingFilter.filter();
        averagingFilter.writeImg(args[1]);
    }
}
