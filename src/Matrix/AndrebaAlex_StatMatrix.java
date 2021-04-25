package Matrix;

import Vector.AndrebaAlex_StatVector;
import Vector.AndrebaAlex_Vector;

import java.nio.ByteBuffer;

public class AndrebaAlex_StatMatrix extends AndrebaAlex_RelMatrix {
    protected double[][] values;

    public AndrebaAlex_StatMatrix(int rows, int cols) {
        super(rows, cols);
        this.values = new double[rows][cols];
    }

    public AndrebaAlex_StatMatrix(double[]... values) {
        super(values.length, values[0].length);
        this.values = values;
    }

    public static AndrebaAlex_StatMatrix fromByteBuffer (ByteBuffer bb) {
        int cols = bb.getInt(0);
        int rows = (bb.capacity() - 4) / (8 * cols);

        AndrebaAlex_StatVector ret = new AndrebaAlex_StatVector(rows * cols);

        for (int i=0;i<ret.getLength();i++) {
            ret.set(i, bb.getDouble(4 + 8*i));
        }

        return ret.toMatrix(cols).toStatic();
    }

    public static AndrebaAlex_StatMatrix fromBytes (byte[] bytes) {
        return fromByteBuffer(ByteBuffer.wrap(bytes));
    }

    public static AndrebaAlex_StatMatrix random (int rows, int cols, double from, double to) {
        return AndrebaAlex_Matrix.random(rows, cols, from, to).toStatic();
    }

    public static AndrebaAlex_StatMatrix random (int rows, int cols) {
        return AndrebaAlex_Matrix.random(rows, cols).toStatic();
    }

    @Override
    public double get(int row, int col) {
        return this.values[row][col];
    }

    @Override
    public void set (int row, int col, double value) {
        this.values[row][col] = value;
    }

    public void set (int row, AndrebaAlex_Vector value) {
        this.values[row] = value.toArray();
    }

    public void set (int row, double... values) {
        this.values[row] = values;
    }

    public <T extends Number> void set (int row, int col, T value) {
        this.values[row][col] = value.doubleValue();
    }

    @Override
    public double[][] toArray() {
        return values;
    }

    public AndrebaAlex_StatMatrix clone () {
        AndrebaAlex_StatMatrix ret = new AndrebaAlex_StatMatrix(rows, cols);

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                ret.set(i,j, get(i,j));
            }
        }

        return ret;
    }
}
