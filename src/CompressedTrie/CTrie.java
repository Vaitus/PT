package CompressedTrie;

import java.util.LinkedList;

/**
 * Created by Vaitus on 15.10.2016.
 * Implementace komprimovane trie
 */
public class CTrie {
    Node root;

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

    /*@Deprecated
    public void pridatA(String x, int zacatecniIndex) {
        Node soucasnyNode;
        Node tempNode;

        boolean posledni = false;
        if ((soucasnyNode = root.naslednikSHodnotou(x.charAt(0))) != null) {
            for (int i = 1; i < x.length(); i++) {
                if(i == x.length() - 1) posledni = true;

                if (soucasnyNode.masHodnotu(x.charAt(i), i)) {
                    if(posledni) soucasnyNode.zacatecniIndex.add(zacatecniIndex);
                    continue;
                } else if ((tempNode = soucasnyNode.naslednikSHodnotou(x.charAt(i))) != null) {
                    soucasnyNode = tempNode;
                } else {
                    //soucasnyNodu pridas dite...
                    //TODO Pridas node
                }
            }
        }
        else {
            //NEexistuje ve strome, pridat celej text x do jednoho nodu
        }
    }*/

    public void pridejRekurzivne(String x, int zacatniIndex) {
        pridatRek(root, x, zacatniIndex);
    }

    private void pridatRek(Node n, String x, int zacatecniIndex) {
        boolean posledni = false;
        for (int i = 0; i < x.length(); i++) {
            if ( i >= x.length()-1 ) posledni = true;

            if (n.masHodnotu(x.charAt(i),i)) {
                if (posledni) {
                    if (n.key.length() > x.length()) {
                        System.out.println("nekdy tu?");
                        n.rozdeleni(i);
                        n.predek.zacatecniIndex.add(zacatecniIndex);
                    }
                    else {
                        n.zacatecniIndex.add(zacatecniIndex);
                    }
                }
                continue;
            } else if (n.masNaslednikaSHodnotou(x.charAt(i))) {
                n.key = n.key.substring(0, i);
                System.out.println(n.key + " Proc se to tu usekne");
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
            return temp.zacatecniIndex;
        } else {
            return null;
        }
    }
}
