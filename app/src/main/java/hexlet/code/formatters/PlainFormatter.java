package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

public final class PlainFormatter implements DiffFormatter {

    @Override
    public String format(List<DiffNode> tree) {
        return renderNodes(tree, "");
    }

    private String renderNodes(List<DiffNode> nodes, String path) {
        return nodes.stream()
                .map(node -> renderNode(node, path))
                .filter(s -> !s.isEmpty())
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");
    }

    private String renderNode(DiffNode node, String path) {
        String fullPath = path.isEmpty() ? node.key() : path + "." + node.key();

        return switch (node.status()) {
            case ADDED -> "Property '" + fullPath + "' was added with value: " + formatValue(node.value2());
            case REMOVED -> "Property '" + fullPath + "' was removed";
            case CHANGED -> "Property '" + fullPath + "' was updated. From " + formatValue(node.value1())
                    + " to " + formatValue(node.value2());
            case NESTED -> renderNodes(node.children(), fullPath);
            case UNCHANGED -> "";
        };
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String s) {
            return "'" + s + "'";
        }
        if (value instanceof List<?> || value instanceof java.util.Map<?, ?>) {
            return "[complex value]";
        }
        return String.valueOf(value);
    }
}
