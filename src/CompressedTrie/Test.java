package CompressedTrie;

/**
 * Created by Vaitus on 13.10.2016.
 */
public class Test {

    public static void main(String[] args) {
        CTrie trie = new CTrie();

        trie.pridejRekurzivne("Blabla", 2);
        trie.pridejRekurzivne("Bl", 3);
        trie.pridejRekurzivne("Bla", 4);
        trie.pridejRekurzivne("B", 5);
        trie.pridejRekurzivne("Black", 7);
        trie.pridejRekurzivne("Blaz", 8);
        trie.pridejRekurzivne("Blaz", 9);
        trie.pridejRekurzivne("Blaze", 10);

        System.out.println(trie.vytvoreniSlovniku());

        System.out.println(trie.prohledat("Bla"));

    }


}
