package org.chatta.controllers_and_view;

import java.io.IOException;
import javafx.fxml.FXML;
import org.chatta.App;


public class PantalladeInscripcion {

    @FXML
    private void switchToPantalladeInicio() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
    }
}