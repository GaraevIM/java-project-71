package hexlet;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public final class StylishFormatter implements Formatter {

    private static final int INDENT_SIZE = 4;

    @Override
    public String format(List<DiffNode> tree) {
        return "{\n" + renderNodes(tree, 1) + "\n}";
    }

    private String renderNodes(List<DiffNode> nodes, int depth) {
        return nodes.stream()
                .map(node -> renderNode(node, depth))
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");
    }

    private String renderNode(DiffNode node, int depth) {
        String lineIndent = " ".repeat(depth * INDENT_SIZE);
        String signIndent = " ".repeat(depth * INDENT_SIZE - 2);

        return switch (node.status()) {
            case UNCHANGED -> lineIndent + node.key() + ": " + formatValue(node.value1(), depth);
            case ADDED -> signIndent + "+ " + node.key() + ": " + formatValue(node.value2(), depth);
            case REMOVED -> signIndent + "- " + node.key() + ": " + formatValue(node.value1(), depth);
            case CHANGED -> signIndent + "- " + node.key() + ": " + formatValue(node.value1(), depth)
                    + "\n"
                    + signIndent + "+ " + node.key() + ": " + formatValue(node.value2(), depth);
            case NESTED -> lineIndent + node.key() + ": {\n"
                    + renderNodes(node.children(), depth + 1)
                    + "\n"
                    + lineIndent + "}";
        };
    }

    private String formatValue(Object value, int depth) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map<?, ?> map) {
            return formatMap(map, depth);
        }
        return String.valueOf(value);
    }

    private String formatMap(Map<?, ?> map, int depth) {
        var keys = new TreeSet<String>();
        for (Object k : map.keySet()) {
            keys.add(String.valueOf(k));
        }

        String currentIndent = " ".repeat((depth + 1) * INDENT_SIZE);
        String closingIndent = " ".repeat(depth * INDENT_SIZE);

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        boolean first = true;
        for (String key : keys) {
            if (!first) {
                sb.append("\n");
            }
            first = false;

            Object v = map.get(key);
            sb.append(currentIndent).append(key).append(": ");
            if (v instanceof Map<?, ?> nested) {
                sb.append(formatMap(nested, depth + 1));
            } else if (v == null) {
                sb.append("null");
            } else {
                sb.append(String.valueOf(v));
            }
        }

        sb.append("\n").append(closingIndent).append("}");
        return sb.toString();
    }
}
