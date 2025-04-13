package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {

    private long milliseconds;

    public TimeDuration(String value) {
        super(value);
        this.milliseconds = parseToMilliseconds(value);
    }

    private long parseToMilliseconds(String input) {
        input = input.trim().toLowerCase();

        if (input.endsWith("ms")) {
            return Long.parseLong(input.replace("ms", ""));
        }
        if (input.endsWith("s")) {
            return (long) (Double.parseDouble(input.replace("s", "")) * 1000);
        }
        if (input.endsWith("sec")) {
            return (long) (Double.parseDouble(input.replace("sec", "")) * 1000);
        }
        if (input.endsWith("min")) {
            return (long) (Double.parseDouble(input.replace("min", "")) * 60 * 1000);
        }
        if (input.endsWith("minutes")) {
            return (long) (Double.parseDouble(input.replace("minutes", "")) * 60 * 1000);
        }

        return Long.parseLong(input); // assume milliseconds if no unit
    }

    public long getMilliseconds() {
        return this.milliseconds;
    }
}
