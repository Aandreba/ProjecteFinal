package OpenGL.Extras.Matrix;

import Matrix.AndrebaAlex_Matrix;

public abstract class AndrebaAlex_Matrix3 extends AndrebaAlex_Matrix {
    final public static AndrebaAlex_StatMatrix3 identity = new AndrebaAlex_StatMatrix3(new double[][]{ {1,0,0}, {0,1,0}, {0,0,1} });

    public AndrebaAlex_Matrix3() {
        super(3, 3);
    }

    public AndrebaAlex_Matrix3 sum (AndrebaAlex_Matrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix3.this.get(row, col) + b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 sum (AndrebaAlex_StatMatrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix3.this.get(row, col) + b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 subtr (AndrebaAlex_Matrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix3.this.get(row, col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 subtr (AndrebaAlex_StatMatrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix3.this.get(row, col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 mul (AndrebaAlex_Matrix3 b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                double sum = 0;

                for (int k=0;k<b.rows;k++) {
                    sum += AndrebaAlex_Matrix3.this.get(row,k) * b.get(k,col);
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
                    sum += AndrebaAlex_Matrix3.this.get(row,k) * b.get(k,col);
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
                return AndrebaAlex_Matrix3.this.get(row,col) * b;
            }
        };
    }

    @Override
    public <T extends Number> AndrebaAlex_Matrix3 scalarMul (T b) {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix3.this.get(row,col) * b.doubleValue();
            }
        };
    }

    public AndrebaAlex_Matrix3 transposed () {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix3.this.get(col, row);
            }
        };
    }

    // Identity
    @Override
    public AndrebaAlex_StatMatrix3 identity () {
        return identity;
    }

    // Determinant
    @Override
    public double determinant() {
        double res = get(0,0) * (get(1, 1) * get(2, 2) - get(1, 2) * get(2, 1));
        res -= get(0, 1) * (get(1, 0) * get(2, 2) - get(1, 2) * get(2, 0));
        res += get(0, 2) * (get(1, 0) * get(2, 1) - get(1, 1) * get(2,0));

        return res;
    }

    // Inverted
    public AndrebaAlex_Matrix3 inverted () {
        return scalarMul(1 / determinant());
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
