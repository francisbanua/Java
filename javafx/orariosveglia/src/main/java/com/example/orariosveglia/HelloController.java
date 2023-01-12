package com.example.orariosveglia;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.awt.*;

public class HelloController {
    @FXML
    public Label meridiemTime;
    public Label hTime;
    public Label _Time;
    public Label mTime;
    public Label al;
    public Label turn;
    public Label meridiemAlarm;
    public Label hAlarm;
    public Label _Alarm;
    public Label mAlarm;
    public Button btnPlay;
    private Timeline timeline;
    private SystemTray tray;
    private Time time;
    private Alarm alarm;
    private boolean isOff, flag, flag2, flag3;
    private int statoOrario, statoSveglia, snoozeValue, anim;
    private float opacity;

    @FXML
    private void off() {
        meridiemTime.setText("");
        hTime.setText("");
        _Time.setText("");
        mTime.setText("");
        al.setText("");
        turn.setText("");
        meridiemAlarm.setText("");
        hAlarm.setText("");
        _Alarm.setText("");
        mAlarm.setText("");
        isOff = true;
        statoOrario = 0;
        statoSveglia = 0;
    }

    private boolean equal(int n) {
        return time.getValoreTotale() == alarm.getValoreTotale() + n;
    }

    private void notifica() throws AWTException {
        //Parte che genera la notifica
        //Obtain only one instance of the SystemTray object
        tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "banua");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("banua");
        tray.add(trayIcon);

        String s = "Sono le " + time.getOra() + ":" + time.getMinuto();
        trayIcon.displayMessage("BEEP BEEP BEEP", s, TrayIcon.MessageType.INFO);
    }

    private void suonaSveglia() throws AWTException {
        System.out.println("ring");
        notifica();
        flag2 = true;
    }

    private void update() throws AWTException {
        meridiemTime.setText(time.getMeridiem());
        hTime.setText(time.getOra());
        mTime.setText(time.getMinuto());

        meridiemAlarm.setText(alarm.getMeridiem());
        hAlarm.setText(alarm.getOra());
        mAlarm.setText(alarm.getMinuto());

        switch (anim) {
            case 0:
                _animation();
                break;
            case 1:
                mTimeAnimation();
                break;
            case 2:
                hTimeAnimation();
                break;
            case 3:
                mAlarmAnimation();
                break;
            case 4:
                hAlarmAnimation();
        }

        if (alarm.isOn()) {
            if (equal(snoozeValue)) {
                if (flag)
                    suonaSveglia();
                flag = false;
            } else {
                flag = true;
                if (flag2)
                    snoozeValue = 0;
            }
        }
    }

    private void _animation() {
        if (flag3) {
            opacity += 0.10;
            if (opacity >= 1)
                flag3 = false;
        } else {
            opacity -= 0.10;
            if (opacity <= 0)
                flag3 = true;
        }
        _Time.setOpacity(opacity);
    }

    private void mTimeAnimation() {
        if (flag3) {
            opacity += 0.10;
            if (opacity >= 1)
                flag3 = false;
        } else {
            opacity -= 0.10;
            if (opacity <= 0)
                flag3 = true;
        }
        mTime.setOpacity(opacity);
    }

    private void hTimeAnimation() {
        if (flag3) {
            opacity += 0.10;
            if (opacity >= 1)
                flag3 = false;
        } else {
            opacity -= 0.10;
            if (opacity <= 0)
                flag3 = true;
        }
        hTime.setOpacity(opacity);
    }

    private void mAlarmAnimation() {
        if (flag3) {
            opacity += 0.10;
            if (opacity >= 1)
                flag3 = false;
        } else {
            opacity -= 0.10;
            if (opacity <= 0)
                flag3 = true;
        }
        mAlarm.setOpacity(opacity);
        _Time.setOpacity(opacity);
    }

    private void hAlarmAnimation() {
        if (flag3) {
            opacity += 0.10;
            if (opacity >= 1)
                flag3 = false;
        } else {
            opacity -= 0.10;
            if (opacity <= 0)
                flag3 = true;
        }
        hAlarm.setOpacity(opacity);
        _Time.setOpacity(opacity);
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
        off();
    }

    public void btnPlayOnClick(ActionEvent actionEvent) {
        if (isOff) {
            isOff = false;
            btnPlay.setText("⏹");
            time = new Time();
            alarm = new Alarm();
            timeline.play();
            _Time.setText(":");
            al.setText("AL");
            _Alarm.setText(":");
            flag3 = true;
            opacity = 0;
            anim = 0;
        } else {
            timeline.stop();
            off();
            btnPlay.setText("▶");
        }
    }

    public void btnTimeOnClick(ActionEvent actionEvent) {
        if (!isOff) {
            if (statoSveglia == 0) {
                switch (statoOrario) {
                    case 0:
                        statoOrario = 1;
                        timeline.stop();
                        _Time.setOpacity(1);
                        meridiemTime.setText("");
                        if (time.isFormat())
                            hTime.setText("12");
                        else
                            hTime.setText("24");
                        mTime.setText("X");
                        break;
                    case 1:
                        statoOrario = 2;
                        timeline.play();
                        anim = 1;
                        break;
                    case 2:
                        statoOrario = 3;
                        mTime.setOpacity(1);
                        anim = 2;
                        break;
                    case 3:
                        statoOrario = 0;
                        hTime.setOpacity(1);
                        anim = 0;
                }
            }
        }
    }

    public void btnAlarmOnClick(ActionEvent actionEvent) {
        if (!isOff) {
            if (statoOrario == 0) {
                switch (statoSveglia) {
                    case 0:
                        statoSveglia = 1;
                        anim = 3;
                        break;
                    case 1:
                        statoSveglia = 2;
                        mAlarm.setOpacity(1);
                        anim = 4;
                        break;
                    case 2:
                        statoSveglia = 0;
                        hAlarm.setOpacity(1);
                        anim = 0;
                }
            }
        }
    }

    public void btnTurnOnClick(ActionEvent actionEvent) {
        flag = true;
        flag2 = true;
        snoozeValue = 0;
        alarm.setOn();
        if (alarm.isOn())
            turn.setText("🔔");
        else
            turn.setText("");
    }

    public void btnIncreaseOnClick(ActionEvent actionEvent) {
        switch (statoOrario) {
            case 1:
                time.setFormat();
                alarm.setFormat();
                if (time.isFormat()) {
                    hTime.setText("12");
                } else {
                    hTime.setText("24");
                }
                break;
            case 2:
                time.incrementaMinuto();
                mTime.setOpacity(1);
                break;
            case 3:
                time.incrementaOra();
                hTime.setOpacity(1);
        }
        switch (statoSveglia) {
            case 1:
                alarm.incrementaMinuto();
                mAlarm.setOpacity(1);
                break;
            case 2:
                alarm.incrementaOra();
                hAlarm.setOpacity(1);
        }
    }

    public void btnSnoozeOnClick(ActionEvent actionEvent) {
        if (alarm.isOn()) {
            if (equal(snoozeValue)) {
                if (flag2)
                    snoozeValue += 5;
                flag2 = false;
            }
        }
    }
}