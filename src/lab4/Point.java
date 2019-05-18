package lab4;


public class Point implements Cloneable {
    private double x;
    private double y;

    private int setCode() {
        double maxX = Settings.getWindow().getMaxX();
        double maxY = Settings.getWindow().getMaxY();
        int b0 = 0;
        int b1 = 0;
        int b2 = 0;
        int b3 = 0;

        if(x >= -maxX){
            b0 = 0;
        } else {
            b0 = 1;
        }

        if(x <= maxX){
            b1 = 0;
        } else {
            b1 = 1;
        }

        if(y >= -maxY){
            b2 = 0;
        } else {
            b2 = 1;
        }

        if(y <= maxY){
            b3 = 0;
        } else {
            b3 = 1;
        }

        return b0*1000 + b1*100 + b2*10 + b3;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getCode() {
        return setCode();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object obj) {
        Point point = (Point) obj;
        return (point.x == x && point.y == y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public Point clone() {
        return new Point(x, y);
    }
}
