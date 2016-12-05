package lab_8.zad_2;

import lab_8.zad_1.Counter;
import lab_8.zad_1.CounterPlus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Marian on 28.11.2016.
 */
public class Main {

    private static final int NTHREADS = 10;
    private static final double XP = 0;
    private static final double XK = Math.PI;
    private static final double DX = 0.0000001;
    private static final int N = 50;
    private static final double NEWDX = (XK-XP)/N;

    public static void main(String[] args) {

        double calka = 0;

        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Double>> list = new ArrayList<>();

        double czas1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            Callable<Double> calkaCallable = new CalkaCallable(XP+(i*NEWDX), XP + (i*NEWDX + NEWDX), DX);
            Future<Double> future = executor.submit(calkaCallable);
            list.add(future);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {}
        double czas2 = System.currentTimeMillis();

        for(Future<Double> doubleFuture : list){
            try {
                calka += doubleFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Wynik: " + calka + " czas: " + (czas2-czas1) + "ms");

    }

}
