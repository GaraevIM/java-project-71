package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CoverageTest {

    @Test
    void parserShouldParseJsonAndYaml() throws Exception {
        Map<String, Object> json = Parser.parse("{\"a\": 1}", "json");
        assertEquals(1, ((Number) json.get("a")).intValue());

        Map<String, Object> yaml = Parser.parse("a: 1\n", "yml");
        assertEquals(1, ((Number) yaml.get("a")).intValue());
    }

    @Test
    void parserShouldThrowOnUnknownFormat() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("a: 1\n", "xml"));
    }

    @Test
    void diffTreeBuilderShouldBuildAllStatuses() {
        Map<String, Object> m1 = Map.of(
                "a", 1,
                "b", Map.of("x", 1),
                "removed", 10
        );

        Map<String, Object> m2 = Map.of(
                "a", 1,
                "b", Map.of("x", 2),
                "c", 3
        );

        List<DiffNode> tree = DiffTreeBuilder.build(m1, m2);

        assertEquals("a", tree.get(0).key());
        assertEquals(NodeStatus.UNCHANGED, tree.get(0).status());

        assertEquals("b", tree.get(1).key());
        assertEquals(NodeStatus.NESTED, tree.get(1).status());
        assertEquals(1, tree.get(1).children().size());
        assertEquals(NodeStatus.CHANGED, tree.get(1).children().get(0).status());

        assertEquals("c", tree.get(2).key());
        assertEquals(NodeStatus.ADDED, tree.get(2).status());

        assertEquals("removed", tree.get(3).key());
        assertEquals(NodeStatus.REMOVED, tree.get(3).status());
    }

    @Test
    void stylishFormatterShouldRenderAllNodeTypes() {
        List<DiffNode> tree = List.of(
                new DiffNode("same", NodeStatus.UNCHANGED, 1, null, List.of()),
                new DiffNode("added", NodeStatus.ADDED, null, 2, List.of()),
                new DiffNode("removed", NodeStatus.REMOVED, 3, null, List.of()),
                new DiffNode("changed", NodeStatus.CHANGED, 4, 5, List.of()),
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
                new DiffNode("b", NodeStatus.REMOVED, 1, null, List.of()),
                new DiffNode("c", NodeStatus.CHANGED, 2, 3, List.of())
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
        assertEquals(0, node.size());
    }

    @Test
    void formatterShouldTrimFormatName() throws Exception {
        List<DiffNode> tree = List.of(new DiffNode("a", NodeStatus.ADDED, null, 1, List.of()));
        assertDoesNotThrow(() -> Formatter.format(tree, " stylish "));
        assertDoesNotThrow(() -> Formatter.format(tree, " plain "));
        assertDoesNotThrow(() -> Formatter.format(tree, " json "));
    }
}