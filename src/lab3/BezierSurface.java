package lab3;

public class BezierSurface {
    private double step = 0.01d;
    private Point[][] controlPoints;
    private Point[][] surfacePoints;
    private int width;
    private int height;

    public BezierSurface(Point[][] controlPoints) {
        this.controlPoints = controlPoints;
        height = controlPoints.length;
        width = controlPoints[0].length;
        if (width > 3 && height > 3) {
            calculateSurfacePoints();
        }
    }

    private int fact(int a) {
        return (a > 1) ? a * fact(a - 1) : 1;
    }

    private double bernstein(int n, int i, double u) {
        return fact(n) / (fact(i) * fact(n - i)) * Math.pow(u, i) * Math.pow((1 - u), (n - i));
    }

    private void calculateSurfacePoints() {
        Point[][] points = new Point[(int) (1 / step)][(int) (1 / step)];

        for (int ui = 0; ui < 100; ui++) {
            for (int vi = 0; vi < 100; vi++) {
                Point sum = new Point(0, 0, 0);
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        double bern = bernstein(height - 1, i, (double)ui/100) * bernstein(width - 1, j, (double)vi/100);
                        double x = controlPoints[i][j].getX() * bern;
                        double y = controlPoints[i][j].getY() * bern;
                        double z = controlPoints[i][j].getZ() * bern;
                        sum.setX(sum.getX() + x);
                        sum.setY(sum.getY() + y);
                        sum.setZ(sum.getZ() + z);
                    }
                }
                points[ui][vi] = sum;
            }
        }
        surfacePoints = points;
    }
    public Point[][] getPoints() {
        return surfacePoints;
    }
}
