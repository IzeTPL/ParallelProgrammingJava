package lab_7.zad_3;

import java.util.Random;

/**
 * Created by Marian on 22.11.2016.
 */
public class Klient implements Runnable{

    protected Czytelnia czytelnia;
    protected Random random;

    public Klient(Czytelnia czytelnia) {
        this.czytelnia = czytelnia;
        random = new Random();
    }

    @Override
    public void run() {

    }

}
