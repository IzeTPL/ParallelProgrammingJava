package lab_7.zad_2;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Marian on 22.11.2016.
 */
public class Czytelnia {

    private int liczba_czyt;
    private int liczba_pisz;
    private int liczba_czek_czyt;
    private int liczba_czek_pisz;
    final ReentrantLock lock = new ReentrantLock();
    final Condition pisarze = lock.newCondition();
    final Condition czytelnicy = lock.newCondition();
    private Random random;


    public Czytelnia() {
        liczba_pisz = 0;
        liczba_czyt = 0;
        liczba_czek_pisz = 0;
        liczba_czek_czyt = 0;
        random = new Random();

    }

    public void my_read_lock_lock() {
        lock.lock();
        if(liczba_pisz > 0 || lock.hasWaiters(pisarze)) {
            try {
                czytelnicy.await();
            } catch (InterruptedException e) {}
        }
        liczba_czyt++;
        czytelnicy.signalAll();
        lock.unlock();
    }


    public void my_read_lock_unlock() {
        lock.lock();
        liczba_czyt--;
        if(liczba_czyt == 0) {
            pisarze.signal();
            lock.unlock();
        } else {
            lock.unlock();
        }
    }


    public void my_write_lock_lock() {
        lock.lock();
        if(liczba_czyt + liczba_pisz > 0) {
            try {
                pisarze.await();
            } catch (InterruptedException e) {}
        }
        liczba_pisz++;
        lock.unlock();
    }


    public void my_write_lock_unlock() {
        lock.lock();
        liczba_pisz--;
        if(lock.hasWaiters(czytelnicy)) {
            czytelnicy.signalAll();
            lock.unlock();
        } else {
            pisarze.signal();
            lock.unlock();
        }
    }

    public void czytam() {
        System.out.println("Czytelnicy: " + liczba_czyt);
        System.out.println("Pisarze: " + liczba_pisz);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {}
    }

    public void pisze() {
        System.out.println("Czytelnicy: " + liczba_czyt);
        System.out.println("Pisarze: " + liczba_pisz);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {}
    }


}
