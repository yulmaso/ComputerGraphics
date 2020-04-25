package lab5;

import java.util.*;


public class Polyhedron{
    private List<Segment> edges;
    private List<Polygon> polygons;
    private List<Polygon> resultPolygons;

    public Polyhedron(Polygon... polygons) {
        this.polygons = Arrays.asList(polygons);
        this.edges = new ArrayList<>();
        for (Polygon p : polygons) {
            for (Segment s : p.getEdges()) {
                edges.add(s);
            }
        }
    }

    public void drawPolyhedron(boolean v) {
        if (v) {
            visibleEdges();
        } else {
            for (Polygon p : polygons) {
                p.drawPolygon();
            }
        }
    }

    private void visibleEdges() {
        List<Polygon> sorted = new ArrayList<>();
        resultPolygons = new ArrayList<>();

        for (Polygon s : polygons) {
            sorted.add(s);
            s.drawPolygon();
            s.drawPolygn();
        }

        sorted.sort((a, b) -> (Double.compare(a.getDepth(), b.getDepth())));
        for (int k = 0; k < sorted.size() - 1; k++) {
            Polygon p1 = sorted.get(k%sorted.size());
            for (int l = k + 1; l < sorted.size(); l++) {
                Polygon p2 = sorted.get((l)%sorted.size());
                Polygon i1 = p1.intersectionsWith(p2);
                if (i1.getPoints().size() != p2.getPoints().size()){
                    sorted.remove((l)%sorted.size());
                    sorted.add((l)%sorted.size(),i1);
                }
                List<Point> drawPoints = new ArrayList<>();
                Point[] p = new Point[drawPoints.size()];
                p = drawPoints.toArray(p);
                resultPolygons.add(new Polygon(drawPoints.toArray(p)));
            }
        }
    }
}
