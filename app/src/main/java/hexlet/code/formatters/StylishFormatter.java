package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

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
            case UNCHANGED -> currentIndent + node.key() + ": " + formatValue(node.value1());
            case ADDED -> signIndent + "+ " + node.key() + ": " + formatValue(node.value2());
            case REMOVED -> signIndent + "- " + node.key() + ": " + formatValue(node.value1());
            case CHANGED -> signIndent + "- " + node.key() + ": " + formatValue(node.value1())
                    + "\n"
                    + signIndent + "+ " + node.key() + ": " + formatValue(node.value2());
            case NESTED -> currentIndent + node.key() + ": {\n"
                    + renderNodes(node.children(), depth + 1)
                    + "\n"
                    + currentIndent + "}";
        };
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        return String.valueOf(value);
    }
}
