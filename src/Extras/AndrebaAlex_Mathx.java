package Extras;

public class AndrebaAlex_Mathx {
    final public static float PI = (float) Math.PI;

    public interface SimpleFunction {
        double apply (double value);
    }

    public interface IntegerFunction {
        double apply (int position);
    }

    public static float pow (float x, float n) { return (float) Math.pow(x, n); }

    public static float sin (float x) {
        return (float) Math.sin(x);
    }

    public static float sin (double x) {
        return (float) Math.sin(x);
    }

    public static float cos (float x) {
        return (float) Math.cos(x);
    }

    public static float cos (double x) {
        return (float) Math.cos(x);
    }

    public static float tan (double x) {
        return (float) Math.tan(x);
    }

    public static double roundTo (double val, int decimals) {
        double pow = Math.pow(10, decimals);
        return Math.round(val * pow) / pow;
    }

    public static float roundTo (float val, int decimals) {
        float pow = pow(10, decimals);
        return Math.round(val * pow) / pow;
    }
}
