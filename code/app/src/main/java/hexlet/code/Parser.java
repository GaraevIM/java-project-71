package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public final class Parser {

    private Parser() {
    }

    public static Map<String, Object> parse(String input, String dataType) throws Exception {
        ObjectMapper mapper = getMapper(dataType);
        return mapper.readValue(input, new TypeReference<Map<String, Object>>() {
        });
    }

    private static ObjectMapper getMapper(String dataType) {
        String normalized = (dataType == null) ? "" : dataType.trim().toLowerCase();

        return switch (normalized) {
            case "", "json" -> new ObjectMapper();
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalArgumentException("Unsupported data format: " + dataType);
        };
    }
}
