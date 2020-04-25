package lab5;

import org.lwjgl.opengl.GL11;

import java.util.*;
import java.util.stream.Collectors;

import static org.lwjgl.opengl.GL11.*;

public class Polygon {
    private List<Segment> edges;
    private double depth = Double.MAX_VALUE;

    public double getDepth() {
        return depth;
    }

    public Polygon(Point... points) {
        this.edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            this.edges.add(new Segment(points[i % points.length], points[(i + 1) % points.length]));
            if (points[i % points.length].getZ() < this.depth) {
                this.depth = points[i % points.length].getDepth();
            }
            if (points[(i + 1) % points.length].getZ() < this.depth) {
                this.depth = points[(i + 1) % points.length].getDepth();
            }
        }
    }

    public Polygon(List<Point> p) {
        this.edges = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            this.edges.add(new Segment(p.get(i % p.size()), p.get((i + 1) % p.size())));
            if (p.get(i % p.size()).getZ() < this.depth) {
                this.depth = p.get(i % p.size()).getDepth();
            }
            if (p.get((i + 1) % p.size()).getZ() < this.depth) {
                this.depth = p.get((i + 1) % p.size()).getDepth();
            }
        }
    }

    public List<Segment> getEdges() {
        return edges;
    }

    public Polygon intersectionsWith(Polygon b) {
        List<Point> ps1 = this.getPoints();
        ps1.sort((a, c) -> (-Double.compare(a.toPolar(getMiddlePoint()), c.toPolar(getMiddlePoint()))));
        Collections.sort(ps1, Collections.reverseOrder());

        List<Point> ps2 = b.getPoints();
        ps2.sort((a, c) -> (Double.compare(a.toPolar(getMiddlePoint()), c.toPolar(getMiddlePoint()))));
        Collections.sort(ps2, Collections.reverseOrder());


        for (int i = 0; i < ps1.size() - 1; i++) {
            Segment s1 = new Segment(ps1.get(i), ps1.get(i + 1));
            for (int j = 0; j < ps2.size() - 1; j++) {
                Segment s2 = new Segment(ps2.get(j), ps2.get(j + 1));

                try {
                    Point p = s1.intersect(s2);
                    ps1.add(i, p);
                    ps2.add(j, p);
                    i++;
                    j++;
                } catch (SegmentDoesntContainPointException e) {

                }
            }
        } //Получили списки точек полигонов с точками пересечений

        List<Point> insidePoints = new ArrayList<>();
        List<Point> outsidePoints = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (insidePoints.size() + outsidePoints.size() < ps1.size() + ps2.size()) {
            outsidePoints.add(ps1.get(i % ps1.size()));
            if (ps1.get(i % ps1.size()).isIntersection()) {
                j = ps2.indexOf(ps1.get(i % ps1.size()));
                insidePoints.add(ps2.get(j % ps2.size()));
                j++;
                while (!ps2.get(j % ps2.size()).isIntersection()) {
                    insidePoints.add(ps2.get(j % ps2.size()));
                    j++;
                }
                i = ps1.indexOf(ps2.get(j % ps2.size()));
                outsidePoints.add(ps1.get(i % ps1.size()));
            }
            i++;
        }

        Point[] pts = new Point[insidePoints.size()];
        Polygon p = new Polygon(insidePoints.toArray(pts));
        pts = new Point[outsidePoints.size()];
        p = new Polygon(outsidePoints.toArray(pts));

        return p;
    }

    public void drawPolygon() {
        for (Segment s : edges) {
            s.drawSegment();
        }
    }

    public List<Point> getPoints() {
        List<Point> p = new ArrayList<>();
        for (Segment s : edges) {
            p.add(s.getStartPoint());
            p.add(s.getEndPoint());
        }
        return p;
    }

    public Point getMiddlePoint() {
        double x = 0;
        double y = 0;

        x = getPoints().stream().mapToDouble(a -> a.getX()).sum() / getPoints().size();
        y = getPoints().stream().mapToDouble(a -> a.getY()).sum() / getPoints().size();

        return new Point(x, y, 0);
    }

    public void drawPolygn() {
        glColor3f(0.97f, 0.97f, 0.97f);
        glBegin(GL_POLYGON);
        for (Point p : getPoints()) {
            glVertex3d(p.getX(), p.getY(), p.getDepth());
        }
        glEnd();
        glColor3f(0.0f, 0.0f, 0.0f);

    }

    public void drawPolygon(float red, float green, float blue) {

        glColor3f(red, green, blue);
        glBegin(GL_POLYGON);
        for (Point p : getPoints()) {
            glVertex3d(p.getX(), p.getY(), p.getZ());
        }
        glEnd();
        glColor3f(1.0f,1.0f, 1.0f);
    }
}
