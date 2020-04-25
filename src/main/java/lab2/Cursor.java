package lab2;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import java.util.ArrayList;

public class Cursor extends GLFWCursorPosCallback {
    public ArrayList<Double> x = new ArrayList<>();
    public ArrayList<Double> y = new ArrayList<>();

    public double currentX;
    public double currentY;

    public Cursor() {
    }

    public void invoke(long window, double x, double y) {
        this.currentX = x;
        this.currentY = y;
    }
}
