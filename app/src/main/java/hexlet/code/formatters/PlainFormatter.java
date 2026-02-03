package hexlet.code.formatters;

import hexlet.code.DiffNode;
import hexlet.code.NodeStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PlainFormatter implements DiffFormatter {

    @Override
    public String format(List<DiffNode> tree) {
        List<String> lines = new ArrayList<>();
        buildLines(tree, "", lines);
        return String.join("\n", lines);
    }

    private void buildLines(List<DiffNode> nodes, String parentPath, List<String> lines) {
        for (DiffNode node : nodes) {
            String propertyPath = parentPath.isEmpty()
                    ? node.key()
                    : parentPath + "." + node.key();

            NodeStatus status = node.status();

            switch (status) {
                case NESTED -> buildLines(node.children(), propertyPath, lines);
                case ADDED -> lines.add(addedLine(propertyPath, node.value2()));
                case REMOVED -> lines.add("Property '" + propertyPath + "' was removed");
                case CHANGED -> lines.add(changedLine(propertyPath, node.value1(), node.value2()));
                case UNCHANGED -> {
                }
                default -> {
                }
            }
        }
    }

    private String addedLine(String path, Object value) {
        return "Property '" + path + "' was added with value: " + formatValue(value);
    }

    private String changedLine(String path, Object before, Object after) {
        return "Property '" + path + "' was updated. From "
                + formatValue(before)
                + " to "
                + formatValue(after);
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
