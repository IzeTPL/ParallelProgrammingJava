package lab_8.zad_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by marian on 29.11.16.
 */
public class SeqTest {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Callable<Double> calkaCallable = new CalkaCallable(0, Math.PI, 0.0000001);
        double czas1 = System.currentTimeMillis();
        Future<Double> future = executor.submit(calkaCallable);

        executor.shutdown();
        while(!executor.isTerminated()) {}
        double czas2 = System.currentTimeMillis();

        try {
            System.out.println("Wynik: " + future.get() + " czas: " + (czas2-czas1) + "ms");
        } catch (Exception e) {}
    }

}
