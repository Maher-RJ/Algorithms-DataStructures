
import se.kth.id1020.*;

public class Proj1algos {

    public static void main(String[] args) throws Exception {
        TinySearchEngineBase searchEngine = new TinySearchEngine();

        Driver.run(searchEngine);
    }

}


import java.util.*;
import se.kth.id1020.*;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

public class TinySearchEngine implements TinySearchEngineBase{
    private IndexOfDocs ID = new IndexOfDocs();


    public void insert(Word word, Attributes attr){

        ID.insert(word, attr);

    }

    public List<Document> search(String query) {
        String[] queries = query.split(" ");
        int priority = 0;
        int upDown = 0;
        int instructions = 0;

        if (querises.length > 1) {
            if (queries[0].equals("popularity") || queries[1].equals("popularity")) {
                priority = 0;
                instructions++;
            }
            if (queries[0].equals("count") || queries[1].equals("count")) {
                priority = 1;
                instructions++;
            }
            if (queries[0].equals("occurence") || queries[1].equals("occurence")) {
                priority = 2;
                instructions++;
            }
            if (queries[1].equals("asc") || queries[0].equals("asc")) {
                upDown = 0;
                instructions++;
            }
            if (queries[1].equals("desc") || queries[0].equals("desc")) {
                upDown = 1;
                instructions++;
            }

        }

        List<WordHolde> holders = ID.getHolders(queries[instructions]);
        if (queries.length - 1 - instructions > 0) {
            for (int i = instructions + 1; i <= queries.length - 1; i++) {
                List<WordHolde> holders2 = ID.getHolders(queries[i]);
                holders = merge(holders, holders2);
            }
        }


        if (instructions == 0) {

            return getL(holders);
        }
        union(holders, priority);
        return bubbleSort(holders, priority, upDown);

    }

    private List<Document> getL(List<WordHolde> holders) {
        List<Document> docs = new ArrayList<Document>();
        for(int i = 0; i <= holders.size() - 1; i++){
            docs.add(holders.get(i).getDoc());
        }
        return docs;
    }



    private List<Document> bubbleSort(List<WordHolde> wh, int prio, int ascend){
        int R = wh.size() - 1;
        boolean swapped = true;
        while(R >= 0 && swapped == true){
            swapped = false;
            for(int i = 0; i < R; i++){
                if(ascend == 0 && wh.get(i).priority(prio) > wh.get(i + 1).priority(prio) || ascend == 1 && wh.get(i).priority(prio) < wh.get(i + 1).priority(prio)){
                    WordHolde w = wh.get(i);
                    wh.set(i, wh.get(i + 1));
                    wh.set(i + 1, w);
                    swapped = true;
                }
            }
            R--;
        }
        return getL(wh);

    }

    private List<WordHolde> merge(List<WordHolde> ett, List<WordHolde> tva){
        for(int i = 0; i <= tva.size() - 1; i++){
            ett.add(tva.get(i));
        }
        return ett;
    }

    private void union(List<WordHolde> list, int prio){
        for(int i = 0; i <= list.size() - 2; i++){
            WordHolde wh = list.get(i);
            for(int j = 0; j <= list.size() - 1; j++){
                if(i == j){
                    continue;
                }
                if(wh.getDoc().name.compareTo(list.get(j).getDoc().name) == 0){
                    if(wh.priority(prio) > list.get(j).priority(prio)){
                        list.remove(j);
                    }else{
                        list.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
    }
}



import java.util.*;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

public class IndexOfDocs {
    private List<DocContainer> list = new ArrayList<DocContainer>();

    public void insert(Word word, Attributes attr) {

        for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i).getDoc().compareTo(attr.document) == 0) {
                list.get(i).addWord(word, attr);
                return;
            }
        }
        DocContainer add = new DocContainer(attr.document);
        add.addWord(word, attr);
        list.add(add);

    }

    public List<Document> getList(String query){
        System.out.println(list.size());
        List<Document> lista = new ArrayList<Document>();

        String s = list.get(0).getHolders().get(3).getWord().word;
        for(int i = 0; i <= list.size() - 1; i++){
            for(int j = 0; j <= list.get(i).getHolders().size() - 1; j++){
                if(query.compareTo(list.get(i).getHolders().get(j).getWord().word) == 0){
                    Document doc = list.get(i).getDoc();
                    lista.add(doc);
                    break;
                }
            }
        }
        return lista;
    }


    public List<WordHolde> getHolders(String query) {
        List<WordHolde> holders = new ArrayList<WordHolde>();

        for (int i = 0; i <= list.size() - 1; i++) {
            WordHolde wh = list.get(i).getHolder(query);
            if (wh != null) {
                holders.add(wh);
            }
        }

        return holders;
    }
}



import java.util.*;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

public class DocContainer {
    private List<WordHolde> words = new ArrayList<WordHolde>();
    private Document doc;

    public DocContainer(Document document){
        this.doc = document;
    }

    public Document getDoc(){
        return this.doc;
    }

    public List<WordHolde> getHolders(){
        return this.words;
    }

    public void addWord(Word word, Attributes attr) {

        for (int i = 0; i <= words.size() - 1; i++) {
            int comp = word.word.compareTo(words.get(i).getWord().word);

            if (comp == 0) {
                words.get(i).addAttr(attr);
                return;
            }
            if (comp < 0) {
                WordHolde added = new WordHolde(word, attr);
                added.addAttr(attr);
                words.add(i, added);
                return;
            }
        }

        WordHolde last = new WordHolde(word, attr);
        last.addAttr(attr);
        words.add(last);
    }

    public WordHolde getHolder(String query){
        int ind = getWord(query, words.size() - 1, 0);
        if(ind < 0){
            return null;
        }else{

            return words.get(ind);
        }
    }

    public int getWord(String query, int hi, int lo) {

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comp = words.get(mid).getWord().word.compareTo(query);

            if (comp > 0) {
                hi = mid - 1;
            } else if (comp < 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return - 1;
    }

 

}



import se.kth.id1020.util.Document;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;
import java.util.*;

public class WordHolde {
    private Word word;
    private Document doc;
    private List<Attributes> attr;


    public WordHolde(Word word, Attributes attr){
        this.attr= new ArrayList<Attributes>();
        this.word = word;
        this.doc = attr.document;
    }

    public void addAttr(Attributes attr){
        this.attr.add(attr);
    }

    public Word getWord(){
        return this.word;
    }

    public Document getDoc(){
        return this.doc;
    }

    public int priority(int prio){
        if(prio == 0){
            return doc.popularity;
        }if (prio == 1){
            return attr.size();
        }else{
            return - (attr.get(0).occurrence);
        }
    }

}
