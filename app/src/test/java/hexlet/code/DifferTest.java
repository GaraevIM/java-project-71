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

    @Test
    void testGenerateDiffForFlatJson() throws Exception {
        var filePath1 = getFixturePath("file1.json").toString();
        var filePath2 = getFixturePath("file2.json").toString();

        var expected = Files.readString(getFixturePath("expected.txt"))
                .replace("\r\n", "\n")
                .trim();
        var actual = Differ.generate(filePath1, filePath2)
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expected, actual);
    }
}
