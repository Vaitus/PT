import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {//TODO tady bych m�l napsat koment��e a p��padn� dod�lat mo�nost na�ten� souboru jako argument

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
