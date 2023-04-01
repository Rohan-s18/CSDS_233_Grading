import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class WordStat {
    private HashTable<Integer> individualCounts;
    private HashTable<Integer> individualRanks;
    private ArrayList<String> wordList;
    private ArrayList<HashTable<Integer>.HashEntry> individuals;

    public WordStat(String file) {
        wordList = (new Tokenizer(file)).wordList();
        individualCounts = new HashTable<Integer>(wordList.size() * 2);
        calcWordStats();
    }

    public WordStat(String[] text) {
        wordList = (new Tokenizer(text)).wordList();
        individualCounts = new HashTable<Integer>(wordList.size() * 2);
        calcWordStats();
    }

    public int wordCount(String word) {
        try {
            return individualCounts.get(word);
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public int wordRank(String word) {
        try {
            return individualRanks.get(word);
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public String[] mostCommonWords(int k) {
        if (k < 0) throw new IllegalArgumentException();
        String[] ret = new String[k];
        for (int i = 0; i < k && i < individuals.size(); i++) {
            ret[i] = individuals.get(i).getKey();
        }
        return ret;
    }

    public String[] leastCommonWords(int k) {
        if (k < 0) throw new IllegalArgumentException();
        String[] ret = new String[k];
        for (int i = 0; i < k && i < individuals.size(); i++) {
            ret[i] = individuals.get(individuals.size() - i - 1).getKey();
        }
        return ret;
    }

    public String[] mostCommonCollocations(int k, String baseWord, boolean precede) {
        if (k < 0) throw new IllegalArgumentException();
        String[] ret = new String[k];
        int pos = 0;
        int baseWordIndex = wordList.indexOf(baseWord);
        if (baseWordIndex == -1) throw new IllegalArgumentException(); // not found
        int i = precede ? -1 : 1;
        baseWordIndex += i;
        // Start from most frequent word, and add to ret if in the right direction relative to baseWord
        for (int x = 0; x < individuals.size() && pos < k; x++) {
            for (int j = baseWordIndex; j >= 0 && j < wordList.size(); j += i) {
                if (wordList.get(j).equals(individuals.get(x).getKey())) { // Valid collocation
                    ret[pos++] = wordList.get(j);
                    break;
                }
            }
        }
        return ret;
    }

    private void calcWordStats() {
        // Adds individual words (with frequency) to first hash table
        individuals = new ArrayList<HashTable<Integer>.HashEntry>();
        for (String word : wordList) {
            individualCounts.put(word, 1);
            if (individualCounts.get(word) == 1) { // Unique word
                individuals.add(individualCounts.new HashEntry(word, 1));
            }
        }
        for (HashTable<Integer>.HashEntry entry : individuals) {
            entry.setValue(individualCounts.get(entry.getKey()));
        }
        Collections.sort(individuals); // Sort by descending frequency
        // Adds individual words (with rank) to second hash table
        individualRanks = new HashTable<Integer>();
        int rank = 1;
        for (int i = 0; i < individuals.size(); i++) {
            individualRanks.put(individuals.get(i).getKey(), rank);
            // Don't increment rank if it's the same as the next entry
            if (i + 1 < individuals.size() && individuals.get(i+1).getValue() != individuals.get(i).getValue()) rank = i+2;
        }
    }
}
