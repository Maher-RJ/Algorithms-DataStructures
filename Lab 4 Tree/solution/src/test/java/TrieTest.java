import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

public class TrieTest {
    private Trie trie;

    @Before
    public void setUp() throws Exception {
        trie = new Trie('0', 0);
        trie.put("ab");
        trie.put("ac");
        trie.put("ac");
        trie.put("b");
        trie.put("baf");
        trie.put("baf");
        trie.put("baf");
        trie.put("bad");
        trie.put("bc");
        trie.put("bc");
        trie.put("bc");
        trie.put("bc");
    }

    @Test
    public void get() {
        Assert.assertEquals(0, trie.get("f"));
        Assert.assertEquals(0, trie.get("a"));
        Assert.assertEquals(1, trie.get("ab"));
        Assert.assertEquals(2, trie.get("ac"));
    }

    @Test
    public void count() {
        Assert.assertEquals(0, trie.count("f"));
        Assert.assertEquals(4, trie.count("ba"));
        Assert.assertEquals(9, trie.count("b"));
    }

    @Test
    public void distinct() {
        Assert.assertEquals(0, trie.distinct("f"));
        Assert.assertEquals(2, trie.distinct("ba"));
        Assert.assertEquals(4, trie.distinct("b"));

        trie.put("bc");
        Assert.assertEquals(4, trie.distinct("b"));
    }

    @Test
    public void iterator() {
        Iterator<Map.Entry<String, Integer>> iterator = trie.iterator("b");
        assertNext(new AbstractMap.SimpleImmutableEntry<>("b", 1), iterator);
        assertNext(new AbstractMap.SimpleImmutableEntry<>("ba", 0), iterator);
        assertNext(new AbstractMap.SimpleImmutableEntry<>("bad", 1), iterator);
        assertNext(new AbstractMap.SimpleImmutableEntry<>("baf", 3), iterator);
        assertNext(new AbstractMap.SimpleImmutableEntry<>("bc", 4), iterator);
        Assert.assertFalse(iterator.hasNext());

        trie.iterator("").next();
    }

    private void assertNext(Map.Entry<String, Integer> expected, Iterator<Map.Entry<String, Integer>> iterator) {
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(expected, iterator.next());
    }
}
