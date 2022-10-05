import java.util.Arrays;

public class Main {
    private static final int MAX = 10;

    public static void bubbleSort(int[] v) {
        boolean flag = true;
        int i = 0;
        while (flag) {
            flag = false;
            for (int j = v.length - 1; j > i; j--) {
                if (v[j] < v[j - 1]) {
                    int temp = v[j];
                    v[j] = v[j - 1];
                    v[j - 1] = temp;
                    flag = true;
                }
            }
            i++;
        }
    }

    public static void main(String[] args) {
        int[] v = new int[MAX];
        for (int i = 0; i < v.length; i++) {
            v[i] = (int)(Math.random() * 100);
        }
        System.out.println(Arrays.toString(v));
        bubbleSort(v);
        System.out.println(Arrays.toString(v));
    }
}