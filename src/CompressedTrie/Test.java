package CompressedTrie;

import Levenshtein.Levenshtein;

/**
 * Created by Vaitus on 13.10.2016.
 */
public class Test {

    public static void main(String[] args) {
        CTrie trie = new CTrie();
        String s = "japonec nekdo ty ona ja syn sin kin klin sona son win won kon licieum kliceum klin";

        System.out.println(Levenshtein.vypisDesetSlov(s.split(" "), "stn", 10, 3));

         s = "Ano ty ne a ty ja proc ja ty ono ja";
        for (int i = 0; i < s.split(" ").length; i++) {
            System.out.println(s.split(" ")[i] + " i:" + i);
            trie.pridejRekurzivne(s.split(" ")[i], i);
        }

        trie.pridejRekurzivne("Blabla", 2);
        trie.pridejRekurzivne("Kokot", 2);
        trie.pridejRekurzivne("Kokotov", 2);
        trie.pridejRekurzivne("Bla", 2);
        trie.pridejRekurzivne("B", 5);
        trie.pridejRekurzivne("Black", 7);
        trie.pridejRekurzivne("Blaz", 8);
        trie.pridejRekurzivne("Blaz", 9);
        trie.pridejRekurzivne("Blaze", 10);



        System.out.println(trie.vytvoreniSlovniku());

        System.out.println(trie.prohledat("Ano"));

    }

}
