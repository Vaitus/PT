import java.io.File;

/**Tøída spravující slovník.*/
public class Slovnik {
	
	/**Bezparametrický konstruktor pro pøípad nouze.*/
	public Slovnik(){}

	/**Hlavní konstruktor zpracovávající pøímo string.*/
	public Slovnik(String s){
		
		
		
	}
	
	/**Konstruktor pro import ze souboru.*/
	public Slovnik(File textFile){
		this(OperaceIO.nactiText(textFile));
	}
	
}
