package org.chatta.test;

import org.chatta.controllers_and_view.XML;
import org.chatta.model.connection.XML_User;
import org.chatta.model.entity.User;

public class EscribirUsuario {
    public static void main(String[] args) {
        User user = new User("salva", "12334");
        boolean success = XML_User.writeXML(user);
        if (success) {
            System.out.println("Mensaje escrito correctamente.");
        } else {
            System.out.println("Error al escribir el mensaje.");
        }

        // Leer los mensajes del archivo XML
        User readUser = XML_User.readXML(XML.USER_XML.getURL());
        if (readUser != null) {
            System.out.println("Último mensaje leído: " + readUser);
        } else {
            System.out.println("No se encontraron mensajes.");
        }
    }

}
