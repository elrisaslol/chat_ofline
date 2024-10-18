package org.chatta.controllers_and_view;

import java.io.IOException;
import javafx.fxml.FXML;
import org.chatta.App;

public class PantalladeInico {

    @FXML
    private void SwitchToPantalladeInscripcion() throws IOException {
        App.setRoot(scenes.PANTALLADEINSCRIPCION);
    }
    @FXML
    private void SwitchToPantalladeEscribir() throws IOException {
        App.setRoot(scenes.PANTALLADEESCRIBIR);
    }
}
