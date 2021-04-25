package Units;

import java.io.Serializable;
import java.text.NumberFormat;

public class AndrebaAlex_Time implements Comparable<AndrebaAlex_Time>, Serializable {
    public enum Type {
        Years(31557600, "y"),
        Months(1036800, "M"),
        Weeks(604800, "w"),
        Days(86400, "d"),
        Hours(3600, "h"),
        Minutes(60, "m"),
        Seconds(1, "s"),
        Milliseconds(1e-3, "ms"),
        Nanoseconds(1e-9, "ns"),
        Picoseconds(1e-12, "ps");

        double w;
        String symbol;
        private Type (double w, String symbol) {
            this.w = w;
            this.symbol = symbol;
        }
    }

    final private double time;

    public AndrebaAlex_Time(double sec) {
        this.time = sec;
    }

    public AndrebaAlex_Time(double time, Type type) {
        this.time = time * type.w;
    }

    // Add
    public AndrebaAlex_Time add (double sec) {
        return new AndrebaAlex_Time(this.time + sec);
    }

    public AndrebaAlex_Time add (AndrebaAlex_Time time) {
        return new AndrebaAlex_Time(this.time + time.time);
    }

    // Subtr
    public AndrebaAlex_Time subtr (double sec) {
        return new AndrebaAlex_Time(this.time - sec);
    }

    public AndrebaAlex_Time subtr (AndrebaAlex_Time time) {
        return new AndrebaAlex_Time(this.time - time.time);
    }

    // Mul
    public AndrebaAlex_Time mul (double sec) {
        return new AndrebaAlex_Time(this.time * sec);
    }

    public AndrebaAlex_Time mul (AndrebaAlex_Time time) {
        return new AndrebaAlex_Time(this.time * time.time);
    }

    // Div
    public AndrebaAlex_Time div (double sec) {
        return new AndrebaAlex_Time(this.time / sec);
    }

    public AndrebaAlex_Time div (AndrebaAlex_Time time) {
        return new AndrebaAlex_Time(this.time / time.time);
    }

    // Get value
    public double getValue (Type type) {
        return time / type.w;
    }

    public double getValue () {
        return time;
    }

    @Override
    public int compareTo(AndrebaAlex_Time o) {
        return Double.compare(getValue(), o.getValue());
    }

    @Override
    public String toString() {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(1);

        double value = this.time;
        String out = "";

        for (Type type: Type.values()) {
            int v = (int)(value / type.w);

            if (v >= 1) {
                out += " "+format.format(v)+" "+type.symbol;
                value -= v * type.w;
            }
        }

        if (out.equals("")) {
            return format.format(getValue(Type.Picoseconds))+" ps";
        }

        return out.substring(1);
    }
}
