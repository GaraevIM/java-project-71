package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class StylishFormatter implements DiffFormatter {

    private static final int INDENT_SIZE = 4;

    private static final int SIGN_OFFSET = 2;

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
        String currentIndent = " ".repeat(depth * INDENT_SIZE);
        String signIndent = " ".repeat(depth * INDENT_SIZE - SIGN_OFFSET);

        return switch (node.status()) {
            case UNCHANGED -> currentIndent + node.key() + ": " + formatValue(node.value1(), depth);
            case ADDED -> signIndent + "+ " + node.key() + ": " + formatValue(node.value2(), depth);
            case REMOVED -> signIndent + "- " + node.key() + ": " + formatValue(node.value1(), depth);
            case CHANGED -> signIndent + "- " + node.key() + ": " + formatValue(node.value1(), depth)
                    + "\n"
                    + signIndent + "+ " + node.key() + ": " + formatValue(node.value2(), depth);
            case NESTED -> currentIndent + node.key() + ": {\n"
                    + renderNodes(node.children(), depth + 1)
                    + "\n"
                    + currentIndent + "}";
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
        Map<String, Object> sorted = new TreeMap<>();
        for (var entry : map.entrySet()) {
            sorted.put(String.valueOf(entry.getKey()), entry.getValue());
        }

        String entryIndent = " ".repeat((depth + 1) * INDENT_SIZE);
        String closingIndent = " ".repeat(depth * INDENT_SIZE);

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        var lines = sorted.entrySet().stream()
                .map(e -> entryIndent + e.getKey() + ": " + formatValue(e.getValue(), depth + 1))
                .toList();

        sb.append(String.join("\n", lines));
        sb.append("\n").append(closingIndent).append("}");

        return sb.toString();
    }
}
