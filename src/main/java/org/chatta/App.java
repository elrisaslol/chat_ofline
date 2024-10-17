package org.chatta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(scenes.PANTALLAINICIO), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(scenes url) throws IOException {
        scene.setRoot(loadFXML(url));
    }

    private static Parent loadFXML(scenes url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(url.getURL()));
        return fxmlLoader.load();
    }

    public static void begin() {
        launch();
    }

}