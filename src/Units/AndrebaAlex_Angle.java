package Units;

import Extras.AndrebaAlex_Mathx;
import OpenGL.Extras.Vector.AndrebaAlex_Vector2;

import java.io.Serializable;
import java.text.NumberFormat;

public class AndrebaAlex_Angle implements Serializable {
    public enum Type {
        Tau(2 * Math.PI, "τ"),
        Radians(1,"rad"),
        Degrees(Math.PI / 180, "deg");

        double w;
        String s;
        private Type(double w, String s) {
            this.w = w;
            this.s = s;
        }
    }

    private double value;

    public AndrebaAlex_Angle(double rad) {
        this.value = rad;
    }

    public AndrebaAlex_Angle(double v, Type type) {
        this.value = v * type.w;
    }

    // Add
    public AndrebaAlex_Angle add (double rad) {
        return new AndrebaAlex_Angle(this.value + rad);
    }

    public AndrebaAlex_Angle add (AndrebaAlex_Angle angle) {
        return new AndrebaAlex_Angle(this.value + angle.value);
    }

    // Subtr
    public AndrebaAlex_Angle subtr (double rad) {
        return new AndrebaAlex_Angle(this.value - rad);
    }

    public AndrebaAlex_Angle subtr (AndrebaAlex_Angle angle) {
        return new AndrebaAlex_Angle(this.value - angle.value);
    }

    // Mul
    public AndrebaAlex_Angle mul (double rad) {
        return new AndrebaAlex_Angle(this.value * rad);
    }

    public AndrebaAlex_Angle mul (AndrebaAlex_Angle angle) {
        return new AndrebaAlex_Angle(this.value * angle.value);
    }

    // Sin
    public double sin () {
        return Math.sin(value);
    }

    public float sinf () {
        return AndrebaAlex_Mathx.sin(value);
    }

    public double cos () {
        return Math.cos(value);
    }

    public float cosf () {
        return AndrebaAlex_Mathx.cos(value);
    }

    public double tan () {
        return Math.tan(value);
    }

    public float tanf () {
        return AndrebaAlex_Mathx.tan(value);
    }

    public AndrebaAlex_Vector2 normal () {
        return new AndrebaAlex_Vector2() {
            @Override
            public double get (int pos) {
                if (pos == 0) {
                    return AndrebaAlex_Angle.this.cos();
                }

                return AndrebaAlex_Angle.this.sin();
            }
        };
    }

    // Get value
    public double getValue (Type type) {
        return this.value / type.w;
    }

    public double getValue () {
        return this.value;
    }

    public String toString(Type type) {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(1);

        double value = this.value % (2 * Math.PI);
        if (value < 0) {
            value = (2 * Math.PI) + value;
        }

        return format.format(value / type.w) + " " + type.s;
    }

    @Override
    public String toString() {
        return toString(Type.Degrees);
    }
}
