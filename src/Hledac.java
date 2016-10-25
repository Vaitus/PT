import java.io.File;
import java.util.LinkedList;

import Levenshtein.Levenshtein;

/** Knihovní tøída obsahující statické metody týkající se prohledávání textu.*/
public class Hledac {

	/**
	 * @overloaded
	 * Pøetížená metoda pro hledání klíèe ve stringu.
	 * Vytáhne text ze souboru a zbytek je identický druhému øešení této metody.
	 * @param klic vyhledávaný v textu
	 * @param textFile soubor s textem
	 * @return vypis o tom, kolikrát a kde byl klíè nalezen.
	 */
	public static String hledej(String klic, File textFile) {
		String text = OperaceIO.nactiText(textFile);
		return Hledac.hledej(klic, text);
	}

	/**
	 * Podívá se, jestli je klíè vùbec ve slovníku.
	 * @param slovnik prohledávaný
	 * @param klic hledaný ve slovníku
	 * @return true pokud se klíè ve slovníku nachází.
	 */
	public static boolean klicJeVeSlovniku(Slovnik slovnik, String klic) {
		slovnik.trie.prohledat(klic);//TODO
		return false;
	}

	/**
	 * @overloaded
	 * Pøetížená metoda pro hledání klíèe.
	 * Tato èást øeší pøímo prohledávání slovniku.
	 * @param slovnik prohledávaný slovník
	 * @param klic hledaný klíè
	 * @return vypis o tom, na kterých indexech byl klíè nalezen v pùvodním textu.
	 */
	public static String hledej(Slovnik slovnik, String klic) {
		LinkedList<Integer> l =slovnik.trie.prohledat(klic);
		if (l==null) {return "Slovo nebylo nalezeno ve slovníku. \nVypisuji podobná slova dle Levenshteinovo metody: "+ Levenshtein.vypisDesetSlov(slovnik.trie.vytvoreniSlovniku().split(" "), klic, 10, 3);}
		else {return "Slovo bylo nalezeno na tìchto indexech slov:" + l.toString();}
	}
	
	/**
	 * @overloaded
	 * Pøetížená metoda pro hledání klíèe ve stringu.
	 * Tato èást øeší pøímo prohledávání textu.
	 * @param klic vyhledávaný v textu
	 * @param text prohledávaný
	 * @return vypis o tom, kolikrát a kde byl klíè nalezen.
	 */
	public static String hledej(String klic, String text) {
		// TODO Auto-generated method stub
		return null;
	}

}
