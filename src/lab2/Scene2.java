package lab2;

import GUI.controllers.LabTwoController;

import java.util.ArrayList;
import java.util.List;

public class Scene2 {
    private double step = 0.01d;
    public List<Double> x = new ArrayList<>();
    public List<Double> y = new ArrayList<>();

    private List<Double> xTemp;
    private List<Double> yTemp;

    public List<Double> curveX = new ArrayList<>();
    public List<Double> curveY = new ArrayList<>();

    private List<Object[]> XY = new ArrayList<>();

    public static LabTwoController labTwoController;

    private void recalculateBezier() {
        int n = x.size() - 1;
        xTemp = new ArrayList<>(x);
        yTemp = new ArrayList<>(y);
        curveY = new ArrayList<>();
        curveX = new ArrayList<>();

        if (x.size() > 4) {
            addMiddlePoints();
        }
        if (x.size() % 2 != 0) {
            xTemp.add(xTemp.get(xTemp.size() - 1));
            yTemp.add(yTemp.get(yTemp.size() - 1));
        }

        for (int i = 0; i < xTemp.size() - 3; i += 3) {
            if (xTemp.size() > 3) {
                for (double t = 0; t < 1; t += step) {
                    double xCurvePoint = Math.pow((1 - t), 3) * xTemp.get(i) + 3 * t * Math.pow((1 - t), 2) * xTemp.get(i + 1) + 3 * Math.pow(t, 2) * (1 - t) * xTemp.get(i + 2) + Math.pow(t, 3) * xTemp.get(i + 3);
                    double yCurvePoint = Math.pow((1 - t), 3) * yTemp.get(i) + 3 * t * Math.pow((1 - t), 2) * yTemp.get(i + 1) + 3 * Math.pow(t, 2) * (1 - t) * yTemp.get(i + 2) + Math.pow(t, 3) * yTemp.get(i + 3);
                    curveX.add(xCurvePoint);
                    curveY.add(yCurvePoint);
                }
            }
        }
    }

    private void addMiddlePoints() {
        for (int i = 2; i < xTemp.size() - 2; i += 3) {
            newMidPoint(i, i + 1);
        }
    }

    private void newMidPoint(int index1, int index2) {
        double xMid = Math.abs(xTemp.get(index1) - xTemp.get(index2)) / 2 +
                Math.min(xTemp.get(index1), xTemp.get(index2));
        double yMid = Math.abs(yTemp.get(index1) - yTemp.get(index2)) / 2 +
                Math.min(yTemp.get(index1), yTemp.get(index2));

        xTemp.add(index2, xMid);
        yTemp.add(index2, yMid);
    }

    public void addPoint(double x, double y) {
        this.x.add(x);
        this.y.add(y);
        if (this.x.size() > 3) {
            recalculateBezier();
        }
        labTwoController.refreshData(x, y);
        Object[] objects = new Object[2];
        objects[0] = x;
        objects[1] = y;
        XY.add(objects);
    }
}
