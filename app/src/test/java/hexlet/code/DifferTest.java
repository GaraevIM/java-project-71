package hexlet.code;

import hexlet.Differ;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    void testGenerateNestedStructures() throws Exception {
        Path file1 = getFixturePath("file1.json");
        Path file2 = getFixturePath("file2.json");
        Path expectedPath = getFixturePath("expected.txt");

        String expected = readFile(expectedPath).trim();
        String actual = Differ.generate(file1.toString(), file2.toString())
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }
}
