package OpenGL;

import OpenGL.Extras.Matrix.AndrebaAlex_Matrix4;
import OpenGL.Extras.Vector.AndrebaAlex_StatVector3;
import OpenGL.Extras.Vector.AndrebaAlex_Vector3;
import Units.AndrebaAlex_Angle;

public class AndrebaAlex_Transform {
    final public AndrebaAlex_StatVector3 position, scale, rotation;
    protected AndrebaAlex_Matrix4 translationMatrix, rotationXMatrix, rotationYMatrix, rotationZMatrix, rotationMatrix, scaleMatrix, matrix;

    public AndrebaAlex_Transform() {
        this.position = new AndrebaAlex_StatVector3();
        this.scale = new AndrebaAlex_StatVector3(1, 1, 1);
        this.rotation = new AndrebaAlex_StatVector3();

        this.translationMatrix = new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                if (row == col) {
                    return 1;
                } else if (col == 3) {
                    return position.get(row);
                }

                return 0;
            }
        };

        this.scaleMatrix = new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                if (row < 3 && row == col) {
                    return scale.get(row);
                } else if (row == 3 && col == 3) {
                    return 1;
                }

                return 0;
            }
        };

        this.rotationXMatrix = new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                if ((row == 0 && col == 0) || (row == 3 && col == 3)) {
                    return 1;
                } else if ((row == 1 && col == 1) || (row == 2 && col == 2)) {
                    return Math.cos(-rotation.get(0));
                } else if (row == 2 && col == 1) {
                    return Math.sin(-rotation.get(0));
                } else if (row == 1 && col == 2) {
                    return -Math.sin(-rotation.get(0));
                }

                return 0;
            }
        };

        this.rotationYMatrix = new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                if ((row == 1 && col == 1) || (row == 3 && col == 3)) {
                    return 1;
                } else if ((row == 0 && col == 0) || (row == 2 && col == 2)) {
                    return Math.cos(-rotation.get(1));
                } else if (row == 0 && col == 2) {
                    return Math.sin(-rotation.get(1));
                } else if (row == 2 && col == 0) {
                    return -Math.sin(-rotation.get(1));
                }

                return 0;
            }
        };

        this.rotationZMatrix = new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                if ((row == 2 && col == 2) || (row == 3 && col == 3)) {
                    return 1;
                } else if ((row == 0 && col == 0) || (row == 1 && col == 1)) {
                    return Math.cos(-rotation.get(2));
                } else if (row == 0 && col == 1) {
                    return Math.sin(-rotation.get(2));
                } else if (row == 1 && col == 0) {
                    return -Math.sin(-rotation.get(2));
                }

                return 0;
            }
        };

        this.rotationMatrix = rotationXMatrix.mul(rotationYMatrix).mul(rotationZMatrix);
        this.matrix = AndrebaAlex_Matrix4.identity.mul(translationMatrix).mul(rotationMatrix).mul(scaleMatrix);
    }

    public void setPosition (double x, double y, double z) {
        this.position.set(0, x);
        this.position.set(1, y);
        this.position.set(2, z);
    }

    public void setPosition (AndrebaAlex_Vector3 pos) {
        this.position.set(0, pos.get(0));
        this.position.set(1, pos.get(1));
        this.position.set(2, pos.get(2));
    }

    public void setPosition (AndrebaAlex_StatVector3 pos) {
        this.position.set(0, pos.get(0));
        this.position.set(1, pos.get(1));
        this.position.set(2, pos.get(2));
    }

    public void setRotation (AndrebaAlex_Vector3 rot) {
        this.rotation.set(0, rot.get(0));
        this.rotation.set(1, rot.get(1));
        this.rotation.set(2, rot.get(2));
    }

    public void setRotation (AndrebaAlex_StatVector3 rot) {
        this.rotation.set(0, rot.get(0));
        this.rotation.set(1, rot.get(1));
        this.rotation.set(2, rot.get(2));
    }

    public void setRotation (double x, double y, double z) {
        this.rotation.set(0, x);
        this.rotation.set(1, y);
        this.rotation.set(2, z);
    }

    public AndrebaAlex_Angle getRotationX () {
        return new AndrebaAlex_Angle(this.rotation.get(0));
    }

    public void setRotationX (AndrebaAlex_Angle angle) {
        this.rotation.set(0, angle.getValue());
    }

    public AndrebaAlex_Angle getRotationY () {
        return new AndrebaAlex_Angle(this.rotation.get(1));
    }

    public void setRotationY (AndrebaAlex_Angle angle) {
        this.rotation.set(1, angle.getValue());
    }

    public AndrebaAlex_Angle getRotationZ () {
        return new AndrebaAlex_Angle(this.rotation.get(2));
    }

    public void setRotationZ (AndrebaAlex_Angle angle) {
        this.rotation.set(2, angle.getValue());
    }

    public void setScale (float scale) {
        this.scale.set(0, scale);
        this.scale.set(1, scale);
        this.scale.set(2, scale);
    }

    public void setScale (float x, float y, float z) {
        this.scale.set(0, x);
        this.scale.set(1, y);
        this.scale.set(2, z);
    }

    public void setScale (AndrebaAlex_Vector3 scale) {
        this.scale.set(0, scale.x());
        this.scale.set(1, scale.y());
        this.scale.set(2, scale.z());
    }

    public void setScale (AndrebaAlex_StatVector3 scale) {
        this.scale.set(0, scale.x());
        this.scale.set(1, scale.y());
        this.scale.set(2, scale.z());
    }

    public AndrebaAlex_StatVector3 translateVector (AndrebaAlex_Vector3 trans) {
        float sinY = getRotationY().sinf();
        float cosY = getRotationY().cosf();

        AndrebaAlex_Vector3 x = new AndrebaAlex_StatVector3(cosY, 0, -sinY).mul(trans.x());
        AndrebaAlex_Vector3 y = new AndrebaAlex_StatVector3(0, 1, 0).mul(trans.y());
        AndrebaAlex_Vector3 z = new AndrebaAlex_StatVector3(sinY, 0, cosY).mul(trans.z());

        float X = x.xf() + y.xf() + z.xf();
        float Y = x.yf() + y.yf() + z.yf();
        float Z = x.zf() + y.zf() + z.zf();

        return new AndrebaAlex_StatVector3(X, Y, Z);
    }

    public void translate (AndrebaAlex_Vector3 trans) {
        this.position.add(translateVector(trans));
    }

    public void moveTowards (AndrebaAlex_Vector3 position, float step) {
        this.position.add(translateVector(position.subtr(this.position).getNormalized().mul(step)));
    }

    public void moveTowards (AndrebaAlex_Vector3 position) {
        this.position.add(translateVector(position.subtr(this.position).getNormalized()));
    }

    public void moveTowards (AndrebaAlex_StatVector3 position, float step) {
        this.position.add(translateVector(position.subtr(this.position).getNormalized().mul(step)));
    }

    public void moveTowards (AndrebaAlex_StatVector3 position) {
        this.position.add(translateVector(position.subtr(this.position).getNormalized()));
    }

    public static AndrebaAlex_Matrix4 translationMatrixOf (AndrebaAlex_Vector3 position) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                if (row == col) {
                    return 1;
                } else if (col == 3) {
                    return position.get(row);
                }

                return 0;
            }
        };
    }

    @Override
    public String toString() {
        return "Transform {" +
                "position = " + position +
                ", scale = " + scale +
                ", rotation = " + rotation +
                '}';
    }
}
