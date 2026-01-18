package hexlet;

import java.util.List;
import java.util.Map;

public final class Differ {

    private Differ() {
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> m1 = Parser.parse(filePath1);
        Map<String, Object> m2 = Parser.parse(filePath2);

        List<DiffNode> tree = DiffTreeBuilder.build(m1, m2);

        return Formatter.format(tree, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
