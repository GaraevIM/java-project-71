package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.DiffNode;
import java.util.List;

public final class JsonFormatter implements DiffFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    @Override
    public String format(List<DiffNode> tree) throws Exception {
        return MAPPER.writeValueAsString(tree);
    }
}
