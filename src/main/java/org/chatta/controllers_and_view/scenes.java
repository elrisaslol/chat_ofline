package org.chatta.controllers_and_view;


public enum scenes {
    PANTALLADEINICIO("controllers_and_view/inicio.fxml"),
    PANTALLADEELEGIR("controllers_and_view/elegir.fxml"),
    PANTALLADEESCRIBIR("controllers_and_view/escribir.fxml"),
    PANTALLADEINSCRIPCION("controllers_and_view/inscripcion.fxml");


    private String url;

    scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }

}
