package lab_7.zad_3;

import java.util.Random;

/**
 * Created by Marian on 22.11.2016.
 */
public class Czytelnia {

    private int liczba_czyt;
    private int liczba_pisz;
    private int liczba_czek_czyt;
    private int liczba_czek_pisz;
    private Random random;
    private boolean barieraP;
    private boolean barieraC;


    public Czytelnia() {
        liczba_pisz = 0;
        liczba_czyt = 0;
        liczba_czek_pisz = 0;
        liczba_czek_czyt = 0;
        random = new Random();
        barieraC = false;
        barieraP = false;

    }

    public synchronized void my_read_lock_lock() {
            if (liczba_pisz > 0 || liczba_czek_pisz != 0) {
                try {
                    liczba_czek_czyt++;
                    wait();
                    liczba_czek_czyt--;
                } catch (InterruptedException e) {
                }
            }
            liczba_czyt++;
    }


    public synchronized void my_read_lock_unlock() {
            liczba_czyt--;
        if(liczba_czyt == 0) {
            notifyAll();
        }
    }


    public synchronized void my_write_lock_lock() {
            if ((liczba_czyt + liczba_pisz) > 0) {
                try {
                    liczba_czek_pisz++;
                    wait();
                    liczba_czek_pisz--;
                } catch (InterruptedException e) {
                }
            }
            liczba_pisz++;
    }


    public synchronized void my_write_lock_unlock() {
            liczba_pisz--;
        if(liczba_czek_czyt != 0) {
            notifyAll();
        }
    }

    public int bariera() {
        if(liczba_czek_czyt > 0) return 0;
        else if(liczba_czek_pisz > 0) return 1;
        else return 3;
    }

    public synchronized void czytam() {
        System.out.println("Czytelnicy: " + liczba_czyt);
        System.out.println("Pisarze: " + liczba_pisz);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {}
    }

    public synchronized void pisze() {
        System.out.println("Czytelnicy: " + liczba_czyt);
        System.out.println("Pisarze: " + liczba_pisz);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {}
    }


}
