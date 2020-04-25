package ThreadHandler;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public abstract class AppRoot implements Runnable {
    protected long window;

    public void stop() {

        glfwSetWindowShouldClose(window, true);
        
        //

        //glfwFreeCallbacks(window);
        //glfwDestroyWindow(window);

        //glfwTerminate();
        //glfwSetErrorCallback(null).free();
    }
}
