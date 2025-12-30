package hexlet.code;

import hexlet.Differ;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private Path getFixturePath(String fileName) {
        return Path.of("src", "test", "resources", fileName)
                .toAbsolutePath()
                .normalize();
    }

    private String readFixture(String fileName) throws Exception {
        return Files.readString(getFixturePath(fileName))
                .replace("\r\n", "\n")
                .trim();
    }

    @Test
    void testGenerateDiffForFlatJson() throws Exception {
        var filePath1 = getFixturePath("file1.json").toString();
        var filePath2 = getFixturePath("file2.json").toString();

        var expected = readFixture("expected.txt");
        var actual = Differ.generate(filePath1, filePath2)
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }

    @Test
    void testGenerateDiffForFlatYaml() throws Exception {
        var filePath1 = getFixturePath("file1.yml").toString();
        var filePath2 = getFixturePath("file2.yml").toString();

        var expected = readFixture("expected_yml.txt");
        var actual = Differ.generate(filePath1, filePath2)
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }
}
