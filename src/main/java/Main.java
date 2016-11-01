import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AveragingFilter averagingFilter = new AveragingFilter(args[0], args[2]);
        averagingFilter.filter();
        averagingFilter.writeImg(args[1]);
    }
}
