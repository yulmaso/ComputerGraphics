package lab3;

public class Point implements Cloneable{
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
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
        Point point = (Point) obj;
        return (point.x == x && point.y == y && point.z == z);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }

    public Point clone() {
        return new Point(x, y, z);
    }
}
