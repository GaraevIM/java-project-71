package hexlet.code;

import hexlet.code.formatters.DiffFormatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import java.util.List;

public final class Formatter {

    private Formatter() {
    }

    public static String format(List<DiffNode> tree, String formatName) {
        DiffFormatter formatter = getFormatter(formatName);
        return formatter.format(tree);
    }

    private static DiffFormatter getFormatter(String formatName) {
        String normalized = (formatName == null) ? "" : formatName.trim();

        return switch (normalized) {
            case "", "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            case "json" -> new JsonFormatter();
            default -> throw new IllegalArgumentException("Unknown format: " + formatName);
        };
    }
}
