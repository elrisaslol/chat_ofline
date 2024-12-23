package org.chatta.controllers_and_view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import com.gluonhq.charm.glisten.control.TextField;
import org.chatta.App;
import org.chatta.model.entity.Sesion;
import org.chatta.model.entity.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;


public class PantalladeInico {

    public void initialize() {
        //mostrar singleton
            try {
                System.out.println(Sesion.getSesion().getUser().getNickName());
            }catch (Exception e){
                System.out.println("null");
            }
        }

    @FXML
    private TextField nombreUsuario;

    @FXML
    private PasswordField contraseñaUsuario;


    @FXML
    private void SwitchToPantalladeInscripcion() throws IOException {
        App.setRoot(scenes.PANTALLADEINSCRIPCION);
    }
    @FXML
    private void SwitchToPantalladeElegir() throws IOException {
        App.setRoot(scenes.PANTALLADEELEGIR);
    }

    @FXML
    private void InicioSesión() throws IOException{
        if (validarCredenciales(nombreUsuario,contraseñaUsuario)){
            Sesion.getSesion().setUser(new User(nombreUsuario.getText(), contraseñaUsuario.getText()));
            SwitchToPantalladeElegir();
        }else{
            System.out.println("jaja easter egg");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error de autenticación");
            alert.setHeaderText("Credenciales inválidas");
            alert.setContentText("Por favor, verifica tu nombre de usuario y contraseña.");

            // Mostrar el popup y esperar a que el usuario lo cierre
            alert.showAndWait();
            //SwitchToPantalladeElegir();
        }

    }



    public boolean validarCredenciales(TextField nombreUsuario, PasswordField contraseñaUsuario) {
        String usuarioIngresado = nombreUsuario.getText();
        String contraseñaIngresada = contraseñaUsuario.getText();

        try {
            // Cargar el archivo XML
            File archivoXML = new File(XML.USER_XML.getURL());  // Cambia a la ruta de tu archivo
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            // Obtener la lista de usuarios en el archivo
            NodeList listaUsuarios = doc.getElementsByTagName("User");

            // Recorrer los usuarios en el archivo
            for (int i = 0; i < listaUsuarios.getLength(); i++) {
                Element usuario = (Element) listaUsuarios.item(i);

                String nombre = usuario.getElementsByTagName("nickName").item(0).getTextContent();
                String contraseña = usuario.getElementsByTagName("password").item(0).getTextContent();

                // Comparar credenciales
                if (usuarioIngresado.equals(nombre) && contraseñaIngresada.equals(contraseña)) {
                    return true;  // Credenciales válidas
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;  // Credenciales no válidas
    }
}
