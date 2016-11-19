package CompressedTrie;

import java.util.LinkedList;

/**
 * T??da p?edstavuj?c? jednotliv? uzly trie.
 * @author V?t Te?l, pom?hal Marek Z?bran.
 * 
 */
public class Node {
    /**
     * Pod?et?zec uzlu (Node) - (??st slova z textu).
     */
    String key;
    /**
     * Odkaz na p?edchoz? uzel.
     */
    Node predek;
    /**
     * List v?ech n?sleduj?c?ch uzl? tohoto uzlu.
     */
   public LinkedList<Node> naslednik = new LinkedList<>();
    /**
     * List v?ech po??te?n?ch index? v textu pro tento uzel
     * (jednou za ka?dou pou?it? tohoto slova).
     */
   public LinkedList<Integer> zacatecniIndex = new LinkedList<>();

    /**
     * Prim?rn? konstruktor uzlu.
     * @param key pod?et?zec uzlu.
     */
    public Node(String key) {
        this.key = key;
    }

    /**
     * Kontroluje, jestli je se jedn? o root.
     * U rootu je tato metoda p?eps?na.
     * @return true, pokud se jedn? o root.
     */
    public boolean jeRoot() {
        return false;
    }

    /**
     * Kontroluje, jestli m? uzel ve sv?m pod?et?zci na zadan?m indexu zadan? znak.
     * @param x zadan? znak.
     * @param index zadan? index.
     * @return True, pokud m? na dan?m indexu dan? znak.
     */
    public boolean masHodnotu(char x, int index) {
        if(index >= key.length()) return false;
        return key.charAt(index) == x;
    }

    /**
     * Kontroluje, jestli existuje n?sledn?k se zadan?m znakem na prvn?m indexu pod?et?zce.
     * @param x kontrolovan? znak.
     * @return True, pokud existuje takov? n?sledn?k.
     */
    public boolean masNaslednikaSHodnotou(char x) {
        for (Node n: naslednik) {
            if (!n.key.isEmpty()) {
                if (n.key.charAt(0) == x) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Hled? n?sledn?ka se zadan?m znakem na prvn?m indexu pod?et?zce.
     * @param x hledan? znak.
     * @return Nalezen? n?sledn?k.
     */
    public Node naslednikSHodnotou(char x){
        for (Node n: naslednik) {
            if (!n.key.isEmpty()) {
                if (n.key.charAt(0) == x) {
                    return n;
                }
            }
        }
        return null;
    }

    /**
     * Rozd?l? uzel na dva.
     * (pou?ito v p??pad? existence indentick?ch 
     * pod?et?zc? slov ve slovn?ku pro komprimaci).
     * @param index znaku, ve kter?m se m? ?et?zec rozd?lit na dva pod?et?zce.
     */
    public void rozdeleni(int index){
        Node novy = new Node(this.key.substring(0,index));
        novy.predek = this.predek;

        predek.naslednik.remove(this);
        predek.naslednik.add(novy);

        novy.naslednik.add(this);

        this.predek = novy;
        this.key = this.key.substring(index);
    }

    /**
     * P?id? tomuto uzlu zadan?ho n?sledn?ka.
     * @param n zadan? n?sledn?k.
     */
    public void pridejPotomka(Node n){
        n.predek = this;
        this.naslednik.add(n);
    }

    /**
     * Vyt?hne ze slovn?ku cel? slovo z tohoto uzlu.
     * @return Cel? slovo p??slu??c? tomuto uzlu.
     */
    public String vypisCeleSlovo() {
        String s = "";
        Node n = this;

        while (!n.jeRoot()) {
            s = n.key + s;
            n = n.predek;
        }

        //System.out.println(s);
        return s;
    }
    
    

    /**
     * Z?sk? pod?et?zec tohoto uzlu a v?echny p??slu?n? po??te?n? indexy v podob? stringu.
     * @return Pod?et?zec uzlu a po??te?n? indexy.
     */
    @Override
    public String toString() {
    	
    	String result = key + " ";
        for(Integer index :zacatecniIndex) {
            result += index + " ";
        }
        return result;
    }
    
    /**
     * Z?sk? v?echny p??slu?n? po??te?n? indexy v podob? stringu.
     * @return Po??te?n? indexy.
     */
    public String vratIndexy(){
    	String result = "\t";
        for(Integer index :zacatecniIndex) {
            result += index + " ";
        }
        return result;
    }

    /** 
     * Getr pro pod?et?zec uzlu.
     * @return Pod?et?zec tohoto uzlu.
     */
	public String getKey() {
		return this.key;
	}
    
    
    
    
}
