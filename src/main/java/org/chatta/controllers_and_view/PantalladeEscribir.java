package org.chatta.controllers_and_view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

import javafx.scene.layout.VBox;
import org.chatta.App;
import org.chatta.model.entity.Sesion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
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
        if (!textoMensaje.isEmpty()) {
            Label mensaje = new Label(textoMensaje);
            mensaje.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-background-radius: 15; -fx-margin: 5;");
            mensajeContainer.getChildren().add(mensaje); // Agrega el mensaje al contenedor
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

                    // Verifica si el receptor o transmisor coincide con uno de los dos nombres
                    if (receptor.equals(nombreFuncion) || receptor.equals(nombreSingleton) ||
                            transmisor.equals(nombreFuncion) || transmisor.equals(nombreSingleton)) {

                        Label mensajeLabel = new Label(mensajeTexto);
                        mensajeLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-background-radius: 15;");
                        mensajeContainer.getChildren().add(mensajeLabel);
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

