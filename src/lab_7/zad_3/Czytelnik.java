package lab_7.zad_3;

import java.util.Random;

/**
 * Created by Marian on 22.11.2016.
 */
public class Czytelnik implements Runnable{

    private Czytelnia czytelnia;
    private Random random;

    public Czytelnik(Czytelnia czytelnia) {
        this.czytelnia = czytelnia;
        random = new Random();
    }

    @Override
    public void run() {

        for (;;) {

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {}

            System.out.println("czytelnik " + Thread.currentThread().getName() + " przed zamkiem\n");

            czytelnia.my_read_lock_lock();

            // korzystanie z zasobow czytelni
            System.out.println("czytelnik " + Thread.currentThread().getName() + " wchodze\n");

            czytelnia.czytam();

            System.out.println("czytelnik " + Thread.currentThread().getName() + " wychodze\n");

            czytelnia.my_read_lock_unlock();

            System.out.println("czytelnik " + Thread.currentThread().getName() + " po zamku\n");

        }

    }

}
