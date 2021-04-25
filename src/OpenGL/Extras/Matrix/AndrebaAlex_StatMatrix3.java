package OpenGL.Extras.Matrix;

import Matrix.AndrebaAlex_StatMatrix;

public class AndrebaAlex_StatMatrix3 extends AndrebaAlex_StatMatrix {
    public AndrebaAlex_StatMatrix3() {
        super (3, 3);
    }

    public AndrebaAlex_StatMatrix3(double[][] vals) {
        super (3, 3);
        this.values = vals;
    }

    public AndrebaAlex_Matrix3 sum (AndrebaAlex_Matrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix3.this.get(row, col) + b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 sum (AndrebaAlex_StatMatrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix3.this.get(row, col) + b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 subtr (AndrebaAlex_Matrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix3.this.get(row, col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 subtr (AndrebaAlex_StatMatrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix3.this.get(row, col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 mul (AndrebaAlex_Matrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                double sum = 0;

                for (int k=0;k<4;k++) {
                    sum += AndrebaAlex_StatMatrix3.this.get(row,k) * b.get(k,col);
                }

                return sum;
            }
        };
    }

    public AndrebaAlex_Matrix3 mul (AndrebaAlex_StatMatrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                double sum = 0;

                for (int k=0;k<4;k++) {
                    sum += AndrebaAlex_StatMatrix3.this.get(row,k) * b.get(k,col);
                }

                return sum;
            }
        };
    }

    @Override
    public AndrebaAlex_Matrix3 scalarMul (double b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix3.this.get(row,col) * b;
            }
        };
    }

    @Override
    public <T extends Number> AndrebaAlex_Matrix3 scalarMul (T b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix3.this.get(row,col) * b.doubleValue();
            }
        };
    }

    public AndrebaAlex_Matrix3 transposed () {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix3.this.get(col, row);
            }
        };
    }

    @Override
    public double determinant() {
        double res = get(0,0) * (get(1, 1) * get(2, 2) - get(1, 2) * get(2, 1));
        res -= get(0, 1) * (get(1, 0) * get(2, 2) - get(1, 2) * get(2, 0));
        res += get(0, 2) * (get(1, 0) * get(2, 1) - get(1, 1) * get(2,0));

        return res;
    }

    public AndrebaAlex_Matrix3 inverted () {
        return scalarMul(1 / determinant());
    }

    public AndrebaAlex_Matrix3 toRelative () {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix3.this.get(row, col);
            }
        };
    }

    @Override
    public AndrebaAlex_StatMatrix3 toStatic() {
        AndrebaAlex_StatMatrix3 ret = new AndrebaAlex_StatMatrix3();
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                ret.set(i, j, get(i, j));
            }
        }

        return ret;
    }
}
