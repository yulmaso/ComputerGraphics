package lab3;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

public class Input {
    public boolean changed = false;

    private Point p1 = new Point(0.3, 0.1, 0.6);
    private Point p2 = new Point(0.25, 0.1, 0.1);
    private Point p3 = new Point(0.5, 0.3, 0.7);
    private Point p4 = new Point(0.75, 0.1, 0.1);

    private Point p5 = new Point(0, 0.25, 0.25);
    private Point p6 = new Point(0.25, 0.75, 0.25);
    private Point p7 = new Point(0.5, 0.75, 0.25);
    private Point p8 = new Point(0.75, 0.25, 0.25);

    private Point p9 = new Point(0.3, 0.5, 0.5);
    private Point p10 = new Point(0.25, 0.2, 0.5);
    private Point p11 = new Point(0.7, 0.5, 0.15);
    private Point p12 = new Point(0.75, 0.3, 0.5);

    private Point p13 = new Point(0, 0.0, 0.75);
    private Point p14 = new Point(0.25, 0.0, 0.75);
    private Point p15 = new Point(0.5, 0.0, 0.75);
    private Point p16 = new Point(0.75, 0.0, 0.75);

    private Point p17 = new Point(0.3, 0.7, 1);
    private Point p18 = new Point(0.25, 0.7, 1);
    private Point p19 = new Point(0.5, 0.7, 1);
    private Point p20 = new Point(0.75, 0.7, 1);

    public Point[][] points = {
            {p1, p2, p3, p4},
            {p5, p6, p7, p8},
            {p9, p10, p11, p12},
            {p13, p14, p15, p16},
            {p17, p18, p19, p20}};
}
