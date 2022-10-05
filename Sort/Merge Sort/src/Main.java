import java.util.Arrays;

public class Main {
    private static final int MAX = 5;

    public static void merge(int[] v, int[] a, int inizio, int m, int fine) {
        int i = inizio, j = m + 1, k = inizio;
        while (i <= m && j <= fine) {
            if (v[i] < v[j]) {
                a[k] = v[i];
                i++;
            } else {
                a[k] = v[j];
                j++;
            }
            k++;
        }
        while (i <= m) {
            a[k] = v[i];
            i++;
            k++;
        }
        while (j <= fine) {
            a[k] = v[j];
            j++;
            k++;
        }
        for (int l = inizio; l <= fine; l++) {
            v[l] = a[l];
        }
    }

    public static void mergeSort(int[] v, int[] a, int inizio, int fine) {
        if (inizio >= fine)
            return;
        int m = (inizio + fine) / 2;
        mergeSort(v, a, inizio, m);
        mergeSort(v, a, m + 1, fine);
        merge(v, a, inizio, m, fine);
    }

    public static void main(String[] args) {
        int[] v = new int[MAX];
        for (int i = 0; i < v.length; i++) {
            v[i] = (int)(Math.random() * 100);
        }
        System.out.println(Arrays.toString(v));
        int[] a = new int[v.length];
        mergeSort(v, a, 0, v.length - 1);
        System.out.println(Arrays.toString(v));
    }
}