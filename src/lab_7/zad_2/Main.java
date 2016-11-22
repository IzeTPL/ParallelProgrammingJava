package lab_7.zad_2;

/**
 * Created by Marian on 22.11.2016.
 */
public class Main {

    public static void main(String[] args) {

        Czytelnia czytelnia = new Czytelnia();
        Thread[] pisarze = new Thread[5];
        Thread[] czytelnicy = new Thread[5];

        for (int i = 0; i < 5; i++) {

            pisarze[i] = new Thread(new Pisarz(czytelnia));
            pisarze[i].setName(Integer.toString(i));
            pisarze[i].start();

        }

        for (int i = 0; i < 5; i++) {

            czytelnicy[i] = new Thread(new Czytelnik(czytelnia));
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
