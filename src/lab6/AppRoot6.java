package lab6;

import GUI.controllers.LabSixController;
import ThreadHandler.AppRoot;
import lab5.Point;
import lab5.Polygon;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class AppRoot6 extends AppRoot implements Runnable {

    // позиция источника света
    private float light_position[] = {31.84215f, 36.019997f, 28.262873f, 1.0f};
    // цвет фонового света
    private float light_ambient[] = { 0.0f, 0.0f, 0.3f, 1.0f };
    // настройка материалов
    private float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
    private float mat_diffuse[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    private void rotationCamera() {
        // вращение
        GL11.glRotatef(controller.xRotate, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(controller.yRotate, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(controller.zRotate, 0.0f, 0.0f, 1.0f);
    }

    private LabSixController controller;

    // The window handle

    public AppRoot6(LabSixController controller){
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
        window = glfwCreateWindow(800, 800, "Lab 6", NULL, NULL);
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

        ByteBuffer temp = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder());


        while ( !glfwWindowShouldClose(window)) {

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

            Point p1 = new Point(0.2, 0.2, 0.2);
            Point p2 = new Point(0.2, 0.2, -0.2);
            Point p3 = new Point(0.2, -0.0, 0.2);
            Point p4 = new Point(-0.2, 0.2, 0.2);
            Point p5 = new Point(-0.2, -0.0, 0.2);
            Point p6 = new Point(-0.2, 0.2, -0.2);
            Point p7 = new Point(0.2, -0.0, -0.2);
            Point p8 = new Point(-0.2, -0.0, -0.2);


            Polygon py1 = new Polygon(p1, p2, p7, p3);
            Polygon py2 = new Polygon(p4, p5, p8, p6);
            Polygon py3 = new Polygon(p4, p6, p2, p1);
            Polygon py4 = new Polygon(p5, p8, p7, p3);
            Polygon py5 = new Polygon(p4, p5, p3, p1);
            Polygon py6 = new Polygon(p8, p6, p2, p7);


            py1.drawPolygon(1.0f, 0.0f, 0.0f);
            py2.drawPolygon(0.0f, 1.0f, 0.0f);
            py3.drawPolygon(0.0f, 1.0f, 1.0f);
            py4.drawPolygon(0.0f, 0.0f, 1.0f);
            py5.drawPolygon(1.0f, 0.0f, 1.0f);
            py6.drawPolygon(1.0f, 1.0f, 0.0f);

            DrawShadow.drawShadow(new Point(controller.xLight,controller.yLight,controller.zLight), py1, py2, py3, py4, py5, py6);


            glFlush();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
}