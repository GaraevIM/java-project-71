package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
        name = "app",
        mixinStandardHelpOptions = true,          // добавляет -h/--help и -V/--version
        version = "app 1.0",
        description = "Demo CLI application based on picocli example."
)
public class App implements Callable<Integer> {

    @Option(
            names = {"-n", "--name"},
            paramLabel = "NAME",
            description = "Name to greet. Default: ${DEFAULT-VALUE}"
    )
    private String name = "World";

    @Override
    public Integer call() {
        System.out.println("Hello, " + name + "!");
        return 0; // код выхода 0 = OK
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
