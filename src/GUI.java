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
	
	/**Sirka používaných textových objektù*/
	private final int sirka = 53;
	
	//Naèátání textu.
	//všechny tøi asi mùžu slouèit, jsou zbyteèný.
	/**Tlaèítko pro naètení textu z JTextArea vstup nebo ze souboru (pøepíná CheckBox).*/
	JButton nactiText;
	
	/**Vybírání souboru pro naètení textu ze souboru.*/
	JFileChooser textFC = new JFileChooser();
	
	/**Soubor s naèteným textem.*/
	private File textFile;
	
	/**Adresa pro naètení textu.*/
    private String textPath="";
	
	//Import slovníku.
	
	/**Tlaèítko pro import slovníku ze souboru.*/
	JButton importSlov;
	
	/**Vybírání souboru pro import slovníku.*/
	JFileChooser importFC = new JFileChooser();
	
	/**Soubor s importovaným slovníkem.*/
	private File importFile;
	
	/**Adresa pro import.*/
    private String importPath="";
	
	//Export slovníku.

	/**Tlaèítko pro export slovníku do souboru.*/
	JButton exportSlov;
	
	/**Vybírání souboru pro export slovníku.*/
	JFileChooser exportFC = new JFileChooser();
	
	/**Soubor s exportovaným slovníkem.*/
	private File exportFile;
	
	/**Adresa pro export.*/
    private String exportPath="";
	
	//Ostatní prvky GUI
	
    /**Tlaèítko pro hledání klíèe z JTextArea klic.*/
	JButton hledej;
	
	/**Objekt, do kterého se zapisuje hledaný klíè.*/
	JTextArea klic= new JTextArea(1, sirka);
	
	/**Objekt, do kterého se vypisuje textový výstup.*/
	JTextArea vypis= new JTextArea(5, sirka);
	
	/**Objekt, do kterého se zapisuje textový vstup.*/
	JTextArea zapis= new JTextArea(10, sirka);
	
	/**Objekt, který urèuje, má-li se brát text ze souboru a nebo z objektu zapis.*/
	JCheckBox check= new JCheckBox();
	
	//Kritické elementy
	
	/**String se souèasnì používaným textem. - pravdìpodobnì nebude potøeba*/
	//public String text;
	
	/**Pøepravka pro slovnik.*/
	public Slovnik slovnik=new Slovnik();
	
	GUI INSTANCE;
	
	/**Hlavní konstruktor.*/
	public GUI(){
		INSTANCE=this;
		this.getContentPane().add(vytvorVnitrek());
		exportFC.addChoosableFileFilter(new FileNameExtensionFilter("Textový soubor","txt"));
		this.addComponentListener(new ComponentAdapter() {//pro zmenu rozmìrù textových objektù pøi zmìnì velikosti
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
	
	/**Privátní metoda sloužící pro vytvoøení containeru s grafickými objekty.
	 * Vytváøí je dynamické a zpravidla zcela nahodile.*/
	private Container vytvorVnitrek()
    {
		JPanel vnitrekPN = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        
        nactiText = new JButton("Naèíst text");
        nactiText.addActionListener(new NactiText());
        
        c.fill = 0;
        c.anchor = 10;
        c.weightx = 0.0D;
        c.weighty = 0.0D;
        c.gridx = 2; 
        c.gridy = 1;
        c.gridwidth = 1;
        vnitrekPN.add(nactiText, c);
        
        importSlov = new JButton("Importovat slovník");
        importSlov.addActionListener(new ImportSlov());
        
        c.fill = 0;
        c.anchor = 10;
        c.weightx = 0.0D;
        c.weighty = 0.0D;
        c.gridx = 3; 
        c.gridy = 1;
        c.gridwidth = 1;
        vnitrekPN.add(importSlov, c);
        
        exportSlov = new JButton("Exportovat slovník");
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
        
        klic.setText("Hledaný klíè");        
        c.fill = 3;//Textový výpis
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 3;
        klic.setEditable(true);
        klic.setLineWrap(true);
        klic.setWrapStyleWord(true);
        vnitrekPN.add(klic, c);
        
        resetujVypis();//Resetuje výpis, aby nebyl zbyteènì velký.
        c.fill = 3;//Textový výpis
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 3;
        vypis.setEditable(false);
        vypis.setLineWrap(true);
        vypis.setWrapStyleWord(true);
        vnitrekPN.add(vypis, c);
        
        zapis.setText("Text ke zpracování. Toto pole bude využito jen pokud je povolen zápis z konzole.");
        c.fill = 3;//Textový výpis
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
	
	/**Soukromá procedura, která znovu zapíše do objektu vypis všechny potøebné údaje.*/
	private void resetujVypis(){
		vypis.setText("Kliknutím na tlaèítko Naèíst text mùžete naèíst text ze souboru a tento text bude automaticky zpracován. \n");
		vypis.append("Kliknutím na tlaèítko Importovat slovník se naimportuje cizí slovník. \n");
		vypis.append("Kliknutím na tlaèítko Exportovat slovník se souèasný slovník exportuje. \n");
		vypis.append("Kliknutím na tlaèítko Hledat bude vyhledán klíè ve slovníku. \n");
		vypis.append("Kliknutím na tlaèítko Vstup z konzole pøepínáte mezi vstupem ze souboru a z konzole níže. \n");
	}
	
	/**
     * Naète text.
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
                vypis.append("Text byl úspìšnì naèten.");
            }
        }}

    }
    
    /**
     * Importuje slovník.
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
                vypis.append("Slovník byl úspìšnì naimportován.");
            }
        }}

    }
    
    /**
     * Exportuje slovník.
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
                vypis.append("Soubor byl úspìšnì vyexportován. \n");
            }
        }}

    }
    
    /**
     * Prohledá text.
     */
    private class Hledej
        implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {if(e.getSource()==hledej){
            if( (!check.isSelected()) && (textPath.isEmpty()) ){//Error, pokud není použita žádná možnost.
            	resetujVypis();
            	vypis.append("Není vybrán vstup z konzole a není vybraný žádný soubor s textem. Hledání neprobìhlo. \n");
            }
           // vypis.setText(Hledac.hledej(slovnik, klic.getText()));
            
            else if(!check.isSelected())  {//hledání ze souboru
            	//vypis.setText(Hledac.hledej(klic.getText(), textFile));
            	vypis.setText(Hledac.hledej(slovnik, klic.getText()));
            }
            else {//hledání z textfieldu
            	slovnik = new Slovnik(zapis.getText());
            	vypis.setText(Hledac.hledej(slovnik, klic.getText()));
            	check.setSelected(false);
            	//vypis.setText(Hledac.hledej(klic.getText(), zapis.getText()));
            	
            }
           /* if(!Hledac.klicJeVeSlovniku(slovnik, klic.getText())){//jestli to slovo vùbec je ve slovníku
            	//TODO
            }*/
        }}

    }
    
    /**
     * Zatím neimplementováno, ponecháno pro pøípad nouze. TODO
     */
    private class Check
        implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {if(e.getSource()==check){
            
        }}

    }
	
}
