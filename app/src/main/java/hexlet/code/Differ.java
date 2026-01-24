package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public final class Differ {

    private Differ() {
    }

    public static String generate(String path1, String path2, String format) throws Exception {
        String input1 = read(path1);
        String input2 = read(path2);

        String type1 = detectType(path1);
        String type2 = detectType(path2);

        Map<String, Object> m1 = Parser.parse(input1, type1);
        Map<String, Object> m2 = Parser.parse(input2, type2);

        List<DiffNode> tree = DiffTreeBuilder.build(m1, m2);

        return Formatter.format(tree, format);
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }

    private static String read(String sourcePath) throws Exception {
        Path path = Path.of(sourcePath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    private static String detectType(String sourcePath) {
        String name = Path.of(sourcePath).getFileName().toString().toLowerCase();

        if (name.endsWith(".yml") || name.endsWith(".yaml")) {
            return "yaml";
        }

        return "json";
    }
}
