package CompressedTrie;

import java.util.LinkedList;

/**
 * Tøída pøedstavující jednotlivé uzly trie.
 * @author Vít Teøl, pomáhal Marek Zábran.
 * 
 */
public class Node {
    /**
     * Podøetìzec uzlu (Node) - (èást slova z textu).
     */
    String key;
    /**
     * Odkaz na pøedchozí uzel.
     */
    Node predek;
    /**
     * List všech následujících uzlù tohoto uzlu.
     */
   public LinkedList<Node> naslednik = new LinkedList<>();
    /**
     * List všech poèáteèních indexù v textu pro tento uzel
     * (jednou za každou použitý tohoto slova).
     */
   public LinkedList<Integer> zacatecniIndex = new LinkedList<>();

    /**
     * Primární konstruktor uzlu.
     * @param key podøetìzec uzlu.
     */
    public Node(String key) {
        this.key = key;
    }

    /**
     * Kontroluje, jestli je se jedná o root.
     * U rootu je tato metoda pøepsána.
     * @return true, pokud se jedná o root.
     */
    public boolean jeRoot() {
        return false;
    }

    /**
     * Kontroluje, jestli má uzel ve svém podøetìzci na zadaném indexu zadaný znak.
     * @param x zadaný znak.
     * @param index zadaný index.
     * @return True, pokud má na daném indexu daný znak.
     */
    public boolean masHodnotu(char x, int index) {
        if(index >= key.length()) return false;
        return key.charAt(index) == x;
    }

    /**
     * Kontroluje, jestli existuje následník se zadaným znakem na prvním indexu podøetìzce.
     * @param x kontrolovaný znak.
     * @return True, pokud existuje takový následník.
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
     * Hledá následníka se zadaným znakem na prvním indexu podøetìzce.
     * @param x hledaný znak.
     * @return Nalezený následník.
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
     * Rozdìlí uzel na dva.
     * (použito v pøípadì existence indentických 
     * podøetìzcù slov ve slovníku pro komprimaci).
     * @param index znaku, ve kterém se má øetìzec rozdìlit na dva podøetìzce.
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
     * Pøidá tomuto uzlu zadaného následníka.
     * @param n zadaný následník.
     */
    public void pridejPotomka(Node n){
        n.predek = this;
        this.naslednik.add(n);
    }

    /**
     * Vytáhne ze slovníku celé slovo z tohoto uzlu.
     * @return Celé slovo pøíslušící tomuto uzlu.
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
     * Získá podøetìzec tohoto uzlu a všechny pøíslušné poèáteèní indexy v podobì stringu.
     * @return Podøetìzec uzlu a poèáteèní indexy.
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
     * Získá všechny pøíslušné poèáteèní indexy v podobì stringu.
     * @return Poèáteèní indexy.
     */
    public String vratIndexy(){
    	String result = "\t";
        for(Integer index :zacatecniIndex) {
            result += index + " ";
        }
        return result;
    }

    /** 
     * Getr pro podøetìzec uzlu.
     * @return Podøetìzec tohoto uzlu.
     */
	public String getKey() {
		return this.key;
	}
    
    
    
    
}
