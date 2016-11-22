package lab_6; /**
 * Created by marian on 13.11.16.
 */

import java.util.Random;


public class Obraz {

    private int size_n;
    private int size_m;
    private char[][] tab;
    private int[] histogram;
    private Object locker = new Object();

    public Obraz(int n, int m) {

        this.size_n = n;
        this.size_m = m;
        tab = new char[n][m];

        final Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tab[i][j] = (char) (random.nextInt(Main.CHARACTERS_NUMBER) + Main.ASCII);  // ascii 33-127
                System.out.print(tab[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");

        histogram = new int[Main.CHARACTERS_NUMBER];
        clear_histogram();
    }

    public void clear_histogram() {

        for (int i = 0; i < Main.CHARACTERS_NUMBER; i++) histogram[i] = 0;

    }

    public synchronized void calculate_histogram(int character) {

        for (int i = 0; i < size_n; i++) {

            for (int j = 0; j < size_m; j++) {

                    if (tab[i][j] == (char) (character)) histogram[character - Main.ASCII]++;

            }

        }

    }

    public synchronized void calculate_histogram_row(int start, int end) {

        for (int i = start; i < end; i++) {

            for (int j = 0; j < size_m; j++) {

                histogram[tab[i][j] - Main.ASCII]++;

            }

        }

    }

    public synchronized void print_histogram(int character) {

        System.out.print("Watek " + Thread.currentThread().getName() + ": ");
        System.out.print((char) (character) + " ");

        for (int i = 0; i < histogram[character - Main.ASCII]; i++)
            System.out.print("=");
        System.out.println();

    }

    public char[][] getTab() {
        return tab;
    }

    public int getSize_n() {
        return size_n;
    }

    public int getSize_m() {
        return size_m;
    }
}
