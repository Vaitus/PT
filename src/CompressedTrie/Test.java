package CompressedTrie;

import Levenshtein.Levenshtein;

/**
 * Created by Vaitus on 13.10.2016.
 */
public class Test {

    public static void main(String[] args) {
        CTrie trie = new CTrie();


        trie.pridejRekurzivne("Klíè", 8);
        trie.pridejRekurzivne("Kliceu", 9);
        trie.pridejRekurzivne("Konzole", 10);


        System.out.println(trie.prohledat("Ano"));

    }

}
