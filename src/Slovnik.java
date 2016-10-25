import java.io.File;

import CompressedTrie.CTrie;

/**T��da spravuj�c� slovn�k.*/
public class Slovnik {
	
	public CTrie trie;
	
	/**Bezparametrick� konstruktor pro p��pad nouze.*/
	public Slovnik(){trie = new CTrie();}

	/**Hlavn� konstruktor zpracov�vaj�c� p��mo string.*/
	public Slovnik(String s){
		trie = new CTrie();
		String [] s1=s.split(" ");
		for (int i = 0; i < s1.length; i++) {
            trie.pridejRekurzivne(s1[i], i);
        }
		
		
	}
	
	/**Konstruktor pro import ze souboru.*/
	public Slovnik(File textFile){
		this(OperaceIO.nactiText(textFile));
	}
	
}
