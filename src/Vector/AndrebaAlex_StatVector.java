package Vector;

import java.nio.ByteBuffer;

public class AndrebaAlex_StatVector extends AndrebaAlex_RelVector {
    protected double[] values;

    public AndrebaAlex_StatVector(int length) {
        super(length);
        this.values = new double[length];
    }

    public AndrebaAlex_StatVector(double... values) {
        super(values.length);
        this.values = values;
    }

    public AndrebaAlex_StatVector(long... values) {
        super(values.length);
        this.values = new double[values.length];

        for (int i=0;i<values.length;i++) {
            this.values[i] = values[i];
        }
    }

    public AndrebaAlex_StatVector(float... values) {
        super(values.length);
        this.values = new double[values.length];

        for (int i=0;i<values.length;i++) {
            this.values[i] = values[i];
        }
    }

    public AndrebaAlex_StatVector(int... values) {
        super(values.length);
        this.values = new double[values.length];

        for (int i=0;i<values.length;i++) {
            this.values[i] = values[i];
        }
    }

    public static AndrebaAlex_StatVector random (int length, double from, double to) {
        return AndrebaAlex_Vector.random(length, from, to).toStatic();
    }

    public static AndrebaAlex_StatVector random (int length) {
        return AndrebaAlex_Vector.random(length).toStatic();
    }

    @Override
    public double get(int pos) {
        return this.values[pos];
    }

    @Override
    public double[] toArray() {
        return values;
    }

    @Override
    public void set (int pos, double value) {
        this.values[pos] = value;
    }

    public <T extends Number> void set (int pos, T value) {
        this.values[pos] = value.doubleValue();
    }

    public static AndrebaAlex_StatVector fromByteBuffer (ByteBuffer bb) {
        AndrebaAlex_StatVector ret = new AndrebaAlex_StatVector(bb.capacity() / 8);

        for (int i=0;i<ret.length;i++) {
            ret.set(i, bb.getDouble(8*i));
        }

        return ret;
    }

    public static AndrebaAlex_StatVector fromBytes (byte[] bytes) {
        return fromByteBuffer(ByteBuffer.wrap(bytes));
    }

    public AndrebaAlex_StatVector clone () {
        AndrebaAlex_StatVector ret = new AndrebaAlex_StatVector(length);

        for (int i=0;i<length;i++) {
            ret.set(i, get(i));
        }

        return ret;
    }
}
