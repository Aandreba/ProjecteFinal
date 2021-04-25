package OpenGL.Extras.Vector;

import Vector.AndrebaAlex_Vector;

public abstract class AndrebaAlex_Vector3 extends AndrebaAlex_Vector {
    public AndrebaAlex_Vector3() {
        super(3);
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
                    return AndrebaAlex_Vector3.this.x();
                }
                return AndrebaAlex_Vector3.this.y();
            }
        };
    }

    public AndrebaAlex_Vector2 zy () {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                if (pos == 0) {
                    return AndrebaAlex_Vector3.this.z();
                }
                return AndrebaAlex_Vector3.this.y();
            }
        };
    }

    public AndrebaAlex_Vector2 xz () {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                if (pos == 0) {
                    return AndrebaAlex_Vector3.this.x();
                }
                return AndrebaAlex_Vector3.this.z();
            }
        };
    }

    // Vector3
    public AndrebaAlex_Vector3 sum (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 sum (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) + b;
            }
        };
    }

    public AndrebaAlex_Vector3 subtr (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 subtr (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) - b;
            }
        };
    }

    public AndrebaAlex_Vector3 mul (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 mul (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) * b;
            }
        };
    }

    public AndrebaAlex_Vector3 div (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) / b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 div (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) / b;
            }
        };
    }

    public AndrebaAlex_Vector3 invDiv (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return b.get(pos) / AndrebaAlex_Vector3.this.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 invDiv (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return b / AndrebaAlex_Vector3.this.get(pos);
            }
        };
    }

    // StatVector3
    public AndrebaAlex_Vector3 sum (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 subtr (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 mul (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 div (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector3.this.get(pos) / b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector3 invDiv (AndrebaAlex_StatVector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return b.get(pos) / AndrebaAlex_Vector3.this.get(pos);
            }
        };
    }

    // Pow
    public AndrebaAlex_Vector3 pow (double b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return Math.pow(AndrebaAlex_Vector3.this.get(pos), b);
            }
        };
    }

    @Override
    public AndrebaAlex_Vector3 abs() {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get (int pos) {
                return Math.abs(AndrebaAlex_Vector3.this.get(pos));
            }
        };
    }

    public AndrebaAlex_Vector3 getNormalized () {
        return div(getSqrtMagnitude());
    }

    // New
    public AndrebaAlex_Vector3 cross (AndrebaAlex_Vector3 b) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                switch (pos) {
                    case 0:
                        return (AndrebaAlex_Vector3.this.get(1) * b.get(2)) - (AndrebaAlex_Vector3.this.get(2) * b.get(1));
                    case 1:
                        return (AndrebaAlex_Vector3.this.get(0) * b.get(2)) - (AndrebaAlex_Vector3.this.get(2) * b.get(0));
                    default:
                        return (AndrebaAlex_Vector3.this.get(0) * b.get(1)) - (AndrebaAlex_Vector3.this.get(1) * b.get(0));
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
                        return (AndrebaAlex_Vector3.this.get(1) * b.get(2)) - (AndrebaAlex_Vector3.this.get(2) * b.get(1));
                    case 1:
                        return (AndrebaAlex_Vector3.this.get(0) * b.get(2)) - (AndrebaAlex_Vector3.this.get(2) * b.get(0));
                    default:
                        return (AndrebaAlex_Vector3.this.get(0) * b.get(1)) - (AndrebaAlex_Vector3.this.get(1) * b.get(0));
                }
            }
        };
    }

    // Round
    public AndrebaAlex_Vector3 round () {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return Math.round(AndrebaAlex_Vector3.this.get(pos));
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
                return function.apply(AndrebaAlex_Vector3.this.get(pos));
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

    @Override
    public AndrebaAlex_StatVector3 toStatic() {
        return new AndrebaAlex_StatVector3(get(0), get(1), get(2));
    }

    public static AndrebaAlex_Vector3 from (AndrebaAlex_Vector vector) {
        return new AndrebaAlex_Vector3() {
            @Override
            public double get(int pos) {
                return vector.get(pos);
            }
        };
    }
}
