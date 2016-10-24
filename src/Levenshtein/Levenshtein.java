package Levenshtein;


import java.util.LinkedList;

/**
 * Created by Vaitus on 23.10.2016.
 * Implementace Levenshteinovy vzdalenosti a pouziti pro rozdil mezi slovnikem a vyhledavanym slovem
 */
public class Levenshtein {

    /**
     * Vypocet Levenshteinovy vzdalenosti ze 2 retezcu
     * @param txt1 1. retezec
     * @param txt2 2. retezec
     * @return hodnota Levenshteinovy vzdalenosti pro dane 2 retezce
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
     * Vypocet minimalni hodnoty ze 3 cisel
     * @param a 1. cislo
     * @param b 2. cislo
     * @param c 3. cislo
     * @return minimalni hodnota
     */
    private static Integer minimum(int a, int b, int c) {
        return Math.min(Math.min(a,b),c);
    }

    /**
     * Vypsani maximalne 10 nejpodobnejsich slov ze slovniku danemu slovu, ktere maji maximalne Levenshteinovu vzdalenost 5
     * @param slovnik pole vsech slov ulozenych ve slovniku
     * @param slovo porovnavane slovo
     * @return List nejpodobnejsich slov
     */
    public static LinkedList<String> vypisDesetSlov(String[] slovnik, String slovo) {
        return vypisDesetSlov(slovnik, slovo, 10, 5);
    }

    /**
     * Pretizena metoda vypisDesetSlov, zde nastavitelna velikost poctu nejpodobnejsich slov a do jake Levenshteinovy vzdalenosti
     * @param slovnik pole vsech slov ulozenych ve slovniku
     * @param slovo porovnavane slovo
     * @param delka maximalni velikost navracujiciho Listu
     * @param velikostVzdalenosti maximalni Levenshteinova vzdalenost
     * @return List nejpodobnejsich slov
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
                if (levenDis[i] == minimalniVzdalenost && vysledek.size() < 11) {
                    vysledek.add(slovnik[i]);
                }
            }
            minimalniVzdalenost++;
        }
        return vysledek;
    }

}
