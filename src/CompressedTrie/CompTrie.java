package CompressedTrie;

/**
 * Created by Vaitus on 13.10.2016.
 * Implementace komprimovane trie
 */
public class CompTrie {
    Node root;

    /**
     * TODO
     */
    public CompTrie(){
        root = new Node("");
    }
    /**
     * TODO
     */
    public void pridani(Node pred,String x, int zacatecniIndex) {
        //kdyz je to root
        Node predchozi = pred;
        if (predchozi == null) {
            predchozi = root;
        }

        if (predchozi.naslednik.isEmpty()) {
            Node novy = new Node(x);
            novy.zacatecniIndex.add(zacatecniIndex);
            novy.predek = predchozi;
            predchozi.naslednik.add(novy);
            return;
        }
        boolean pokudNeni = true;
        for (int i = 0; i < predchozi.naslednik.size(); i++) {
            int prefix = prefix(predchozi.naslednik.get(i).key,x);
            if (prefix==-1){
                predchozi.naslednik.get(i).zacatecniIndex.add(zacatecniIndex);
                pokudNeni = false;
                break;
            } else if (prefix<0) {
                rozdeleni(predchozi.naslednik.get(i),-prefix);
                //predchozi.predek.zacatecniIndex.add(zacatecniIndex);
                pokudNeni = false;
                break;
            } else if (prefix==predchozi.naslednik.get(i).key.length()) {
                pridani(predchozi.naslednik.get(i),x,zacatecniIndex);
                pokudNeni = false;
                break;
            } else if (prefix > 0 && prefix < predchozi.naslednik.get(i).key.length()) {
                rozdeleni(predchozi.naslednik.get(i), prefix);
                pridani(predchozi.naslednik.get(i), x, zacatecniIndex);
                pokudNeni = false;
                break;
            }
        }
        if (pokudNeni) {
            Node novy = new Node(x);
            novy.zacatecniIndex.add(zacatecniIndex);
            novy.predek = predchozi;

            predchozi.naslednik.add(novy);
        }
    }
    /**
     * TODO
     */
    public void rozdeleni(Node x, int prefix) {
        Node novy = new Node(x.key.substring(0,prefix));
        novy.predek = x.predek;
        novy.naslednik.add(x);

        x.key = x.key.substring(prefix);
        x.predek = novy;
    }
    /**
     * TODO
     */
    public int prefix(String x, String key) {
        if (x.equals(key)) {
            return -1;
        }
        for (int k = 0; k < x.length(); k++) {
            if (k == key.length() || x.charAt(k)!=key.charAt(k)) {
                if (key.equals(x.substring(0,k))) {
                    return -k;
                } else {
                    return k;
                }
            }
        }
        return x.length();
    }
    /**
     * TODO
     */
    public void prohledavani() {

    }

    public String toString(Node x) {
        String s = "";
        if (x==null) x = root;

        if (x.naslednik.isEmpty()) {
            s+= x.key + " ";
            return s;
        }
        for (int i = 0; i < x.naslednik.size(); i++) {
            s += toString(x.naslednik.get(i));
        }

        return s;
    }
}
