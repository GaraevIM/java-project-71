package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

public interface DiffFormatter {
    String format(List<DiffNode> tree);
}
