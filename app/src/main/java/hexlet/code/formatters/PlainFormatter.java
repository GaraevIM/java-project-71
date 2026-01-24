package hexlet.code.formatters;

import hexlet.code.DiffNode;
import hexlet.code.NodeStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PlainFormatter implements DiffFormatter {

    @Override
    public String format(List<DiffNode> tree) {
        List<String> lines = renderLines(tree, "");
        return String.join("\n", lines);
    }

    private List<String> renderLines(List<DiffNode> nodes, String path) {
        List<String> lines = new ArrayList<>();

        for (DiffNode node : nodes) {
            String propertyPath = path.isEmpty() ? node.key() : path + "." + node.key();
            NodeStatus status = node.status();

            if (status == NodeStatus.NESTED) {
                lines.addAll(renderLines(node.children(), propertyPath));
                continue;
            }

            if (status == NodeStatus.ADDED) {
                lines.add("Property '" + propertyPath + "' was added with value: " + formatValue(node.value2()));
            } else if (status == NodeStatus.REMOVED) {
                lines.add("Property '" + propertyPath + "' was removed");
            } else if (status == NodeStatus.CHANGED) {
                lines.add("Property '" + propertyPath + "' was updated. From " + formatValue(node.value1())
                        + " to " + formatValue(node.value2()));
            }
        }

        return lines;
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (value instanceof String s) {
            return "'" + s + "'";
        }
        return Objects.toString(value);
    }
}
