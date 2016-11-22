package lab_7.zad_1;

public class TestProdKons {
    public static void main(String[] args) {
        Pojemnik pojemnik = new Pojemnik();
        (new Thread(new Producent(pojemnik))).start();
        (new Thread(new Konsument(pojemnik))).start();
    }
}
