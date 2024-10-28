package org.chatta.model.connection;

import org.chatta.controllers_and_view.XML;
import org.chatta.model.entity.Message;
import org.chatta.model.entity.MessageList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XML_Message {

    public static boolean writeXML(Message message) {
        boolean result = false;
        try {
            File file = new File(XML.MESSAGE_XML.getURL());
            List<Message> messages = new ArrayList<>();

            // Si el archivo ya existe, leer los mensajes existentes
            if (file.exists()) {
                messages = readMessagesFromFile(file);
            }

            // Agregar el nuevo mensaje a la lista
            messages.add(message);

            // Crear un nuevo archivo XML con todos los mensajes
            JAXBContext context = JAXBContext.newInstance(MessageList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // Crear un contenedor para todos los mensajes
            MessageList list = new MessageList();
            list.setMessages(messages);

            // Escribir todos los mensajes en el archivo
            marshaller.marshal(list, file);
            result = true;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Message> readMessagesFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(MessageList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MessageList list = (MessageList) unmarshaller.unmarshal(file);
            return list.getMessages();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Message readXML(String filename) {
        Message message = null;
        try {
            JAXBContext context = JAXBContext.newInstance(MessageList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MessageList list = (MessageList) unmarshaller.unmarshal(new File(filename));
            List<Message> messages = list.getMessages();
            if (!messages.isEmpty()) {
                message = messages.get(messages.size() - 1); // Lee el último mensaje como ejemplo
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return message;
    }
}
