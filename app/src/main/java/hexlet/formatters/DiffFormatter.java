package hexlet.formatters;

import hexlet.DiffNode;
import java.util.List;

public interface DiffFormatter {
    String format(List<DiffNode> tree);
}
