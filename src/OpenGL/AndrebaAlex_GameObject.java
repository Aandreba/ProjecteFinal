package OpenGL;

import OpenGL.Extras.Vector.AndrebaAlex_Vector3;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class AndrebaAlex_GameObject {
    final public AndrebaAlex_Transform transform;
    public ArrayList<String> tags;
    public AndrebaAlex_Mesh mesh;
    public AndrebaAlex_Material material;
    protected AndrebaAlex_Window window;

    public AndrebaAlex_GameObject(AndrebaAlex_Mesh mesh, AndrebaAlex_Transform transform, AndrebaAlex_Material material) {
        this.mesh = mesh;
        this.transform = transform;
        this.material = material;
        this.tags = new ArrayList<>();
    }

    public AndrebaAlex_GameObject(AndrebaAlex_Mesh mesh, AndrebaAlex_Material material, AndrebaAlex_Vector3 pos, AndrebaAlex_Vector3 rot, float scale) {
        this(mesh, material);

        if (pos != null) {
            this.transform.setPosition(pos);
        }

        if (rot != null) {
            this.transform.setRotation(rot);
        }

        this.transform.setScale(scale);
    }

    public AndrebaAlex_GameObject(AndrebaAlex_Mesh mesh, AndrebaAlex_Material material) {
        this.mesh = mesh;
        this.transform = new AndrebaAlex_Transform();
        this.material = material;
        this.tags = new ArrayList<>();
    }

    public AndrebaAlex_GameObject(AndrebaAlex_Mesh mesh, Color color) {
        this(mesh, new AndrebaAlex_Material(color, 1f));
    }

    public AndrebaAlex_GameObject(AndrebaAlex_Mesh mesh, AndrebaAlex_Texture texture) {
        this(mesh, new AndrebaAlex_Material(texture, 1f));
    }

    /**
     * Render mesh on screen
     */
    public void render () {
        if (mesh == null) {
            return;
        }

        window.shader.setUniform("transform", this.transform.matrix);
        material.setAsUniform(window.shader);

        if (material.texture != null) {
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, material.texture.id);
        }

        // Bind to the VAO
        glBindVertexArray(this.mesh.getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        // Draw the vertices
        glDrawElements(GL_TRIANGLES, mesh.getTriangleCount() * 3, GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void cleanup () {
        if (mesh != null) {
            mesh.cleanup();
        }
        material.cleanup();
    }
}
