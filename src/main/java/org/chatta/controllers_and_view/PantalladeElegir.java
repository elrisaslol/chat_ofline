package org.chatta.controllers_and_view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.chatta.App;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.chatta.model.connection.XML_Message;
import org.chatta.model.entity.Message;
import org.chatta.model.entity.Sesion;
import org.chatta.model.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.chatta.model.connection.XML_Message.readMessagesFromFile;
import static org.chatta.model.connection.XML_User.readUserFromFile;

public class PantalladeElegir {

    public static class DATA {
        private String nombre;
        private String ultimoMensaje;
        private String hora;
        private int numeroMensajes;

        public DATA(String nombre, String ultimoMensaje, String hora, int numeroMensajes) {
            this.nombre = nombre;
            this.ultimoMensaje = ultimoMensaje;
            this.hora = hora;
            this.numeroMensajes = numeroMensajes;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getUltimoMensaje() {
            return ultimoMensaje;
        }

        public void setUltimoMensaje(String ultimoMensaje) {
            this.ultimoMensaje = ultimoMensaje;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public int getNumeroMensajes() {
            return numeroMensajes;
        }

        public void setNumeroMensajes(int numeroMensajes) {
            this.numeroMensajes = numeroMensajes;
        }
    }

    @FXML
    private TableView<DATA> tableView;

    @FXML
    private TableColumn<DATA, String> nombreColumn;

    @FXML
    private TableColumn<DATA, String> ultimoMensajeColumn;

    @FXML
    private TableColumn<DATA, String> horaColumn;

    @FXML
    private TableColumn<DATA, Integer> numeroMensajesColumn;

    @FXML
    public void initialize() {
        //mostrar singleton
        System.out.println(Sesion.getSesion().getUser().getNickName());


        // Configurar las columnas
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ultimoMensajeColumn.setCellValueFactory(new PropertyValueFactory<>("ultimoMensaje"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        numeroMensajesColumn.setCellValueFactory(new PropertyValueFactory<>("numeroMensajes"));

       /* // Información de los datos de abajo
        List<String> nicknames = mostrarTodosLosUsuarios();
        List<Message> messages = readMessagesFromFile(new File(XML.MESSAGE_XML.getURL()));


        // Crear lista de datos
        for (String nickname : nicknames) {
            if (Sesion.getSesion().getUser().getNickName() != nickname) {       //Si el que se encuentra en sesion no se muestra
                ObservableList<DATA> dataList = FXCollections.observableArrayList(

                        new DATA(nickname, "Ultimo mensaje", "10:30 AM", 5)

                );
                tableView.setItems(dataList);
            }
        }

        // Agregar los datos a la tabla


        // Añadir listener para detectar clics en filas
        tableView.setRowFactory(tv -> {
            TableRow<DATA> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    DATA rowData = row.getItem();
                    System.out.println("Nombre seleccionado: " + rowData.getNombre()); // Para pruebas
                    try {
                        // Cambiar a la pantalla de escribir
                        SwitchTopantalladeEscribir(rowData.getNombre());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });*/
        // Crear lista de datos
        ObservableList<DATA> dataList = FXCollections.observableArrayList(
                new DATA("Alberto", "Hola", "10:30 AM", 5),
                new DATA("Maria", "Adiós", "11:00 AM", 3)

        );

        // Agregar los datos a la tabla
        tableView.setItems(dataList);

        // Añadir listener para detectar clics en filas
        tableView.setRowFactory(tv -> {
            TableRow<DATA> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    DATA rowData = row.getItem();
                    System.out.println("Nombre seleccionado: " + rowData.getNombre()); // Para pruebas
                    try {
                        // Cambiar a la pantalla de escribir
                        SwitchTopantalladeEscribir(rowData.getNombre());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }


    @FXML
    private void SwitchTopantalladeInicio() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
    }

    @FXML
    private void SwitchTopantalladeEscribir(String nombre) throws IOException {
        // Cambia a la escena de escribir
        App.setRoot(scenes.PANTALLADEESCRIBIR);

        // Obtener el controlador de PantalladeEscribir
        PantalladeEscribir escribirController = (PantalladeEscribir) App.getCurrentController();
        escribirController.recibirNombre(nombre); // Llama al método para establecer el nombre
        escribirController.cargarMensajesFiltrados();
    }

    public static List<String> mostrarTodosLosUsuarios() {

        List<User> users = readUserFromFile(new File(XML.USER_XML.getURL()));
        ArrayList<String> nickNames = new ArrayList<>();

        for (User user : users) {
            nickNames.add(user.getNickName());
        }

        return nickNames;
    }

}
