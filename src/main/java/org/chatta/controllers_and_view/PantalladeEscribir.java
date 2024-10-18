package org.chatta.controllers_and_view;

import javafx.fxml.FXML;
import org.chatta.App;

import java.io.IOException;

public class PantalladeEscribir {

    @FXML
    private void SwitchTopantalladeinicio() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
    }
}
