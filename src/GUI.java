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


public class GUI extends JFrame{
	
	/**Serial.*/
	private static final long serialVersionUID = 1L;
	
	/**Sirka pouívanıch textovıch objektù*/
	private final int sirka = 53;
	
	//Naèátání textu.
	
	/**Tlaèítko pro naètení textu z JTextArea vstup nebo ze souboru (pøepíná CheckBox).*/
	JButton nactiText;
	
	/**Vybírání souboru pro naètení textu ze souboru.*/
	JFileChooser textFC = new JFileChooser();
	
	/**Soubor s naètenım textem.*/
	private File textFile;
	
	/**Adresa pro naètení textu.*/
    private String textPath="";
	
	//Import slovníku.
	
	/**Tlaèítko pro import slovníku ze souboru.*/
	JButton importSlov;
	
	/**Vybírání souboru pro import slovníku.*/
	JFileChooser importFC = new JFileChooser();
	
	/**Soubor s importovanım slovníkem.*/
	private File importFile;
	
	/**Adresa pro import.*/
    private String importPath="";
	
	//Export slovníku.

	/**Tlaèítko pro export slovníku do souboru.*/
	JButton exportSlov;
	
	/**Vybírání souboru pro export slovníku.*/
	JFileChooser exportFC = new JFileChooser();
	
	/**Soubor s exportovanım slovníkem.*/
	private File exportFile;
	
	/**Adresa pro export.*/
    private String exportPath="";
	
	//Ostatní prvky GUI
	
    /**Tlaèítko pro hledání klíèe z JTextArea klic.*/
	JButton hledej;
	
	/**Objekt, do kterého se zapisuje hledanı klíè.*/
	JTextArea klic= new JTextArea(1, sirka);
	
	/**Objekt, do kterého se vypisuje textovı vıstup.*/
	JTextArea vypis= new JTextArea(5, sirka);
	
	/**Objekt, do kterého se zapisuje textovı vstup.*/
	JTextArea zapis= new JTextArea(10, sirka);
	
	/**Objekt, kterı urèuje, má-li se brát text ze souboru a nebo z objektu zapis.*/
	JCheckBox check= new JCheckBox();
	
	//Kritické elementy
	
	/**String se souèasnì pouívanım textem. - pravdìpodobnì nebude potøeba*/
	public String text;
	
	/**Pøepravka pro slovnik.*/
	public Slovnik slovnik=new Slovnik();
	
	GUI INSTANCE;
	
	/**Hlavní konstruktor.*/
	public GUI(){
		INSTANCE=this;
		this.getContentPane().add(vytvorVnitrek());
		this.addComponentListener(new ComponentAdapter() {//pro zmenu rozmìrù textovıch objektù pøi zmìnì velikosti
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
	
	/**Privátní metoda slouící pro vytvoøení containeru s grafickımi objekty.
	 * Vytváøí je dynamické a zcela nahodile, nebo toto absolutnì neumím pouívat.*/
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
        c.gridx = 2; //tady je zmìna pùvodnì 1
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
        
        check = new JCheckBox("Zápis z konzole");
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
        
        klic.setText("Hledanı klíè");        
        c.fill = 3;//Textovı vıpis
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 3;
        klic.setEditable(true);
        klic.setLineWrap(true);
        klic.setWrapStyleWord(true);
        vnitrekPN.add(klic, c);
        
        resetujVypis();//Resetuje vıpis, aby nebyl zbyteènì velkı.
        c.fill = 3;//Textovı vıpis
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 3;
        vypis.setEditable(false);
        vypis.setLineWrap(true);
        vypis.setWrapStyleWord(true);
        vnitrekPN.add(vypis, c);
        
        zapis.setText("Text ke zpracování. Toto pole bude vyuito jen pokud je povolen zápis z konzole.");
        c.fill = 3;//Textovı vıpis
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
		vypis.setText("Kliknutím na tlaèátko Naèíst text mùete naèíst text ze souboru a tento text bude automaticky zpracován. \n");
		vypis.append("Kliknutím na tlaèítko Importovat slovník se souèasnı slovník importuje. \n");//TODO nìkdy by to chtìlo dopsat zbytek a poøešit, kdy se to má vypsat a pøepsat
		
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
                OperaceIO.exportuj(exportFile, slovnik);
                vypis.append("Soubor uloen i vyexportován. \n");
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
            if( (!check.isSelected()) && (textPath.isEmpty()) ){//Error, pokud není pouita ádná monost.
            	resetujVypis();
            	vypis.append("Není vybrán vstup z konzole a není vybranı ádnı soubor s textem. Hledání neprobìhlo. \n");
            }
           // vypis.setText(Hledac.hledej(slovnik, klic.getText()));
            
            else if(!check.isSelected())  {//hledání ze souboru
            	//vypis.setText(Hledac.hledej(klic.getText(), textFile));
            	vypis.setText(Hledac.hledej(slovnik, klic.getText()));
            }
            else {//hledání z textfieldu
            	slovnik = new Slovnik(zapis.getText());
            	vypis.setText(Hledac.hledej(slovnik, klic.getText()));
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
