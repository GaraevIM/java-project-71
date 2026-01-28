package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PlainFormatter implements DiffFormatter {

    @Override
    public String format(List<DiffNode> tree) {
        List<String> lines = new ArrayList<>();
        buildLines(tree, "", lines);

        if (lines.isEmpty()) {
            return "";
        }

        return String.join("\n", lines) + "\n";
    }

    private void buildLines(List<DiffNode> nodes, String path, List<String> lines) {
        for (DiffNode node : nodes) {
            String property = path.isEmpty() ? node.key() : path + "." + node.key();

            switch (node.status()) {
                case NESTED -> buildLines(node.children(), property, lines);
                case ADDED -> lines.add("Property '" + property + "' was added with value: " + formatValue(node.value2()));
                case REMOVED -> lines.add("Property '" + property + "' was removed");
                case CHANGED -> lines.add("Property '" + property + "' was updated. From " + formatValue(node.value1())
                        + " to " + formatValue(node.value2()));
                case UNCHANGED -> { }
                default -> throw new IllegalStateException("Unknown status: " + node.status());
            }
        }
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String s) {
            return "'" + s + "'";
        }
        return String.valueOf(value);
    }
}
