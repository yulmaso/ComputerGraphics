package lab4;

public class Segment {
    private Point p1;
    private Point p2;

    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getStartPoint() {
        return p1;
    }

    public Point getEndPoint() {
        return p2;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    public Point getMiddlePoint() {
        return new Point(Math.min(p1.getX(), p2.getX()) + Math.abs(p1.getX() - p2.getX()) / 2, Math.min(p1.getY(), p2.getY()) + Math.abs(p1.getY() - p2.getY()) / 2);
    }
}
