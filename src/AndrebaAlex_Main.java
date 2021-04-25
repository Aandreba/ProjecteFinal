import OpenGL.Extras.Move.AndrebaAlex_KeyMouse;
import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.AndrebaAlex_GameObject;
import OpenGL.Input.Buttons.AndrebaAlex_KeyCode;
import OpenGL.Light.AndrebaAlex_DirectionalLight;
import OpenGL.Light.AndrebaAlex_PointLight;
import OpenGL.Light.AndrebaAlex_SpotLight;
import OpenGL.Primitives.AndrebaAlex_Sphere;
import OpenGL.AndrebaAlex_Window;
import Units.AndrebaAlex_Angle;
import Units.AndrebaAlex_Time;

import java.awt.*;
import java.util.Collections;

public class AndrebaAlex_Main {
    public static AndrebaAlex_Window window;

    public static void main (String... args) throws Exception {
        window = new AndrebaAlex_Window("Projecte final", 900, 900, true) {
            AndrebaAlex_GameObject simple, normal, complex;
            AndrebaAlex_PointLight point = null;
            AndrebaAlex_SpotLight spot = null;
            AndrebaAlex_KeyMouse move = null;

            @Override
            public void start () {
                point = points[0];
                spot = spots[0];
                move = new AndrebaAlex_KeyMouse(this);

                simple = get(0);
                normal = get(1);
                complex = get(2);

                point.position = normal.transform.position.toStatic();
                point.position.addY(2);
            }

            @Override
            public void update (AndrebaAlex_Time deltaTime) {
                if (input.isPressed(AndrebaAlex_KeyCode.Escape)) {
                    cleanup();
                    System.exit(1);
                }

                move.update(deltaTime);
                spot.position = getMainCamera().position.toRelative();
            }
        };

        createLights();
        createObjects();
        window.run();
    }

    public static void createLights () {
        AndrebaAlex_DirectionalLight ambient = new AndrebaAlex_DirectionalLight(Color.WHITE, new AndrebaAlex_Angle(90, AndrebaAlex_Angle.Type.Degrees), 0.2f);
        AndrebaAlex_PointLight point = new AndrebaAlex_PointLight(AndrebaAlex_StatVector3.zero, Color.ORANGE, 1f);
        AndrebaAlex_SpotLight spot = new AndrebaAlex_SpotLight(AndrebaAlex_StatVector3.zero, AndrebaAlex_StatVector3.forward, new AndrebaAlex_Angle(45, AndrebaAlex_Angle.Type.Degrees), Color.YELLOW, 1f);

        window.directionals[0] = ambient;
        window.points[0] = point;
        window.spots[0] = spot;
    }

    public static void createObjects () {
        AndrebaAlex_GameObject simple = new AndrebaAlex_GameObject(new AndrebaAlex_Sphere(2, 2), Color.RED);
        AndrebaAlex_GameObject normal = new AndrebaAlex_GameObject(new AndrebaAlex_Sphere(20, 20), Color.YELLOW);
        AndrebaAlex_GameObject complex = new AndrebaAlex_GameObject(new AndrebaAlex_Sphere(200, 200), Color.GREEN);

        simple.transform.setScale(0.25f);
        normal.transform.setScale(0.25f);
        complex.transform.setScale(0.25f);

        simple.transform.setPosition(-1, 0, -2);
        normal.transform.setPosition(0, 0, -2);
        complex.transform.setPosition(1, 0, -2);

        Collections.addAll(window, simple, normal, complex);
    }
}
