package lab_7.zad_3;

/**
 * Created by Marian on 22.11.2016.
 */
public class Main {

    public static void main(String[] args) {

        Czytelnia czytelnia = new Czytelnia();
        Thread[] pisarze = new Thread[5];
        Thread[] czytelnicy = new Thread[5];

        for (int i = 0; i < 5; i++) {

            Klient klient = new Pisarz(czytelnia);
            pisarze[i] = new Thread(klient);
            pisarze[i].setName(Integer.toString(i));
            pisarze[i].start();

        }

        for (int i = 0; i < 5; i++) {

            Klient klient = new Czytelnik(czytelnia);
            czytelnicy[i] = new Thread(klient);
            czytelnicy[i].setName(Integer.toString(i));
            czytelnicy[i].start();

        }

        for (int i = 0; i < 5; i++) {

            try {
                pisarze[i].join();
            } catch (InterruptedException e) {}

        }

        for (int i = 0; i < 5; i++) {

            try {
                czytelnicy[i].join();
            } catch (InterruptedException e) {}

        }

    }

}
