import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Lorem.txt");
        Scanner lettore = new Scanner(file);
        ArrayList<String> parole = new ArrayList<>();
        while (lettore.hasNextLine()) {
            String s = lettore.nextLine();
            String[] p = s.split("\\s+");
            parole.addAll(Arrays.asList(p));
        }

        HashMap<String, HashSet<String>> markov = new HashMap<>();
        for (int i = 0; i < parole.size() - 1; i++) {
            if (!markov.containsKey(parole.get(i))) {
                HashSet<String> set = new HashSet<>();
                set.add(parole.get(i + 1));
                markov.put(parole.get(i), set);
            } else {
                HashSet<String> set = markov.get(parole.get(i));
                set.add(parole.get(i + 1));
            }
        }

        String prefisso = "il";
        System.out.print(prefisso + " ");
        for (int i = 0; i < 100; i++) {
            HashSet<String> set = markov.get(prefisso);
            int ran = (int)(Math.random() * set.size());
            String suffisso = "";
            int j = 0;
            for (String s: set) {
                if (j == ran) {
                    suffisso = s;
                    break;
                }
                j++;
            }
            System.out.print(suffisso + " ");
            prefisso = suffisso;
        }
    }
}