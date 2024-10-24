package org.chatta.test;

import org.chatta.model.connection.XML_Message;
import org.chatta.model.entity.Message;
import org.chatta.model.entity.MessageList;
import org.chatta.model.entity.User;

public class EscribirMensage {
    public static void main(String[] args) {
        MessageList messageList = new MessageList();
        Message message = new Message(new User("Jua2n", "pass1"), new User("Maria", "pass2"), "Hola Maria");

        // Escribir un mensaje en el archivo XML
        boolean success = XML_Message.writeXML(message, "XML_Messages.xml");
        if (success) {
            System.out.println("Mensaje escrito correctamente.");
        } else {
            System.out.println("Error al escribir el mensaje.");
        }

        // Leer los mensajes del archivo XML
        Message readMessage = XML_Message.readXML("XML_Messages.xml");
        if (readMessage != null) {
            System.out.println("Último mensaje leído: " + readMessage);
        } else {
            System.out.println("No se encontraron mensajes.");
        }
    }

}
