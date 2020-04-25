package lab5;

import static org.lwjgl.opengl.GL11.*;

public class Segment {
    public static final double EPS = 0.001;
    private Point p1;
    private Point p2;
    private double depth;

    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.depth = Math.min(p1.getDepth(), p2.getDepth());
    }

    public Point getStartPoint() {
        return p1;
    }

    public Point getEndPoint() {
        return p2;
    }

    public void drawSegment() {
        glLineWidth(2);
        glBegin(GL_LINE_STRIP);
        glVertex3d(p1.getX(), p1.getY(), p1.getDepth());
        glVertex3d(p2.getX(), p2.getY(), p2.getDepth());
        glEnd();
        glLineWidth(1);
    }

    public static Point intersection(Point a, Point b, Point c, Point d) throws SegmentDoesntContainPointException {
        double x11 = a.getX();
        double x12 = b.getX();
        double y11 = a.getY();
        double y12 = b.getY();
        double x21 = c.getX();
        double x22 = d.getX();
        double y21 = c.getY();
        double y22 = d.getY();
        double a1 = y11 - y12;
        double a2 = y21 - y22;
        double b1 = x12 - x11;
        double b2 = x22 - x21;
        double dd = a1 * b2 - a2 * b1;

        if (!(0 + EPS > dd && 0 - EPS < dd)) {
            double c1 = y12 * x11 - x12 * y11;
            double c2 = y22 * x21 - x22 * y21;
            double x = (b1 * c2 - b2 * c1) / dd;
            double y = (a2 * c1 - a1 * c2) / dd;
            Segment s1 = new Segment(a, b);
            Segment s2 = new Segment(c, d);
            Point p = new Point(x, y, 0, true);
            if (s1.containsPoint(p) && s2.containsPoint(p)) {
                return p;
            }
        }
        throw new SegmentDoesntContainPointException("This segments doesn't have an intersection point");
    }

    public boolean containsPoint(Point p) {
        boolean isXOk = p.getX() > Math.min(p1.getX(), p2.getX()) && p.getX() < Math.max(p1.getX(), p2.getX());
        boolean isYOk = p.getY() > Math.min(p1.getY(), p2.getY()) && p.getY() < Math.max(p1.getY(), p2.getY());
        boolean isZOk = p.getX() > Math.min(p1.getZ(), p2.getZ()) && p.getZ() < Math.max(p1.getZ(), p2.getZ());
        double s = (p.getX() - this.p1.getX())*(this.p2.getY()-this.p1.getY())-(p.getY()-this.p1.getY())*(this.p2.getX()-this.p1.getX());
        boolean isOK = 0 - EPS <= s && s <= 0 + EPS;
        return isXOk && isYOk && isZOk && isOK;
    }

    public static Point intersection(Segment a, Segment b) throws SegmentDoesntContainPointException {
        return intersection(a.p1, a.p2, b.p1, b.p2);
    }

    public Point intersect(Segment s2) throws SegmentDoesntContainPointException {
        return intersection(this, s2);
    }

    @Override
    public String toString() {
        return ("[" + p1 + "][" + p2 + "]");
    }

    @Override
    public int hashCode() {
        return p1.hashCode() * 10000000 + p2.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Segment s = (Segment) obj;
            if ((s.p1.equals(this.p1) && (s.p2.equals(this.p2)) || (s.p2.equals(this.p1) && s.p1.equals(this.p2)))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

class SegmentDoesntContainPointException extends Exception {
    public SegmentDoesntContainPointException(String errorMessage) {
        super(errorMessage);
    }
}
