package hexlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class Parser {

    private Parser() {
    }

    public static Map<String, Object> parse(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        ObjectMapper mapper = getMapper(path);
        String content = Files.readString(path);

        return mapper.readValue(content, new TypeReference<Map<String, Object>>() {
        });
    }

    private static ObjectMapper getMapper(Path path) {
        String name = path.getFileName().toString().toLowerCase();

        if (name.endsWith(".yml") || name.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory());
        }

        return new ObjectMapper();
    }
}
