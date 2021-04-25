package OpenGL.Extras.Vector;

import Vector.AndrebaAlex_StatVector;

public class AndrebaAlex_StatVector3 extends AndrebaAlex_StatVector {
    final public static AndrebaAlex_StatVector3 forward = new AndrebaAlex_StatVector3(0, 0, 1);
    final public static AndrebaAlex_StatVector3 backward = new AndrebaAlex_StatVector3(0, 0, -1);
    final public static AndrebaAlex_StatVector3 right = new AndrebaAlex_StatVector3(1, 0, 0);
    final public static AndrebaAlex_StatVector3 left = new AndrebaAlex_StatVector3(-1, 0, 0);
    final public static AndrebaAlex_StatVector3 up = new AndrebaAlex_StatVector3(0, 1, 0);
    final public static AndrebaAlex_StatVector3 down = new AndrebaAlex_StatVector3(0, -1, 0);
    final public static AndrebaAlex_StatVector3 zero = new AndrebaAlex_StatVector3(0, 0, 0);
    final public static AndrebaAlex_StatVector3 one = new AndrebaAlex_StatVector3(1, 1, 1);

    public AndrebaAlex_StatVector3() {
        super(3);
    }

    public AndrebaAlex_StatVector3(AndrebaAlex_Vector2 v, float z) {
        super(3);
        set(0, v.x());
        set(1, v.y());
        set(2, z);
    }

    public AndrebaAlex_StatVector3(double x, double y, double z) {
        super(3);
        set(0, x);
        set(1, y);
        set(2, z);
    }

    public double x () {
        return get(0);
    }

    public float xf () {
        return getFloat(0);
    }

    public double y () {
        return get(1);
    }

    public float yf () {
        return getFloat(1);
    }

    public double z () {
        return get(2);
    }

    public float zf () {
        return getFloat(2);
    }

    public AndrebaAlex_Vector2 xy () {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                if (pos == 0) {
                    return AndrebaAlex_StatVector3.this.x();
                }
                return AndrebaAlex_StatVector3.this.y();
            }
        };
    }

    public AndrebaAlex_Vector2 zy () {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                if (pos == 0) {
                    return AndrebaAlex_StatVector3.this.z();
                }
                return AndrebaAlex_StatVector3.this.y();
            }
        };
    }

    public AndrebaAlex_Vector2 xz () {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                if (pos == 0) {
                    return AndrebaAlex_StatVector3.this.x();
                }
                return AndrebaAlex_StatVector3.this.z();
            }
        };
    }

    // Vector3
    public AndrebaAlex_Vector3 sum (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 sum (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) + b;
            }
        };
    }

    public AndrebaAlex_Vector3 subtr (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 subtr (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) - b;
            }
        };
    }

    public AndrebaAlex_Vector3 mul (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 mul (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) * b;
            }
        };
    }

    public AndrebaAlex_Vector3 div (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) / b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 div (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) / b;
            }
        };
    }

    public AndrebaAlex_Vector3 invDiv (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return b.get(pos) / AndrebaAlex_StatVector3.this.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 invDiv (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return b / AndrebaAlex_StatVector3.this.get(pos);
            }
        };
    }

    // StatVector3
    public AndrebaAlex_Vector3 sum (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 subtr (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 mul (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 div (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos) / b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 invDiv (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return b.get(pos) / AndrebaAlex_StatVector3.this.get(pos);
            }
        };
    }

    // Pow
    public AndrebaAlex_Vector3 pow (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return Math.pow(AndrebaAlex_StatVector3.this.get(pos), b);
            }
        };
    }

    @Override
    public AndrebaAlex_Vector3 abs() {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get (int pos) {
                return Math.abs(AndrebaAlex_StatVector3.this.get(pos));
            }
        };
    }

    public AndrebaAlex_Vector3 getNormalized () {
        double sqrt = getSqrtMagnitude();
        if (Double.isNaN(sqrt) || sqrt == 0) {
            return new AndrebaAlex_Vector3() {
                @Override
                public double get(int pos) {
                    return 0;
                }
            };
        } else {
            return div(sqrt);
        }
    }

    // New
    public AndrebaAlex_Vector3 cross (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                switch (pos) {
                    case 0:
                        return (AndrebaAlex_StatVector3.this.get(1) * b.get(2)) - (AndrebaAlex_StatVector3.this.get(2) * b.get(1));
                    case 1:
                        return (AndrebaAlex_StatVector3.this.get(0) * b.get(2)) - (AndrebaAlex_StatVector3.this.get(2) * b.get(0));
                    default:
                        return (AndrebaAlex_StatVector3.this.get(0) * b.get(1)) - (AndrebaAlex_StatVector3.this.get(1) * b.get(0));
                }
            }
        };
    }

    public AndrebaAlex_Vector3 cross (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                switch (pos) {
                    case 0:
                        return (AndrebaAlex_StatVector3.this.get(1) * b.get(2)) - (AndrebaAlex_StatVector3.this.get(2) * b.get(1));
                    case 1:
                        return (AndrebaAlex_StatVector3.this.get(0) * b.get(2)) - (AndrebaAlex_StatVector3.this.get(2) * b.get(0));
                    default:
                        return (AndrebaAlex_StatVector3.this.get(0) * b.get(1)) - (AndrebaAlex_StatVector3.this.get(1) * b.get(0));
                }
            }
        };
    }

    // Round
    public AndrebaAlex_Vector3 round () {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return Math.round(AndrebaAlex_StatVector3.this.get(pos));
            }
        };
    }

    public AndrebaAlex_Vector3 round (int to) {
        double k = Math.pow(10,to);
        return this.mul(k).round().div(k);
    }

    public AndrebaAlex_Vector3 forEachValue(ValueFunction function) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return function.apply(AndrebaAlex_StatVector3.this.get(pos));
            }
        };
    }

    public AndrebaAlex_Vector3 forEachIndex(IndexFunction function) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return function.apply(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 toRelative () {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector3.this.get(pos);
            }
        };
    }

    public void addX (double x) {
        set(0, get(0) + x);
    }

    public void addY (double y) {
        set(1, get(1) + y);
    }

    public void addZ (double z) {
        set(2, get(2) + z);
    }

    public void add (AndrebaAlex_Vector3 v) {
        addX(v.x());
        addY(v.y());
        addZ(v.z());
    }

    @Override
    public AndrebaAlex_StatVector3 toStatic() {
        AndrebaAlex_StatVector3 ret = new AndrebaAlex_StatVector3();

        for (int i=0;i<3;i++) {
            ret.set(i, get(i));
        }

        return ret;
    }
}
