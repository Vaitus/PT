package CompressedTrie;

import java.util.LinkedList;

/**
 * Tøída pøedstavující komprimovanou trii.
 * Jednotlivé uzly pøedstavují instance tøídy Node.
 * @author Vít Teøl, pomáhal Marek Zábran.
 * 
 */
public class CTrie {
	/**Poèáteèní uzel trie (koøen).*/
    public Node root;

    /**
     * Primární bezparametrický konstruktor trie.
     * Vytváøí root a pøepisuje nìkteré jeho metody.
     */
    public CTrie() {
        root = new Node("") {
            @Override
            public boolean jeRoot() {
                return true;
            }

            @Override
            public boolean masHodnotu(char x, int index) {
                return false;
            }
        };
    }

    

   /* /** zbyteèné
     * Pøidá slovo do trie. 
     * @param x pridavane slovo
     * @param zacatniIndex index na kterem zacinalo v textu
     */
    /*public void pridejRekurzivne(String x, int zacatniIndex) {
        pridatRek(root, x, zacatniIndex);
    }*/

    /**
     * Pøidá rekurzivnì slovo do trie.
     * @param n souèasný uzel.
     * @param x pøidávané slovo.
     * @param zacatecniIndex index pøidávaného slova v textu.
     */
    public void pridatRek(Node n, String x, int zacatecniIndex) {
        boolean posledni = false;
        for (int i = 0; i < x.length(); i++) {//pøímé prohledání
            if ( i >= x.length()-1) posledni = true;//pokud jde o poslední znak

            if (n.masHodnotu(x.charAt(i),i)) {//hodota shodná v jednom písmenì
                if (posledni) //jen pokud jde o poslední znak
                {
                    if (n.key.length() > x.length()) 
                    {
                        n.rozdeleni(i+1);
                        n.predek.zacatecniIndex.add(zacatecniIndex);
                    }
                    else {
                        n.zacatecniIndex.add(zacatecniIndex);
                    }
                }
                continue;
            } else if (n.masNaslednikaSHodnotou(x.charAt(i))) {
                n.key = n.key.substring(0, i);
                pridatRek(n.naslednikSHodnotou(x.charAt(i)), x.substring(i), zacatecniIndex);
                return;
            } else {
                Node novy = new Node(x.substring(i));
                novy.zacatecniIndex.add(zacatecniIndex);

                if(n.jeRoot()) {
                    n.pridejPotomka(novy);
                } else if (n.key.length()-1 <= i) {
                    n.pridejPotomka(novy);
                } else {
                    n.rozdeleni(i);
                    n.predek.pridejPotomka(novy);
                }

                return;
            }
        }
    }

    /**
     * Vyhledá všechny indexy zadaného slova v trii.
     * @param x hledané slovo.
     * @return Poèáteèní indexy slova v textu. 
     * Vrací null, pokud slovo nebylo nalezeno.
     */
    public LinkedList<Integer> prohledat(String x){
        Node temp = root;

        int index = 0;
        int nodeInd = 0;

        while (index < x.length()) {
            if (nodeInd < temp.key.length() && temp.key.charAt(nodeInd) == x.charAt(index)) {
                index++;
                nodeInd++;
            } else if (index < x.length() && temp.masNaslednikaSHodnotou(x.charAt(index))) {
                temp = temp.naslednikSHodnotou(x.charAt(index));
                nodeInd = 0;
            } else {
                return null;
            }
        }
        if (nodeInd == temp.key.length()) {
            if (temp.zacatecniIndex.isEmpty()) {
                return null;
            } else {
                return temp.zacatecniIndex;
            }
        } else {
            return null;
        }
    }
    
    
    

    /**
     * Pomocný øetìzec obsahující všechna celá slova ve slovníku.
     */
    private static String slovnik = "";

    /**
     * Vytvoøí øetìzec ze všech slov ve trii.
     * @return Øetìzec všech slov ve trii.
     */
    public String vytvoreniSlovniku() {
        slovnik = "";
        vytvoreniSlovnikuRek(root);
        return slovnik;
    }

    /**
     * Rekuretni doplnkova metoda k vytvoreniSlovniku().
     * Prochází všechny následníky a zapisuje celá slova.
     * @param n souèasný uzel.
     */
    private void vytvoreniSlovnikuRek(Node n) {
        for (Node x : n.naslednik) {
            if (!x.zacatecniIndex.isEmpty() && !x.naslednik.isEmpty()) {
                slovnik += x.vypisCeleSlovo() + " ";
            }
            if (x.naslednik.isEmpty()) {
                slovnik += x.vypisCeleSlovo() + " ";
            } else {
                vytvoreniSlovnikuRek(x);
            }
        }
    }
    
    /**
     * Importuje uzly.
     * Už existující uzly se nepøepisují, jen se pøidávají indexy.
     * @param nody array podøetìzcù vzniklý rozdìlením slova 
     * na podøetìzce uzlù v komprimované trii.
     * @param indexy slova v pùvodním textu.
     */
    public void importujNod(String [] nody, int [] indexy){
    	Node old = root;
    	Node temp;
    	for(String s : nody){
    		temp = new Node(s);
    		if(!old.naslednik.contains(temp))
    		{
    			old.naslednik.add(temp);
    			temp.predek=old;
    			old=temp;
    		}
    		else{old=old.naslednikSHodnotou(s.charAt(0));}
    	}
    	for(int i : indexy){
    		old.zacatecniIndex.add(i);
    	}
    }

}
