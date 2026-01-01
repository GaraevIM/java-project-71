package hexlet.formatters;

import hexlet.DiffNode;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PlainFormatter implements DiffFormatter {

    @Override
    public String format(List<DiffNode> tree) {
        String result = render(tree, "");
        return result.trim();
    }

    private String render(List<DiffNode> nodes, String path) {
        StringBuilder sb = new StringBuilder();

        for (DiffNode node : nodes) {
            String propertyPath = path.isEmpty() ? node.key() : path + "." + node.key();

            switch (node.status()) {
                case NESTED -> sb.append(render(node.children(), propertyPath));
                case ADDED -> sb.append("Property '").append(propertyPath).append("' was added with value: ")
                        .append(formatValue(node.value2())).append("\n");
                case REMOVED -> sb.append("Property '").append(propertyPath).append("' was removed").append("\n");
                case CHANGED -> sb.append("Property '").append(propertyPath).append("' was updated. From ")
                        .append(formatValue(node.value1())).append(" to ").append(formatValue(node.value2()))
                        .append("\n");
                case UNCHANGED -> {
                }
                default -> throw new IllegalStateException("Unknown status: " + node.status());
            }
        }

        return sb.toString();
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
