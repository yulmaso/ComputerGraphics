package lab4;

public class Scene4 {
    private double width;
    private double height;
    private double minSegSize = 0.0001;

    public Scene4(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public boolean isInside(Segment s) {
        if (s.getStartPoint().getCode() == s.getEndPoint().getCode()) {
            if (s.getStartPoint().getCode() == 0)
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    public Segment insidePart(Segment s) {
        if (isInside(s)) {
            return s;
        } else {
            if (Double.compare(s.getLength(),minSegSize) <= 0 ){
                return null;
            } else {
                Segment h1 = new Segment(s.getStartPoint(), s.getMiddlePoint());
                Segment h2 = new Segment(s.getMiddlePoint(), s.getEndPoint());
                Segment s1 = insidePart(h1);
                Segment s2 = insidePart(h2);
                if(s1 != null && s2 != null){
                    return new Segment(s1.getStartPoint(), s2.getEndPoint());
                } else {
                    if(s1 == null){
                        return s2;
                    } else {
                        return s1;
                    }
                }
            }
        }
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMaxX() {
        return width;
    }

    public double getMaxY() {
        return height;
    }
}
