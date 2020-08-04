import edu.princeton.cs.introcs.In;
import javafx.util.Pair;

import java.net.URL;
import java.util.*;

public class Driver {

    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("kap1.txt");

        if (url != null) {
            System.out.println("Reading from: " + url);
        } else {
            System.out.println("Couldn't find file: kap1.txt");
        }

        In input = new In(url);

        // INSTANSIERING
        Trie wordTrie = new Trie('\0', 0); // \0 innebär tom karaktär

        while (!input.isEmpty()) {
            String line = input.readLine().trim();
            String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
            String lastOfLine = words[words.length - 1];

            if (lastOfLine.endsWith(".")) {
                words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
            }

            for (String word : words) {
                String word2 = word.replaceAll("\"|\\(|\\)", "").toLowerCase();

                if (word2.isEmpty()) {
                    continue;
                }

                System.out.println(word2);
                // Add the word to the trie
                wordTrie.put(word2);
            }
        }
        //Perform analysis

        //1) 2) //

        List<Pair<String, Integer>> words = new ArrayList<>();


        for (Iterator<Map.Entry<String, Integer>> iterator = wordTrie.iterator("");  // new iterator
             iterator.hasNext();
            //iterator = wordTrie.iterator (iterator.next().getKey())
                ) {
            // new iterator will loop through all nodes, until the iterator will be null, cause there is no more elements
            Map.Entry<String, Integer> currentNode = iterator.next(); // currentNode gets the next node the iterator is at
            if (currentNode.getValue() > 0) { // then it's a word
                words.add(new Pair<String, Integer>(currentNode.getKey(), currentNode.getValue())); //
            }

//            iterator = wordTrie.iterator(currentNode.getKey()); //
        }

        words.sort(new Comparator<Pair<String, Integer>>() { //  anonymous class
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) { //increased order, least frequent first
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        // words.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue())); lambda expression
        System.out.println();
        System.out.println("The 10 least frequent words are: ");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + " least frequent word is: " + words.get(i).getKey() +
                    " with value of " + words.get(i).getValue());
        }
        System.out.println();
        System.out.println("The 10 most frequent words are:");
        System.out.println();

        // for (int i = words.size()- 1; i > words.size() - 11; i--  ){
        int i = 0;
        while (i < 10) {
            Pair<String, Integer> word = words.get(words.size() - 1 - i);
            System.out.println((i + 1) + " most frequent word is: " + word.getKey() +
                    " with value of " + word.getValue());
            i++;
        }

        //3) //

        int bestCandidateCount = -1;
        String highFrequencyPrefix = "";
        for (char pos1 = 'a'; pos1 <= 'z'; pos1++) {
            for (char pos2 = 'a'; pos2 <= 'z'; pos2++) {
                int candidate = wordTrie.count("" + pos1 + pos2); //gör en char till en string
                if (candidate > bestCandidateCount) {
                    bestCandidateCount = candidate;
                    highFrequencyPrefix = "" + pos1 + pos2; // " " ANNARS BLIR DET SUMMA, gör char till string

                }
            }
        }

        System.out.println();
        System.out.println("The most frequent prefix is : " + highFrequencyPrefix);
        System.out.println("And the amount of times it is being used : " + bestCandidateCount);


        // 4) //

        int bestCandidateDistinct = -1;
        String mostPopularFirstLetter = "";
        for (char pos = 'a'; pos <= 'z'; pos++) {
            int candidate = wordTrie.distinct("" + pos);
            if (candidate > bestCandidateDistinct) {
                bestCandidateDistinct = candidate;
                mostPopularFirstLetter = "" + pos;
            }
        }

        System.out.println();
        System.out.println("The most common letter that words starts with : " + mostPopularFirstLetter);
        System.out.println("And the amount of times it is being used : " + bestCandidateDistinct);


    }
}
