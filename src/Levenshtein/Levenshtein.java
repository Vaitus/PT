package Levenshtein;


import java.util.LinkedList;

/**
 * Knihovní tøída implementující Levenshteinovu vzdálenost
 * a její využití pro nalezení nejbližších slov.
 * @author Vít Teøl, pomáhal Marek Zábran.
 */
public class Levenshtein {

    /**
     * Výpoèet Levenshteinovy vzdálenosti ze dvou øetìzcù.
     * @param txt1 1. øetìzec
     * @param txt2 2. øetìzec
     * @return Hodnota Levenshteinovy vzdálenosti pro dané dva øetìzce.
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
     * Výpoèet minimální hodnoty ze tøí èísel.
     * @param a 1. èíslo.
     * @param b 2. èíslo.
     * @param c 3. èíslo.
     * @return Minimálni hodnota.
     */
    private static Integer minimum(int a, int b, int c) {
        return Math.min(Math.min(a,b),c);
    }

    /**
     * Vypíše až 10 nejpodobnìjších slov ze slovníku, které mají maximalní Levenshteinovu vzdálenost 5.
     * Jedná se o specifickou verzi stejnojmenné pøetížené metody.
     * @param slovnik pole všech slov uložených ve slovníku.
     * @param slovo porovnávané slovo.
     * @return List nejpodobnìjších slov.
     */
    public static LinkedList<String> vypisDesetSlov(String[] slovnik, String slovo) {
        return vypisDesetSlov(slovnik, slovo, 10, 5);
    }

    /**
     * Pøetížená metoda vypisDesetSlov.
     * Maximum nejbližších slov a nejvyšší Levenshteinova vzdálenost jsou nastavitelné.
     * @param slovnik pole vsech slov uložených ve slovníku.
     * @param slovo porovnávané slovo.
     * @param delka maximalní množství nejbližších slov.
     * @param velikostVzdalenosti maximalní Levenshteinova vzdálenost.
     * @return List nejpodobnìjších slov.
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
                if (levenDis[i] == minimalniVzdalenost && vysledek.size() < 11) {//Co ta konstanta tady? Nemá tu být velikostVzdalenosti+1?
                    vysledek.add(slovnik[i]);
                }
            }
            minimalniVzdalenost++;
        }
        return vysledek;
    }

}
