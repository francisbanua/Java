module com.example.svegliam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens fx.sveglia to javafx.fxml;
    exports fx.sveglia;
}