package org.chatta.model.entity;

public class Sesion {
    private User user;
    private static Sesion _sesionIniciada;

    public static Sesion getSesion(){
        if (_sesionIniciada == null) {
            _sesionIniciada = new Sesion();
        }
        return _sesionIniciada;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
}
