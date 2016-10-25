import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**Knihovní tøída se statickými metodami, které se starají o vstup a výstup.*/
public class OperaceIO {

	/**
	 * Metoda, která naète text ze souboru jako string.
	 * @param textFile soubor s textem
	 * @return text ze souboru.
	 */
	public static String nactiText(File textFile) {
		String s= "";
		String text="";
		try{
			BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(textFile.getAbsolutePath()), "UTF-8") );
			//s = br.readLine();
        	while(br.ready()){//Zkopíruje do nového souboru pùvodní èást souboru až k dekleraci tøídy.
        		s=br.readLine();
        		text+=s+" \n";
        	}
			br.close();
		} catch (IOException e) {//Nejsou pokryté všechny chyby
			System.err.println("Soubor se nepodaøilo naèíst.");
		}
		return text;
	}

	/**
	 * Metoda, která naète slovník ze souboru.
	 * @param importFile soubor se slovníkem.
	 * @return slovnik naimportovaný ze souboru.
	 */
	public static Slovnik importuj(File importFile) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metoda, která exportuje slovník do souboru.
	 * @param exportFile soubor, do kterého se bude zapisovat
	 * @param slovnik zapisovaný do souboru
	 */
	public static void exportuj(File exportFile, Slovnik slovnik) {
		// TODO Auto-generated method stub
		
	}

}
