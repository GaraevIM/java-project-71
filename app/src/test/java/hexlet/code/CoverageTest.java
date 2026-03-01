package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoverageTest {

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int THREE = 3;

    private static final int FOUR = 4;

    private static final int FIVE = 5;

    private static final int TEN = 10;

    @Test
    void parserShouldParseJsonAndYaml() throws Exception {
        Map<String, Object> json = Parser.parse("{\"a\": 1}", "json");
        assertEquals(ONE, ((Number) json.get("a")).intValue());

        Map<String, Object> yaml = Parser.parse("a: 1\n", "yml");
        assertEquals(ONE, ((Number) yaml.get("a")).intValue());
    }

    @Test
    void parserShouldThrowOnUnknownFormat() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("a: 1\n", "xml"));
    }

    @Test
    void diffTreeBuilderShouldBuildAllStatuses() {
        Map<String, Object> m1 = Map.of(
                "a", ONE,
                "b", Map.of("x", ONE),
                "removed", TEN
        );

        Map<String, Object> m2 = Map.of(
                "a", ONE,
                "b", Map.of("x", TWO),
                "c", THREE
        );

        List<DiffNode> tree = DiffTreeBuilder.build(m1, m2);

        assertEquals("a", tree.get(ZERO).key());
        assertEquals(NodeStatus.UNCHANGED, tree.get(ZERO).status());

        assertEquals("b", tree.get(ONE).key());
        assertEquals(NodeStatus.NESTED, tree.get(ONE).status());
        assertEquals(ONE, tree.get(ONE).children().size());
        assertEquals(NodeStatus.CHANGED, tree.get(ONE).children().get(ZERO).status());

        assertEquals("c", tree.get(TWO).key());
        assertEquals(NodeStatus.ADDED, tree.get(TWO).status());

        assertEquals("removed", tree.get(THREE).key());
        assertEquals(NodeStatus.REMOVED, tree.get(THREE).status());
    }

    @Test
    void stylishFormatterShouldRenderAllNodeTypes() {
        List<DiffNode> tree = List.of(
                new DiffNode("same", NodeStatus.UNCHANGED, ONE, null, List.of()),
                new DiffNode("added", NodeStatus.ADDED, null, TWO, List.of()),
                new DiffNode("removed", NodeStatus.REMOVED, THREE, null, List.of()),
                new DiffNode("changed", NodeStatus.CHANGED, FOUR, FIVE, List.of()),
                new DiffNode("nested", NodeStatus.NESTED, null, null, List.of(
                        new DiffNode("inside", NodeStatus.ADDED, null, "v", List.of())
                ))
        );

        String out = new StylishFormatter().format(tree);

        assertTrue(out.contains("same: 1"));
        assertTrue(out.contains("+ added: 2"));
        assertTrue(out.contains("- removed: 3"));
        assertTrue(out.contains("- changed: 4"));
        assertTrue(out.contains("+ changed: 5"));
        assertTrue(out.contains("nested: {"));
        assertTrue(out.contains("+ inside: v"));
    }

    @Test
    void plainFormatterShouldRenderBasicCases() {
        List<DiffNode> tree = List.of(
                new DiffNode("a", NodeStatus.ADDED, null, "x", List.of()),
                new DiffNode("b", NodeStatus.REMOVED, ONE, null, List.of()),
                new DiffNode("c", NodeStatus.CHANGED, TWO, THREE, List.of())
        );

        String out = new PlainFormatter().format(tree);

        assertTrue(out.contains("Property 'a' was added with value: 'x'"));
        assertTrue(out.contains("Property 'b' was removed"));
        assertTrue(out.contains("Property 'c' was updated. From 2 to 3"));
    }

    @Test
    void jsonFormatterShouldProduceValidJson() throws Exception {
        String json = new JsonFormatter().format(List.of());
        JsonNode node = new ObjectMapper().readTree(json);
        assertTrue(node.isArray());
        assertEquals(ZERO, node.size());
    }

    @Test
    void formatterShouldTrimFormatName() {
        List<DiffNode> tree = List.of(new DiffNode("a", NodeStatus.ADDED, null, ONE, List.of()));
        assertDoesNotThrow(() -> Formatter.format(tree, " stylish "));
        assertDoesNotThrow(() -> Formatter.format(tree, " plain "));
        assertDoesNotThrow(() -> Formatter.format(tree, " json "));
    }
}
