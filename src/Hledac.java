import java.io.File;

/** Knihovn� t��da obsahuj�c� statick� metody t�kaj�c� se prohled�v�n� textu.*/
public class Hledac {

	/**
	 * @overloaded
	 * P�et�en� metoda pro hled�n� kl��e ve stringu.
	 * Vyt�hne text ze souboru a zbytek je identick� druh�mu �e�en� t�to metody.
	 * @param klic vyhled�van� v textu
	 * @param textFile soubor s textem
	 * @return vypis o tom, kolikr�t a kde byl kl�� nalezen.
	 */
	public static String hledej(String klic, File textFile) {
		String text = OperaceIO.nactiText(textFile);
		return Hledac.hledej(klic, text);
	}

	/**
	 * Pod�v� se, jestli je kl�� v�bec ve slovn�ku.
	 * @param slovnik prohled�van�
	 * @param klic hledan� ve slovn�ku
	 * @return true pokud se kl�� ve slovn�ku nach�z�.
	 */
	public static boolean klicJeVeSlovniku(Slovnik slovnik, String klic) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @overloaded
	 * P�et�en� metoda pro hled�n� kl��e ve stringu.
	 * Tato ��st �e�� p��mo prohled�v�n� textu.
	 * @param klic vyhled�van� v textu
	 * @param text prohled�van�
	 * @return vypis o tom, kolikr�t a kde byl kl�� nalezen.
	 */
	public static String hledej(String klic, String text) {
		// TODO Auto-generated method stub
		return null;
	}

}
