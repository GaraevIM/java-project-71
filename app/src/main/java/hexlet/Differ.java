package hexlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public final class Differ {

    private Differ() {
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> m1 = Parser.parse(filePath1);
        Map<String, Object> m2 = Parser.parse(filePath2);

        List<DiffNode> tree = buildTree(m1, m2);

        return Formatter.format(tree, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static List<DiffNode> buildTree(Map<String, Object> m1, Map<String, Object> m2) {
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
                    @SuppressWarnings("unchecked")
                    Map<String, Object> cm1 = (Map<String, Object>) map1;
                    @SuppressWarnings("unchecked")
                    Map<String, Object> cm2 = (Map<String, Object>) map2;

                    result.add(new DiffNode(key, NodeStatus.NESTED, null, null, buildTree(cm1, cm2)));
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
}
