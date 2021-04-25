package Vector;

import Extras.AndrebaAlex_Rand;
import Matrix.AndrebaAlex_Matrix;

import java.nio.ByteBuffer;

public abstract class AndrebaAlex_Vector {
    final public int length;

    public AndrebaAlex_Vector(int length) {
        this.length = length;
    }

    public static AndrebaAlex_Vector random (int length, double from, double to) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Rand.getDouble(from, to);
            }
        };
    }

    public static AndrebaAlex_Vector random (int length) {
        return random(length, -1, 1);
    }

    public int getLength() {
        return length;
    }

    public abstract double get (int pos);

    public float getFloat (int pos) {
        return (float)get(pos);
    }

    public double getFirst () {
        return get(0);
    }

    public double getLast () {
        return get(length - 1);
    }

    public double[] toArray () {
        double[] ret = new double[length];

        for (int i=0;i<length;i++) {
            ret[i] = get(i);
        }

        return ret;
    }

    public float[] toFloatArray () {
        float[] ret = new float[length];

        for (int i=0;i<length;i++) {
            ret[i] = getFloat(i);
        }

        return ret;
    }

    public AndrebaAlex_Matrix toMatrix(int cols) {
        return new AndrebaAlex_Matrix(this.length / cols, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Vector.this.get((row * cols) + col);
            }
        };
    }

    public AndrebaAlex_StatVector toStatic () {
        AndrebaAlex_StatVector ret = new AndrebaAlex_StatVector(length);

        for (int i=0;i<length;i++) {
            ret.set(i, get(i));
        }

        return ret;
    }

    // Sums
    public AndrebaAlex_Vector sum (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) + b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector sum (double b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) + b;
            }
        };
    }

    public <T extends Number> AndrebaAlex_Vector sum (T b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) + b.doubleValue();
            }
        };
    }

    // Subtrs
    public AndrebaAlex_Vector subtr (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) - b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector subtr (double b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) - b;
            }
        };
    }

    public <T extends Number> AndrebaAlex_Vector subtr (T b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) - b.doubleValue();
            }
        };
    }

    // Inv subtr
    public AndrebaAlex_Vector invSubtr (double b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return b - AndrebaAlex_Vector.this.get(pos);
            }
        };
    }

    public <T extends Number> AndrebaAlex_Vector invSubtr (T b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return b.doubleValue() - AndrebaAlex_Vector.this.get(pos);
            }
        };
    }

    // Mul
    public AndrebaAlex_Vector mul (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) * b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector mul (AndrebaAlex_Matrix b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                double sum = 0;

                for (int i=0;i<b.getCols();i++) {
                    sum += AndrebaAlex_Vector.this.get(i) * b.get(pos, i);
                }

                return sum;
            }
        };
    }

    public AndrebaAlex_Vector mul (double b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) * b;
            }
        };
    }

    public <T extends Number> AndrebaAlex_Vector mul (T b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) * b.doubleValue();
            }
        };
    }

    // Div
    public AndrebaAlex_Vector div (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) / b.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector div (double b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) / b;
            }
        };
    }

    public <T extends Number> AndrebaAlex_Vector div (T b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Vector.this.get(pos) / b.doubleValue();
            }
        };
    }

    // Inv div
    public AndrebaAlex_Vector invDiv (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return b.get(pos) / AndrebaAlex_Vector.this.get(pos);
            }
        };
    }

    public AndrebaAlex_Vector invDiv (double b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return b / AndrebaAlex_Vector.this.get(pos);
            }
        };
    }

    public <T extends Number> AndrebaAlex_Vector invDiv (T b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return b.doubleValue() / AndrebaAlex_Vector.this.get(pos);
            }
        };
    }

    // Pow
    public AndrebaAlex_Vector pow (double b) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return Math.pow(AndrebaAlex_Vector.this.get(pos), b);
            }
        };
    }

    // Abs
    public AndrebaAlex_Vector abs () {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return Math.abs(AndrebaAlex_Vector.this.get(pos));
            }
        };
    }

    // Dot
    public double dot (AndrebaAlex_Vector b) {
        return mul(b).getSum();
    }

    // Round
    public AndrebaAlex_Vector round () {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return Math.round(AndrebaAlex_Vector.this.get(pos));
            }
        };
    }

    public AndrebaAlex_Vector round (int to) {
        double k = Math.pow(10,to);
        return this.mul(k).round().div(k);
    }

    // Max
    public double max () {
        double max = 0;

        for (int i=0;i<length;i++) {
            double v = get(i);
            if (i == 0 || v > max) {
                max = v;
            }
        }

        return max;
    }

    public int maxPos () {
        double max = 0;
        int pos = -1;

        for (int i=0;i<length;i++) {
            double v = get(i);
            if (i == 0 || v > max) {
                max = v;
                pos = 1;
            }
        }

        return pos;
    }

    public double min () {
        double min = 0;

        for (int i=0;i<length;i++) {
            double v = get(i);
            if (i == 0 || v < min) {
                min = v;
            }
        }

        return min;
    }

    public int minPos () {
        double min = 0;
        int pos = -1;

        for (int i=0;i<length;i++) {
            double v = get(i);
            if (i == 0 || v < min) {
                min = v;
                pos = i;
            }
        }

        return pos;
    }

    // Exp
    public AndrebaAlex_Vector exp () {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return Math.exp(AndrebaAlex_Vector.this.get(pos));
            }
        };
    }

    // Ln
    public AndrebaAlex_Vector ln () {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return Math.log(AndrebaAlex_Vector.this.get(pos));
            }
        };
    }

    // Clamp
    public AndrebaAlex_Vector clamp (double min, double max) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                double v = AndrebaAlex_Vector.this.get(pos);
                return Math.max(min, Math.min(v, max));
            }
        };
    }

    public AndrebaAlex_Vector clamp (AndrebaAlex_Vector min, AndrebaAlex_Vector max) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                double v = AndrebaAlex_Vector.this.get(pos);
                return Math.max(min.get(pos), Math.min(v, max.get(pos)));
            }
        };
    }

    public AndrebaAlex_Vector clamp (double value, boolean isMax) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                double v = AndrebaAlex_Vector.this.get(pos);
                return isMax ? Math.min(v,value) : Math.max(v,value);
            }
        };
    }

    public interface ValueFunction {
        double apply (double value);
    }

    public AndrebaAlex_Vector forEachValue(ValueFunction function) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return function.apply(AndrebaAlex_Vector.this.get(pos));
            }
        };
    }

    public interface IndexFunction {
        double apply (int index);
    }

    public AndrebaAlex_Vector forEachIndex(IndexFunction function) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return function.apply(pos);
            }
        };
    }

    public static AndrebaAlex_Vector forEachIndex(int length, IndexFunction function) {
        return new AndrebaAlex_Vector(length) {
            @Override
            public double get(int pos) {
                return function.apply(pos);
            }
        };
    }

    // Distance
    public double dist (AndrebaAlex_Vector b) {
        return subtr(b).getSqrtMagnitude();
    }

    // Magnitude
    public double getMagnitude () {
        return pow(2).getSum();
    }

    public double getSqrtMagnitude () {
        return Math.sqrt(getMagnitude());
    }

    // Normalized
    public AndrebaAlex_Vector getNormalized () {
        double sqrt = getSqrtMagnitude();
        if (Double.isNaN(sqrt) || sqrt == 0) {
            return new AndrebaAlex_Vector(length) {
                @Override
                public double get(int pos) {
                    return 0;
                }
            };
        } else {
            return div(getSqrtMagnitude());
        }
    }

    // Self sum
    public double getSum () {
        double s = 0;

        for (int i=0;i<length;i++) {
            s += get(i);
        }

        return s;
    }

    // Mean
    public double getMean () {
        return getSum() / length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AndrebaAlex_Vector)) {
            return false;
        }

        try {
            AndrebaAlex_Vector mo = (AndrebaAlex_Vector) o;
            if (length != mo.length) {
                return false;
            }

            for (int i = 0; i < length; i++) {
                if (get(i) != mo.get(i)) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // String
    @Override
    public String toString() {
        String r = "("+length+") { ";
        for (int i=0;i<length;i++) {
            if (i > 0){
                r += ", ";
            }
            r += get(i);
        }

        return r + " }";
    }

    // Bytes
    public ByteBuffer getByteBuffer() {
        ByteBuffer bb = ByteBuffer.allocate(8 * length);
        for (int i=0;i<length;i++) {
            bb = bb.putDouble(8*i, get(i));
        }

        return bb;
    }

    public byte[] getBytes() {
        return getByteBuffer().array();
    }
}
