package org.chatta.test;

import org.chatta.model.connection.XML_User;
import org.chatta.model.entity.User;

public class test {
    public static void main(String[] args) {
        // Suponiendo que tienes un usuario con nickName "testUser" en tu XML
        String nickName = "Alberto";

        User user = XML_User.readNickName(nickName);

        System.out.println(user.getNickName() + user.getPassword());
    }
}
