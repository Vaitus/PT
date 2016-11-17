import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Tento program slou�� ke zpracov�n� textov�ho souboru (ide�ln� .txt) 
 * a vytvo�en� slovn�ku obsahuj�c� v�echna jeho slova 
 * (v�etn� pozic v�ech jeho v�skyt� v textu) 
 * implementovan�ho komprimovanou tri�.
 * Slovn�k je mo�n� exportovat i importovat do textov�ch soubor�.
 * Ve slovn�ku je mo�n� vyhled�vat nejbli��� slova pomoc� Levenshteinovy vzd�lenosti.
 * @author Marek Z�bran - A15B0160P a V�t Te�l - A15BXXXXP.
 *
 */
public class Main {

	/**
	 * Spou�t� cel� program - zap�n� GUI.
	 * Nem� ��dn� dal�� vyu�it�.
	 * @param args s parametry se nepo��t�. Nejsou vyu��v�ny.
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
