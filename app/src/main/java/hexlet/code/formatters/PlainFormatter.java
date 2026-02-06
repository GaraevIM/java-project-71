package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PlainFormatter implements DiffFormatter {

    @Override
    public String format(List<DiffNode> tree) {
        return renderNodes(tree, "");
    }

    private String renderNodes(List<DiffNode> nodes, String path) {
        return nodes.stream()
                .flatMap(node -> renderNode(node, path).stream())
                .collect(Collectors.joining("\n"));
    }

    private List<String> renderNode(DiffNode node, String path) {
        String currentPath = path.isEmpty() ? node.key() : path + "." + node.key();

        return switch (node.status()) {
            case NESTED -> {
                String nested = renderNodes(node.children(), currentPath);
                yield nested.isEmpty() ? List.of() : List.of(nested);
            }
            case ADDED -> List.of(
                    "Property '" + currentPath + "' was added with value: " + formatValue(node.value2())
            );
            case REMOVED -> List.of(
                    "Property '" + currentPath + "' was removed"
            );
            case CHANGED -> List.of(
                    "Property '" + currentPath + "' was updated. From "
                            + formatValue(node.value1()) + " to " + formatValue(node.value2())
            );
            case UNCHANGED -> List.of();
        };
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            return "[complex value]";
        }
        if (value instanceof String s) {
            return "'" + s + "'";
        }
        return String.valueOf(value);
    }
}
