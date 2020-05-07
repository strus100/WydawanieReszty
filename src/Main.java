import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static int[] monety = {1, 2, 11};
    public static int[] monetyTemp = {1, 2, 11};
    public static int[] nominaly = {500, 200, 100};
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        LinkedList max = new LinkedList<>();
        int max_size = 2;
        int sym_size = 0;
        for (int i = 0; i < monetyTemp.length; i++) {
            max_size += monetyTemp[i] * monetyTemp[i];
        }

        for (int i = 0; i < max_size; i++) {
            max.add(0);
        }





        while (true) {
            System.out.println("Podaj resztę do wydania.");
            int reszta = (int) s.nextDouble() * 100;
            for (int i = 0; i < nominaly.length; i++) {
                for (int j = monetyTemp[i]; j >= 0; j--) {
                    monetyTemp[i] = j;
                    LinkedList symulacja = zachlanny((reszta));
                    for (int k = 0; k < symulacja.size(); k++) {
                        sym_size = (int) symulacja.get(k);
                    }
                    if (sym_size < max_size && symulacja.size() != 0) {
                        max.clear();
                        max.addAll(symulacja);
                        max_size = sym_size;
                    }
                }
            }
            wydaj(max);
        }
    }

    public static LinkedList zachlanny(int reszta) {
        int temp = 0;
        LinkedList symulacja = new LinkedList<Integer>();
        for (int i = 0; ((i < nominaly.length) && (reszta > 0)); i++) {
            if (reszta >= nominaly[i]) {
                temp = (int) Math.floor(reszta / nominaly[i]);
                if (temp <= monetyTemp[i]) {
                    reszta = Math.round(100 * (reszta - (temp * nominaly[i]))) / 100;
                    symulacja.add(temp);
                } else {
                    if (monetyTemp[i] > 0) {
                        temp = monetyTemp[i];
                        reszta = Math.round(100 * (reszta - (temp * nominaly[i]))) / 100;
                        symulacja.add(temp);
                    } else {
                        symulacja.add(0);
                    }
                }
            } else {
                symulacja.add(0);
            }
        }
        if (reszta != 0) {
            symulacja.clear();
        }
        return symulacja;
    }

    public static void wydaj(LinkedList sym) {
        String wynik = "";
        for (int i = 0; i < sym.size(); i++) {
            wynik += (nominaly[i] / 100.0) + " PLN x " + sym.get(i) + " Ilość monet: " + monety[i] + " Ilość do usunięcia: " + sym.get(i) + "\n";
            monety[i] = monety[i] - (int) sym.get(i);
            monetyTemp[i] = monety[i];
        }
        System.out.println(wynik);
    }
}
