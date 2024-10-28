package org.chatta.model.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class User {
    private String nickName;
    private String password;

    public User() {
    }

    public User(String nickName, String password) {
        setNickName(nickName);
        setPassword(password);
    }

    public String getNickName() {
        return nickName;
    }
    public String getPassword() {
        return password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nickName, user.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickName);
    }
}
