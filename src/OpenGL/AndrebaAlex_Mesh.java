package OpenGL;

import Matrix.AndrebaAlex_RelMatrix;
import Vector.AndrebaAlex_RelVector;
import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Extras.Vector.AndrebaAlex_Vector2;
import OpenGL.Extras.Vector.AndrebaAlex_Vector3;
import Vector.AndrebaAlex_Vector;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;

public class AndrebaAlex_Mesh {
    final private int vertexCount, triangleCount;

    protected float[] vertices;
    protected float[] normals;
    protected int[] triangles;
    protected float[] texCoords;

    private int vao, vbo, tVbo, nVbo, xVbo;

    public AndrebaAlex_Mesh(int vertices, int triangles) {
        this.vertexCount = vertices;
        this.triangleCount = triangles;

        this.vertices = new float[vertices * 3];
        this.normals = new float[vertices * 3];
        this.triangles = new int[triangles * 3];
        this.texCoords = new float[vertices * 2];
        this.draw();
    }

    public AndrebaAlex_Mesh(float[] vertices, float[] texCoords, int[] triangles, float[] normals) {
        this.vertexCount = vertices.length / 3;
        this.triangleCount = triangles.length / 3;

        this.vertices = vertices;
        this.triangles = triangles;
        this.texCoords = texCoords;
        this.normals = normals;
        this.draw();
    }

    public AndrebaAlex_Mesh(float[] vertices, float[] texCoords, int[] triangles) {
        this.vertexCount = vertices.length / 3;
        this.triangleCount = triangles.length / 3;

        this.vertices = vertices;
        this.triangles = triangles;
        this.texCoords = texCoords;
        this.calculateNormals();
        this.draw();
    }

    public AndrebaAlex_Mesh(float[] vertices, int[] triangles) {
        this.vertexCount = vertices.length / 3;
        this.triangleCount = triangles.length / 3;

        this.vertices = vertices;
        this.triangles = triangles;
        this.texCoords = new float[vertices.length];
        this.calculateNormals();
        this.calculateTexCoords();
        this.draw();
    }

    public void calculateNormals () {
        this.normals = new float[this.vertices.length];
        AndrebaAlex_RelMatrix matrix = normalMatrix();

        for (int j=0;j<triangleCount;j++) {
            int[] triangle = getTriangle(j);
            if (triangle[0] >= vertexCount || triangle[1] >= vertexCount || triangle[2] >= vertexCount) {
                continue;
            }

            AndrebaAlex_Vector3 a = getVertex(triangle[0]);
            AndrebaAlex_Vector3 b = getVertex(triangle[1]);
            AndrebaAlex_Vector3 c = getVertex(triangle[2]);
            AndrebaAlex_StatVector3 n = b.subtr(a).cross(c.subtr(a)).toStatic();

            matrix.get(triangle[0]).add(n);
            matrix.get(triangle[1]).add(n);
            matrix.get(triangle[2]).add(n);
        }

        for (int i=0;i<vertexCount;i++) {
            matrix.set(i, matrix.get(i).getNormalized());
        }
    }

    public void calculateTexCoords () {
        this.texCoords = new float[this.texCoords.length];
        AndrebaAlex_RelMatrix matrix = vertexMatrix();
        AndrebaAlex_RelMatrix coords = textureMatrix();

        for (int i=0;i<matrix.getRows();i++) {
            AndrebaAlex_Vector vertex = matrix.get(i).getNormalized();
            AndrebaAlex_RelVector texture = coords.get(i);

            texture.set(vertex.sum(1).div(-2));
            texture.set(0, -texture.get(0) + 1);
        }
    }

    public AndrebaAlex_RelMatrix vertexMatrix() {
        return new AndrebaAlex_RelMatrix(vertexCount, 3) {
            @Override
            public double get(int row, int col) {
                return vertices[(row * 3) + col];
            }

            @Override
            public void set(int row, int col, double value) {
                vertices[(row * 3) + col] = (float) value;
            }
        };
    }

    public AndrebaAlex_RelMatrix vertexMatrix4() {
        return new AndrebaAlex_RelMatrix(vertexCount, 4) {
            @Override
            public double get(int row, int col) {
                if (col == 3) {
                    return 1;
                }

                return vertices[(row * 3) + col];
            }

            @Override
            public void set(int row, int col, double value) {
                if (col == 3) {
                    return;
                }

                vertices[(row * 3) + col] = (float) value;
            }
        };
    }

    public AndrebaAlex_RelMatrix normalMatrix() {
        return new AndrebaAlex_RelMatrix(vertexCount, 3) {
            @Override
            public double get(int row, int col) {
                return normals[(row * 3) + col];
            }

            @Override
            public void set(int row, int col, double value) {
                normals[(row * 3) + col] = (float) value;
            }
        };
    }

    public AndrebaAlex_RelMatrix textureMatrix() {
        return new AndrebaAlex_RelMatrix(texCoords.length / 2, 2) {
            @Override
            public double get(int row, int col) {
                return texCoords[(row * 2) + col];
            }

            @Override
            public void set(int row, int col, double value) {
                texCoords[(row * 2) + col] = (float) value;
            }
        };
    }

    public int getVao() {
        return vao;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getTriangleCount() {
        return triangleCount;
    }

    /**
     * Set vertex position
     * @param id Vertex position in matrix
     * @param x X axis position (from -1 to 1)
     * @param y Y axis position (from -1 to 1)
     * @param z Z axis position (from -1 to 1)
     */
    public void setVertex (int id, float x, float y, float z) {
        this.vertices[3 * id] = x;
        this.vertices[3 * id + 1] = y;
        this.vertices[3 * id + 2] = z;
    }

    public AndrebaAlex_Vector3 getVertex (int id) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return vertices[3 * id + pos];
            }
        };
    }

    /**
     * Set vertex normal
     * @param id Vertex position in matrix
     * @param x X axis position (from -1 to 1)
     * @param y Y axis position (from -1 to 1)
     * @param z Z axis position (from -1 to 1)
     */
    public void setNormal (int id, float x, float y, float z) {
        this.normals[3 * id] = x;
        this.normals[3 * id + 1] = y;
        this.normals[3 * id + 2] = z;
    }

    public AndrebaAlex_Vector3 getNormal (int id) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return normals[3 * id + pos];
            }
        };
    }

    /**
     * Set triangle values
     * @param id Triangle position in matrix
     * @param from Initial vertex in vertices matrix
     * @param middle Middle vertex in vertices matrix
     * @param to Final vertex in vertices matrix
     */
    public void setTriangle (int id, int from, int middle, int to) {
        this.triangles[3 * id] = from;
        this.triangles[3 * id + 1] = middle;
        this.triangles[3 * id + 2] = to;
    }

    public int[] getTriangles() {
        return triangles;
    }

    public int[] getTriangle (int id) {
        return new int[]{ triangles[3 * id], triangles[3 * id + 1], triangles[3 * id + 2] };
    }

    /**
     * Set texture coord
     * @param id Vertex position in matrix
     * @param x X axis position (from 0 to 1)
     * @param y Y axis position (from 0 to 1)
     */
    public void setTexCoord (int id, float x, float y) {
        this.texCoords[2 * id] = x;
        this.texCoords[2 * id + 1] = y;
    }

    public AndrebaAlex_Vector2 getTexCoord (int id) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return texCoords[2 * id + pos];
            }
        };
    }

    /**
     * Draw mesh to be later rendered
     */
    public void draw () {
        // VAO
        this.vao = glGenVertexArrays();
        glBindVertexArray(this.vao);

        // VBO
        this.vbo = bindFloats(this.vertices);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        // Texture VBO
        this.xVbo = bindFloats(this.texCoords);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        // Vertex normals VBO
        this.nVbo = bindFloats(this.normals);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);

        // Triangles VBO
        IntBuffer tBuffer = MemoryUtil.memAllocInt(triangles.length);
        tBuffer.put(triangles).flip();

        this.tVbo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.tVbo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, tBuffer, GL_STATIC_DRAW);
        memFree(tBuffer);

        // Others
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private int bindFloats (float[] array) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(array.length);
        buffer.put(array).flip();

        int id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        memFree(buffer);

        return id;
    }

    public void cleanup() {
        glDisableVertexAttribArray(0);

        // Delete the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(this.vbo);
        glDeleteBuffers(this.tVbo);
        glDeleteBuffers(this.nVbo);
        glDeleteBuffers(this.xVbo);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(this.vao);
    }
}
