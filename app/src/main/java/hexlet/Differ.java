package hexlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public final class Differ {

    private Differ() { }

    public static String generate(String filePath1, String filePath2) throws Exception {
        Path p1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path p2 = Paths.get(filePath2).toAbsolutePath().normalize();

        String c1 = Files.readString(p1);
        String c2 = Files.readString(p2);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> m1 = mapper.readValue(c1, new TypeReference<Map<String, Object>>() { });
        Map<String, Object> m2 = mapper.readValue(c2, new TypeReference<Map<String, Object>>() { });

        var keys = new TreeSet<String>();
        keys.addAll(m1.keySet());
        keys.addAll(m2.keySet());

        var sb = new StringBuilder();
        sb.append("{\n");

        for (String key : keys) {
            boolean in1 = m1.containsKey(key);
            boolean in2 = m2.containsKey(key);
            Object v1 = m1.get(key);
            Object v2 = m2.get(key);

            if (in1 && in2) {
                if (Objects.equals(v1, v2)) {
                    sb.append("    ").append(key).append(": ").append(stringify(v1)).append("\n");
                } else {
                    sb.append("  - ").append(key).append(": ").append(stringify(v1)).append("\n");
                    sb.append("  + ").append(key).append(": ").append(stringify(v2)).append("\n");
                }
            } else if (in1) {
                sb.append("  - ").append(key).append(": ").append(stringify(v1)).append("\n");
            } else {
                sb.append("  + ").append(key).append(": ").append(stringify(v2)).append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String s) {
            return s;
        }
        return String.valueOf(value);
    }
}
