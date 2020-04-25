package lab5;

//import lab1.utils.graphics.Rotator;

import java.util.List;

public class PointsMatrix {
    private Point[] points;
    private static final double[][] Z_PROJECTION_MATRIX = {{1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 0.5},
            {0, 0, 0, 1}
    };
    public static final int X_AXIS = 1;
    public static final int Y_AXIS = 2;
    public static final int Z_AXIS = 3;

    public PointsMatrix(Point... array) {
        this.points = array;
    }

    private Point[] multiplyBy(double[][] a) throws DimensionMismatchException {
        if (canBeMultipliedBy(a)) {
            Point[] result = new Point[this.rows()];

            double[][] pnts = new double[this.rows()][4];
            double[][] res = new double[this.rows()][4];

            for (int i = 0; i < this.rows(); i++) {
                pnts[i][0] = points[i].getX();
                pnts[i][1] = points[i].getY();
                pnts[i][2] = points[i].getZ();
                pnts[i][3] = 1;
            }

            for (int i = 0; i < this.rows(); i++) {//Цикл по каждой строке новой матрицы
                for (int j = 0; j < a[0].length; j++) {// Цикл по каждому столбцу новой матрицы
                    double sum = 0;
                    for (int k = 0; k < 4; k++) {
                        sum += pnts[i][k] * a[k][j];
                    }
                    res[i][j] = sum;
                }
            }

            for (int i = 0; i < this.rows(); i++) {
                result[i] = new Point(res[i]);
                result[i].setDepth(pnts[i][2]);
            }

            return result;
        } else {
            throw new DimensionMismatchException("Cannot multiply " + this.getDimensions() + " matrix by rotational matrix");
        }
    }

    private boolean canBeMultipliedBy(double[][] a) {
        return a.length == 4;
    }

    private String getDimensions() {
        return points.length + "x" + 1;
    }

    private int rows() {
        return points.length;
    }

    public Point get(int index) {
        if (index < rows() && index >= 0) {
            return points[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void rotateBy(int angle, int axis) throws NoSuchAxisException {
        try {
            switch (axis) {
                case X_AXIS: {
                    points = this.multiplyBy(Rotator.makeXTransitionMatrix(angle));
                    break;
                }
                case Y_AXIS: {
                    points = this.multiplyBy(Rotator.makeYTransitionMatrix(angle));
                    break;
                }
                case Z_AXIS: {
                    points = this.multiplyBy(Rotator.makeZTransitionMatrix(angle));
                    break;
                }
                default:
                    throw new NoSuchAxisException("There is no such axis");
            }
        } catch (DimensionMismatchException e) {
            e.printStackTrace();
        }
    }
    public Point[] getZProjectionMatrix() throws DimensionMismatchException {
        return multiplyBy(Z_PROJECTION_MATRIX);
    }
    public int size(){
        return points.length;
    }
}

class DimensionMismatchException extends Exception {
    public DimensionMismatchException(String errorMessage) {
        super(errorMessage);
    }
}

class NoSuchAxisException extends Exception {
    public NoSuchAxisException(String em) {
        super(em);
    }
}
