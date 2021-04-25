package OpenGL.Extras.Matrix;

import Matrix.AndrebaAlex_Matrix;

public abstract class AndrebaAlex_Matrix4 extends AndrebaAlex_Matrix {
    final public static AndrebaAlex_StatMatrix4 identity = new AndrebaAlex_StatMatrix4(new double[][]{ {1,0,0,0}, {0,1,0,0}, {0,0,1,0}, {0,0,0,1} });

    public AndrebaAlex_Matrix4() {
        super(4,4);
    }

    public AndrebaAlex_Matrix4 sum (AndrebaAlex_Matrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix4.this.get(row, col) + b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix4 sum (AndrebaAlex_StatMatrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix4.this.get(row, col) + b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix4 subtr (AndrebaAlex_Matrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix4.this.get(row, col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix4 subtr (AndrebaAlex_StatMatrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix4.this.get(row, col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix4 mul (AndrebaAlex_Matrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                double sum = 0;

                for (int k=0;k<b.rows;k++) {
                    sum += AndrebaAlex_Matrix4.this.get(row,k) * b.get(k,col);
                }

                return sum;
            }
        };
    }

    public AndrebaAlex_Matrix4 mul (AndrebaAlex_StatMatrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                double sum = 0;

                for (int k=0;k<4;k++) {
                    sum += AndrebaAlex_Matrix4.this.get(row,k) * b.get(k,col);
                }

                return sum;
            }
        };
    }

    @Override
    public AndrebaAlex_Matrix4 scalarMul (double b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix4.this.get(row,col) * b;
            }
        };
    }

    @Override
    public <T extends Number> AndrebaAlex_Matrix4 scalarMul (T b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix4.this.get(row,col) * b.doubleValue();
            }
        };
    }

    public AndrebaAlex_Matrix4 transposed () {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix4.this.get(col, row);
            }
        };
    }

    // Identity
    @Override
    public AndrebaAlex_StatMatrix4 identity () {
        return identity;
    }

    // Determinnat
    @Override
    public double determinant() {
        double result = 0;

        for (int i=0;i<4;i++) {
            int k = i;
            AndrebaAlex_Matrix3 matrix = new AndrebaAlex_Matrix3() {
                @Override
                public double get(int row, int col) {
                    return AndrebaAlex_Matrix4.this.get(row + 1, col < k ? col : col + 1);
                }
            };

            double mul = get(0, i);
            if (i == 1 || i == 3) {
                mul = -mul;
            }

            result += mul * matrix.determinant();
        }

        return result;
    }

    // Inverted
    public AndrebaAlex_Matrix4 inverted () {
        return scalarMul(1 / determinant());
    }

    @Override
    public AndrebaAlex_StatMatrix4 toStatic() {
        AndrebaAlex_StatMatrix4 ret = new AndrebaAlex_StatMatrix4();
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                ret.set(i, j, get(i, j));
            }
        }

        return ret;
    }

    public AndrebaAlex_Matrix3 toMatrix3 () {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_Matrix4.this.get(row, col);
            }
        };
    }
}
