package GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DualTableContainer {
    private StringProperty first;
    private StringProperty second;

    public DualTableContainer(String first, String second){
        this.first = new SimpleStringProperty(first);
        this.second = new SimpleStringProperty(second);
    }

    public String getY() {
        return second.get();
    }

    public StringProperty yProperty() {
        return second;
    }

    public void setY(String y) {
        this.second.set(y);
    }

    public String getX() {
        return first.get();
    }

    public StringProperty xProperty() {
        return first;
    }

    public void setX(String x) {
        this.first.set(x);
    }
}
