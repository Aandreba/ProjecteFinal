package OpenGL.Input;

import OpenGL.Extras.Vector.AndrebaAlex_StatVector2;
import OpenGL.Extras.Vector.AndrebaAlex_Vector2;
import OpenGL.Input.Buttons.AndrebaAlex_GamepadButton;
import OpenGL.Input.Buttons.AndrebaAlex_KeyCode;
import OpenGL.Input.Gamepad.AndrebaAlex_GamepadAxes;
import OpenGL.Input.Gamepad.AndrebaAlex_GamepadAxis;
import OpenGL.Input.Gamepad.AndrebaAlex_GamepadState;
import OpenGL.AndrebaAlex_Transform;
import OpenGL.AndrebaAlex_Window;
import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;

public class AndrebaAlex_Input {
    final private AndrebaAlex_Window window;

    final private HashMap<AndrebaAlex_KeyCode, Boolean> keyPressed;
    final private HashMap<AndrebaAlex_Joystick, HashMap<AndrebaAlex_GamepadButton, Boolean>> buttonPressed;
    final private HashMap<AndrebaAlex_Joystick, HashMap<AndrebaAlex_GamepadAxes, AndrebaAlex_StatVector2>> currentJoystick;
    private AndrebaAlex_StatVector2 currentMouse;

    public AndrebaAlex_Input(AndrebaAlex_Window window) {
        this.window = window;
        this.keyPressed = new HashMap<>();
        this.buttonPressed = new HashMap<>();
        this.currentMouse = new AndrebaAlex_StatVector2();
        this.currentJoystick = new HashMap<>();

        // Keys
        glfwSetKeyCallback(window.id, (w, k, scancode, action, mods) -> {
            boolean val;

            if (action == GLFW_PRESS) {
                val = true;
            } else if (action == GLFW_RELEASE) {
                val = false;
            } else {
                return;
            }

            AndrebaAlex_KeyCode key = AndrebaAlex_KeyCode.getKey(k);
            keyPressed.put(key, val);
        });

        // Mouse
        glfwSetCursorPosCallback(window.id, (handle, xpos, ypos) -> {
            currentMouse = new AndrebaAlex_StatVector2(xpos, ypos);
        });

        // Setup buttons
        for (AndrebaAlex_Joystick joystick: AndrebaAlex_Joystick.values()) {
            this.buttonPressed.put(joystick, new HashMap<>());

            this.currentJoystick.put(joystick, new HashMap<>());
            for (AndrebaAlex_GamepadAxes axis: AndrebaAlex_GamepadAxes.values()) {
                this.currentJoystick.get(joystick).put(axis, new AndrebaAlex_StatVector2());
            }
        }
    }

    public void update () {
        for (AndrebaAlex_Joystick joystick: AndrebaAlex_Joystick.values()) {
            if (!joystick.isPresent() || !joystick.isGamepad()) {
                this.currentJoystick.put(joystick, new HashMap<>());
                this.buttonPressed.put(joystick, new HashMap<>());
                continue;
            }
            AndrebaAlex_GamepadState state = joystick.getState();

            HashMap<AndrebaAlex_GamepadAxes, AndrebaAlex_StatVector2> axis = new HashMap<>();
            axis.put(AndrebaAlex_GamepadAxes.Left, state.getLeftAxis());
            axis.put(AndrebaAlex_GamepadAxes.Right, state.getRightAxis());
            axis.put(AndrebaAlex_GamepadAxes.Triggers, new AndrebaAlex_StatVector2(state.getAxisValue(AndrebaAlex_GamepadAxis.LeftTrigger), state.getAxisValue(AndrebaAlex_GamepadAxis.RightTrigger)));
            this.currentJoystick.put(joystick, axis);

            for (AndrebaAlex_GamepadButton button: AndrebaAlex_GamepadButton.values()) {
                this.buttonPressed.get(joystick).put(button, state.isPressed(button));
            }
        }
    }

    public boolean isPressed (AndrebaAlex_KeyCode key) {
        return keyPressed.getOrDefault(key, false);
    }

    public boolean isPressed (AndrebaAlex_Joystick joystick, AndrebaAlex_GamepadButton key) {
        return buttonPressed.get(joystick).getOrDefault(key, false);
    }

    public boolean isHovering (AndrebaAlex_Transform transform) {
        AndrebaAlex_StatVector2 rel = getMouseRel();
        AndrebaAlex_Vector2 pos = transform.position.xy();

        AndrebaAlex_Vector2 dist = rel.subtr(pos);
        return dist.x() <= transform.scale.x() || dist.y() <= transform.scale.y();
    }

    public AndrebaAlex_StatVector2 getMouse () {
        return currentMouse;
    }

    public AndrebaAlex_StatVector2 getMouseRel () {
        return new AndrebaAlex_StatVector2(currentMouse.get(0) / window.getWidth(), currentMouse.get(1) / window.getHeight());
    }

    public AndrebaAlex_StatVector2 getJoystick (AndrebaAlex_Joystick joystick, AndrebaAlex_GamepadAxes axis) {
        return this.currentJoystick.get(joystick).getOrDefault(axis, new AndrebaAlex_StatVector2());
    }

    public float mouseAxisValue (AndrebaAlex_MouseAxis axis) {
        if (axis == AndrebaAlex_MouseAxis.X) {
            return currentMouse.getFloat(0) / window.getWidth();
        } else {
            return currentMouse.getFloat(1) / window.getHeight();
        }
    }

    public float joystickAxisValue(AndrebaAlex_Joystick joystick, AndrebaAlex_GamepadAxis axis) {
        HashMap<AndrebaAlex_GamepadAxes, AndrebaAlex_StatVector2> joy = this.currentJoystick.get(joystick);
        try {
            return switch (axis) {
                case LeftX -> joy.get(AndrebaAlex_GamepadAxes.Left).getFloat(0);
                case LeftY -> joy.get(AndrebaAlex_GamepadAxes.Left).getFloat(1);
                case RightX -> joy.get(AndrebaAlex_GamepadAxes.Right).getFloat(0);
                case RightY -> joy.get(AndrebaAlex_GamepadAxes.Right).getFloat(1);
                case LeftTrigger -> joy.get(AndrebaAlex_GamepadAxes.Triggers).getFloat(0);
                case RightTrigger -> joy.get(AndrebaAlex_GamepadAxes.Triggers).getFloat(1);
            };
        } catch (Exception e) {
            return 0;
        }
    }

    public float getValue (AndrebaAlex_GeneralInput input, AndrebaAlex_Joystick joystick) {
        if (input instanceof AndrebaAlex_KeyCode) {
            return isPressed((AndrebaAlex_KeyCode)input) ? 1 : 0;
        } else if (input instanceof AndrebaAlex_GamepadButton) {
            return isPressed(joystick, (AndrebaAlex_GamepadButton)input) ? 1 : 0;
        } else if (input instanceof AndrebaAlex_GamepadAxis) {
            return joystickAxisValue(joystick, (AndrebaAlex_GamepadAxis)input);
        } else if (input instanceof AndrebaAlex_MouseAxis) {
            return mouseAxisValue((AndrebaAlex_MouseAxis)input);
        }

        return 1f / 0;
    }
}
