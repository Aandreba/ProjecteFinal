package OpenGL.Extras.Matrix;

import Matrix.AndrebaAlex_StatMatrix;

public class AndrebaAlex_StatMatrix4 extends AndrebaAlex_StatMatrix {
    public AndrebaAlex_StatMatrix4() {
        super (4, 4);
    }

    public AndrebaAlex_StatMatrix4(double[][] vals) {
        super (4, 4);
        this.values = vals;
    }

    public AndrebaAlex_Matrix4 sum (AndrebaAlex_Matrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix4.this.get(row, col) + b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix4 sum (AndrebaAlex_StatMatrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix4.this.get(row, col) + b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix4 subtr (AndrebaAlex_Matrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix4.this.get(row, col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix4 subtr (AndrebaAlex_StatMatrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix4.this.get(row, col) - b.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix4 mul (AndrebaAlex_Matrix4 b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                double sum = 0;

                for (int k=0;k<4;k++) {
                    sum += AndrebaAlex_StatMatrix4.this.get(row,k) * b.get(k,col);
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
                    sum += AndrebaAlex_StatMatrix4.this.get(row,k) * b.get(k,col);
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
                return AndrebaAlex_StatMatrix4.this.get(row,col) * b;
            }
        };
    }

    @Override
    public <T extends Number> AndrebaAlex_Matrix4 scalarMul (T b) {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix4.this.get(row,col) * b.doubleValue();
            }
        };
    }

    public AndrebaAlex_Matrix4 transposed () {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix4.this.get(col, row);
            }
        };
    }

    @Override
    public double determinant() {
        double result = 0;

        for (int i=0;i<4;i++) {
            int k = i;
            AndrebaAlex_Matrix3 matrix = new AndrebaAlex_Matrix3() {
                @Override
                public double get(int row, int col) {
                    return AndrebaAlex_StatMatrix4.this.get(row + 1, col < k ? col : col + 1);
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

    public AndrebaAlex_Matrix4 inverted () {
        return scalarMul(1 / determinant());
    }

    public AndrebaAlex_Matrix4 toRelative () {
        return new AndrebaAlex_Matrix4() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix4.this.get(row, col);
            }
        };
    }

    public AndrebaAlex_Matrix3 toMatrix3 () {
        return new AndrebaAlex_Matrix3() {
            @Override
            public double get(int row, int col) {
                return AndrebaAlex_StatMatrix4.this.get(row, col);
            }
        };
    }

    @Override
    public AndrebaAlex_StatMatrix4 toStatic () {
        AndrebaAlex_StatMatrix4 ret = new AndrebaAlex_StatMatrix4();

        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                ret.set(i,j, get(i,j));
            }
        }

        return ret;
    }
}
