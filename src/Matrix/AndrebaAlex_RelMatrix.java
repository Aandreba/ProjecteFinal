package Matrix;

import Vector.AndrebaAlex_RelVector;
import Vector.AndrebaAlex_Vector;

public abstract class AndrebaAlex_RelMatrix extends AndrebaAlex_Matrix {
    public AndrebaAlex_RelMatrix(int rows, int cols) {
        super(rows, cols);
    }

    public abstract void set (int row, int col, double value);

    public void set (int row, AndrebaAlex_Vector value) {
        for (int i=0;i<value.getLength();i++) {
            set(row, i, value.get(i));
        }
    }

    @Override
    public AndrebaAlex_RelVector get (int row) {
        return new AndrebaAlex_RelVector(cols) {
            @Override
            public void set(int pos, double value) {
                AndrebaAlex_RelMatrix.this.set(row, pos, value);
            }

            @Override
            public double get(int pos) {
                return AndrebaAlex_RelMatrix.this.get(row, pos);
            }
        };
    }

    public void add (AndrebaAlex_Matrix b) {
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                set(i, j, get(i,j) + b.get(i,j));
            }
        }
    }

    public void add (double b) {
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                set(i, j, get(i,j) + b);
            }
        }
    }

    public <T extends Number> void add (T b) {
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                set(i, j, get(i,j) + b.doubleValue());
            }
        }
    }
}
