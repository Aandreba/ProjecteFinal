package OpenGL.Light;

import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Extras.Vector.AndrebaAlex_Vector3;
import OpenGL.Shaders.AndrebaAlex_Shader;
import Units.AndrebaAlex_Angle;

import java.awt.*;

public class AndrebaAlex_SpotLight {
    public AndrebaAlex_Vector3 position;
    public AndrebaAlex_StatVector3 direction;
    public AndrebaAlex_Angle angle;
    public Color color;
    public float intensity;

    public AndrebaAlex_SpotLight(AndrebaAlex_Vector3 position, AndrebaAlex_StatVector3 direction, AndrebaAlex_Angle angle, Color color, float intensity) {
        this.position = position;
        this.direction = direction;
        this.angle = angle;
        this.color = color;
        this.intensity = intensity;
    }

    public AndrebaAlex_SpotLight(AndrebaAlex_StatVector3 position, AndrebaAlex_StatVector3 direction, AndrebaAlex_Angle angle, Color color, float intensity) {
        this.position = position.toRelative();
        this.direction = direction;
        this.angle = angle;
        this.color = color;
        this.intensity = intensity;
    }

    public void setAsUniform (String name, AndrebaAlex_Shader shader) {
        shader.setUniform(name+".pos", position);
        shader.setUniform(name+".dir", direction.getNormalized());
        shader.setUniform(name+".color", color);
        shader.setUniform(name+".cutoff", angle.cosf());
        shader.setUniform(name+".intensity", intensity);
    }

    public void setAsUniform (String name, int pos, AndrebaAlex_Shader shader) {
        setAsUniform(name+"["+pos+"]", shader);
    }

    public static void createUniform (String name, AndrebaAlex_Shader shader) throws Exception {
        shader.createUniform(name+".pos");
        shader.createUniform(name+".dir");
        shader.createUniform(name+".color");
        shader.createUniform(name+".cutoff");
        shader.createUniform(name+".intensity");
    }

    public static void createArrayUniform (String name, int size, AndrebaAlex_Shader shader) throws Exception {
        for (int i=0;i<size;i++) {
            createUniform(name + "[" + i + "]", shader);
        }
    }
}
