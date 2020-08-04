import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

public class TrieIterator implements Iterator<Map.Entry<String, Integer>> {
    public Trie currentNode;
    private boolean hasReturnedCurrent = false;
    private Iterator<Trie> directChildNodes; // barnen
    private TrieIterator deepChildNodes; // barnen och barnbarnen etc fast ett barn åt gången
    private String prefix; // ha med hela barnets släkthistoria

    TrieIterator(Trie currentNode, String prefix) { //Iterator<Trie> directChildNodes) {
        this.currentNode = currentNode;
        this.directChildNodes = currentNode.getChildNodes();
        this.prefix = prefix + currentNode.getKey();
    }

    @Override
    public boolean hasNext() {
        return directChildNodes.hasNext() || (deepChildNodes != null && deepChildNodes.hasNext());
    }

    @Override
    public Map.Entry<String, Integer> next() {
        if (!hasReturnedCurrent){
            hasReturnedCurrent = true;
            return new AbstractMap.SimpleImmutableEntry<String, Integer>(
                    prefix,
                    currentNode.getValue()
            );
        }
        if (deepChildNodes != null && deepChildNodes.hasNext()) { //första fall (rekursivt
            return deepChildNodes.next(); //returnerar nästa barn
        }
        deepChildNodes = new TrieIterator(directChildNodes.next(), prefix); // updaterar till barnens barnnoder
        return deepChildNodes.next();
    }
}
