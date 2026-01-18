package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.Differ;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



class DifferTest {

    private Path getFixturePath(String name) {
        return Path.of("src", "test", "resources", "fixtures", name)
                .toAbsolutePath()
                .normalize();
    }

    private String read(Path path) throws IOException {
        return Files.readString(path).replace("\r\n", "\n");
    }

    @Test
    void testDefaultFormatJsonInput() throws Exception {
        Path first = getFixturePath("file1.json");
        Path second = getFixturePath("file2.json");
        Path expectedPath = getFixturePath("expected.txt");

        String expected = read(expectedPath).trim();
        String actual = Differ.generate(first.toString(), second.toString())
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testStylishFormatJsonInput() throws Exception {
        Path first = getFixturePath("file1.json");
        Path second = getFixturePath("file2.json");
        Path expectedPath = getFixturePath("expected.txt");

        String expected = read(expectedPath).trim();
        String actual = Differ.generate(first.toString(), second.toString(), "stylish")
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testPlainFormatJsonInput() throws Exception {
        Path first = getFixturePath("file1.json");
        Path second = getFixturePath("file2.json");
        Path expectedPath = getFixturePath("expected_plain.txt");

        String expected = read(expectedPath).trim();
        String actual = Differ.generate(first.toString(), second.toString(), "plain")
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testJsonFormatJsonInput() throws Exception {
        Path first = getFixturePath("file1.json");
        Path second = getFixturePath("file2.json");
        Path expectedPath = getFixturePath("expected_json.json");

        String actual = Differ.generate(first.toString(), second.toString(), "json");
        String expected = read(expectedPath);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualNode = mapper.readTree(actual);
        JsonNode expectedNode = mapper.readTree(expected);

        assertEquals(expectedNode, actualNode);
    }

    @Test
    void testDefaultFormatYamlInput() throws Exception {
        Path first = getFixturePath("file1.yml");
        Path second = getFixturePath("file2.yml");
        Path expectedPath = getFixturePath("expected.txt");

        String expected = read(expectedPath).trim();
        String actual = Differ.generate(first.toString(), second.toString())
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testStylishFormatYamlInput() throws Exception {
        Path first = getFixturePath("file1.yml");
        Path second = getFixturePath("file2.yml");
        Path expectedPath = getFixturePath("expected.txt");

        String expected = read(expectedPath).trim();
        String actual = Differ.generate(first.toString(), second.toString(), "stylish")
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testPlainFormatYamlInput() throws Exception {
        Path first = getFixturePath("file1.yml");
        Path second = getFixturePath("file2.yml");
        Path expectedPath = getFixturePath("expected_plain.txt");

        String expected = read(expectedPath).trim();
        String actual = Differ.generate(first.toString(), second.toString(), "plain")
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testJsonFormatYamlInput() throws Exception {
        Path first = getFixturePath("file1.yml");
        Path second = getFixturePath("file2.yml");
        Path expectedPath = getFixturePath("expected_json.json");

        String actual = Differ.generate(first.toString(), second.toString(), "json");
        String expected = read(expectedPath);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualNode = mapper.readTree(actual);
        JsonNode expectedNode = mapper.readTree(expected);

        assertEquals(expectedNode, actualNode);
    }
}
