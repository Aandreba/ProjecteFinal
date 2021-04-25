package OpenGL.Shaders;

import Extras.AndrebaAlex_Files;
import OpenGL.Extras.Matrix.AndrebaAlex_Matrix4;
import OpenGL.Extras.Matrix.AndrebaAlex_StatMatrix4;
import OpenGL.Extras.Vector.AndrebaAlex_StatVector2;
import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Extras.Vector.AndrebaAlex_Vector2;
import OpenGL.Extras.Vector.AndrebaAlex_Vector3;
import OpenGL.Light.AndrebaAlex_DirectionalLight;
import OpenGL.Light.AndrebaAlex_PointLight;
import OpenGL.Light.AndrebaAlex_SpotLight;
import OpenGL.AndrebaAlex_Material;

import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL43.*;

public class AndrebaAlex_Shader {
    final protected int id;
    final protected Map<String, Integer> uniforms;
    protected int vertex;
    protected int fragment;

    protected AndrebaAlex_Shader(boolean init) throws Exception {
        this.id = glCreateProgram();
        if (this.id == 0) {
            throw new Exception("Could not create Shader");
        }
        this.uniforms = new HashMap<>();
    }

    public AndrebaAlex_Shader() throws Exception {
        this(true);

        this.createVertexShader(AndrebaAlex_Files.loadResource("/OpenGL/GL/AndrebaAlex_vertex.vert"));
        this.createFragmentShader(AndrebaAlex_Files.loadResource("/OpenGL/GL/AndrebaAlex_fragment.frag"));

        this.createUniform("project");
        this.createUniform("view");
        this.createUniform("transform");
        this.createUniform("textureSampler");

        AndrebaAlex_Material.createUniform(this);
        AndrebaAlex_PointLight.createArrayUniform("points", 5, this);
        AndrebaAlex_DirectionalLight.createArrayUniform("directionals", 5, this);
        AndrebaAlex_SpotLight.createArrayUniform("spots", 5, this);
    }

    public void createVertexShader (String code) throws Exception {
        this.vertex = createShader(code, GL_VERTEX_SHADER);
    }

    public void createVertexShader (File file) throws Exception {
        createVertexShader(AndrebaAlex_Files.loadFile(file, StandardCharsets.UTF_8));
    }

    public void createFragmentShader (String code) throws Exception {
        this.fragment = createShader(code, GL_FRAGMENT_SHADER);
    }

    public void createFragmentShader (File file) throws Exception {
        createFragmentShader(AndrebaAlex_Files.loadFile(file, StandardCharsets.UTF_8));
    }

    protected int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creating shader. Type: " + shaderType);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(this.id, shaderId);
        this.link();

        return shaderId;
    }

    public void createUniform (String uniformName) {
        int uniformLocation = glGetUniformLocation(this.id,
                uniformName);
        if (uniformLocation < 0) {
            System.out.println("Could not find uniform:" + uniformName);
        }
        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform (String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniform (String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform (String uniformName, AndrebaAlex_Vector2 value) {
        glUniform2f(uniforms.get(uniformName), value.xf(), value.yf());
    }

    public void setUniform (String uniformName, AndrebaAlex_StatVector2 value) {
        glUniform2f(uniforms.get(uniformName), value.xf(), value.yf());
    }

    public void setUniform (String uniformName, float x, float y) {
        glUniform2f(uniforms.get(uniformName), x, y);
    }

    public void setUniform (String uniformName, AndrebaAlex_Vector3 value) {
        glUniform3f(uniforms.get(uniformName), value.xf(), value.yf(), value.zf());
    }

    public void setUniform (String uniformName, AndrebaAlex_StatVector3 value) {
        glUniform3f(uniforms.get(uniformName), value.xf(), value.yf(), value.zf());
    }

    public void setUniform (String uniformName, float x, float y, float z) {
        glUniform3f(uniforms.get(uniformName), x, y, z);
    }

    public void setUniform (String uniformName, AndrebaAlex_Vector3 value, float w) {
        glUniform4f(uniforms.get(uniformName), value.xf(), value.yf(), value.zf(), w);
    }

    public void setUniform (String uniformName, AndrebaAlex_StatVector3 value, float w) {
        glUniform4f(uniforms.get(uniformName), value.xf(), value.yf(), value.zf(), w);
    }

    public void setUniform (String uniformName, float x, float y, float z, float w) {
        glUniform4f(uniforms.get(uniformName), x, y, z, w);
    }

    public void setUniform (String uniformName, Color color) {
        setUniform(uniformName, color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
    }

    public void setUniform (String uniformName, AndrebaAlex_Matrix4 value) {
        glUniformMatrix4fv(uniforms.get(uniformName), true, value.toVector().toFloatArray());
    }

    public void setUniform (String uniformName, AndrebaAlex_StatMatrix4 value) {
        glUniformMatrix4fv(uniforms.get(uniformName), true, value.toVector().toFloatArray());
    }

    public void link () throws Exception {
        glLinkProgram(this.id);
        if (glGetProgrami(this.id, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(this.id, 1024));
        }

        if (this.vertex != 0) {
            glDetachShader(this.id, this.vertex);
        }
        if (this.fragment != 0) {
            glDetachShader(this.id, this.fragment);
        }

        glValidateProgram(this.id);
        if (glGetProgrami(this.id, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(this.id, 1024));
        }

    }

    public void bind() {
        glUseProgram(this.id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (this.id != 0) {
            glDeleteProgram(this.id);
        }
    }
}
