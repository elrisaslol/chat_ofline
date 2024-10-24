package org.chatta.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "UserList")
public class UserList {
    private List<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    @XmlElement(name = "User")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // Método para añadir un usuario a la lista
    public void addUser(User user) {
        this.users.add(user);
    }
}
