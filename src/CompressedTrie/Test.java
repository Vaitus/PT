package CompressedTrie;

/**
 * Created by Vaitus on 13.10.2016.
 */
public class Test {

    public static void main(String[] args) {
        CTrie trie = new CTrie();

        trie.pridejRekurzivne("Blabla", 2);
        trie.pridejRekurzivne("Blablaaa", 2);
        trie.pridejRekurzivne("Black", 7);
        trie.pridejRekurzivne("Blaz", 8);
        trie.pridejRekurzivne("Blaz", 9);
        trie.pridejRekurzivne("Blaze", 10);

        System.out.println("WTF?");

        System.out.println(trie.prohledat("Bla"));

    }


}
