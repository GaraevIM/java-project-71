package hexlet.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.DiffNode;
import java.util.List;

public final class JsonFormatter implements DiffFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    @Override
    public String format(List<DiffNode> tree) {
        try {
            return MAPPER.writeValueAsString(tree);
        } catch (Exception e) {
            throw new RuntimeException("Failed to format diff as json", e);
        }
    }
}
