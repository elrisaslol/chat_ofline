package org.chatta.test;

import java.util.List;

import static org.chatta.controllers_and_view.PantalladeElegir.mostrarTodosLosUsuarios;

public class MostrarTodosLosNicknames {
    public static void main(String[] args) {
        // Llamar al método para obtener todos los nicknames de los usuarios
        List<String> nicknames = mostrarTodosLosUsuarios();

        // Mostrar cada nickname en la terminal
        for (String nickname : nicknames) {
            System.out.println(nickname);
        }
    }
}
