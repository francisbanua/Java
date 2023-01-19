package fx.sveglia;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 485, 233);
        stage.setMinWidth(485 + 20);
        stage.setMinHeight(233 + 40);
        controller = fxmlLoader.getController();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        System.out.println("Uscita");
        controller.onShutDown();
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }
}