import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numeri = new ArrayList<>();
        int n_numeri = 5;
        for (int i = 0; i < n_numeri; i++) {
            int temp = (int)(Math.random()* 20 + 1);
            if (numeri.contains(temp)) {
                i--;
            } else {
                numeri.add(temp);
            }
        }
        System.out.println(numeri);
        Scanner in = new Scanner(System.in);
        int n_tentativi = 0;
        while (!numeri.isEmpty()) {
            System.out.println("Inserisci un numero: ");
            int temp = in.nextInt();
            if (numeri.remove(new Integer(temp))) {
                System.out.println("Hai indovinato" + "\n" + numeri);
            } else {
                System.out.println("Non hai indovinato");
            }
            n_tentativi++;
        }
        System.out.println("Numero tentativi: " + n_tentativi);
    }
}