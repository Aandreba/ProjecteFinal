package OpenGL.Extras.Move;

import OpenGL.Extras.Vector.AndrebaAlex_Vector3;
import OpenGL.AndrebaAlex_Transform;
import OpenGL.AndrebaAlex_Window;
import Units.AndrebaAlex_Time;

public abstract class AndrebaAlex_Movement {
    final public AndrebaAlex_Window window;
    final public AndrebaAlex_Transform transform;

    public AndrebaAlex_Movement(AndrebaAlex_Window window, AndrebaAlex_Transform transform) {
        this.window = window;
        this.transform = transform;
    }

    public AndrebaAlex_Movement(AndrebaAlex_Window window) {
        this (window, window.getMainCamera());
    }

    public abstract AndrebaAlex_Vector3 movementTranslate(AndrebaAlex_Time delta);
    public abstract void rotate (AndrebaAlex_Time delta);

    public void update (AndrebaAlex_Time delta) {
        rotate(delta);
        transform.translate(movementTranslate(delta));
    }
}
