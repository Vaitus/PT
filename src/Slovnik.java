import java.io.File;

/**T��da spravuj�c� slovn�k.*/
public class Slovnik {
	
	/**Bezparametrick� konstruktor pro p��pad nouze.*/
	public Slovnik(){}

	/**Hlavn� konstruktor zpracov�vaj�c� p��mo string.*/
	public Slovnik(String s){
		
		
		
	}
	
	/**Konstruktor pro import ze souboru.*/
	public Slovnik(File textFile){
		this(OperaceIO.nactiText(textFile));
	}
	
}
