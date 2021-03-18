import java.util.*;

public class TrieNode implements Comparable<TrieNode> {
    Map<Character, TrieNode> links;
    int hitTimes = 0;
    String s;
    List<TrieNode> hotWords;
    private Set<String> hotSet;
    private int topK;

    TrieNode(int k) {
        hotWords = new ArrayList<>();
        this.topK = k;
    }

    void insertWord(TrieNode node) {
        if (!hotSet.contains(node.s)) {
            hotWords.add(node);
            hotSet.add(node.s);
        }
        Collections.sort(hotWords);
        if (hotWords.size() > topK) {
            String removed = hotWords.remove(topK).s; //remove the last word
            hotSet.remove(removed);
        }
    }

    @Override
    public int compareTo(TrieNode o) {
        return o.hitTimes == this.hitTimes ? s.compareTo(o.s) : Integer.compare(o.hitTimes, this.hitTimes);
    }
}
