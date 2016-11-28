package lab_8.zad_4;

import lab_6.MyThread;
import lab_6.Obraz;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final int CHARACTERS_NUMBER = 94;
    public static final int ASCII = 33;
    private static final int NTHREADS = 10;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

        System.out.println("\nPunkt 1a:");

        System.out.println("Set image size: n (#rows), m(#kolumns)");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Obraz obraz = new Obraz(n, m);

        MyThread[] NewThr = new MyThread[CHARACTERS_NUMBER];

        for (int i = 0; i < CHARACTERS_NUMBER; i++) {

            Runnable runnable = new MyThread((ASCII + i), obraz);
            executor.execute(runnable);

        }

        executor.shutdown(); // egzekutor przestaje przyjmować nowe wątki
        while (!executor.isTerminated()) {
        } // oczekiwanie na zakończenie pracy wątków

    }
}