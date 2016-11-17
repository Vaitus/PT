package CompressedTrie;

import java.util.LinkedList;

/**
 * T��da p�edstavuj�c� komprimovanou trii.
 * Jednotliv� uzly p�edstavuj� instance t��dy Node.
 * @author V�t Te�l, pom�hal Marek Z�bran.
 * 
 */
public class CTrie {
	/**Po��te�n� uzel trie (ko�en).*/
    public Node root;

    /**
     * Prim�rn� bezparametrick� konstruktor trie.
     * Vytv��� root a p�episuje n�kter� jeho metody.
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

    

   /* /** zbyte�n�
     * P�id� slovo do trie. 
     * @param x pridavane slovo
     * @param zacatniIndex index na kterem zacinalo v textu
     */
    /*public void pridejRekurzivne(String x, int zacatniIndex) {
        pridatRek(root, x, zacatniIndex);
    }*/

    /**
     * P�id� rekurzivn� slovo do trie.
     * @param n sou�asn� uzel.
     * @param x p�id�van� slovo.
     * @param zacatecniIndex index p�id�van�ho slova v textu.
     */
    public void pridatRek(Node n, String x, int zacatecniIndex) {
        boolean posledni = false;
        for (int i = 0; i < x.length(); i++) {//p��m� prohled�n�
            if ( i >= x.length()-1) posledni = true;//pokud jde o posledn� znak

            if (n.masHodnotu(x.charAt(i),i)) {//hodota shodn� v jednom p�smen�
                if (posledni) //jen pokud jde o posledn� znak
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
     * Vyhled� v�echny indexy zadan�ho slova v trii.
     * @param x hledan� slovo.
     * @return Po��te�n� indexy slova v textu. 
     * Vrac� null, pokud slovo nebylo nalezeno.
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
     * Pomocn� �et�zec obsahuj�c� v�echna cel� slova ve slovn�ku.
     */
    private static String slovnik = "";

    /**
     * Vytvo�� �et�zec ze v�ech slov ve trii.
     * @return �et�zec v�ech slov ve trii.
     */
    public String vytvoreniSlovniku() {
        slovnik = "";
        vytvoreniSlovnikuRek(root);
        return slovnik;
    }

    /**
     * Rekuretni doplnkova metoda k vytvoreniSlovniku().
     * Proch�z� v�echny n�sledn�ky a zapisuje cel� slova.
     * @param n sou�asn� uzel.
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
     * U� existuj�c� uzly se nep�episuj�, jen se p�id�vaj� indexy.
     * @param nody array pod�et�zc� vznikl� rozd�len�m slova 
     * na pod�et�zce uzl� v komprimovan� trii.
     * @param indexy slova v p�vodn�m textu.
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
