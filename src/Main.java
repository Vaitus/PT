import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Tento program slouží ke zpracování textového souboru (ideálnì .txt) 
 * a vytvoøení slovníku obsahující všechna jeho slova 
 * (vèetnì pozic všech jeho výskytù v textu) 
 * implementovaného komprimovanou trií.
 * Slovník je možné exportovat i importovat do textových souborù.
 * Ve slovníku je možné vyhledávat nejbližší slova pomocí Levenshteinovy vzdálenosti.
 * @author Marek Zábran - A15B0160P a Vít Teøl - A15BXXXXP.
 *
 */
public class Main {

	/**
	 * Spouští celý program - zapíná GUI.
	 * Nemá žádné další využití.
	 * @param args s parametry se nepoèítá. Nejsou využívány.
	 */
	public static void main(String args[])
	{
		GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(610, 360);
		gui.setMinimumSize(new Dimension(610, 300));
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		return;
		
		
	}
}
