package OpenGL.Light;

import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Extras.Vector.AndrebaAlex_Vector3;
import OpenGL.Shaders.AndrebaAlex_Shader;

import java.awt.*;

public class AndrebaAlex_PointLight {
    public AndrebaAlex_StatVector3 position;
    public Color color;
    public float intensity;

    public AndrebaAlex_PointLight(AndrebaAlex_Vector3 position, Color color, float intensity) {
        this.position = position.toStatic();
        this.color = color;
        this.intensity = intensity;
    }

    public AndrebaAlex_PointLight(AndrebaAlex_StatVector3 position, Color color, float intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public void setAsUniform (String name, AndrebaAlex_Shader shader) {
        shader.setUniform(name+".pos", position);
        shader.setUniform(name+".color", color);
        shader.setUniform(name+".intensity", intensity);
    }

    public void setAsUniform (String name, int pos, AndrebaAlex_Shader shader) {
        setAsUniform(name+"["+pos+"]", shader);
    }

    public static void createUniform (String name, AndrebaAlex_Shader shader) throws Exception {
        shader.createUniform(name+".pos");
        shader.createUniform(name+".color");
        shader.createUniform(name+".intensity");
    }

    public static void createArrayUniform (String name, int size, AndrebaAlex_Shader shader) throws Exception {
        for (int i=0;i<size;i++) {
            createUniform(name + "[" + i + "]", shader);
        }
    }
}
