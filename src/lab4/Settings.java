package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Settings {
    private Random random = new Random(System.currentTimeMillis());
    private List<Segment> segments;
    private static Scene4 window;

    public Settings(int n, double width, double height) {
        window = new Scene4(width, height);
        segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Point start = new Point(random.nextDouble()*2-1, random.nextDouble()*2-1);
            Point end = new Point(random.nextDouble()*2-1, random.nextDouble()*2-1);
            Segment segment = new Segment(start, end);
            segments.add(segment);
        }
    }

    public static Scene4 getWindow() {
        return window;
    }

    public List<Segment> getSegments() {
        return segments;
    }
}
