package Matrix;

import Extras.AndrebaAlex_Rand;
import Vector.AndrebaAlex_Vector;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.function.Function;

/**
 * {@link AndrebaAlex_Matrix} allows to easily do matrix operations
 * @author Alex Andreba
 * @since 0.1A
 */
public abstract class AndrebaAlex_Matrix implements Iterable<AndrebaAlex_Vector> {
    /**
     * Number of rows
     */
    final public int rows;

    /**
     * Number of columns
     */
    final public int cols;

    public AndrebaAlex_Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Create matrix that returns random numbers
     * @param rows Number of rows
     * @param cols Number of cols
     * @param from Minimum value
     * @param to Maximum value
     * @return Specified matrix
     * @see AndrebaAlex_Rand
     */
    public static AndrebaAlex_Matrix random (int rows, int cols, double from, double to) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Rand.getDouble(from, to);
            }
        };
    }

    /**
     * Create matrix that returns random numbers between -1 and 1
     * @see #random(int, int, double, double)
     */
    public static AndrebaAlex_Matrix random (int rows, int cols) {
        return random(rows, cols, -1, 1);
    }

    /**
     * Returns number of rows
     * @deprecated Used for legacy content. Preferably use {@link #rows}
     * @return Number of rows
     */
    @Deprecated
    public int getRows() {
        return rows;
    }

    /**
     * Returns number of columns
     * @deprecated Used for legacy content. Preferably use {@link #cols}
     * @return Number of columns
     */
    @Deprecated
    public int getCols() {
        return cols;
    }

    /**
     * Call to retrieve value from {@link AndrebaAlex_Matrix}
     * @param row Value's row
     * @param col Value's col
     * @return Value at specified position
     */
    public abstract double get (int row, int col);

    /**
     * Retrieve specified value as a {@link Float}
     * @see #get(int, int)
     */
    public float getFloat (int row, int col) {
        return (float)get(row, col);
    }

    /**
     * Retrieve a full row
     * @param row Desired row
     * @return Specified row as a {@link AndrebaAlex_Vector}
     */
    public AndrebaAlex_Vector get (int row) {
        return new AndrebaAlex_Vector(cols) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Matrix.this.get(row, pos);
            }
        };
    }

    /**
     * Retrieve all values
     * @return Array containing matrix's values
     */
    public double[][] toArray () {
        double[][] array = new double[rows][cols];

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                array[i][j] = get(i,j);
            }
        }

        return array;
    }

    /**
     * Retrieve all values as {@link Float}
     * @see #toArray()
     */
    public float[][] toFloatArray () {
        float[][] array = new float[rows][cols];

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                array[i][j] = (float)get(i,j);
            }
        }

        return array;
    }

    /**
     * @return {@link AndrebaAlex_Matrix} as a {@link AndrebaAlex_Vector} in row-major order
     */
    public AndrebaAlex_Vector toVector () {
        return new AndrebaAlex_Vector(rows * cols) {
            @Override
            public double get(int pos) {
                int row = pos / cols;
                int col = pos % cols;

                return AndrebaAlex_Matrix.this.get(row, col);
            }
        };
    }

    /**
     * Stores current {@link AndrebaAlex_Matrix} values inside a {@link AndrebaAlex_StatMatrix}
     * @return Resulting {@link AndrebaAlex_StatMatrix}
     */
    public AndrebaAlex_StatMatrix toStatic () {
        AndrebaAlex_StatMatrix ret = new AndrebaAlex_StatMatrix(rows, cols);
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                ret.values[i][j] = get(i,j);
            }
        }

        return ret;
    }

    @Override
    public Iterator<AndrebaAlex_Vector> iterator() {
        return new Iterator<AndrebaAlex_Vector>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i + i < cols;
            }

            @Override
            public AndrebaAlex_Vector next() {
                return get(i++);
            }
        };
    }

    /**
     * Adds up two matrices
     * @param b Matrix to sum
     * @return Resulting {@link AndrebaAlex_Matrix}
     */
    public AndrebaAlex_Matrix sum (AndrebaAlex_Matrix b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) + b.get(row, col);
            }
        };
    }

    /**
     * Sums
     * @param b Matrix to sum
     * @return Resulting {@link AndrebaAlex_Matrix}
     */
    public AndrebaAlex_Matrix sum (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) + b.get(col);
            }
        };
    }

    public AndrebaAlex_Matrix sum (double b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) + b;
            }
        };
    }

    public <T extends Number> AndrebaAlex_Matrix sum (T b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) + b.doubleValue();
            }
        };
    }

    // Subtrs
    public AndrebaAlex_Matrix subtr (AndrebaAlex_Matrix b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix subtr (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) - b.get(col);
            }
        };
    }

    public AndrebaAlex_Matrix subtr (double b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) - b;
            }
        };
    }

    public <T extends Number> AndrebaAlex_Matrix subtr (T b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) - b.doubleValue();
            }
        };
    }

    // Inv Subtrs
    public AndrebaAlex_Matrix invSubtr (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return b.get(col) - AndrebaAlex_Matrix.this.get(row,col);
            }
        };
    }

    public AndrebaAlex_Matrix invSubtr (double b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return b - AndrebaAlex_Matrix.this.get(row,col);
            }
        };
    }

    public <T extends Number> AndrebaAlex_Matrix invSubtr (T b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return b.doubleValue() - AndrebaAlex_Matrix.this.get(row,col);
            }
        };
    }

    // Mul

    /**
     * Performs matrix multiplication
     * @param b Matrix to multiply by
     * @return Resulting matrix
     */
    public AndrebaAlex_Matrix mul (AndrebaAlex_Matrix b) {
        return new AndrebaAlex_Matrix(rows, b.cols) {
            @Override
            public double get(int row, int col) {
                double sum = 0;

                for (int k=0;k<b.rows;k++) {
                    sum += AndrebaAlex_Matrix.this.get(row,k) * b.get(k,col);
                }

                return sum;
            }
        };
    }

    // Scalar mul
    public AndrebaAlex_Matrix scalarMul (AndrebaAlex_Matrix b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) * b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix scalarMul (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) * b.get(col);
            }
        };
    }

    public AndrebaAlex_Matrix scalarMul (double b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) * b;
            }
        };
    }

    public <T extends Number> AndrebaAlex_Matrix scalarMul (T b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) * b.doubleValue();
            }
        };
    }

    // Div
    public AndrebaAlex_Matrix div (AndrebaAlex_Matrix b) {
        return mul(b.inverted());
    }

    // Scalar div
    public AndrebaAlex_Matrix scalarDiv (AndrebaAlex_Matrix b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) / b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix scalarDiv (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) / b.get(col);
            }
        };
    }

    public AndrebaAlex_Matrix scalarDiv (double b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) / b;
            }
        };
    }

    public <T extends Number> AndrebaAlex_Matrix scalarDiv (T b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(row,col) / b.doubleValue();
            }
        };
    }

    // Scalar inv div
    public AndrebaAlex_Matrix scalarInvDiv (AndrebaAlex_Vector b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return b.get(col) / AndrebaAlex_Matrix.this.get(row,col);
            }
        };
    }

    public AndrebaAlex_Matrix scalarInvDiv (double b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return b / AndrebaAlex_Matrix.this.get(row,col);
            }
        };
    }

    public <T extends Number> AndrebaAlex_Matrix scalarInvDiv (T b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return b.doubleValue() / AndrebaAlex_Matrix.this.get(row,col);
            }
        };
    }

    // Pow
    public AndrebaAlex_Matrix pow (int b) {
        AndrebaAlex_Matrix r = this;
        for (int i=1;i<b;i++) {
            r = r.mul(r);
        }

        return r;
    }

    public AndrebaAlex_Matrix scalarPow (double b) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return Math.pow(AndrebaAlex_Matrix.this.get(row, col), b);
            }
        };
    }

    // Exp
    public AndrebaAlex_Matrix exp () {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return Math.exp(AndrebaAlex_Matrix.this.get(row, col));
            }
        };
    }

    // ln
    public AndrebaAlex_Matrix ln () {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return Math.log(AndrebaAlex_Matrix.this.get(row, col));
            }
        };
    }

    // Tanh
    public AndrebaAlex_Matrix tanh () {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return Math.tanh(AndrebaAlex_Matrix.this.get(row, col));
            }
        };
    }

    // Abs
    public AndrebaAlex_Matrix abs () {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return Math.abs(AndrebaAlex_Matrix.this.get(row, col));
            }
        };
    }

    // Round
    public AndrebaAlex_Matrix round () {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return Math.round(AndrebaAlex_Matrix.this.get(row, col));
            }
        };
    }

    public AndrebaAlex_Matrix round (int to) {
        double k = Math.pow(10,to);

        return scalarMul(k).round().scalarDiv(k);
    }

    // Clamp
    public AndrebaAlex_Matrix clamp (double min, double max) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                double v = AndrebaAlex_Matrix.this.get(row, col);
                return Math.max(min, Math.min(v, max));
            }
        };
    }

    public AndrebaAlex_Matrix clamp (double value, boolean isMax) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                double v = AndrebaAlex_Matrix.this.get(row, col);
                return isMax ? Math.min(v,value) : Math.max(v,value);
            }
        };
    }

    // Applying function
    public AndrebaAlex_Matrix applying (Function<Double, Double> func) {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return func.apply(AndrebaAlex_Matrix.this.get(row, col));
            }
        };
    }

    public AndrebaAlex_Matrix applyingVectors (Function<AndrebaAlex_Vector, AndrebaAlex_Vector> func) {
        AndrebaAlex_Vector[] vectors = new AndrebaAlex_Vector[rows];
        for (int i=0;i<rows;i++) {
            vectors[i] = func.apply(get(i));
        }

        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                return vectors[row].get(col);
            }
        };
    }

    // Transposed
    public AndrebaAlex_Matrix transposed () {
        return new AndrebaAlex_Matrix(cols, rows) {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix.this.get(col, row);
            }
        };
    }

    // Determinant
    public double determinant () {
        if (cols == 1) {
            return get(0, 0);
        } else if (cols == 2) {
            return get(0,0) * get(1, 1) - get(0, 1) * get(1, 0);
        }

        double result = 0;

        for (int i=0;i<cols;i++) {
            int k = i;
            AndrebaAlex_Matrix matrix = new AndrebaAlex_Matrix(rows-1, cols-1) {
                @Override
                public double get(int row, int col) {
                    return AndrebaAlex_Matrix.this.get(row + 1, col < k ? col : col + 1);
                }
            };

            double mul = get(0, i);
            if (i%2 == 1) {
                mul = -mul;
            }

            result += mul * matrix.determinant();
        }

        return result;
    }

    // Inverted
    public AndrebaAlex_Matrix inverted () {
        return scalarMul(1 / determinant());
    }

    // Identity
    public AndrebaAlex_Matrix identity () {
        return new AndrebaAlex_Matrix(rows, cols) {
            @Override
            public double get(int row, int col) {
                if (row == col) {
                    return 1;
                }

                return 0;
            }
        };
    }

    // Self sum
    public AndrebaAlex_Vector getRowSum () {
        return new AndrebaAlex_Vector(rows) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Matrix.this.get(pos).getSum();
            }
        };
    }

    public double getSum () {
        return getRowSum().getSum();
    }

    // Mean
    public AndrebaAlex_Vector getRowMean () {
        return new AndrebaAlex_Vector(rows) {
            @Override
            public double get(int pos) {
                return AndrebaAlex_Matrix.this.get(pos).getMean();
            }
        };
    }

    public double getMean () {
        return getSum() / (rows * cols);
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AndrebaAlex_Matrix)) {
            return false;
        }

        AndrebaAlex_Matrix mo = (AndrebaAlex_Matrix)o;
        if (rows != mo.rows || cols != mo.cols) {
            return false;
        }

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (get(i,j) != mo.get(i,j)) {
                    return false;
                }
            }
        }

        return true;
    }

    // String
    @Override
    public String toString() {
        String r = "("+rows+", "+cols+") { ";
        for (int i=0;i<rows;i++) {
            if (i > 0){
                r += ", ";
            }
            r += "{ ";

            for (int j=0;j<cols;j++) {
                if (j > 0){
                    r += ", ";
                }
                r += get(i,j);
            }

            r += " }";
        }

        return r + " }";
    }

    public ByteBuffer getByteBuffer() {
        byte[] vector = this.toVector().getByteBuffer().array();
        ByteBuffer bb = ByteBuffer.allocate(4 + vector.length);

        bb.putInt(0,cols);
        bb.put(4, vector);
        return bb;
    }

    public byte[] getBytes () {
        return getByteBuffer().array();
    }
}
