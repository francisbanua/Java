package fx.sveglia;

public class Orario {
    private int ora;
    private int minuto;

    public Orario() {
        ora = 0;
        minuto = 0;
    }

    private String getString(int n) {
        if (n < 10)
            return "0" + n;
        return "" + n;
    }

    public int getOra12() {
        if (ora == 0)
            return 12;
        if (ora < 12)
            return ora;
        return ora - 12;
    }

    public int getMinuto() {
        return minuto;
    }

    public String getOra24String() {
        return "" + ora;
    }

    public String getOra12String() {
        int temp = getOra12();
        return "" + temp;
    }

    public String getMinutoString() {
        return getString(minuto);
    }

    public String getMeridiem() {
        if (ora < 12)
            return "AM";
        return "PM";
    }

    public int minutiTotali() {
        return ora * 60 + minuto;
    }

    public void incrementaOra() {
        ora = (ora + 1) % 24;
    }

    public void incrementaMinuto() {
        minuto = (minuto + 1) % 60;
    }
}
