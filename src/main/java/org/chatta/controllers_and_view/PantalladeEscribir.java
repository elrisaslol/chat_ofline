package org.chatta.controllers_and_view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.chatta.App;
import org.chatta.model.connection.XML_Message;
import org.chatta.model.connection.XML_User;
import org.chatta.model.entity.Message;
import org.chatta.model.entity.Sesion;
import org.chatta.model.entity.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.LocalTimeAdapter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDateTime;

public class PantalladeEscribir {

    @FXML
    private VBox mensajeContainer;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea nombreusuario; // Asegúrate de que este campo coincida con el fx:id en el FXML

    @FXML
    public void initialize() {
        System.out.println(Sesion.getSesion().getUser().getNickName());
        // Inicializaciones adicionales si son necesarias
        // Por ejemplo, puedes limpiar el TextArea de nombre de usuario al inicio
        nombreusuario.setText("Usuario desconocido");
    }

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
        String textoMensaje = inputField.getText().trim();
        System.out.println("Texto del mensaje: " + textoMensaje);
        if (!textoMensaje.isEmpty()) {
            // Crear un contenedor HBox para alinear el mensaje
            HBox contenedorMensaje = new HBox();
            Label mensajeLabel = new Label(textoMensaje);
            mensajeLabel.setWrapText(true);  // Permitir que el texto se envuelva

            // Estilo para el mensaje del emisor
            mensajeLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-background-radius: 15; -fx-margin: 5;");
            contenedorMensaje.setAlignment(Pos.TOP_RIGHT);  // Alinear a la derecha para el emisor

            // Agregar la etiqueta al contenedor HBox y luego al VBox
            contenedorMensaje.getChildren().add(mensajeLabel);
            mensajeContainer.getChildren().add(contenedorMensaje); // Agrega el mensaje al contenedor

            User user = XML_User.readNickName(obtenerNombreDesdeFuncion());

            Message message = new Message(Sesion.getSesion().getUser(),
                    user,
                    textoMensaje);

            XML_Message.writeXML(message);

            inputField.clear();  // Limpia el campo de entrada
        }
    }

    public void cargarMensajesFiltrados() {
        String nombreFuncion = obtenerNombreDesdeFuncion();
        String nombreSingleton = Sesion.getSesion().getUser().getNickName();

        try {
            File archivoXML = new File("XML_Messages.xml");  // Cambia a la ruta correcta
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            NodeList listaMensajes = doc.getElementsByTagName("Message");

            for (int i = 0; i < listaMensajes.getLength(); i++) {
                Node nodoMensaje = listaMensajes.item(i);

                if (nodoMensaje.getNodeType() == Node.ELEMENT_NODE) {
                    Element mensaje = (Element) nodoMensaje;

                    String mensajeTexto = mensaje.getElementsByTagName("infoMessage").item(0).getTextContent();

                    // Extrae los nombres de receiver y transmitter
                    String receptor = ((Element) mensaje.getElementsByTagName("receiver").item(0))
                            .getElementsByTagName("nickName").item(0).getTextContent();
                    String transmisor = ((Element) mensaje.getElementsByTagName("transmitter").item(0))
                            .getElementsByTagName("nickName").item(0).getTextContent();

                    // Verifica si el receptor y transmisor coincide
                    if (receptor.equals(nombreFuncion) && transmisor.equals(nombreSingleton) ||
                            transmisor.equals(nombreFuncion) && receptor.equals(nombreSingleton)) {

                        // Crear un contenedor HBox para alinear los mensajes
                        HBox contenedorMensaje = new HBox();
                        Label mensajeLabel = new Label(mensajeTexto);
                        mensajeLabel.setWrapText(true);  // Permitir que el texto se envuelva

                        // Estilo y alineación según quien es el receptor o el emisor
                        if (receptor.equals(nombreSingleton) && transmisor.equals(nombreFuncion)) {
                            // Mensaje del receptor (alineación a la izquierda)
                            mensajeLabel.setStyle("-fx-background-color: lightgreen; -fx-padding: 10; -fx-background-radius: 15; -fx-margin: 5;");
                            contenedorMensaje.setAlignment(Pos.TOP_LEFT);
                        } else {
                            // Mensaje del emisor (alineación a la derecha)
                            mensajeLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-background-radius: 15; -fx-margin: 5;");
                            contenedorMensaje.setAlignment(Pos.TOP_RIGHT);
                        }

                        // Agregar la etiqueta al contenedor HBox y luego al VBox
                        contenedorMensaje.getChildren().add(mensajeLabel);
                        mensajeContainer.getChildren().add(contenedorMensaje);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String obtenerNombreDesdeFuncion() {
        // Implementa este método para devolver el nombre almacenado en tu función.
        return nombreusuario.getText();
    }
}
