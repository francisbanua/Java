module com.example.orariosveglia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens com.example.orariosveglia to javafx.fxml;
    exports com.example.orariosveglia;
}