package hexlet;

import hexlet.formatters.DiffFormatter;
import hexlet.formatters.JsonFormatter;
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
        String normalized = (formatName == null) ? "" : formatName.trim();

        return switch (normalized) {
            case "", "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            case "json" -> new JsonFormatter();
            default -> throw new IllegalArgumentException("Unknown format: " + formatName);
        };
    }
}
