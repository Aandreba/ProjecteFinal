package OpenGL.Input.Gamepad;

import static OpenGL.Input.Gamepad.AndrebaAlex_GamepadAxis.*;

public enum AndrebaAlex_GamepadAxes {
    Left(LeftX, LeftY),
    Right(RightX, RightY),
    Triggers(LeftTrigger, RightTrigger);

    AndrebaAlex_GamepadAxis x;
    AndrebaAlex_GamepadAxis y;
    AndrebaAlex_GamepadAxes(AndrebaAlex_GamepadAxis x, AndrebaAlex_GamepadAxis y) {
        this.x = x;
        this.y = y;
    }
}
