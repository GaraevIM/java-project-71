package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable<Integer> {

    @Option(
            names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]"
    )
    private String format = "stylish";

    @Parameters(index = "0", paramLabel = "filepath1",
            description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2",
            description = "path to second file")
    private String filepath2;

    @Override
    public Integer call() throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data1 = mapper.readValue(content1, new TypeReference<Map<String, Object>>() {});
        Map<String, Object> data2 = mapper.readValue(content2, new TypeReference<Map<String, Object>>() {});

        System.out.println("Files parsed successfully:");
        System.out.println("  " + path1);
        System.out.println("  " + path2);
        System.out.println("Chosen format: " + format);

        return 0;
    }

    public static void main(String[] args) {
        try {
            int exit = new CommandLine(new App()).execute(args);
            System.exit(exit);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
