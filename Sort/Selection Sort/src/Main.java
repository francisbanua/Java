import java.util.Arrays;

public class Main {
    private static final int MAX = 10;

    public static void selectionSort(int[] v) {
        for (int i = 0; i < v.length; i++) {
            int min = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[min])
                    min = j;
            }
            int temp = v[i];
            v[i] = v[min];
            v[min] = temp;
        }
    }

    public static void main(String[] args) {
        int[] v = new int[MAX];
        for (int i = 0; i < v.length; i++) {
            v[i] = (int)(Math.random() * 100);
        }
        System.out.println(Arrays.toString(v));
        selectionSort(v);
        System.out.println(Arrays.toString(v));
    }
}