package lab1;

import ThreadHandler.AppRoot;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class AppRoot1 extends AppRoot implements Runnable {

//    private long window;

    public void run(){
        //thread.start();

        init();
        loop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

//    public void stop(){
//        glfwDestroyWindow(window);
//
//        glfwTerminate();
//        glfwSetErrorCallback(null).free();
//    }

    public void init(){
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");



        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(800, 800, "Lab 1", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
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

    private void loop(){
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.97f, 0.97f, 0.97f, 0.0f);
        glRotated(45, 0, 1, 0);
        glRotated(-22.5, 1, 0, 1);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            //glRotated(5, 1, 1, 1);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glEnable(GL_DEPTH_TEST);

            glBegin(GL_LINES);
            glColor3f(0.0f, 0.0f, 0.0f);
            glVertex3d(Scene1.axis_default[0][0], Scene1.axis_default[0][1], Scene1.axis_default[0][2]);
            glVertex3d(Scene1.axis_default[1][0], Scene1.axis_default[1][1], Scene1.axis_default[1][2]);
            glEnd();

            glBegin(GL_LINES);
            glColor3f(0.0f, 0.0f, 0.0f);
            glVertex3d(Scene1.axis_default[0][0], Scene1.axis_default[0][1], Scene1.axis_default[0][2]);
            glVertex3d(Scene1.axis_default[2][0], Scene1.axis_default[2][1], Scene1.axis_default[2][2]);
            glEnd();

            glBegin(GL_LINES);
            glColor3f(0.0f, 0.0f, 0.0f);
            glVertex3d(Scene1.axis_default[0][0], Scene1.axis_default[0][1], Scene1.axis_default[0][2]);
            glVertex3d(Scene1.axis_default[3][0], Scene1.axis_default[3][1], Scene1.axis_default[3][2]);
            glEnd();

            glBegin(GL_POLYGON); // front
            glColor3f(0.0f, 0.0f, 1.0f);
            glVertex3d(Scene1.cube[0][0], Scene1.cube[0][1], Scene1.cube[0][2]);
            glVertex3d(Scene1.cube[1][0], Scene1.cube[1][1], Scene1.cube[1][2]);
            glVertex3d(Scene1.cube[2][0], Scene1.cube[2][1], Scene1.cube[2][2]);
            glVertex3d(Scene1.cube[3][0], Scene1.cube[3][1], Scene1.cube[3][2]);
            glEnd();

            glBegin(GL_POLYGON); // back
            glColor3f(1.0f, 1.0f, 0.0f);
            glVertex3d(Scene1.cube[4][0], Scene1.cube[4][1], Scene1.cube[4][2]);
            glVertex3d(Scene1.cube[5][0], Scene1.cube[5][1], Scene1.cube[5][2]);
            glVertex3d(Scene1.cube[6][0], Scene1.cube[6][1], Scene1.cube[6][2]);
            glVertex3d(Scene1.cube[7][0], Scene1.cube[7][1], Scene1.cube[7][2]);
            glEnd();

            glBegin(GL_POLYGON); // top
            glColor3f(1.0f, 0.0f, 1.0f);
            glVertex3d(Scene1.cube[8][0], Scene1.cube[8][1], Scene1.cube[8][2]);
            glVertex3d(Scene1.cube[9][0], Scene1.cube[9][1], Scene1.cube[9][2]);
            glVertex3d(Scene1.cube[10][0], Scene1.cube[10][1], Scene1.cube[10][2]);
            glVertex3d(Scene1.cube[11][0], Scene1.cube[11][1], Scene1.cube[11][2]);
            glEnd();

            glBegin(GL_POLYGON); // bottom
            glColor3f(1.0f, 0.0f, 0.0f);
            glVertex3d(Scene1.cube[12][0], Scene1.cube[12][1], Scene1.cube[12][2]);
            glVertex3d(Scene1.cube[13][0], Scene1.cube[13][1], Scene1.cube[13][2]);
            glVertex3d(Scene1.cube[14][0], Scene1.cube[14][1], Scene1.cube[14][2]);
            glVertex3d(Scene1.cube[15][0], Scene1.cube[15][1], Scene1.cube[15][2]);
            glEnd();

            glBegin(GL_POLYGON); // left
            glColor3f(0.0f, 1.0f, 1.0f);
            glVertex3d(Scene1.cube[16][0], Scene1.cube[16][1], Scene1.cube[16][2]);
            glVertex3d(Scene1.cube[17][0], Scene1.cube[17][1], Scene1.cube[17][2]);
            glVertex3d(Scene1.cube[18][0], Scene1.cube[18][1], Scene1.cube[18][2]);
            glVertex3d(Scene1.cube[19][0], Scene1.cube[19][1], Scene1.cube[19][2]);
            glEnd();

            glBegin(GL_POLYGON); // right
            glColor3f(0.0f, 1.0f, 0.0f);
            glVertex3d(Scene1.cube[20][0], Scene1.cube[20][1], Scene1.cube[20][2]);
            glVertex3d(Scene1.cube[21][0], Scene1.cube[21][1], Scene1.cube[21][2]);
            glVertex3d(Scene1.cube[22][0], Scene1.cube[22][1], Scene1.cube[22][2]);
            glVertex3d(Scene1.cube[23][0], Scene1.cube[23][1], Scene1.cube[23][2]);
            glEnd();

            glFlush();
            glfwSwapBuffers(window); // swap the color buffers
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }

    }
}
