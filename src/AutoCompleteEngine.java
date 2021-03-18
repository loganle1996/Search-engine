import java.util.ArrayList;
import java.util.List;

public class AutoCompleteEngine {
    private TrieNode root, curr;
    private StringBuilder sb;

    public AutoCompleteEngine(String[] sentences, int[] times) {
        root = new TrieNode(3);
        curr = root;
        sb = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }
    private void insert(String sentence, int time) {
        TrieNode walker = root;
        List<TrieNode> buffer = new ArrayList<>();
        for (char c : sentence.toCharArray()) {
            if (!walker.links.containsKey(c))
                walker.links.put(c, new TrieNode(3));
            walker = walker.links.get(c);
            buffer.add(walker);
        }

        walker.hitTimes += time;
        walker.s = sentence;
        for (TrieNode node : buffer)
            node.updateTopSentences(walker);
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            insert(sb.toString(), 1);
            sb = new StringBuilder();
            curr = root;
        } else {
            sb.append(c);
            if (curr != null)
                curr = curr.links.get(c);
            if (curr == null) return res;

            curr.hottests.forEach(node -> res.add(node.s));
        }
        return res;
    }
}
