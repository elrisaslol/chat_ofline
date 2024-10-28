package org.chatta.model.connection;

import org.chatta.controllers_and_view.XML;
import org.chatta.model.entity.User;
import org.chatta.model.entity.UserList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XML_User {
    public static boolean writeXML(User user) {
        boolean result = false;
        try {
            File file = new File(XML.USER_XML.getURL());
            List<User> users = new ArrayList<>();

            // Si el archivo ya existe, leer los mensajes existentes
            if (file.exists()) {
                users = readUserFromFile(file);
            }

            // Agregar el nuevo mensaje a la lista
            users.add(user);

            // Crear un nuevo archivo XML con todos los mensajes
            JAXBContext context = JAXBContext.newInstance(UserList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // Crear un contenedor para todos los mensajes
            UserList list = new UserList();
            list.setUsers(users);

            // Escribir todos los mensajes en el archivo
            marshaller.marshal(list, file);
            result = true;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<User> readUserFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(UserList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserList list = (UserList) unmarshaller.unmarshal(file);
            return list.getUsers();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static User readXML(String filename) {
        User message = null;
        try {
            JAXBContext context = JAXBContext.newInstance(UserList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserList list = (UserList) unmarshaller.unmarshal(new File(filename));
            List<User> messages = list.getUsers();
            if (!messages.isEmpty()) {
                message = messages.get(messages.size() - 1); // Lee el último mensaje como ejemplo
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static User readNickName(String nickName){
        User user = null;

        try {
            JAXBContext context = JAXBContext.newInstance(UserList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserList list = (UserList) unmarshaller.unmarshal(new File(XML.USER_XML.getURL()));
            List<User> users = list.getUsers();

            for (User readUsers : users){
                if (readUsers.getNickName() == nickName){
                    user = readUsers;
                    break;
                }
            }

        }catch (JAXBException e){
            e.printStackTrace();
        }

        return user;
    }
}
