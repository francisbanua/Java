package fx.sveglia;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class OrarioDigitale {
    private Orario orario, sveglia;
    private boolean formato; //true = 12     false = 24
    private int snoozeValue;
    private Timeline timeline;

    public OrarioDigitale() {
        orario = new Orario();
        sveglia = new Orario();
        formato = true;
        snoozeValue = 0;
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                e -> incrementaOrario()
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void incrementaOrario() {
        if (orario.getMinuto() == 59)
            orario.incrementaOra();
        orario.incrementaMinuto();
    }

    public void play() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public boolean getFormato() {
        return formato;
    }

    public void setFormato() {
        formato = !formato;
    }

    public void incrementaOrarioOra() {
        orario.incrementaOra();
    }

    public void incrementaOrarioMinuto() {
        orario.incrementaMinuto();
    }

    public void incrementaSvegliaOra() {
        sveglia.incrementaOra();
    }

    public void incrementaSvegliaMinuto() {
        sveglia.incrementaMinuto();
    }

    public void incrementaSnoozeValue() {
        snoozeValue += 5;
    }

    public void azzeraSnoozeValue() {
        snoozeValue = 0;
    }

    public int getSnoozeValue() {
        return snoozeValue;
    }

    public String getOrarioOra() {
        if (formato)
            return orario.getOra12String();
        return orario.getOra24String();
    }

    public String getOrarioMinuto() {
        return orario.getMinutoString();
    }

    public String getSvegliaOra() {
        if (formato)
            return sveglia.getOra12String();
        return sveglia.getOra24String();
    }

    public String getSvegliaMinuto() {
        return sveglia.getMinutoString();
    }

    public String getOrarioMeridiem() {
        if (formato)
            return orario.getMeridiem();
        return "";
    }

    public String getSvegliaMeridiem() {
        if (formato)
            return sveglia.getMeridiem();
        return "";
    }

    public boolean equal() {
        return (orario.minutiTotali() == ((sveglia.minutiTotali() + snoozeValue) % 1440));
    }
}
