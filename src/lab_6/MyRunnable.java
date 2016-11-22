package lab_6;

/**
 * Created by marian on 13.11.16.
 */
public class MyRunnable implements Runnable {

    private int start;
    private int end;
    private Obraz obraz;

    public MyRunnable(int start, int end, Obraz obraz) {

        super();
        this.obraz = obraz;
        this.start = start;
        this.end = end;

    }

    public void run() {

        for (int i = start; i < end; i++) {

            obraz.calculate_histogram(i);
            obraz.print_histogram(i);

        }

    }
}
