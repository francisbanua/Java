package fx.sveglia;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.awt.*;
public class Controller {
    public Button accendi;
    public Button attiva;
    public Label meridiemOrario;
    public Label oraOrario;
    public Label _Orario;
    public Label minutoOrario;
    public Label alText;
    public Label bellText;
    public Label meridiemSveglia;
    public Label oraSveglia;
    public Label _Sveglia;
    public Label minutoSveglia;

    private Timeline timeline;
    private SystemTray tray;
    private OrarioDigitale orarioDigitale;
    private int statoOrario; //0 null   1 formato   2 minuto   3 ora
    private int statoSveglia; //0 null   1 minuto   2 ora
    private int statoAnimazione; //0 _Orario   1 minutoOrario   2 oraOrario   3 minutoSveglia   4 oraSveglia
    private double opacity;
    private boolean incrementaOpacity, isAcceso, isAttivo, isSuonata, isSnooze;

    private void accendi() {
        accendi.setText("⏹");
        oraOrario.setOpacity(1);
        _Orario.setText(":");
        minutoOrario.setOpacity(1);
        alText.setText("AL");
        oraSveglia.setOpacity(1);
        _Sveglia.setText(":");
        minutoSveglia.setOpacity(1);

        timeline.play();
        orarioDigitale = new OrarioDigitale();
        orarioDigitale.play();
        isAcceso = true;
    }

    private void spegni() {
        accendi.setText("▶");
        attiva.setText("🔔");
        meridiemOrario.setText("");
        oraOrario.setText("");
        _Orario.setText("");
        minutoOrario.setText("");
        alText.setText("");
        bellText.setText("");
        meridiemSveglia.setText("");
        oraSveglia.setText("");
        _Sveglia.setText("");
        minutoSveglia.setText("");

        timeline.stop();
        statoOrario = 0;
        statoSveglia = 0;
        statoAnimazione = 0;
        opacity = 0;
        incrementaOpacity = true;
        isAcceso = false;
        isAttivo = false;
    }

    private void cambioOpacity() {
        if (opacity >= 1)
            incrementaOpacity = false;
        if (opacity <= 0)
            incrementaOpacity = true;
        if (incrementaOpacity)
            opacity += 0.10;
        else
            opacity -= 0.10;
    }

    private void notifica() throws AWTException {
        //Parte che genera la notifica
        //Obtain only one instance of the SystemTray object
        tray = SystemTray.getSystemTray();

        //If the icon is a file
        //Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Sveglia");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Sevglia");
        tray.add(trayIcon);

        String s = "Sono le " + orarioDigitale.getOrarioOra() + ":" + orarioDigitale.getOrarioMinuto();
        trayIcon.displayMessage("BEEP BEEP BEEP", s, TrayIcon.MessageType.INFO);
    }

    private void update() throws AWTException {
        meridiemOrario.setText(orarioDigitale.getOrarioMeridiem());
        oraOrario.setText(orarioDigitale.getOrarioOra());
        minutoOrario.setText(orarioDigitale.getOrarioMinuto());

        meridiemSveglia.setText(orarioDigitale.getSvegliaMeridiem());
        oraSveglia.setText(orarioDigitale.getSvegliaOra());
        minutoSveglia.setText(orarioDigitale.getSvegliaMinuto());

        cambioOpacity();
        switch (statoAnimazione) {
            case 0:
                oraOrario.setOpacity(1);
                oraSveglia.setOpacity(1);
                _Orario.setOpacity(opacity);
                break;
            case 1:
                _Orario.setOpacity(1);
                minutoOrario.setOpacity(opacity);
                break;
            case 2:
                minutoOrario.setOpacity(1);
                oraOrario.setOpacity(opacity);
                break;
            case 3:
                _Orario.setOpacity(1);
                minutoSveglia.setOpacity(opacity);
                break;
            case 4:
                minutoSveglia.setOpacity(1);
                oraSveglia.setOpacity(opacity);
        }

        if (isAttivo) {
            if (orarioDigitale.equal()) {
                if (!isSuonata) {
                    isSuonata = true;
                    isSnooze = false;
                    notifica();
                    System.out.println("E' suonata la sveglia");
                }
            } else {
                isSuonata = false;
                if (!isSnooze) {
                    orarioDigitale.azzeraSnoozeValue();
                }
            }
        }
    }

    public void initialize() {
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(0.1),
                e -> {
                    try {
                        update();
                    } catch (AWTException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        spegni();
    }

    public void onShutDown(){
        //Prima di uscire rimuove le notifiche eventualmente rimaste
        TrayIcon[] icons = tray.getTrayIcons();
        for (TrayIcon t: icons) {
            tray.remove(t);
        }
    }

    public void btnAccendiOnClick(ActionEvent actionEvent) {
        if (isAcceso) {
            spegni();
            System.out.println("Spegnimento");
        } else {
            accendi();
            System.out.println("Accensione");
        }
    }

    public void btnOrarioOnClick(ActionEvent actionEvent) {
        if (isAcceso) {
            if (statoSveglia == 0) {
                switch (statoOrario) {
                    case 0:
                        statoOrario = 1;
                        timeline.stop();
                        orarioDigitale.stop();
                        meridiemOrario.setText("");
                        if (orarioDigitale.getFormato())
                            oraOrario.setText("12");
                        else
                            oraOrario.setText("24");
                        minutoOrario.setText("X");
                        System.out.println("Stai cambiando il formato dell'ora");
                        break;
                    case 1:
                        statoOrario = 2;
                        timeline.play();
                        orarioDigitale.play();
                        statoAnimazione = 1;
                        System.out.println("Stai cambiando il minuto dell'orario");
                        break;
                    case 2:
                        statoOrario = 3;
                        statoAnimazione = 2;
                        System.out.println("Stai cambiando l'ora dell'orario");
                        break;
                    case 3:
                        statoOrario = 0;
                        statoAnimazione = 0;
                        System.out.println("Hai finito di cambiare l'orario");
                }
            }
        }
    }

    public void btnSvegliaOnClick(ActionEvent actionEvent) {
        if (isAcceso) {
            if (statoOrario == 0) {
                switch (statoSveglia) {
                    case 0:
                        statoSveglia = 1;
                        statoAnimazione = 3;
                        System.out.println("Stai cambiando il minuto della sveglia");
                        break;
                    case 1:
                        statoSveglia = 2;
                        statoAnimazione = 4;
                        System.out.println("Stai cambiando l'ora della sveglia");
                        break;
                    case 2:
                        statoSveglia = 0;
                        statoAnimazione = 0;
                        System.out.println("Hai finito di cambiare la sveglia");
                }
            }
        }
    }

    public void btnAttivaOnClick(ActionEvent actionEvent) {
        if (isAcceso) {
            if (isAttivo) {
                attiva.setText("🔔");
                bellText.setText("");
                System.out.println("Hai disattivato la sveglia");
            } else {
                isSuonata = false;
                isSnooze = false;
                orarioDigitale.azzeraSnoozeValue();
                attiva.setText("🔕");
                bellText.setText("🔔");
                System.out.println("Hai attivato la sveglia");
            }
            isAttivo = !isAttivo;
        }
    }

    public void btnIncrementaOnClick(ActionEvent actionEvent) {
        if (isAcceso) {
            switch (statoOrario) {
                case 1:
                    if (orarioDigitale.getFormato()) {
                        orarioDigitale.setFormato();
                        oraOrario.setText("24");
                    } else {
                        orarioDigitale.setFormato();
                        oraOrario.setText("12");
                    }
                    break;
                case 2:
                    orarioDigitale.incrementaOrarioMinuto();
                    opacity = 1;
                    break;
                case 3:
                    orarioDigitale.incrementaOrarioOra();
                    opacity = 1;
            }
            switch (statoSveglia) {
                case 1:
                    orarioDigitale.incrementaSvegliaMinuto();
                    opacity = 1;
                    break;
                case 2:
                    orarioDigitale.incrementaSvegliaOra();
                    opacity = 1;
            }
        }
    }

    public void btnSnoozeOnClick(ActionEvent actionEvent) {
        if (isAcceso) {
            if (isSuonata) {
                if (!isSnooze) {
                    isSnooze = true;
                    orarioDigitale.incrementaSnoozeValue();
                    System.out.println("Hai rinviato la sveglia di " + orarioDigitale.getSnoozeValue() + " minuti");
                }
            }
        }
    }
}