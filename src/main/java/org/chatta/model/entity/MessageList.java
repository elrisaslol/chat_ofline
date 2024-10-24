package org.chatta.model.entity;

import org.chatta.model.entity.Message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "MessageList")
public class MessageList {
    private List<Message> messages;

    public MessageList() {
        this.messages = new ArrayList<>();
    }

    @XmlElement(name = "Message") // Asegúrate de que el nombre coincida con el nombre de tu clase Message
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // Método para añadir un mensaje a la lista
    public void addMessage(Message message) {
        this.messages.add(message);
    }
}

