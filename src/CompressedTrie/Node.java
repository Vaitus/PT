package CompressedTrie;

import java.util.LinkedList;

/**
 * Created by Vaitus on 13.10.2016.
 */
public class Node {
    String key;

    Node predek;
    LinkedList<Node> naslednik = new LinkedList<>();
    LinkedList<Integer> zacatecniIndex = new LinkedList<>();

    /**
     * Konstruktor
     * @param key hodnota Node
     */
    public Node(String key) {
        this.key = key;
    }

    public boolean jeRoot() {
        return false;
    }

    public boolean masHodnotu(char x, int index) {
        if(index >= key.length()) return false;
        return key.charAt(index) == x;
    }

    public boolean masNaslednikaSHodnotou(char x) {
        for (Node n: naslednik) {
            if (!n.key.isEmpty()) {
                if (n.key.charAt(0) == x) {
                    return true;
                }
            }
        }

        return false;
    }

    public Node naslednikSHodnotou(char x){
        for (Node n: naslednik) {
            if (!n.key.isEmpty()) {
                if (n.key.charAt(0) == x) {
                    return n;
                }
            }
        }
        return null;
    }
    public void rozdeleni(int index){
        System.out.println(this.key + " tohohle key");
        Node novy = new Node(this.key.substring(0,index));
        novy.predek = this.predek;

        predek.naslednik.remove(this);
        predek.naslednik.add(novy);

        novy.naslednik.add(this);

        this.predek = novy;
        this.key = this.key.substring(index);

        System.out.println(this.key + " " + novy.key);
    }

    public void pridejPotomka(Node n){
        n.predek = this;
        this.naslednik.add(n);
    }

    @Override
    public String toString() {
        String result = key + " ";
        for(Integer index :zacatecniIndex) {
            result += index + " ";
        }
        return result;
    }
}
