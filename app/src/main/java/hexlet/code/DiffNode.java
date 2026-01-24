package hexlet.code;

import java.util.List;

public record DiffNode(
        String key,
        NodeStatus status,
        Object value1,
        Object value2,
        List<DiffNode> children
) {
}
