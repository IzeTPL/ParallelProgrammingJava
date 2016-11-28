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
    private static final double DX = 0.00001;
    private static final int N = (int) Math.ceil((XK-XP)/DX);

    public static void main(String[] args) {

        double calka = 0;

        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Double>> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Callable<Double> calkaCallable = new CalkaCallable(XP+(i*DX), XP + (i*DX + DX), DX);
            Future<Double> future = executor.submit(calkaCallable);
            list.add(future);
        }

        for(Future<Double> doubleFuture : list){
            try {
                calka += doubleFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        // Wait until all threads finish
        while (!executor.isTerminated()) {}

        System.out.println("Wynik: " + calka);

        CalkaCallable calkaSeq = new CalkaCallable(XP, XK, DX);
        try {
            calkaSeq.call();
        } catch (Exception e) {

        }

    }

}
