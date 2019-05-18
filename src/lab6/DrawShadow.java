package lab6;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lab5.Point;
import lab5.Polygon;

public class DrawShadow {
    public static void drawShadow(Point light, Polygon... polygons) {
        List<Point> pts = new ArrayList<>();
        for (Polygon p : polygons) {
            List<Point> temp = p.getPoints();

            for (Point point : temp) {
                double k = (light.getY() - point.getY()) / (light.getX() - point.getX());
                double b = -(k * light.getX()) + light.getY();

                double x = b / k;

                k = (light.getY() - point.getY()) / (light.getZ() - point.getZ());
                b = -(k * light.getZ()) + light.getY();
                double z = b / k;
                pts.add(new Point(x, 0, z));
            }

            Polygon shadow = new Polygon(pts);
            shadow.drawPolygon(0.5f, 0.5f, 0.5f);
            pts = new ArrayList<>();
        }
    }
}
