package lab3;

import GUI.controllers.LabThreeController;
import ThreadHandler.AppRoot;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class AppRoot3 extends AppRoot implements Runnable {

    public static Point[][] pointsArr;

//    private long window;

    private LabThreeController controller;

    public AppRoot3(LabThreeController controller){
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
//    public void stop(){
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
        window = glfwCreateWindow(800, 800, "Lab 3", NULL, NULL);
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

        BezierSurface bz = new BezierSurface(controller.input.points);
        Point[][] bzp = bz.getPoints();

        while (!glfwWindowShouldClose(window)) {

            FloatBuffer fb = BufferUtils.createFloatBuffer(16);
            Matrix4f m = new Matrix4f();
            m.setPerspective((float) Math.toRadians(10.0f), 1.0f, 0.01f, 1000.0f);
            glMatrixMode(GL_PROJECTION);
            glLoadMatrixf(m.get(fb));
            m.setLookAt(controller.xRotate, controller.yRotate, controller.zRotate,
                    0.0f, 0.0f, 0.0f,
                    0.0f, 1.0f, 0.0f);
            glMatrixMode(GL_MODELVIEW);
            glLoadMatrixf(m.get(fb));

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glEnable(GL_DEPTH_TEST);

            glBegin(GL_LINE_STRIP);
            glColor3f(0.0f,0.0f,0.0f);
            glVertex3d(0.0,0.0,0.0);
            glVertex3d(10,0,0);
            glEnd();

            glBegin(GL_LINE_STRIP);
            glColor3f(0.0f,0.0f,0.0f);
            glVertex3d(0.0,0.0,0.0);
            glVertex3d(0,10,0);
            glEnd();

            glBegin(GL_LINE_STRIP);
            glColor3f(0.0f,0.0f,0.0f);
            glVertex3d(0.0,0.0,0.0);
            glVertex3d(0,0,10);
            glEnd();

            for (int i = 0; i < controller.input.points.length; i++) {
                glBegin(GL_LINE_STRIP);
                glColor3f(0.0f, 0.0f, 0.0f);
                for (int j = 0; j < controller.input.points[0].length; j++) {
                    glVertex3d(controller.input.points[i][j].getX(), controller.input.points[i][j].getY(), controller.input.points[i][j].getZ());

                }
                glEnd();
            }

            for (int j = 0; j < controller.input.points[0].length; j++) {
                glBegin(GL_LINE_STRIP);
                glColor3f(0.0f, 0.0f, 0.0f);
                for (int i = 0; i < controller.input.points.length; i++) {
                    glVertex3d(controller.input.points[i][j].getX(), controller.input.points[i][j].getY(), controller.input.points[i][j].getZ());

                }
                glEnd();
            }

            for (int i = 0; i < bzp.length; i++) {
                glBegin(GL_LINE_STRIP);
                glColor3f(1.0f, 0.0f, 0.0f);
                for (int j = 0; j < bzp[0].length; j++) {
                    glVertex3d(bzp[i][j].getX(), bzp[i][j].getY(), bzp[i][j].getZ());
                }
                glEnd();
            }

            for (int j = 0; j < bzp[0].length; j++) {
                glBegin(GL_LINE_STRIP);
                glColor3f(1.0f, 0.0f, 0.0f);
                for (int i = 0; i < bzp.length; i++) {
                    glVertex3d(bzp[i][j].getX(), bzp[i][j].getY(), bzp[i][j].getZ());
                }
                glEnd();
            }

            glFlush();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
}
