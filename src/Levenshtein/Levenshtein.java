package Levenshtein;


import java.util.LinkedList;

/**
 * Knihovn� t��da implementuj�c� Levenshteinovu vzd�lenost
 * a jej� vyu�it� pro nalezen� nejbli���ch slov.
 * @author V�t Te�l, pom�hal Marek Z�bran.
 */
public class Levenshtein {

    /**
     * V�po�et Levenshteinovy vzd�lenosti ze dvou �et�zc�.
     * @param txt1 1. �et�zec
     * @param txt2 2. �et�zec
     * @return Hodnota Levenshteinovy vzd�lenosti pro dan� dva �et�zce.
     */
    public static Integer levenshteinVzdalenost(String txt1, String txt2) {
        int[][] pole = new int[txt1.length()+1][txt2.length()+1];

        for (int i = 0; i < txt1.length()+1; i++) {
            pole[i][0] = i;
        }
        for (int i = 0; i < txt2.length()+1; i++) {
            pole[0][i] = i;
        }

        for (int i = 0; i < txt1.length(); i++) {
            for (int j = 0; j < txt2.length(); j++) {
                int edit = 0;
                if (txt1.charAt(i) != txt2.charAt(j)) {
                    edit = 1;
                }
                pole[i+1][j+1] = minimum(pole[i+1][j]+1, pole[i][j+1]+1, pole[i][j]+edit);
            }
        }

        return pole[txt1.length()][txt2.length()];
    }

    /**
     * V�po�et minim�ln� hodnoty ze t�� ��sel.
     * @param a 1. ��slo.
     * @param b 2. ��slo.
     * @param c 3. ��slo.
     * @return Minim�lni hodnota.
     */
    private static Integer minimum(int a, int b, int c) {
        return Math.min(Math.min(a,b),c);
    }

    /**
     * Vyp�e a� 10 nejpodobn�j��ch slov ze slovn�ku, kter� maj� maximaln� Levenshteinovu vzd�lenost 5.
     * Jedn� se o specifickou verzi stejnojmenn� p�et�en� metody.
     * @param slovnik pole v�ech slov ulo�en�ch ve slovn�ku.
     * @param slovo porovn�van� slovo.
     * @return List nejpodobn�j��ch slov.
     */
    public static LinkedList<String> vypisDesetSlov(String[] slovnik, String slovo) {
        return vypisDesetSlov(slovnik, slovo, 10, 5);
    }

    /**
     * P�et�en� metoda vypisDesetSlov.
     * Maximum nejbli���ch slov a nejvy��� Levenshteinova vzd�lenost jsou nastaviteln�.
     * @param slovnik pole vsech slov ulo�en�ch ve slovn�ku.
     * @param slovo porovn�van� slovo.
     * @param delka maximaln� mno�stv� nejbli���ch slov.
     * @param velikostVzdalenosti maximaln� Levenshteinova vzd�lenost.
     * @return List nejpodobn�j��ch slov.
     */
    public static LinkedList<String> vypisDesetSlov(String[] slovnik, String slovo, int delka, int velikostVzdalenosti) {
        int[] levenDis = new int[slovnik.length];
        for (int i = 0; i < slovnik.length; i++) {
            levenDis[i] = levenshteinVzdalenost(slovnik[i], slovo);
        }

        LinkedList<String> vysledek = new LinkedList<>();
        int minimalniVzdalenost = 0;

        while (vysledek.size() <= delka && minimalniVzdalenost < velikostVzdalenosti) {
            for (int i = 0; i < slovnik.length; i++) {
                if (levenDis[i] == minimalniVzdalenost && vysledek.size() < 11) {//Co ta konstanta tady? Nem� tu b�t velikostVzdalenosti+1?
                    vysledek.add(slovnik[i]);
                }
            }
            minimalniVzdalenost++;
        }
        return vysledek;
    }

}
