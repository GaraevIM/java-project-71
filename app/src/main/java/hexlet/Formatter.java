package hexlet;

import hexlet.formatters.DiffFormatter;
import hexlet.formatters.PlainFormatter;
import hexlet.formatters.StylishFormatter;
import java.util.List;

public final class Formatter {

    private Formatter() {
    }

    public static String format(List<DiffNode> tree, String formatName) {
        DiffFormatter formatter = getFormatter(formatName);
        return formatter.format(tree);
    }

    private static DiffFormatter getFormatter(String formatName) {
        if (formatName == null || formatName.isBlank() || formatName.equals("stylish")) {
            return new StylishFormatter();
        }
        if (formatName.equals("plain")) {
            return new PlainFormatter();
        }
        throw new IllegalArgumentException("Unknown format: " + formatName);
    }
}
