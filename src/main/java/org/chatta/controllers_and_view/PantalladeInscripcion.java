package org.chatta.controllers_and_view;

import java.io.File;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.chatta.App;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class PantalladeInscripcion {
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
                File archivoXML = new File("ruta/del/archivo/usuarios.xml");  // Cambia a la ruta de tu archivo
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(archivoXML);
                doc.getDocumentElement().normalize();

                // Obtener la lista de usuarios en el archivo
                NodeList listaUsuarios = doc.getElementsByTagName("usuario");

                // Recorrer los usuarios en el archivo
                for (int i = 0; i < listaUsuarios.getLength(); i++) {
                    Element usuario = (Element) listaUsuarios.item(i);

                    String nombre = usuario.getElementsByTagName("nombre").item(0).getTextContent();


                    // Comparar credenciales
                    if (usuarioIngresado.equals(nombre)) {
                        return true;  // Nombre repetido
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @FXML
        private void validarUsuario () throws IOException {
            if (validarNombre(nombreUsuario)) {
                System.out.println("Las nombres ya registrado.");
                System.out.println("jaja easter egg");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de nomenclatura");
                alert.setHeaderText("el nombre de usuario ya se encuentra ocupado");
                alert.setContentText("Por favor, utilize otro nombre.");

                // Mostrar el popup y esperar a que el usuario lo cierre
                alert.showAndWait();
            } else if (!sonContraseñasIguales()){
                System.out.println("Las contraseñas no son iguales.");
                System.out.println("jaja easter egg");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de confirmación");
                alert.setHeaderText("Confirmación de contraseña erronea");
                alert.setContentText("Por favor, verifica tu contraseña.");

                // Mostrar el popup y esperar a que el usuario lo cierre
                alert.showAndWait();
            }else{
                SwitchToPantalladeElegir();
            }
        }
}