package org.chatta.controllers_and_view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import org.chatta.App;
import org.chatta.model.entity.Sesion;

public class PantalladeEscribir {

    @FXML
    private TextArea mensajeArea1;

    @FXML
    private TextArea mensajeArea2;

    @FXML
    private TextArea mensajeArea3;

    @FXML
    private TextArea mensajeArea4;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea nombreusuario; // Asegúrate de que este campo coincida con el fx:id en el FXML

    @FXML
    public void initialize() {
            //mostrar singleton
            System.out.println(Sesion.getSesion().getUser());


        // Inicializaciones adicionales si son necesarias
        // Por ejemplo, puedes limpiar el TextArea de nombre de usuario al inicio
        nombreusuario.setText("Usuario desconocido");
    }

    // Cambié el modificador a `public` para que sea accesible desde el otro controlador
    public void recibirNombre(String nombre) {
        if (nombre != null) {
            nombreusuario.setText(nombre); // Establece el nombre en el TextArea
        } else {
            nombreusuario.setText("Usuario desconocido");
        }
    }

    @FXML
    private void SwitchToPantalladeElegir() throws IOException {
        App.setRoot(scenes.PANTALLADEELEGIR);
    }

    @FXML
    private void enviarMensaje() {
        String mensaje = inputField.getText();
        if (!mensaje.isEmpty()) {
            mensajeArea1.appendText(mensaje + "\n"); // Agregar el mensaje al TextArea
            inputField.clear(); // Limpiar el campo de entrada
        }
    }
}

