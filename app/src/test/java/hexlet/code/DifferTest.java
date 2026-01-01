package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



class DifferTest {

    private Path getFixturePath(String fileName) {
        return Path.of("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath()
                .normalize();
    }

    private String readFile(Path path) throws IOException {
        return Files.readString(path).replace("\r\n", "\n");
    }

    @Test
    void testGenerateStylishNestedStructures() throws Exception {
        Path file1 = getFixturePath("file1.json");
        Path file2 = getFixturePath("file2.json");
        Path expectedPath = getFixturePath("expected.txt");

        String expected = readFile(expectedPath).trim();
        String actual = Differ.generate(file1.toString(), file2.toString())
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testGeneratePlainFormat() throws Exception {
        Path file1 = getFixturePath("file1.json");
        Path file2 = getFixturePath("file2.json");
        Path expectedPath = getFixturePath("expected_plain.txt");

        String expected = readFile(expectedPath).trim();
        String actual = Differ.generate(file1.toString(), file2.toString(), "plain")
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testGenerateJsonFormat() throws Exception {
        Path file1 = getFixturePath("file1.json");
        Path file2 = getFixturePath("file2.json");
        Path expectedPath = getFixturePath("expected_json.json");

        String actual = Differ.generate(file1.toString(), file2.toString(), "json");
        String expected = readFile(expectedPath);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode actualNode = mapper.readTree(actual);
        JsonNode expectedNode = mapper.readTree(expected);

        assertEquals(expectedNode, actualNode);
    }

}
