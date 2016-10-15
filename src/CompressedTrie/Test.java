package CompressedTrie;

/**
 * Created by Vaitus on 13.10.2016.
 */
public class Test {

    public static void main(String[] args) {
        CTrie trie = new CTrie();

        trie.pridejRekurzivne("Blabla", 0);
        trie.pridejRekurzivne("Blabla", 2);
        trie.pridejRekurzivne("Black", 7);
        trie.pridejRekurzivne("Blabe", 11);

        System.out.println("WTF?");
    }


}
