public class AutoCompleteEngine {
    private TrieNode root, curr;
    public AutoCompleteEngine(String[] sentences, int[] times) {
        root = new TrieNode(3);
        curr = root;

        for(int i = 0; i < sentences.length; i++){
            insert(sentences[i], times[i]);
        }
    }
    private void insert(String sentence,)
}
