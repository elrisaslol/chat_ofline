package org.chatta.controllers_and_view;

public enum XML {
    USER_XML("XML_User.xml"),
    MESSAGE_XML("XML_Message.xml");

    private String url;

    // Constructor para inicializar el valor del URL
    XML(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}

