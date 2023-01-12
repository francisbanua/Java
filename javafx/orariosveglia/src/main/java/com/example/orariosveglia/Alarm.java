package com.example.orariosveglia;

public class Alarm {
    private int ora, minuto;
    private String meridiem;
    private boolean format, on;

    public Alarm() {
        ora = 12;
        minuto = 0;
        meridiem = "AM";
        format = true;
        on = false;
    }

    public void incrementaOra() {
        ora++;
        if (ora == 12) {
            if (meridiem == "AM")
                meridiem = "PM";
            else
                meridiem = "AM";
        }
        if (ora >= 13)
            ora = 1;
    }

    public void incrementaMinuto() {
        minuto = (minuto + 1) % 60;
    }

    public String getMeridiem() {
        if (format)
            return meridiem;
        return "";
    }

    public String getOra() {
        if (format)
            return "" + ora;
        if (meridiem == "AM") {
            if (ora == 12)
                return "0";
            return "" + ora;
        }
        if (ora == 12)
            return "12";
        return "" + (ora + 12);
    }

    public String getMinuto() {
        if (minuto < 10)
            return "0" + minuto;
        return "" + minuto;
    }

    public boolean isFormat() {
        return format;
    }

    public void setFormat() {
        format = !format;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn() {
        on = !on;
    }

    public int getValoreTotale() {
        int tot = 0;
        if (meridiem == "AM") {
            if (ora == 12)
                tot += 0;
            else
                tot += ora;
        } else {
            if (ora == 12)
                tot += 12;
            else
                tot += 12 + ora;
        }
        tot *= 60;
        tot += minuto;
        return tot;
    }
}
