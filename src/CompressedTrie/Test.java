package CompressedTrie;

import Levenshtein.Levenshtein;

import java.io.*;

/**
 * Created by Vaitus on 13.10.2016.
 */
public class Test {

    public static void main(String[] args) {
        CTrie trie = new CTrie();

        System.out.println(Levenshtein.LevenshteinVzdalenost("Kokot", "kojoti"));

       /* String s = "Ano ty ne a ty ja proc ja ty ono ja";
        for (int i = 0; i < s.split(" ").length; i++) {
            System.out.println(s.split(" ")[i] + " i:" + i);
            trie.pridejRekurzivne(s.split(" ")[i], i);
        }*/

        /*trie.pridejRekurzivne("Blabla", 2);
        trie.pridejRekurzivne("Kokot", 2);
        trie.pridejRekurzivne("Kokotov", 2);
        trie.pridejRekurzivne("Bla", 2);
        trie.pridejRekurzivne("B", 5);
        trie.pridejRekurzivne("Black", 7);
        trie.pridejRekurzivne("Blaz", 8);
        trie.pridejRekurzivne("Blaz", 9);
        trie.pridejRekurzivne("Blaze", 10);*/



       /* System.out.println(trie.vytvoreniSlovniku());

        System.out.println(trie.prohledat("Ano"));*/

    }

}
