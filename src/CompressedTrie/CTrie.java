package CompressedTrie;

/**
 * Created by Vaitus on 15.10.2016.
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
    }

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
                pridatRek(n.naslednikSHodnotou(x.charAt(i)), x.substring(i), zacatecniIndex);
                return;
            } else {
                Node novy = new Node(x.substring(i));
                novy.zacatecniIndex.add(zacatecniIndex);

                if(n.jeRoot()) {
                    n.pridejPotomka(novy);
                }
                else {
                    n.rozdeleni(i);
                    n.predek.pridejPotomka(novy);
                }

                return;
            }
        }
    }

    public String toString(Node x) {


        return super.toString();
    }
}
