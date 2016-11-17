import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GUI extends JFrame{
	
	/**Serial.*/
	private static final long serialVersionUID = 1L;
	
	/**Sirka pou��van�ch textov�ch objekt�*/
	private final int sirka = 53;
	
	//Na��t�n� textu.
	//v�echny t�i asi m��u slou�it, jsou zbyte�n�.
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
	JTextArea klic= new JTextArea(1, sirka);
	
	/**Objekt, do kter�ho se vypisuje textov� v�stup.*/
	JTextArea vypis= new JTextArea(5, sirka);
	
	/**Objekt, do kter�ho se zapisuje textov� vstup.*/
	JTextArea zapis= new JTextArea(10, sirka);
	
	/**Objekt, kter� ur�uje, m�-li se br�t text ze souboru a nebo z objektu zapis.*/
	JCheckBox check= new JCheckBox();
	
	//Kritick� elementy
	
	/**String se sou�asn� pou��van�m textem. - pravd�podobn� nebude pot�eba*/
	//public String text;
	
	/**P�epravka pro slovnik.*/
	public Slovnik slovnik=new Slovnik();
	
	GUI INSTANCE;
	
	/**Hlavn� konstruktor.*/
	public GUI(){
		INSTANCE=this;
		this.getContentPane().add(vytvorVnitrek());
		exportFC.addChoosableFileFilter(new FileNameExtensionFilter("Textov� soubor","txt"));
		this.addComponentListener(new ComponentAdapter() {//pro zmenu rozm�r� textov�ch objekt� p�i zm�n� velikosti
            public void componentResized(ComponentEvent e) {
                int a = zapis.getHeight();
                int b =INSTANCE.getWidth()-20;
                zapis.setSize(b, a);
                a=vypis.getHeight();
                vypis.setSize(b, a);
                a=klic.getHeight();
                b =(int) (INSTANCE.getWidth()*0.75);
                klic.setSize(b-100, a);
            }
        });
	}
	
	/**Priv�tn� metoda slou��c� pro vytvo�en� containeru s grafick�mi objekty.
	 * Vytv��� je dynamick� a zpravidla zcela nahodile.*/
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
        c.gridx = 2; 
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
        
        check = new JCheckBox("Vstup z konzole");
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
        klic.setLineWrap(true);
        klic.setWrapStyleWord(true);
        vnitrekPN.add(klic, c);
        
        resetujVypis();//Resetuje v�pis, aby nebyl zbyte�n� velk�.
        c.fill = 3;//Textov� v�pis
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 3;
        vypis.setEditable(false);
        vypis.setLineWrap(true);
        vypis.setWrapStyleWord(true);
        vnitrekPN.add(vypis, c);
        
        zapis.setText("Text ke zpracov�n�. Toto pole bude vyu�ito jen pokud je povolen z�pis z konzole.");
        c.fill = 3;//Textov� v�pis
        c.anchor = GridBagConstraints.SOUTH;
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 3;
        zapis.setEditable(true);
        zapis.setLineWrap(true);
        zapis.setWrapStyleWord(true);
        vnitrekPN.add(zapis, c);
        
        
        
        return vnitrekPN;
    }
	
	/**Soukrom� procedura, kter� znovu zap�e do objektu vypis v�echny pot�ebn� �daje.*/
	private void resetujVypis(){
		vypis.setText("Kliknut�m na tla��tko Na��st text m��ete na��st text ze souboru a tento text bude automaticky zpracov�n. \n");
		vypis.append("Kliknut�m na tla��tko Importovat slovn�k se naimportuje ciz� slovn�k. \n");
		vypis.append("Kliknut�m na tla��tko Exportovat slovn�k se sou�asn� slovn�k exportuje. \n");
		vypis.append("Kliknut�m na tla��tko Hledat bude vyhled�n kl�� ve slovn�ku. \n");
		vypis.append("Kliknut�m na tla��tko Vstup z konzole p�ep�n�te mezi vstupem ze souboru a z konzole n�e. \n");
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
                zapis.setText(OperaceIO.nactiText(textFile));
                resetujVypis();
                vypis.append("Text byl �sp�n� na�ten.");
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
                check.setSelected(false);
                resetujVypis();
                zapis.setText(slovnik.trie.vytvoreniSlovniku());
                vypis.append("Slovn�k byl �sp�n� naimportov�n.");
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
                if(check.isSelected()){slovnik = new Slovnik(zapis.getText());}
                OperaceIO.exportuj(exportFile, slovnik);
                resetujVypis();
                vypis.append("Soubor byl �sp�n� vyexportov�n. \n");
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
           // vypis.setText(Hledac.hledej(slovnik, klic.getText()));
            
            else if(!check.isSelected())  {//hled�n� ze souboru
            	//vypis.setText(Hledac.hledej(klic.getText(), textFile));
            	vypis.setText(Hledac.hledej(slovnik, klic.getText()));
            }
            else {//hled�n� z textfieldu
            	slovnik = new Slovnik(zapis.getText());
            	vypis.setText(Hledac.hledej(slovnik, klic.getText()));
            	check.setSelected(false);
            	//vypis.setText(Hledac.hledej(klic.getText(), zapis.getText()));
            	
            }
           /* if(!Hledac.klicJeVeSlovniku(slovnik, klic.getText())){//jestli to slovo v�bec je ve slovn�ku
            	//TODO
            }*/
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
