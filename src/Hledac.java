import java.util.LinkedList;
import javax.swing.JOptionPane;
import Levenshtein.Levenshtein;

/** Knihovn� t��da obsahuj�c� statickou metodu prohled�vaj�c� slovn�k.
 * @author Marek Z�bran.*/
public class Hledac {

	/**
	 * P�et�en� metoda pro hled�n� kl��e ve stringu.
	 * Vyt�hne text ze souboru a zbytek je identick� druh�mu �e�en� t�to metody.
	 * @param klic vyhled�van� v textu
	 * @param textFile soubor s textem
	 * @return vypis o tom, kolikr�t a kde byl kl�� nalezen.
	 */
	/*public static String hledej(String klic, File textFile) {
		String text = OperaceIO.nactiText(textFile);
		return Hledac.hledej(klic, text);
	}*/

	/**
	 * Pod�v� se, jestli je kl�� v�bec ve slovn�ku.
	 * @param slovnik prohled�van� slovn�k.
	 * @param klic hledan� ve slovn�ku.
	 * @return True, pokud se kl�� ve slovn�ku nach�z�.
	 */
	/*public static boolean klicJeVeSlovniku(Slovnik slovnik, String klic) {
		slovnik.trie.prohledat(klic);
		return false;
	}*/

	/**
	 * Metoda pro hled�n� kl��e.
	 * P��mo prohled�v�v� slovn�k.
	 * @param slovnik prohled�van� slovn�k.
	 * @param klic hledan� kl��.
	 * @return V�pis, na kter�ch indexech byl kl�� nalezen v p�vodn�m textu.
	 */
	public static String hledej(Slovnik slovnik, String klic) {
		klic = Slovnik.uprav(klic);
		LinkedList<Integer> l =slovnik.trie.prohledat(klic);
		if (l==null) {
			if(JOptionPane.showConfirmDialog(null, "Slovo nebylo nalezeno ve slovn�ku. Chcete jej do n�j p�idat?", "P�idat slovo.", JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION){slovnik.pridatKlic(klic);}
			return "Slovo nebylo nalezeno ve slovn�ku. \nVypisuji podobn� slova dle Levenshteinovo metody: "+ Levenshtein.vypisDesetSlov(slovnik.trie.vytvoreniSlovniku().split(" "), klic, 10, 3);}
		else {
			String s="Slovo bylo nalezeno " + l.size() + "-kr�t v textu na t�chto indexech: \n";
			for (int i:l){
				s+="["+i+"-"+(i+klic.length())+"] \n";
			}
			return s;
		}
	}
	
	/**
	 * P�et�en� metoda pro hled�n� kl��e ve stringu.
	 * Tato ��st �e�� p��mo prohled�v�n� textu.
	 * @param klic vyhled�van� v textu
	 * @param text prohled�van�
	 * @return vypis o tom, kolikr�t a kde byl kl�� nalezen.
	 */
	/*public static String hledej(String klic, String text) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
