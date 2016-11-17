import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import CompressedTrie.Node;

/**Knihovní tøída se statickými metodami, které se starají o vstup a výstup.
 * @author Marek Zábran, A15B0160P.*/
public class OperaceIO {

	/**
	 * Metoda, která naète text ze souboru jako string.
	 * @param textFile soubor s textem.
	 * @return Text ze souboru.
	 */
	public static String nactiText(File textFile) {
		String s= "";
		String text="";
		try{
			BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(textFile.getAbsolutePath()), "UTF-8") );
			//s = br.readLine();
        	while(br.ready()){
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
	 * @return Slovník naimportovaný ze souboru.
	 */
	public static Slovnik importuj(File importFile) {
		Slovnik slovnik = new Slovnik();
		String s= "";
		String []s2= new String[2];
		String []nody;
		int [] indexy;
		try{
			BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(importFile.getAbsolutePath()), "UTF-8") );
			//s = br.readLine();
        	while(br.ready()){
        		s=br.readLine();
        		s2=s.split("\t");
        		nody = s2[0].split(" ");
        		indexy = parseInty(s2[1].split(" "));
        		slovnik.trie.importujNod(nody, indexy);
        	}
			br.close();
		} catch (IOException e) {//Nejsou pokryté všechny chyby
			System.err.println("Soubor se nepodaøilo naèíst.");
		}
		return slovnik;
	}
	
	public static int[] parseInty(String [] s){
		int[] inty = new int[s.length];
		for(int i=0; i<s.length;i++){
			inty[i]=Integer.parseInt(s[i]);
		}
		return inty;
	}

	/**
	 * Metoda, která exportuje slovník do souboru.
	 * @param exportFile soubor, do kterého se bude zapisovat.
	 * @param Slovník zapisovaný do souboru.
	 */
	public static void exportuj(File exportFile, Slovnik slovnik) {
		try{
			BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(exportFile.getAbsolutePath()), "UTF-8") );
			//s = br.readLine();
        	
        		//vypisSlovnik(bw, slovnik);
			for(Node n:slovnik.trie.root.naslednik){
				vypisNod("", bw, n);
			}
        		
			bw.close();
		} catch (IOException e) {//Nejsou pokryté všechny chyby
			System.err.println("Soubor se nepodaøilo uložit.");
		}
	}

	/**
	 * Pomocná metoda exportuj()
	 * @param bw
	 * @param slovnik
	 */
	/*private static void vypisSlovnik(BufferedWriter bw, Slovnik slovnik) {
		for(Node n:slovnik.trie.root.naslednik){
			vypisNod("", bw, n);
		}
	}*/

	/**
	 * Pomocná metoda exportuj(), která vypíše do souboru souèasný uzel, 
	 * pokud se vyskytuje v textu, a rekurzivnì se pak volá pro uzly svých následníkù.
	 * @param s podøetìzec vzniklý spojením podøetìzcù všech pøedkù.
	 * @param bw BufferedWriter zapisující do souboru.
	 * @param n souèasný uzel.
	 */
	private static void vypisNod(String s, BufferedWriter bw, Node n) {
		s +=" " + n.getKey();
		if(!n.zacatecniIndex.isEmpty()){
			String line = s+n.vratIndexy();
			//for(int i=0;i<n.zacatecniIndex.size();i++){
			//	line +=n.zacatecniIndex.get(i)+" ";
			//}
			line=line.substring(1, line.length()-1);
			try {
				bw.write(line);
				bw.newLine();
			} catch (IOException e) {
				System.err.println("Chyba pøi ukládání do souboru..");
			}
    		
		}
		for(Node dalsi:n.naslednik){
			vypisNod(s, bw, dalsi);
		}
	}
	

}
