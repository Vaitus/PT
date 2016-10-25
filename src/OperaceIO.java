import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**Knihovn� t��da se statick�mi metodami, kter� se staraj� o vstup a v�stup.*/
public class OperaceIO {

	/**
	 * Metoda, kter� na�te text ze souboru jako string.
	 * @param textFile soubor s textem
	 * @return text ze souboru.
	 */
	public static String nactiText(File textFile) {
		String s= "";
		String text="";
		try{
			BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(textFile.getAbsolutePath()), "UTF-8") );
			//s = br.readLine();
        	while(br.ready()){//Zkop�ruje do nov�ho souboru p�vodn� ��st souboru a� k dekleraci t��dy.
        		s=br.readLine();
        		text+=s+" \n";
        	}
			br.close();
		} catch (IOException e) {//Nejsou pokryt� v�echny chyby
			System.err.println("Soubor se nepoda�ilo na��st.");
		}
		return text;
	}

	/**
	 * Metoda, kter� na�te slovn�k ze souboru.
	 * @param importFile soubor se slovn�kem.
	 * @return slovnik naimportovan� ze souboru.
	 */
	public static Slovnik importuj(File importFile) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metoda, kter� exportuje slovn�k do souboru.
	 * @param exportFile soubor, do kter�ho se bude zapisovat
	 * @param slovnik zapisovan� do souboru
	 */
	public static void exportuj(File exportFile, Slovnik slovnik) {
		// TODO Auto-generated method stub
		
	}

}
