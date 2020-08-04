import java.util.*;

public class Trie {

    private char key; //key
    private int value; //value
    private List<Trie> childNodes = new ArrayList<>();

    public Trie(char key, int value) {
        this.key = key;
        this.value = value;

    }
    Iterator<Trie> getChildNodes(){

        return childNodes.iterator(); // konverterar listan till en iterator som man kan använda i iteratorklassen
    }
    char getKey (){
        return key;
    }
    int getValue (){
        return value;
    }

    public void put(String key) {
        if (key.equals("")) { //basfall om vi hittar noden
            value++;
            return; //ist för else
        }
        char firstChar = key.charAt(0); //rekursiva fallet
        for (int i = 0; i < childNodes.size(); i++) {
            Trie child = childNodes.get(i);
            if (child.key == firstChar) {
                child.put(key.substring(1));
                return;
            }
        }
        Trie child = new Trie(firstChar, 0); // om vi inte hittar förgående noder, om vi vill lägga till på d och a ej existerar
        childNodes.add(child);
        childNodes.sort(Comparator.comparingInt(Trie::getKey)); //sorterar efter ascii
        child.put(key.substring(1));

    }

    public int get(String key) {
        if (key.equals("")) {
            return value;
        }
        char firstChar = key.charAt(0);
        for (int i = 0; i < childNodes.size(); i++) {
            Trie child = childNodes.get(i);
            if (child.key == firstChar) {
                return child.get(key.substring(1));
            }
        }
        return 0;
    }

    public int count(String key) {
        if (key.equals("")) {
            int sum = value; // första nodens värde
            for (int i = 0; i < childNodes.size(); i++) {
                Trie child = childNodes.get(i);
                sum = sum + child.count(key); //barnnodernas värde
            }
            return sum;
        }
        char firstChar = key.charAt(0); // försöker hitta nästa nod
        for (int i = 0; i < childNodes.size(); i++) {
            Trie child = childNodes.get(i);
            if (child.key == firstChar) {
                return child.count(key.substring(1)); //hittar nästa nod, kör summan rekursivt
            }
        }
        return 0; //uteslutit de övriga fallen, hittar ingen mer nod.
    }


    public int distinct(String key) {
        if (key.equals("")) {
            int count= 0;
            if(value != 0) {
                count++;
            }
                for (int i = 0; i < childNodes.size(); i++) {
                    Trie child = childNodes.get(i);
                    count += child.distinct(key);
                }
                return count;
        }
        char firstChar = key.charAt(0); // försöker hitta nästa nod
        for (int i = 0; i < childNodes.size(); i++) {
            Trie child = childNodes.get(i);
            if (child.key == firstChar) {
                return child.distinct(key.substring(1)); //hittar nästa nod, kör summan rekursivt
            }
        }
        return 0; //uteslutit de övriga fallen, hittar ingen mer nod.
    }

    public  Iterator<Map.Entry<String, Integer>> iterator (String key){

        if (key.equals("")) {
            return new TrieIterator(this, "");
        }
        char firstChar = key.charAt(0);
        for (int i = 0; i < childNodes.size(); i++) {
            Trie child = childNodes.get(i);
            if (child.key == firstChar) {
                return child.iterator(key.substring(1));
            }
        }
        return new Iterator<Map.Entry<String, Integer>>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Map.Entry<String, Integer> next() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
