package lab_6;

/**
 * Created by marian on 13.11.16.
 */
public class MyThread extends Thread{

    private int character;
    private Obraz obraz;

    public MyThread(int character, Obraz obraz) {

        super();
        this.obraz = obraz;
        this.character = character;

    }

    public void run() {
        obraz.calculate_histogram(character);
        obraz.print_histogram(character);
    }

}
