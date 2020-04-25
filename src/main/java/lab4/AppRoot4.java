package lab4;

import GUI.controllers.LabFourController;
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

public class AppRoot4 extends AppRoot implements Runnable {

//    private long window;

    private static LabFourController controller;

    public AppRoot4(LabFourController controller){
        this.controller = controller;
    }

    //UtilityWindow controller = new UtilityWindow();

    // The window handle

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

//    public void stop(){
////        glfwDestroyWindow(window);
////
////        glfwTerminate();
////        glfwSetErrorCallback(null).free();
////    }

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
        window = glfwCreateWindow(800, 800, "Lab 4", NULL, NULL);
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


            glColor3f(0.0f, 0.0f, 0.0f);
            glBegin(GL_LINE_LOOP);
            glVertex2d(-controller.getWindow().getWidth(), controller.getWindow().getHeight());
            glVertex2d(controller.getWindow().getWidth(), controller.getWindow().getHeight());
            glVertex2d(controller.getWindow().getWidth(), -controller.getWindow().getHeight());
            glVertex2d(-controller.getWindow().getWidth(), -controller.getWindow().getHeight());
            glEnd();

            glColor3f(1.0f, 0.0f, 0.0f);
            //glLineWidth(2);
            for (Segment s : controller.getSegments()) {
                Segment segm = controller.getWindow().insidePart(s);
                if (segm != null) {
                    glBegin(GL_LINE_STRIP);
                    glVertex2d(segm.getStartPoint().getX(), segm.getStartPoint().getY());
                    glVertex2d(segm.getEndPoint().getX(), segm.getEndPoint().getY());
                    glEnd();
                }
            }

            glColor3f(0.0f, 0.0f, 0.0f);
            for (Segment s : controller.getSegments()) {
                glBegin(GL_LINE_STRIP);
                glVertex2d(s.getStartPoint().getX(), s.getStartPoint().getY());
                glVertex2d(s.getEndPoint().getX(), s.getEndPoint().getY());
                glEnd();
            }


            glFlush();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    /*public static void main(String[] args) {

        new AppRoot4().run();
    }*/

}