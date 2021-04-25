package OpenGL.Extras.Move;

import Extras.AndrebaAlex_Mathx;
import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Extras.Vector.AndrebaAlex_Vector2;
import OpenGL.Extras.Vector.AndrebaAlex_Vector3;
import OpenGL.Input.Buttons.AndrebaAlex_KeyCode;
import OpenGL.AndrebaAlex_Transform;
import OpenGL.AndrebaAlex_Window;
import Units.AndrebaAlex_Time;

public class AndrebaAlex_KeyMouse extends AndrebaAlex_Movement {
    public AndrebaAlex_KeyCode forward, backward, right, left, up, down;
    public int accuracy = 3;
    public float speed;

    public AndrebaAlex_KeyMouse(AndrebaAlex_Window window, AndrebaAlex_Transform transform, float speed, AndrebaAlex_KeyCode forward, AndrebaAlex_KeyCode backward, AndrebaAlex_KeyCode right, AndrebaAlex_KeyCode left, AndrebaAlex_KeyCode up, AndrebaAlex_KeyCode down) {
        super(window, transform);
        this.speed = speed;
        this.forward = forward;
        this.backward = backward;
        this.right = right;
        this.left = left;
        this.up = up;
        this.down = down;
    }

    public AndrebaAlex_KeyMouse(AndrebaAlex_Window window, AndrebaAlex_Transform transform, float speed) {
        this(window, transform, speed, AndrebaAlex_KeyCode.W, AndrebaAlex_KeyCode.S, AndrebaAlex_KeyCode.D, AndrebaAlex_KeyCode.A, AndrebaAlex_KeyCode.Space, AndrebaAlex_KeyCode.LeftShift);
    }

    public AndrebaAlex_KeyMouse(AndrebaAlex_Window window, float speed) {
        this(window, window.getMainCamera(), speed);
    }

    public AndrebaAlex_KeyMouse(AndrebaAlex_Window window) {
        this(window, 1);
    }

    @Override
    public AndrebaAlex_Vector3 movementTranslate(AndrebaAlex_Time delta) {
        return new AndrebaAlex_StatVector3(getValue(right, left), getValue(up, down), getValue(backward, forward)).mul(speed * delta.getValue());
    }

    @Override
    public void rotate(AndrebaAlex_Time delta) {
        AndrebaAlex_Vector2 rot = window.input.getMouseRel().subtr(0.5f).mul(-2 * Math.PI);
        transform.setRotation(new AndrebaAlex_StatVector3(AndrebaAlex_Mathx.roundTo(rot.y(), accuracy), AndrebaAlex_Mathx.roundTo(rot.x(), accuracy), 0));
    }

    private int getValue (AndrebaAlex_KeyCode plus, AndrebaAlex_KeyCode minus) {
        return (window.input.isPressed(plus) ? 1 : 0) - (window.input.isPressed(minus) ? 1 : 0);
    }
}
