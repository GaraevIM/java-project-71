package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

public final class StylishFormatter implements DiffFormatter {

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
            case UNCHANGED -> lineIndent + node.key() + ": " + formatValue(node.value1());
            case ADDED -> signIndent + "+ " + node.key() + ": " + formatValue(node.value2());
            case REMOVED -> signIndent + "- " + node.key() + ": " + formatValue(node.value1());
            case CHANGED -> signIndent + "- " + node.key() + ": " + formatValue(node.value1())
                    + "\n"
                    + signIndent + "+ " + node.key() + ": " + formatValue(node.value2());
            case NESTED -> lineIndent + node.key() + ": {\n"
                    + renderNodes(node.children(), depth + 1)
                    + "\n"
                    + lineIndent + "}";
            default -> throw new IllegalStateException("Unexpected status: " + node.status());
        };
    }

    private String formatValue(Object value) {
        return value == null ? "null" : String.valueOf(value);
    }
}
