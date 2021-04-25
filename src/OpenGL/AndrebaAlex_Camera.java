package OpenGL;

import OpenGL.Extras.Matrix.AndrebaAlex_Matrix4;
import OpenGL.Extras.Matrix.AndrebaAlex_StatMatrix4;
import Units.AndrebaAlex_Angle;

public class AndrebaAlex_Camera extends AndrebaAlex_Transform {
    private AndrebaAlex_Window window;

    final private AndrebaAlex_StatMatrix4 proj;
    final public AndrebaAlex_Matrix4 view;

    private AndrebaAlex_Angle fov;
    private float zFar;
    private float zNear;

    public AndrebaAlex_Camera(AndrebaAlex_Angle fov, float zFar, float zNear) {
        super();

        this.fov = fov;
        this.zFar = zFar;
        this.zNear = zNear;

        float tanFov = 1 / (float) Math.tan(fov.getValue() / 2);
        float zm = zFar - zNear;
        float zp = zFar + zNear;

        this.proj = new AndrebaAlex_StatMatrix4();
        this.proj.set(1, 1, tanFov);
        this.proj.set(2, 2, -zp / zm);
        this.proj.set(3, 2, -1);
        this.proj.set(2, 3, -2 * zFar * zNear / zm);

        this.view = AndrebaAlex_Matrix4.identity.toRelative().mul(rotationXMatrix).mul(rotationYMatrix).mul(translationMatrixOf(position.toRelative().mul(-1)));
    }

    public AndrebaAlex_Camera() {
        this(new AndrebaAlex_Angle(60, AndrebaAlex_Angle.Type.Degrees), 1000f, 0.01f);
    }

    protected void setWindow (AndrebaAlex_Window window) {
        this.window = window;
        this.proj.set(0, 0, proj.get(1, 1) / window.getAspectRatio());
    }

    public AndrebaAlex_Angle getFov () {
        return fov;
    }

    public void setFov (AndrebaAlex_Angle fov) {
        this.fov = fov;
        this.proj.set(1, 1, 1 / (float) Math.tan(fov.getValue() / 2));
    }

    public float getZFar () {
        return zFar;
    }

    public void setZFar (float zFar) {
        this.zFar = zFar;
        zUpdate();
    }

    public float getZNear () {
        return zNear;
    }

    public void setZNear (float zNear) {
        this.zNear = zNear;
        zUpdate();
    }

    private void zUpdate () {
        float zm = zFar - zNear;
        float zp = zFar + zNear;

        this.proj.set(2, 2, -zp / zm);
        this.proj.set(2, 3, -2 * zFar * zNear / zm);
    }

    public AndrebaAlex_StatMatrix4 getProjectionMatrix () {
        return proj.toStatic();
    }
}
