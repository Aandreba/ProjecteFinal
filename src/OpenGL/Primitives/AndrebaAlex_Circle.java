package OpenGL.Primitives;

import Extras.AndrebaAlex_Mathx;
import OpenGL.AndrebaAlex_Mesh;

public class AndrebaAlex_Circle extends AndrebaAlex_Mesh {
    public AndrebaAlex_Circle(int points) {
        super(2+points, points);

        this.setVertex(0, 0, 0, 0);
        this.setVertex(1, 1, 0, 0);

        float step = 2 * AndrebaAlex_Mathx.PI / points;
        for (int i=1;i<points;i++) {
            float angle = i * step;

            float x = AndrebaAlex_Mathx.cos(angle);
            float y = AndrebaAlex_Mathx.sin(angle);

            this.setVertex(i+1, x, y, 0);
            this.setTriangle(i-1, 0, i, i+1);
        }

        this.setTriangle(points-1, 0, points, 1);

        this.calculateTexCoords();
        this.calculateNormals();
        this.draw();
    }

    public AndrebaAlex_Circle() {
        this(100);
    }
}
