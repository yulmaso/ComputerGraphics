package lab5;

import GUI.controllers.LabFiveController;
import ThreadHandler.AppRoot;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class AppRoot5 extends AppRoot implements Runnable {

    //private Settings settings = new Settings();
    //private Thread thread = new Thread(settings);
    private LabFiveController controller;

    // The window handle
//    private long window;

    public AppRoot5(LabFiveController controller){
        this.controller = controller;
    }

    public void run() {

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
//
//    public void stop() {
//        glfwDestroyWindow(window);
//
//        glfwTerminate();
//        glfwSetErrorCallback(null).free();
//    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(800, 800, "lab 5", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.97f, 0.97f, 0.97f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.


        while (!glfwWindowShouldClose(window)) {


            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glEnable(GL_DEPTH_TEST);

            Point viewPoint = new Point(1.2, 1.2, 0);


            Point p1 = new Point(0.2, 0.2, 0.2);
            Point p2 = new Point(0.2, 0.2, -0.2);
            Point p3 = new Point(0.2, -0.0, 0.2);
            Point p4 = new Point(-0.2, 0.2, 0.2);
            Point p5 = new Point(-0.2, -0.0, 0.2);
            Point p6 = new Point(-0.2, 0.2, -0.2);
            Point p7 = new Point(0.2, -0.0, -0.2);
            Point p8 = new Point(-0.2, -0.0, -0.2);

            PointsMatrix p = new PointsMatrix(p1, p2, p3, p4, p5, p6, p7, p8);

            try {
                p.rotateBy(controller.angleX, PointsMatrix.X_AXIS);
                p.rotateBy(controller.angleY, PointsMatrix.Y_AXIS);
             } catch (NoSuchAxisException e) {
                e.printStackTrace();
            }

            try {
                p = new PointsMatrix(p.getZProjectionMatrix());
            } catch (DimensionMismatchException e) {
                e.printStackTrace();
            }

            //glColor3f(0.97f, 0.97f, 0.97f);
            Polygon py1 = new Polygon(p.get(0), p.get(1), p.get(6), p.get(2));
            Polygon py2 = new Polygon(p.get(3), p.get(4), p.get(7), p.get(5));
            Polygon py3 = new Polygon(p.get(3), p.get(5), p.get(1), p.get(0));
            Polygon py4 = new Polygon(p.get(4), p.get(7), p.get(6), p.get(2));
            Polygon py5 = new Polygon(p.get(3), p.get(4), p.get(2), p.get(0));
            Polygon py6 = new Polygon(p.get(7), p.get(5), p.get(1), p.get(6));


            //glColor3f(0.0f, 0.0f, 0.0f);
            Polyhedron ph = new Polyhedron(py1, py2, py3, py4, py5, py6);

            ph.drawPolyhedron(controller.showLines());

            glFlush();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
}