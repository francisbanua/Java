import java.util.Arrays;

public class Main {
    public static int removeZero(int[] v, int l) {
        for (int i = 0; i < l; i++) {
            if (v[i] == 0) {
                for (int j = i; j < l - 1; j++) {
                    v[j] = v[j + 1];
                }
                i--;
                l--;
            }
        }
        return l;
    }
    public static void sort(int[] v, int l) {
        for (int i = 0; i < l; i++) {
            int max = i;
            for (int j = i + 1; j < l; j++) {
                if (v[j] > v[max])
                    max = j;
            }
            int temp = v[i];
            v[i] = v[max];
            v[max] = temp;
        }
    }
    public static int removeFirst(int[] v, int l) {
        for (int i = 0; i < l - 1; i++) {
            v[i] = v[i + 1];
        }
        return l - 1;
    }
    public static void subtractOne(int[] v, int l) {
        for (int i = 0; i < l; i++) {
            v[i]--;
        }
    }
    public static boolean hh(int[] v) {
        int lunghezza = v.length;
        while(true) {
            lunghezza = removeZero(v, lunghezza);
            if (lunghezza <= 0)
                return true;
            sort(v, lunghezza);
            int N = v[0];
            lunghezza = removeFirst(v, lunghezza);
            if (N > lunghezza)
                return false;
            subtractOne(v, N);

        }
    }
    public static void main(String[] args) {
        int[] v = {16, 9, 9, 15, 9, 7, 9, 11, 17, 11, 4, 9, 12, 14, 14, 12, 17, 0, 3, 16};
        System.out.println(Arrays.toString(v));
        System.out.println(hh(v));
    }
}