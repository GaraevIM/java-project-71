package hexlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public final class DiffTreeBuilder {

    private DiffTreeBuilder() {
    }

    public static List<DiffNode> build(Map<String, Object> m1, Map<String, Object> m2) {
        var keys = new TreeSet<String>();
        keys.addAll(m1.keySet());
        keys.addAll(m2.keySet());

        List<DiffNode> result = new ArrayList<>();

        for (String key : keys) {
            boolean in1 = m1.containsKey(key);
            boolean in2 = m2.containsKey(key);
            Object v1 = m1.get(key);
            Object v2 = m2.get(key);

            if (in1 && in2) {
                if (v1 instanceof Map<?, ?> map1 && v2 instanceof Map<?, ?> map2) {
                    result.add(new DiffNode(key, NodeStatus.NESTED, null, null, build(castMap(map1), castMap(map2))));
                } else if (Objects.equals(v1, v2)) {
                    result.add(new DiffNode(key, NodeStatus.UNCHANGED, v1, null, List.of()));
                } else {
                    result.add(new DiffNode(key, NodeStatus.CHANGED, v1, v2, List.of()));
                }
            } else if (in1) {
                result.add(new DiffNode(key, NodeStatus.REMOVED, v1, null, List.of()));
            } else {
                result.add(new DiffNode(key, NodeStatus.ADDED, null, v2, List.of()));
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> castMap(Map<?, ?> map) {
        return (Map<String, Object>) map;
    }
}
