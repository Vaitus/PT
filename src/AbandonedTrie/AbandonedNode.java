package AbandonedTrie;

/**
 * Created by Vaitus on 13.10.2016.
 * AbandonedNode pro stromovou strukturu trie
 */
public class AbandonedNode {
    String key;
    int len;
    AbandonedNode link;
    AbandonedNode next;

    public AbandonedNode(String key, int n) {
        len = n;
        key = this.key;
    }
}
