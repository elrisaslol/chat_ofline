package org.chatta.controllers_and_view;

import java.io.File;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.chatta.App;
import org.chatta.model.connection.XML_User;
import org.chatta.model.entity.Sesion;
import org.chatta.model.entity.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class PantalladeInscripcion {
    public void initialize() {
        //mostrar singleton
        System.out.println(Sesion.getSesion().getUser());
    }
    @FXML
    private PasswordField contraseñaUsuario;

    @FXML
    private PasswordField confirmarContraseña;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private void switchToPantalladeInicio() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
    }



    @FXML
    private void SwitchToPantalladeElegir() throws IOException {
        App.setRoot(scenes.PANTALLADEELEGIR);
    }

    // Método que compara las contraseñas
    public boolean sonContraseñasIguales() {
        String contraseña1 = contraseñaUsuario.getText();
        String contraseña2 = confirmarContraseña.getText();

        // Compara las contraseñas y retorna true si son iguales, false si no
        return contraseña1.equals(contraseña2);
    }



    public boolean validarNombre(TextField nombreUsuario) {
        String usuarioIngresado = nombreUsuario.getText();


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


                // Comparar credenciales
                if (usuarioIngresado.equalsIgnoreCase(nombre)) {
                    return true;  // Nombre repetido
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void validarUsuario() throws IOException {
        if (validarNombre(nombreUsuario)) {
            System.out.println("Las nombres ya registrado.");
            System.out.println("jaja easter egg");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error de nomenclatura");
            alert.setHeaderText("el nombre de usuario ya se encuentra ocupado");
            alert.setContentText("Por favor, utilize otro nombre.");

            // Mostrar el popup y esperar a que el usuario lo cierre
            alert.showAndWait();
        } else if (!sonContraseñasIguales()) {
            System.out.println("Las contraseñas no son iguales.");
            System.out.println("jaja easter egg");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error de confirmación");
            alert.setHeaderText("Confirmación de contraseña erronea");
            alert.setContentText("Por favor, verifica tu contraseña.");

            // Mostrar el popup y esperar a que el usuario lo cierre

            alert.showAndWait();
        } else if (nombreUsuario.getText() == null || nombreUsuario.getText().trim().isEmpty() || nombreUsuario.getText().trim().isBlank()) {
            System.out.println("No hay nombre.");
            System.out.println("jaja easter egg");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error de nomenclatura");
            alert.setHeaderText("el nombre de usuario esta vacio o no es valido");
            alert.setContentText("Por favor, utilize otro nombre.");
            alert.showAndWait();
        } else if (contraseñaUsuario.getText() == null || contraseñaUsuario.getText().trim().isEmpty() || contraseñaUsuario.getText().trim().isBlank()) {
            System.out.println("No hay nombre.");
            System.out.println("jaja easter egg");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error de nomenclatura");
            alert.setHeaderText("la contraseña esta vacio o no es valido");
            alert.setContentText("Por favor, utilize otra contraseña.");
            alert.showAndWait();
        } else {
            registrar();
            SwitchToPantalladeElegir();

        }

    }

    public void registrar(){
        User user;
        user = new User(nombreUsuario.getText(), contraseñaUsuario.getText());
        Sesion.getSesion().setUser(new User(nombreUsuario.getText(), contraseñaUsuario.getText()));
        boolean almacenado = XML_User.writeXML(user, XML.USER_XML.getURL());
        if (almacenado) {
            System.out.println(Sesion.getSesion().getUser().getNickName());
        }else {
            System.out.println("Error al almacenar");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al almacenar");
            alert.setHeaderText("Error al almacenar");
            alert.setContentText("Error al almacenar");
            alert.showAndWait();
        }
    }
}