import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int contaCaratteri(ArrayList<String> lista) {
        int n = 0;
        for (String s: lista) {
            n += s.length();
        }
        return n;
    }
    public static int contaParoleMaiuscola(ArrayList<String> lista) {
        int n = 0;
        for (String s: lista) {
            if (Character.isUpperCase(s.charAt(0)))
                n++;
        }
        return n;
    }
    public static int contaSegni(ArrayList<String> lista) {
        int n = 0;
        for (String s: lista) {
            if (s.contains("."))
                n++;
            if (s.contains(","))
                n++;
            if (s.contains(";"))
                n++;
            if (s.contains(":"))
                n++;
            if (s.contains("!"))
                n++;
            if (s.contains("?"))
                n++;
        }
        return n;
    }
    public static void menu() {
        System.out.println("[1] numero caratteri     [2] numero parole" + "\n" +
                "[3] numero parole che iniziano con la maiuscola     [4] numero segni" + "\n" +
                "[0] esci" + "\n" +
                "Scegli un numero: ");
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Lorem.txt");
        Scanner lettore = new Scanner(file);
        ArrayList<String> parole = new ArrayList<>();
        while (lettore.hasNextLine()) {
            String s = lettore.nextLine();
            String[] p = s.split("\\s+");
            parole.addAll(Arrays.asList(p));
        }
        System.out.println(parole);
        Scanner in = new Scanner(System.in);
        int scelta = -1;
        while (scelta != 0) {
            menu();
            scelta = in.nextInt();
            switch (scelta) {
                case 1:
                    System.out.println("Numero caratteri: " + contaCaratteri(parole));
                    break;
                case 2:
                    System.out.println("Numero parole: " + parole.size());
                    break;
                case 3:
                    System.out.println("Numero parole che iniziano con la maiuscola: " + contaParoleMaiuscola(parole));
                    break;
                case 4:
                    System.out.println("Numero segni: " + contaSegni(parole));
            }
        }
    }
}