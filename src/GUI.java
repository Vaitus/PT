import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class GUI extends JFrame{
	
	/**Serial.*/
	private static final long serialVersionUID = 1L;
	
	//Na��t�n� textu.
	
	/**Tla��tko pro na�ten� textu z JTextArea vstup nebo ze souboru (p�ep�n� CheckBox).*/
	JButton nactiText;
	
	/**Vyb�r�n� souboru pro na�ten� textu ze souboru.*/
	JFileChooser textFC = new JFileChooser();
	
	/**Soubor s na�ten�m textem.*/
	private File textFile;
	
	/**Adresa pro na�ten� textu.*/
    private String textPath="";
	
	//Import slovn�ku.
	
	/**Tla��tko pro import slovn�ku ze souboru.*/
	JButton importSlov;
	
	/**Vyb�r�n� souboru pro import slovn�ku.*/
	JFileChooser importFC = new JFileChooser();
	
	/**Soubor s importovan�m slovn�kem.*/
	private File importFile;
	
	/**Adresa pro import.*/
    private String importPath="";
	
	//Export slovn�ku.

	/**Tla��tko pro export slovn�ku do souboru.*/
	JButton exportSlov;
	
	/**Vyb�r�n� souboru pro export slovn�ku.*/
	JFileChooser exportFC = new JFileChooser();
	
	/**Soubor s exportovan�m slovn�kem.*/
	private File exportFile;
	
	/**Adresa pro export.*/
    private String exportPath="";
	
	//Ostatn� prvky GUI
	
    /**Tla��tko pro hled�n� kl��e z JTextArea klic.*/
	JButton hledej;
	
	/**Objekt, do kter�ho se zapisuje hledan� kl��.*/
	JTextArea klic= new JTextArea(1, 50);
	
	/**Objekt, do kter�ho se vypisuje textov� v�stup.*/
	JTextArea vypis= new JTextArea(5, 50);
	
	/**Objekt, do kter�ho se zapisuje textov� vstup.*/
	JTextArea zapis= new JTextArea(10, 50);
	
	/**Objekt, kter� ur�uje, m�-li se br�t text ze souboru a nebo z objektu zapis.*/
	JCheckBox check= new JCheckBox();
	
	//Kritick� elementy
	
	/**String se sou�asn� pou��van�m textem. - pravd�podobn� nebude pot�eba*/
	public String text;
	
	/**P�epravka pro slovnik.*/
	public Slovnik slovnik=new Slovnik();
	
	/**Hlavn� konstruktor.*/
	public GUI(){
		this.getContentPane().add(vytvorVnitrek());
	}
	
	/**Priv�tn� metoda slou��c� pro vytvo�en� containeru s grafick�mi objekty.
	 * Vytv��� je dynamick� a zcela nahodile, nebo� toto absolutn� neum�m pou��vat.*/
	private Container vytvorVnitrek()
    {
		JPanel vnitrekPN = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        
        nactiText = new JButton("Na��st text");
        nactiText.addActionListener(new NactiText());
        
        c.fill = 0;
        c.anchor = 10;
        c.weightx = 0.0D;
        c.weighty = 0.0D;
        c.gridx = 2; //tady je zm�na p�vodn� 1
        c.gridy = 1;
        c.gridwidth = 1;
        vnitrekPN.add(nactiText, c);
        
        importSlov = new JButton("Importovat slovn�k");
        importSlov.addActionListener(new ImportSlov());
        
        c.fill = 0;
        c.anchor = 10;
        c.weightx = 0.0D;
        c.weighty = 0.0D;
        c.gridx = 3; 
        c.gridy = 1;
        c.gridwidth = 1;
        vnitrekPN.add(importSlov, c);
        
        exportSlov = new JButton("Exportovat slovn�k");
        exportSlov.addActionListener(new ExportSlov());
        
        c.fill = 0;
        c.anchor = 10;
        c.weightx = 0.0D;
        c.weighty = 0.0D;
        c.gridx = 3; 
        c.gridy = 1;
        c.gridwidth = 1;
        vnitrekPN.add(exportSlov, c);
        
        hledej = new JButton("Hledat");
        hledej.addActionListener(new Hledej());
        
        c.fill = 0;
        c.anchor = 10;
        c.weightx = 0.0D;
        c.weighty = 0.0D;
        c.gridx = 3; 
        c.gridy = 1;
        c.gridwidth = 1;
        vnitrekPN.add(hledej, c);
        
        check = new JCheckBox("Z�pis z konzole");
        check.setSelected(true);
        check.addActionListener(new Check());
        
        c.fill = 0;
        c.anchor = 10;
        c.weightx = 0.0D;
        c.weighty = 0.0D;
        c.gridx = 3; 
        c.gridy = 1;
        c.gridwidth = 1;
        vnitrekPN.add(check, c);
        
        klic.setText("Hledan� kl��");        
        c.fill = 3;//Textov� v�pis
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 3;
        klic.setEditable(true);
        vnitrekPN.add(klic, c);
        
        resetujVypis();//Resetuje v�pis, aby nebyl zbyte�n� velk�.
        c.fill = 3;//Textov� v�pis
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 3;
        vypis.setEditable(false);
        vnitrekPN.add(vypis, c);
        
        zapis.setText("Text ke zpracov�n�. Toto pole bude vyu�ito jen pokud je povolen z�pis z konzole.");
        c.fill = 3;//Textov� v�pis
        c.anchor = GridBagConstraints.SOUTH;
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 3;
        zapis.setEditable(true);
        vnitrekPN.add(zapis, c);
        
        
        
        return vnitrekPN;
    }
	
	/**Soukrom� procedura, kter� znovu zap�e do objektu vypis v�echny pot�ebn� �daje.*/
	private void resetujVypis(){
		vypis.setText("Kliknut�m na tla��tko Na��st text m��ete na��st text ze souboru a tento text bude automaticky zpracov�n. \n");
		vypis.append("Kliknut�m na tla��tko Importovat slovn�k se sou�asn� slovn�k importuje. \n");//TODO n�kdy by to cht�lo dopsat zbytek a po�e�it, kdy se to m� vypsat a p�epsat
		
	}
	
	/**
     * Na�te text.
     */
    private class NactiText
        implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {if(e.getSource()==nactiText){
            int ret = textFC.showOpenDialog(GUI.this);
            if(ret == 0)//== JFileChooser.APPROVE_OPTION
            {
            	check.setSelected(false);
                textFile = textFC.getSelectedFile();
                textFC.setCurrentDirectory(textFile);
                textPath = textFile.getPath();
                slovnik= new Slovnik(textFile);
            }
        }}

    }
    
    /**
     * Importuje slovn�k.
     */
    private class ImportSlov
        implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {if(e.getSource()==importSlov){
            int ret = importFC.showOpenDialog(GUI.this);
            if(ret == 0)//== JFileChooser.APPROVE_OPTION
            {
                importFile = importFC.getSelectedFile();
                importFC.setCurrentDirectory(importFile);
                importPath = importFile.getPath();
                slovnik= OperaceIO.importuj(importFile);
            }
        }}

    }
    
    /**
     * Exportuje slovn�k.
     */
    private class ExportSlov
        implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {if(e.getSource()==exportSlov){
            int ret = exportFC.showSaveDialog(GUI.this);
            if(ret == 0)//== JFileChooser.APPROVE_OPTION
            {
                exportFile = exportFC.getSelectedFile();
                exportFC.setCurrentDirectory(exportFile);
                exportPath = exportFile.getPath();
                OperaceIO.exportuj(exportFile, slovnik);
                vypis.append("Soubor ulo�en i vyexportov�n. \n");
            }
        }}

    }
    
    /**
     * Prohled� text.
     */
    private class Hledej
        implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {if(e.getSource()==hledej){
            if( (!check.isSelected()) && (textPath.isEmpty()) ){//Error, pokud nen� pou�ita ��dn� mo�nost.
            	resetujVypis();
            	vypis.append("Nen� vybr�n vstup z konzole a nen� vybran� ��dn� soubor s textem. Hled�n� neprob�hlo. \n");
            }
            else if(!check.isSelected())  {//hled�n� ze souboru
            	vypis.setText(Hledac.hledej(klic.getText(), textFile));
            	
            }
            else {//hled�n� z textfieldu
            	slovnik = new Slovnik(zapis.getText());
            	vypis.setText(Hledac.hledej(klic.getText(), zapis.getText()));
            	
            }
            if(!Hledac.klicJeVeSlovniku(slovnik, klic.getText())){//jestli to slovo v�bec je ve slovn�ku
            	//TODO
            }
        }}

    }
    
    /**
     * Zat�m neimplementov�no, ponech�no pro p��pad nouze. TODO
     */
    private class Check
        implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {if(e.getSource()==check){
            
        }}

    }
	
}