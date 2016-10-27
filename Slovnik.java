import java.io.File;

import CompressedTrie.CTrie;

/**Tøída spravující slovník.*/
public class Slovnik {
	
	public CTrie trie;
	
	/**Bezparametrický konstruktor pro pøípad nouze.*/
	public Slovnik(){trie = new CTrie();}

	/**Hlavní konstruktor zpracovávající pøímo string.*/
	public Slovnik(String s){
		trie = new CTrie();
		String [] s1=s.split(" ");
		int index=0;
		for (int i = 0; i < s1.length; i++) {
            trie.pridejRekurzivne(uprav(s1[i]), index);
            index+=s1[i].length()+1;
        }
	}
	/**
	 * Upraveni vstupu na lower case a bez diakritiky
	 * @param s vstupni string
	 * @return upraveny string
	 */
	public static String uprav(String s) {
		String temp = s.toLowerCase();
		temp = temp.replace(",", "");
		temp = temp.replace(".", "");
		temp = temp.replace("\n", "");
		
		return temp;
	}
	
	/**Konstruktor pro import ze souboru.*/
	public Slovnik(File textFile){
		this(OperaceIO.nactiText(textFile));
	}

	public void pridatKlic(String klic) {
		trie.pridejRekurzivne(klic, -1);
		
	}
	
}
