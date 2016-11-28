package lab_8.zad_3;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Marian on 28.11.2016.
 */
public class Main {

    private static final int TOTAL_NUMBERS = 100;

    public static void main(String[] args) {

        Random random = new Random();

        int[] numbers = new int[TOTAL_NUMBERS]; // dalej: wypełnienie tablicy

        for (int i = 0; i < numbers.length; i++) {

            numbers[i] = random.nextInt(100);

        }

            DivideTask task = new DivideTask(numbers);
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            numbers = forkJoinPool.invoke(task); // invoke waits for completion and returns results

        for (int i = 0; i < numbers.length; i++) {

            System.out.print(numbers[i] + " ");

        }

    }

}
