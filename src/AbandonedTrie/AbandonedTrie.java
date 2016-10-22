package AbandonedTrie;
/**
 * Created by Vaitus on 13.10.2016.
 * Implementování algoritmu komprimovaná trie
 */
public class AbandonedTrie {

    public AbandonedTrie() {

    }

    public AbandonedNode insert(AbandonedNode t, String x, int n) {
        if (n==0) n = x.length()+1;
        if (t==null) return new AbandonedNode(x,n);
        int k = prefix(x,n,t.key,t.len);
        if (k==0){
            t.next = insert(t.next,x,n);
        } else if (k<n) {
            if (k<t.len){
                split(t,k);
            }
            t.link = insert(t.link,x+k,n-k);
        }
        return t;
    }

    private void split(AbandonedNode t, int k) { //rozdeleni podle k key symbolu
        AbandonedNode p = new AbandonedNode(t.key+k,t.len-k);
        p.link = t.link;
        t.link = p;

        String a = t.key.substring(0,k);
        t.key = a;
        t.len = k;
    }

    public AbandonedNode find(AbandonedNode t, String x, int n){
        if (n==0) n = x.length()+1;
        if (t==null) return null;
        int k = prefix(x,n,t.key,t.len);
        if (k==0) return find(t.next,x,n); //child node
        if (k==n) return t;
        if (k==t.len) return find(t.link,x+k,n-k); //
        return null;
    }

    private int prefix(String x, int n, String key, int m) {
        for (int k = 0; k < n; k++) {
            if (k==m || x.charAt(k)!=key.charAt(k)) {
                return k;
            }
        }
        return n;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
