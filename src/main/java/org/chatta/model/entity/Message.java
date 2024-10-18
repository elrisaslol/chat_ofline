package org.chatta.model.entity;

import org.chatta.model.entity.User;
import utils.LocalTimeAdapter;  // Ajuste para la ruta correcta
import java.time.LocalTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
    private User transmitter;           // Emisor
    private User receiver;              // Receiver
    private String infoMessage;         // Lo que se envia por el mensaje
    @XmlAttribute
    private String dateMessage;      // FechaMensaje

    public Message(User transmitter, User receiver, String infoMessage) {
        this.transmitter = transmitter;
        this.receiver = receiver;
        this.infoMessage = infoMessage;
        this.dateMessage = LocalTimeAdapter.convertLocalTimeToString(LocalTime.now());
    }

    public Message(User transmitter) {
        this.transmitter = transmitter;
    }

    public Message() {
    }

    // aunque solo se cambie la información del mensaje necesitamos los otros para el xml
    public User getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(User transmitter) {
        this.transmitter = transmitter;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(LocalTime dateMessage) {
        this.dateMessage = LocalTimeAdapter.convertLocalTimeToString(LocalTime.now());
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    @Override
    public String toString() {
        return "Message de " + transmitter.getNickName() + " fecha: " + LocalTimeAdapter.convertStringToLocalTime(dateMessage) +"  \n" +
                "Remitente: " + receiver.getNickName() + " { \n"+
                infoMessage + " \n}";
    }
}
