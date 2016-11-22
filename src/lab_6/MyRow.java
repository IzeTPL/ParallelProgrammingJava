package lab_6;

/**
 * Created by marian on 15.11.16.
 */
public class MyRow implements Runnable{

    private int start;
    private int end;
    private Obraz obraz;

    public MyRow(int start, int end, Obraz obraz) {

        super();
        this.obraz = obraz;
        this.start = start;
        this.end = end;

    }

    public void run() {
        obraz.calculate_histogram_row(start, end);
    }

}
