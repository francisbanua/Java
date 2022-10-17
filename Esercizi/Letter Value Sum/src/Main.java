public class Main {
    public static int lettersum(String s) {
        int somma = 0;
        for (int i = 0; i < s.length(); i++) {
            somma += s.codePointAt(i) - 96;
        }
        return somma;
    }

    public static void main(String[] args) {
        System.out.println(lettersum("microspectrophotometries"));
    }
}