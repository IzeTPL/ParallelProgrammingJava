package lab_6;

import java.util.Scanner;

public class Main {

    public static final int CHARACTERS_NUMBER = 10;
    public static final int ASCII = 33;

    public static void main(String[] args) {

        //PUNKT A

        Scanner scanner = new Scanner(System.in);

        System.out.println("Set image size: n (#rows), m(#kolumns)");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Obraz obraz = new Obraz(n, m);

        MyThread[] NewThr = new MyThread[CHARACTERS_NUMBER];

        for (int i = 0; i < CHARACTERS_NUMBER; i++) {

            NewThr[i] = new MyThread( (i + ASCII), obraz);
            NewThr[i].setName(Integer.toString(i));
            NewThr[i].start();

        }

        for (int i = 0; i < CHARACTERS_NUMBER; i++) {

            try {
        	NewThr[i].join();
            } catch (InterruptedException e) {}

        }

        obraz.clear_histogram();

        //PUNKT B

        System.out.println("Set number of threads");
        int num_threads = scanner.nextInt();

        Thread[] threads = new Thread[num_threads];

        int character = 0, start = 33, end = 33;
        int[] characters;
        for (int i = 0; i < num_threads; i++) {

            if(CHARACTERS_NUMBER % num_threads == 0) {

                end += CHARACTERS_NUMBER / num_threads;

            } else {

                end += (CHARACTERS_NUMBER / num_threads) + 1;

            }

            int warunek = (end - start) * (i + 1);

            if(warunek > CHARACTERS_NUMBER) {
                end -= (warunek  - CHARACTERS_NUMBER);
            }

            threads[i] = new Thread(new MyRunnable(start, end, obraz));
            threads[i].setName(Integer.toString(i));
            threads[i].start();

            start = end;

        }

        for (int i = 0; i < num_threads; i++) {

            try {
                threads[i].join();
            } catch (InterruptedException e) {}

        }

        obraz.clear_histogram();

        //PUNKT 2

        System.out.println("Block\n");

        Thread[] threads2 = new Thread[num_threads];

        character = 0;
        start = 0;
        end = 0;
        int size = n*m;

        for (int i = 0; i < num_threads; i++) {

            if((n) % num_threads == 0) {

                end += n / num_threads;

            } else {

                end += (n / num_threads) + 1;

            }

            int warunek = (end - start) * (i + 1);

            if(warunek > n) {
                end -= (warunek  - n);
            }

            threads2[i] = new Thread(new MyRow(start, end, obraz));
            threads2[i].setName(Integer.toString(i));
            threads2[i].start();

            start = end;

        }

        for (int i = 0; i < num_threads; i++) {

            try {
                threads2[i].join();
            } catch (InterruptedException e) {}

        }

        for (int i = ASCII; i < CHARACTERS_NUMBER + ASCII; i++) {
            obraz.print_histogram(i);
        }

    }
}
