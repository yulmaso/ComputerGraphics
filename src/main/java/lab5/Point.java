package lab5;

import java.util.Objects;

public class Point implements Cloneable, Comparable {
    private double x;
    private double y;
    private double z;
    private boolean intersection = false;
    private double depth;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.depth = -z;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.depth = -z;
    }

    public void setDepth(double depth) {
        this.depth = -depth;
    }

    public double getDepth() {
        return depth;
    }

    public Point(double x, double y, double z, boolean intersection) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.depth = -z;
        this.intersection = intersection;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.nonNull(obj)) {
            Point point = (Point) obj;
            return (point.x == x && point.y == y && point.z == z && point.isIntersection() == intersection);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) (100000 * x) + (int) (100 * y);
    }

    @Override
    public String toString() {
        return "[" + x + " " + y + " " + z + " " + intersection + "]";
    }

    public Point clone() {
        return new Point(x, y, z);
    }

    public boolean isIntersection() {
        return intersection;
    }

    public Point(double[] coordVector) {
        this.x = coordVector[0];
        this.y = coordVector[1];
        this.z = coordVector[2];
    }

    public double toPolar(Point middlePoint) {
        return Math.atan2(y - middlePoint.getY() ,x - middlePoint.getX() );
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}