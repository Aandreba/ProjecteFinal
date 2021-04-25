package OpenGL.Primitives;

import Extras.AndrebaAlex_Mathx;
import OpenGL.AndrebaAlex_Mesh;

public class AndrebaAlex_Sphere extends AndrebaAlex_Mesh {
    public AndrebaAlex_Sphere(int rows, int cols) {
        super ((rows + 1) * cols, (rows + 1) * cols * 2);

        float angleStep = AndrebaAlex_Mathx.PI / rows;
        float colStep = 2 * AndrebaAlex_Mathx.PI / cols; // in radians

        for (int i=0;i<=rows;i++) {
            float angle = i * angleStep;
            float y = AndrebaAlex_Mathx.cos(angle);
            float xz = AndrebaAlex_Mathx.sin(angle);

            for (int j=0;j<cols;j++) {
                float colAngle = colStep * j;
                float z = xz * AndrebaAlex_Mathx.sin(colAngle);
                float x = xz * AndrebaAlex_Mathx.cos(colAngle);

                int self = (i * cols) + j;
                this.setVertex(self, x, y, z);

                // Triangles
                int down = ((i + 1) * cols) + j;
                int left, downLeft;

                if (j > 0) {
                    left = (i * cols) + j - 1;
                    downLeft = ((i + 1) * cols) + j - 1;
                } else {
                    left = (i * cols) + (cols - 1);
                    downLeft = ((i + 1) * cols) + (cols - 1);
                }

                this.setTriangle(2 * self, self, downLeft, down);
                this.setTriangle(2 * self + 1, self, left, downLeft);
            }
        }

        this.calculateNormals();
        this.calculateTexCoords();
        this.draw();
    }

    public AndrebaAlex_Sphere() {
        this (20, 20);
    }
}
