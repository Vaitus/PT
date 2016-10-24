package CompressedTrie;

import java.util.LinkedList;

/**
 * Created by Vaitus on 13.10.2016.
 * Node pro danou Trie
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

    /**
     * Rozsliseni jestli je dany Node root nebo ne
     * @return zda je root
     */
    public boolean jeRoot() {
        return false;
    }

    /**
     * Rozhodnuti jestli ma danou hodnotu na zadanem indexu
     * @param x zadana hodnota
     * @param index dany index
     * @return pokud ma hodnotu na indexu
     */
    public boolean masHodnotu(char x, int index) {
        if(index >= key.length()) return false;
        return key.charAt(index) == x;
    }

    /**
     * Rozhodnuti jestli ma naslednika s danou hodnotou
     * @param x hodnota
     * @return zda ma naslednika s hodnotou
     */
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

    /**
     * Navraci naslednika s zacatecni hodnotou
     * @param x hodnota
     * @return naslednik s danou hodnotou
     */
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

    /**
     * Rozdeli dany Node na 2 pokud je to nutne
     * @param index kde se ma rozdelit
     */
    public void rozdeleni(int index){
        Node novy = new Node(this.key.substring(0,index));
        novy.predek = this.predek;

        predek.naslednik.remove(this);
        predek.naslednik.add(novy);

        novy.naslednik.add(this);

        this.predek = novy;
        this.key = this.key.substring(index);
    }

    /**
     * Prida naslednika danemu Node
     * @param n dany Node
     */
    public void pridejPotomka(Node n){
        n.predek = this;
        this.naslednik.add(n);
    }

    /**
     * Vypise cele slovo pro Trie
     * @return cele slovo
     */
    public String vypisCeleSlovo() {
        String s = "";
        Node n = this;

        while (!n.jeRoot()) {
            s = n.key + s;
            n = n.predek;
        }

        //System.out.println(s);
        return s;
    }

    /**
     * Vypisuje hodnotu a jeji zacatecni indexy
     * @return hodnota a zacatecni idnexy
     */
    @Override
    public String toString() {
        String result = key + " ";
        for(Integer index :zacatecniIndex) {
            result += index + " ";
        }
        return result;
    }
}
