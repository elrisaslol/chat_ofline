package org.chatta.model.connection;

import org.chatta.model.entity.User;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XML_User {
    public static boolean writeXML(User user, String fileName){
        boolean result = false;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(user.getClass());             //Permite la conversion de java a XML
            Marshaller marshaller = context.createMarshaller();             //Pasa a representación XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //Permite que el XML sea legible
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");      //Codificación UTF-8
            marshaller.marshal(user, new File(fileName));                   //Convierte el usuario a XML
            result = true;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static User readXML(User user, String filename){
        User userTmp = user;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(user.getClass());             //Permite la conversion de java a XML
            Unmarshaller unmarshaller = context.createUnmarshaller();       //Convierte de XML a java
            userTmp = (User) unmarshaller.unmarshal(new File(filename));    //Se mete el XML convertido en java a una variable User
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return userTmp;
    }
}
