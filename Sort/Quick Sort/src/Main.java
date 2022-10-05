import java.util.Arrays;

public class Main {
    private static final int MAX = 10;

    public static int partition(int[] v, int inizio, int fine) {
        int i = inizio, j = fine - 1;
        while (i <= j) {
            while (v[i] < v[fine]) {
                i++;
            }
            while (j > inizio && v[j] >= v[fine]) {
                j--;
            }
            if (i >= j)
                break;
            int temp = v[i];
            v[i] = v[j];
            v[j] = temp;
        }
        int temp = v[i];
        v[i] = v[fine];
        v[fine] = temp;
        return i;
    }

    public static void quickSort(int[] v, int inizio, int fine) {
        if (inizio >= fine)
            return;
        int i = partition(v, inizio, fine);
        quickSort(v, inizio, i -1);
        quickSort(v, i + 1, fine);
    }
    public static void main(String[] args) {
        int[] v = new int[MAX];
        for (int i = 0; i < v.length; i++) {
            v[i] = (int)(Math.random() * 100);
        }
        System.out.println(Arrays.toString(v));
        quickSort(v, 0, v.length - 1);
        System.out.println(Arrays.toString(v));
    }
}