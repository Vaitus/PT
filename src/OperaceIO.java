import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import CompressedTrie.Node;

/**Knihovn� t��da se statick�mi metodami, kter� se staraj� o vstup a v�stup.
 * @author Marek Z�bran, A15B0160P.*/
public class OperaceIO {

	/**
	 * Metoda, kter� na�te text ze souboru jako string.
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
		} catch (IOException e) {//Nejsou pokryt� v�echny chyby
			System.err.println("Soubor se nepoda�ilo na��st.");
		}
		return text;
	}

	/**
	 * Metoda, kter� na�te slovn�k ze souboru.
	 * @param importFile soubor se slovn�kem.
	 * @return Slovn�k naimportovan� ze souboru.
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
		} catch (IOException e) {//Nejsou pokryt� v�echny chyby
			System.err.println("Soubor se nepoda�ilo na��st.");
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
	 * Metoda, kter� exportuje slovn�k do souboru.
	 * @param exportFile soubor, do kter�ho se bude zapisovat.
	 * @param Slovn�k zapisovan� do souboru.
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
		} catch (IOException e) {//Nejsou pokryt� v�echny chyby
			System.err.println("Soubor se nepoda�ilo ulo�it.");
		}
	}

	/**
	 * Pomocn� metoda exportuj()
	 * @param bw
	 * @param slovnik
	 */
	/*private static void vypisSlovnik(BufferedWriter bw, Slovnik slovnik) {
		for(Node n:slovnik.trie.root.naslednik){
			vypisNod("", bw, n);
		}
	}*/

	/**
	 * Pomocn� metoda exportuj(), kter� vyp�e do souboru sou�asn� uzel, 
	 * pokud se vyskytuje v textu, a rekurzivn� se pak vol� pro uzly sv�ch n�sledn�k�.
	 * @param s pod�et�zec vznikl� spojen�m pod�et�zc� v�ech p�edk�.
	 * @param bw BufferedWriter zapisuj�c� do souboru.
	 * @param n sou�asn� uzel.
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
				System.err.println("Chyba p�i ukl�d�n� do souboru..");
			}
    		
		}
		for(Node dalsi:n.naslednik){
			vypisNod(s, bw, dalsi);
		}
	}
	

}
