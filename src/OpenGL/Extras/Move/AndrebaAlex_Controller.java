package OpenGL.Extras.Move;

import Extras.AndrebaAlex_Mathx;
import OpenGL.Extras.Vector.AndrebaAlex_StatVector2;
import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Extras.Vector.AndrebaAlex_Vector3;
import OpenGL.Input.Buttons.AndrebaAlex_GamepadButton;
import OpenGL.Input.Gamepad.AndrebaAlex_GamepadAxes;
import OpenGL.Input.AndrebaAlex_Joystick;
import OpenGL.AndrebaAlex_Transform;
import OpenGL.AndrebaAlex_Window;
import Units.AndrebaAlex_Time;

public class AndrebaAlex_Controller extends AndrebaAlex_Movement {
    public AndrebaAlex_Joystick joystick;
    public AndrebaAlex_GamepadAxes move, rotate;
    public AndrebaAlex_GamepadButton up, down;

    float sensitivity;
    float speed;
    int accuracy = 2;

    public AndrebaAlex_Controller(AndrebaAlex_Joystick joystick, AndrebaAlex_Window window, AndrebaAlex_Transform transform, float speed, float sensitivity, AndrebaAlex_GamepadAxes move, AndrebaAlex_GamepadAxes rotate, AndrebaAlex_GamepadButton up, AndrebaAlex_GamepadButton down) {
        super(window, transform);
        this.speed = speed;
        this.sensitivity = sensitivity;
        this.joystick = joystick;
        this.move = move;
        this.rotate = rotate;
        this.up = up;
        this.down = down;
    }

    public AndrebaAlex_Controller(AndrebaAlex_Joystick joystick, AndrebaAlex_Window window, AndrebaAlex_Transform transform, float speed, float sensitivity) {
        this(joystick, window, transform, speed, sensitivity, AndrebaAlex_GamepadAxes.Left, AndrebaAlex_GamepadAxes.Right, AndrebaAlex_GamepadButton.B, AndrebaAlex_GamepadButton.A);
    }

    public AndrebaAlex_Controller(AndrebaAlex_Joystick joystick, AndrebaAlex_Window window, float speed, float sensitivity) {
        this(joystick, window, window.getMainCamera(), speed, sensitivity);
    }

    public AndrebaAlex_Controller(AndrebaAlex_Joystick joystick, AndrebaAlex_Window window) {
        this(joystick, window, 1, 0.5f);
    }

    @Override
    public AndrebaAlex_Vector3 movementTranslate(AndrebaAlex_Time delta) {
        AndrebaAlex_StatVector2 trans = window.input.getJoystick(joystick, move);
        return new AndrebaAlex_StatVector3(trans.get(0), 0, trans.get(1)).toRelative().mul(speed * delta.getValue());
    }

    @Override
    public void rotate (AndrebaAlex_Time delta) {
        AndrebaAlex_StatVector2 rot = window.input.getJoystick(joystick, rotate);
        AndrebaAlex_StatVector3 target = new AndrebaAlex_StatVector3(-AndrebaAlex_Mathx.roundTo(rot.get(1), accuracy), -AndrebaAlex_Mathx.roundTo(rot.get(0), accuracy), 0);
        AndrebaAlex_Vector3 dt = target.mul(window.getMainCamera().getFov().getValue()).subtr(transform.rotation.toRelative());

        transform.rotation.add(dt.mul(sensitivity));
    }
}
