package Levenshtein;


/**
 * Created by Vaitus on 23.10.2016.
 */
public class Levenshtein {

    public static Integer LevenshteinVzdalenost(String txt1, String txt2) {
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

    private static Integer minimum(int a, int b, int c) {
        return Math.min(Math.min(a,b),c);
    }

}
