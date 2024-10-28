package org.chatta.test;

import org.chatta.controllers_and_view.PantalladeElegir;
import org.chatta.controllers_and_view.XML;
import org.chatta.model.connection.XML_Message;
import org.chatta.model.entity.Message;
import org.chatta.model.entity.Sesion;
import org.chatta.model.entity.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class encontrarMensajeMasCerca {
    public static void main(String[] args) {
        User user1 = new User("Maria", "pass2");
        User user = new User("Juan", "pass1");
        Sesion.getSesion().setUser(user1);
        List<Message> messages = XML_Message.readMessagesFromFile(new File((XML.MESSAGE_XML.getURL())));
        List<Message> messagesTMP = new ArrayList<>();

        for (Message message : messages) {
            if ((message.getTransmitter().equals(user) && message.getReceiver().equals(Sesion.getSesion().getUser())) ||
                    (message.getTransmitter().equals(Sesion.getSesion().getUser()) && message.getReceiver().equals(user))) {
                messagesTMP.add(message);
                System.out.println(message);
            }
        }

        System.out.println("----------------------------------");

        for (Message message : messagesTMP){
            System.out.println(message);
        }

        System.out.println("----------------------------------");

    }
}
