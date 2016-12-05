package lab_8.zad_4;

import lab_6.MyRunnable;
import lab_6.Obraz;
import lab_8.zad_2.CalkaCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static final int CHARACTERS_NUMBER = 94;
    public static final int ASCII = 33;
    private static final int NTHREADS = 5;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPunkt 1a:");

        System.out.println("Set image size: n (#rows), m(#kolumns)");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Obraz obraz = new Obraz(n, m);

        int start = 33, end = 33;
        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Double>> list = new ArrayList<>();

        for (int i = 0; i < 25; i++) {

            if(CHARACTERS_NUMBER % NTHREADS == 0) {

                end += CHARACTERS_NUMBER / NTHREADS;

            } else {

                end += (CHARACTERS_NUMBER / NTHREADS) + 1;

            }

            int warunek = (end - start) * (i + 1);

            if(warunek > CHARACTERS_NUMBER) {
                end -= (warunek  - CHARACTERS_NUMBER);
            }

            Runnable runnable = new MyRunnable(start, end, obraz);
            executor.execute(runnable);

            start = end;
        }

        executor.shutdown();

        while (!executor.isTerminated()) {}

    }
}
