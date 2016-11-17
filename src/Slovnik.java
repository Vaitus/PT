import java.io.File;

import CompressedTrie.CTrie;

/**Tøída typu pøepravka spravující slovník.
 * @author Marek Zábran, pomáhal Vít Teøl.*/
public class Slovnik {
	
	/**Instance komprimované trie, ve které je slovník uložen.*/
	public CTrie trie;
	
	/**Bezparametrický konstruktor pro pøípad nouze.*/
	public Slovnik(){trie = new CTrie();}

	/**Hlavní konstruktor zpracovávající pøímo string.
	 * @param s zpracovávaný text.
	 * */
	public Slovnik(String s){
		trie = new CTrie();
		String [] s1=s.split(" ");
		int index=0;
		for (int i = 0; i < s1.length; i++) {
			trie.pridatRek(trie.root,uprav(s1[i]), index);
            //trie.pridejRekurzivne(uprav(s1[i]), index);
            index+=s1[i].length()+1;
        }
	}
	/**
	 * Upravení vstupu na malá písmena a odstranìní interpunkce.
	 * @param s neupravený string.
	 * @return Opravený string.
	 */
	public static String uprav(String s) {
		String temp = s.toLowerCase();
		temp = temp.replace(",", "");
		temp = temp.replace(".", "");
		temp = temp.replace("\n", "");
		temp = temp.replace("\t", "");
		
		return temp;
	}
	
	/**
	 * Konstruktor pro import ze souboru.
	 * @param textFile soubor, ze kterého se slovník importuje.
	 */
	public Slovnik(File textFile){
		this(OperaceIO.nactiText(textFile));
	}

	/**
	 * Pøidává do slovníku nové slovo, 
	 * které pøedtím nebylo ve slovníku nalezeno, na pøání uživatele.
	 * @param klic pøidávané slovo.
	 */
	public void pridatKlic(String klic) {
		trie.pridatRek(trie.root,klic, -1);
		
	}
	
}
