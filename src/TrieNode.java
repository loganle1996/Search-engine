import java.util.*;

public class TrieNode implements Comparable<TrieNode> {
    Map<Character, TrieNode> links;
    int hitTimes = 0;
    String s;
    List<TrieNode> hottests;
    private Set<String> hotSet;
    private int topK;

    TrieNode(int k) {
        hottests = new ArrayList<>();
        links = new HashMap<>();
        hotSet = new HashSet<>();
        this.topK = k;
    }

    public void updateTopSentences(TrieNode node) {
        if (!hotSet.contains(node.s)) {
            hottests.add(node);
            hotSet.add(node.s);
        }
        Collections.sort(hottests);
        if (hottests.size() > topK) {
            String removed = hottests.remove(topK).s; //remove the last word
            hotSet.remove(removed);
        }
    }

    @Override
    public int compareTo(TrieNode o) {
        return o.hitTimes == this.hitTimes ? s.compareTo(o.s) : Integer.compare(o.hitTimes, this.hitTimes);
    }
}
