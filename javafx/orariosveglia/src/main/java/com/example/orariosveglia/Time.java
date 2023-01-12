package com.example.orariosveglia;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Time {
    private int ora, minuto;
    private String meridiem;
    private boolean format;

    public Time() {
        ora = 12;
        minuto = 0;
        meridiem = "AM";
        format = true;
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(60),
                e -> incrementa()
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void incrementa() {
        minuto++;
        if (minuto >= 60) {
            minuto = 0;
            incrementaOra();
        }
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
