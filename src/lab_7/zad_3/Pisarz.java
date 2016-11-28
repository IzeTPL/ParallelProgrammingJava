package lab_7.zad_3;

import java.util.Random;

/**
 * Created by Marian on 22.11.2016.
 */
public class Pisarz extends Klient{

    public Pisarz(Czytelnia czytelnia) {
        super(czytelnia);
    }

    @Override
    public void run() {

        for (;;) {

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {}

            while(czytelnia.bariera() == 0) {}

            System.out.println("pisarz " + Thread.currentThread().getName() + " przed zamkiem\n");

            czytelnia.my_write_lock_lock();

            // korzystanie z zasobow czytelni
            System.out.println("pisarz " + Thread.currentThread().getName() + " wchodze\n");

            czytelnia.pisze();

            System.out.println("pisarz " + Thread.currentThread().getName() + " wychodze\n");

            czytelnia.my_write_lock_unlock();

            System.out.println("pisarz " + Thread.currentThread().getName() + " po zamku\n");

        }

    }
}
