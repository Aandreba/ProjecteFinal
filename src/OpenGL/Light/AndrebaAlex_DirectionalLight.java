package OpenGL.Light;

import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Shaders.AndrebaAlex_Shader;
import Units.AndrebaAlex_Angle;

import java.awt.*;

public class AndrebaAlex_DirectionalLight {
    public Color color;
    public AndrebaAlex_Angle angle;
    public float intensity;

    public AndrebaAlex_DirectionalLight(Color color, AndrebaAlex_Angle angle, float intensity) {
        this.color = color;
        this.angle = angle;
        this.intensity = intensity;
    }

    public void setAsUniform (String name, AndrebaAlex_Shader shader) {
        AndrebaAlex_StatVector3 dir = new AndrebaAlex_StatVector3(angle.sinf(), angle.cosf(), 0);

        shader.setUniform(name+".color", color);
        shader.setUniform(name+".dir", dir.getNormalized());
        shader.setUniform(name+".intensity", intensity);
    }

    public void setAsUniform (String name, int pos, AndrebaAlex_Shader shader) {
        setAsUniform(name+"["+pos+"]", shader);
    }

    public static void createUniform (String name, AndrebaAlex_Shader shader) throws Exception {
        shader.createUniform(name+".color");
        shader.createUniform(name+".dir");
        shader.createUniform(name+".intensity");
    }

    public static void createArrayUniform (String name, int size, AndrebaAlex_Shader shader) throws Exception {
        for (int i=0;i<size;i++) {
            createUniform(name + "[" + i + "]", shader);
        }
    }
}
