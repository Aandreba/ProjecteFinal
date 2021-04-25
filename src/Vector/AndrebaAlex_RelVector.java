package Vector;

public abstract class AndrebaAlex_RelVector extends AndrebaAlex_Vector {
    public AndrebaAlex_RelVector(int length) {
        super(length);
    }

    public abstract void set (int pos, double value);

    public void set (AndrebaAlex_Vector vector) {
        int k = Math.min(length, vector.getLength());

        for (int i=0;i<k;i++) {
            set(i, vector.get(i));
        }
    }

    public void set (int start, AndrebaAlex_Vector vector) {
        int k = Math.min(length - start, vector.getLength());

        for (int i=start;i<k;i++) {
            set(i, vector.get(i));
        }
    }

    public void add (AndrebaAlex_Vector b) {
        for (int i=0;i<length;i++) {
            set(i, get(i) + b.get(i));
        }
    }

    public void add (double b) {
        for (int i=0;i<length;i++) {
            set(i, get(i) + b);
        }
    }

    public <T extends Number> void add (T b) {
        for (int i=0;i<length;i++) {
            set(i, get(i) + b.doubleValue());
        }
    }
}
