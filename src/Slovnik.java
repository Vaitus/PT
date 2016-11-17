import java.io.File;

import CompressedTrie.CTrie;

/**T��da typu p�epravka spravuj�c� slovn�k.
 * @author Marek Z�bran, pom�hal V�t Te�l.*/
public class Slovnik {
	
	/**Instance komprimovan� trie, ve kter� je slovn�k ulo�en.*/
	public CTrie trie;
	
	/**Bezparametrick� konstruktor pro p��pad nouze.*/
	public Slovnik(){trie = new CTrie();}

	/**Hlavn� konstruktor zpracov�vaj�c� p��mo string.
	 * @param s zpracov�van� text.
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
	 * Upraven� vstupu na mal� p�smena a odstran�n� interpunkce.
	 * @param s neupraven� string.
	 * @return Opraven� string.
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
	 * @param textFile soubor, ze kter�ho se slovn�k importuje.
	 */
	public Slovnik(File textFile){
		this(OperaceIO.nactiText(textFile));
	}

	/**
	 * P�id�v� do slovn�ku nov� slovo, 
	 * kter� p�edt�m nebylo ve slovn�ku nalezeno, na p��n� u�ivatele.
	 * @param klic p�id�van� slovo.
	 */
	public void pridatKlic(String klic) {
		trie.pridatRek(trie.root,klic, -1);
		
	}
	
}
