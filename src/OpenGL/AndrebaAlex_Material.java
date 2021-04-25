package OpenGL;

import OpenGL.Shaders.AndrebaAlex_Shader;

import java.awt.*;

public class AndrebaAlex_Material {
    public AndrebaAlex_Texture texture;
    public Color color;
    public float reflectance;

    public AndrebaAlex_Material(AndrebaAlex_Texture texture, float reflectance) {
        this.texture = texture;
        this.color = Color.WHITE;
        this.reflectance = reflectance;
    }

    public AndrebaAlex_Material(Color color, float reflectance) {
        this.color = color;
        this.reflectance = reflectance;
    }

    public void setAsUniform (AndrebaAlex_Shader shader) {
        shader.setUniform("defColor", color);
        shader.setUniform("reflectance", reflectance);
        shader.setUniform("hasTexture", texture != null ? 1 : 0);
    }

    public static void createUniform (AndrebaAlex_Shader shader) throws Exception {
        shader.createUniform("defColor");
        shader.createUniform("reflectance");
        shader.createUniform("hasTexture");
    }

    public void cleanup () {
        if (texture != null) {
            texture.cleanup();
        }
    }
}
