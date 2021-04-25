package OpenGL.Extras.Vector;

import Vector.AndrebaAlex_StatVector;

public class AndrebaAlex_StatVector2 extends AndrebaAlex_StatVector {
    final public static AndrebaAlex_StatVector2 right = new AndrebaAlex_StatVector2(1, 0);
    final public static AndrebaAlex_StatVector2 left = new AndrebaAlex_StatVector2(-1, 0);
    final public static AndrebaAlex_StatVector2 up = new AndrebaAlex_StatVector2(0, 1);
    final public static AndrebaAlex_StatVector2 down = new AndrebaAlex_StatVector2(0, -1);
    final public static AndrebaAlex_StatVector2 zero = new AndrebaAlex_StatVector2(0, 0);

    public AndrebaAlex_StatVector2() {
        super(2);
    }

    public AndrebaAlex_StatVector2(double x, double y) {
        super(2);
        set(0, x);
        set(1, y);
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

    // Vector2
    public AndrebaAlex_Vector2 sum (AndrebaAlex_Vector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 sum (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) + b;
            }
        };
    }

    public AndrebaAlex_Vector2 subtr (AndrebaAlex_Vector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 subtr (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) - b;
            }
        };
    }

    public AndrebaAlex_Vector2 mul (AndrebaAlex_Vector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 mul (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) * b;
            }
        };
    }

    public AndrebaAlex_Vector2 div (AndrebaAlex_Vector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) / b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 div (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) / b;
            }
        };
    }

    // StatVector2
    public AndrebaAlex_Vector2 sum (AndrebaAlex_StatVector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 subtr (AndrebaAlex_StatVector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 mul (AndrebaAlex_StatVector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 div (AndrebaAlex_StatVector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos) / b.get(pos);
            }
        };
    }

    // Pow
    public AndrebaAlex_Vector2 pow (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return Math.pow(AndrebaAlex_StatVector2.this.get(pos), b);
            }
        };
    }

    @Override
    public AndrebaAlex_Vector2 abs() {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get (int pos) {
                return Math.abs(AndrebaAlex_StatVector2.this.get(pos));
            }
        };
    }

    public AndrebaAlex_Vector2 forEachValue(ValueFunction function) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return function.apply(AndrebaAlex_StatVector2.this.get(pos));
            }
        };
    }

    public AndrebaAlex_Vector2 forEachIndex(IndexFunction function) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return function.apply(pos);
            }
        };
    }

    @Override
    public AndrebaAlex_Vector2 getNormalized() {
        return div(getSqrtMagnitude());
    }

    public AndrebaAlex_Vector2 toRelative () {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_StatVector2.this.get(pos);
            }
        };
    }

    public void addX (double x) {
        set(0, get(0) + x);
    }

    public void addY (double y) {
        set(1, get(1) + y);
    }

    @Override
    public AndrebaAlex_StatVector2 toStatic() {
        AndrebaAlex_StatVector2 ret = new AndrebaAlex_StatVector2();

        for (int i=0;i<3;i++) {
            ret.set(i, get(i));
        }

        return ret;
    }
}
