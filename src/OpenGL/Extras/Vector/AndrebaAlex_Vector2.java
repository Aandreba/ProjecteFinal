package OpenGL.Extras.Vector;

import Vector.AndrebaAlex_Vector;

public abstract class AndrebaAlex_Vector2 extends AndrebaAlex_Vector {
    public AndrebaAlex_Vector2() {
        super(2);
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

    public AndrebaAlex_Vector2 sum (AndrebaAlex_Vector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 sum (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) + b;
            }
        };
    }

    public AndrebaAlex_Vector2 subtr (AndrebaAlex_Vector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 subtr (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) - b;
            }
        };
    }

    public AndrebaAlex_Vector2 mul (AndrebaAlex_Vector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 mul (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) * b;
            }
        };
    }

    public AndrebaAlex_Vector2 div (AndrebaAlex_Vector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) / b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 div (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) / b;
            }
        };
    }

    public AndrebaAlex_Vector2 sum (AndrebaAlex_StatVector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 subtr (AndrebaAlex_StatVector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 mul (AndrebaAlex_StatVector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector2 div (AndrebaAlex_StatVector2 b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector2.this.get(pos) / b.get(pos);
            }
        };
    }

    // Pow
    public AndrebaAlex_Vector2 pow (double b) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return Math.pow(AndrebaAlex_Vector2.this.get(pos), b);
            }
        };
    }

    @Override
    public AndrebaAlex_Vector2 abs() {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get (int pos) {
                return Math.abs(AndrebaAlex_Vector2.this.get(pos));
            }
        };
    }

    public AndrebaAlex_Vector2 forEachValue(ValueFunction function) {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get(int pos) {
                return function.apply(AndrebaAlex_Vector2.this.get(pos));
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

    @Override
    public AndrebaAlex_StatVector2 toStatic() {
        return new AndrebaAlex_StatVector2(get(0), get(1));
    }
}
