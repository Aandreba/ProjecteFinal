package OpenGL;

import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Input.AndrebaAlex_Input;
import OpenGL.Light.AndrebaAlex_DirectionalLight;
import OpenGL.Light.AndrebaAlex_PointLight;
import OpenGL.Light.AndrebaAlex_SpotLight;
import OpenGL.Shaders.AndrebaAlex_Shader;
import Units.AndrebaAlex_Time;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.awt.*;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public abstract class AndrebaAlex_Window extends ArrayList<AndrebaAlex_GameObject> implements Runnable {
    final public long id;
    final public String title;
    final public AndrebaAlex_Input input;
    private AndrebaAlex_Camera mainCamera;

    private int width, height;
    private boolean resized, vSync;
    private Color bkgColor;
    public AndrebaAlex_Shader shader;
    public AndrebaAlex_StatVector3 gravity = new AndrebaAlex_StatVector3(0, -9.81, 0);

    public AndrebaAlex_PointLight[] points = new AndrebaAlex_PointLight[5];
    public AndrebaAlex_DirectionalLight[] directionals = new AndrebaAlex_DirectionalLight[5];
    public AndrebaAlex_SpotLight[] spots = new AndrebaAlex_SpotLight[5];

    public AndrebaAlex_Window(String title, int width, int height, boolean vSync) throws Exception {
        super();

        this.title = title;
        this.width = width;
        this.height = height;
        this.resized = false;
        this.vSync = vSync;
        this.mainCamera = new AndrebaAlex_Camera();

        init();

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        this.id = glfwCreateWindow(width, height, title, NULL, NULL);

        if (this.id == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup resize callback
        glfwSetFramebufferSizeCallback(this.id, (window, w, h) -> {
            this.width = w;
            this.height = h;
            this.resized = true;

            mainCamera.setWindow(this);
        });

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(this.id, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
        });

        this.input = new AndrebaAlex_Input(this);
        this.pushFrame();
        this.shader = new AndrebaAlex_Shader();
    }

    public static void init () throws IllegalStateException {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
    }

    @Override
    public boolean add (AndrebaAlex_GameObject gameObject) {
        gameObject.window = this;
        return super.add(gameObject);
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof AndrebaAlex_GameObject && super.remove(o)) {
            AndrebaAlex_GameObject object = (AndrebaAlex_GameObject) o;
            object.window = null;
            return true;
        }

        return false;
    }

    public void pushFrame () {
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(this.id, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    this.id,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(this.id);

        // Enable v-sync
        if (this.vSync) {
            glfwSwapInterval(1);
        }

        // Make the window visible
        glfwShowWindow(this.id);

        GL.createCapabilities();
        //glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public abstract void start ();

    public abstract void update (AndrebaAlex_Time deltaTime);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getAspectRatio () {
        return width * 1f / height;
    }

    public boolean isResized() {
        return resized;
    }

    public void setResized(boolean resized) {
        this.resized = resized;
    }

    public void setVSync(boolean vSync) {
        this.vSync = vSync;
    }

    public boolean isVSync() {
        return vSync;
    }

    public Color getBkgColor() {
        return bkgColor;
    }

    public void setBackgroundColor (Color color) {
        this.bkgColor = color;
        glClearColor(this.bkgColor.getRed() / 255f,this.bkgColor.getGreen() / 255f,this.bkgColor.getBlue() / 255f,this.bkgColor.getAlpha() / 255f);
    }

    public AndrebaAlex_Camera getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera (AndrebaAlex_Camera mainCamera) {
        this.mainCamera = mainCamera;
        this.mainCamera.setWindow(this);
    }

    public boolean windowShouldClose () {
        return glfwWindowShouldClose(this.id);
    }

    public void render (AndrebaAlex_Time delta) {
        if (isResized()) {
            glViewport(0, 0, getWidth(), getHeight());
            setResized(false);
        }

        shader.bind();

        shader.setUniform("project", mainCamera.getProjectionMatrix());
        shader.setUniform("view", mainCamera.view);
        shader.setUniform("textureSampler", 0);

        for (int i = 0; i< points.length; i++) {
            if (points[i] != null) {
                points[i].setAsUniform("points", i, shader);
            }

            if (directionals[i] != null) {
                directionals[i].setAsUniform("directionals", i, shader);
            }

            if (spots[i] != null) {
                spots[i].setAsUniform("spots", i, shader);
            }
        }

        for (AndrebaAlex_GameObject object: this) {
            object.render();
        }

        shader.unbind();
    }

    public void updateFrame () {
        glfwSwapBuffers(this.id);
        glfwPollEvents();
    }

    public void clear () {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void loop () {
        long lastTime = System.nanoTime();
        AndrebaAlex_Time deltaTime;

        boolean running = true;
        while (running && !windowShouldClose()) {
            long thisTime = System.nanoTime();
            deltaTime = new AndrebaAlex_Time(thisTime - lastTime, AndrebaAlex_Time.Type.Nanoseconds);

            clear();
            input.update();
            update(deltaTime);
            render(deltaTime);

            updateFrame();
            lastTime = thisTime;
        }
    }

    @Override
    public void run() {
        try {
            start();
            loop();
        } catch (Exception e) {
            e.printStackTrace();
            cleanup();
        } finally {
            cleanup();
        }
    }

    public void cleanup () {
        this.shader.cleanup();

        for (AndrebaAlex_GameObject object: this) {
            object.cleanup();
        }
    }
}
