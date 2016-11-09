package CompressedTrie;

import java.util.LinkedList;

/**
 * Created by Vaitus on 15.10.2016.
 * Implementace komprimovane trie
 */
public class CTrie {
    public Node root;

    /**
     * Konstruktor na vytvoreni trie a vytvoreni jejiho roota
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

    

    /**
     * Pridavani slov do Trie, zacina od roota
     * @param x pridavane slovo
     * @param zacatniIndex index na kterem zacinalo v textu
     */
    public void pridejRekurzivne(String x, int zacatniIndex) {
        pridatRek(root, x, zacatniIndex);
    }

    /**
     * Pridavani slov do Trie
     * @param n prohledavany Node
     * @param x dane slovo
     * @param zacatecniIndex index na kterem zacinalo v textu
     */
    private void pridatRek(Node n, String x, int zacatecniIndex) {
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
     * Vyhleda jestli hledane slovo se nachazi v Trie
     * @param x hledane slovo
     * @return pokud se nachazi navrati zacatecni indexy v textu, pokud nenachazi vrati null
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
     * Vytvareny slovnik
     */
    private static String slovnik = "";

    /**
     * Vytvori retezec ze vsech slov v Trie
     * @return retezec slovniku
     */
    public String vytvoreniSlovniku() {
        slovnik = "";
        vytvoreniSlovnikuRek(root);
        return slovnik;
    }

    /**
     * Rekuretni vytvareni slovniku, doplnkova metoda k vytvoreniSlovniku
     * @param n dany Node
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
