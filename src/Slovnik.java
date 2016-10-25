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
		int index=0;
		for (int i = 0; i < s1.length; i++) {
            trie.pridejRekurzivne(s1[i], index);
            index+=s1[i].length();
        }
		
		
	}
	
	/**Konstruktor pro import ze souboru.*/
	public Slovnik(File textFile){
		this(OperaceIO.nactiText(textFile));
	}

	public void pridatKlic(String klic) {
		trie.pridejRekurzivne(klic, -1);
		
	}
	
}
