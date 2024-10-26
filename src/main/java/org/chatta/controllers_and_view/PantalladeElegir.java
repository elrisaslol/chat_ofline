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
import utils.LocalTimeAdapter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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
        System.out.println(Sesion.getSesion().getUser());


        // Configurar las columnas
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ultimoMensajeColumn.setCellValueFactory(new PropertyValueFactory<>("ultimoMensaje"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        numeroMensajesColumn.setCellValueFactory(new PropertyValueFactory<>("numeroMensajes"));

        // Información de los datos de abajo
        List<User> users = readUserFromFile(new File(XML.USER_XML.getURL()));
        List<Message> messages = readMessagesFromFile(new File(XML.MESSAGE_XML.getURL()));


        // Crear lista de datos
        ObservableList<DATA> dataList = FXCollections.observableArrayList();

        for (User user : users) {
            int numeroDeMensajes = contarMensajes(user);
            Message ultimoMessage = UltimoMensaje(user);
            if (Sesion.getSesion().getUser() != user) { // Verificar que el usuario en sesión no se muestre
                if (ultimoMessage != null) {
                    // Agregar datos con el último mensaje encontrado
                    dataList.add(new DATA(
                            user.getNickName(),
                            ultimoMessage.getInfoMessage(),
                            ultimoMessage.getDateMessage(),
                            numeroDeMensajes
                    ));
                } else {
                    // Agregar datos con un mensaje vacío si no hay último mensaje
                    dataList.add(new DATA(
                            user.getNickName(),
                            "",
                            "",
                            numeroDeMensajes
                    ));
                }
            }
        }

        // Establecer la lista completa en el TableView
        tableView.setItems(dataList);


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
    }

    public static Message UltimoMensaje(User user) {
        List<Message> messages = XML_Message.readMessagesFromFile(new File((XML.MESSAGE_XML.getURL())));
        List<Message> messagesTMP = new ArrayList<>();

        for (Message message : messages) {
            if ((message.getTransmitter().equals(user) && message.getReceiver().equals(Sesion.getSesion().getUser())) ||
                    (message.getTransmitter().equals(Sesion.getSesion().getUser()) && message.getReceiver().equals(user))) {
                messagesTMP.add(message);
            }
        }

        Message ultimoMensaje = encontrarMensajeMasCercano(messagesTMP);

        return ultimoMensaje;
    }


    public static Message encontrarMensajeMasCercano(List<Message> messages) {
        LocalDateTime ahora = LocalDateTime.now();
        Message mensajeMasCercano = null;

        for (Message message : messages) {
            LocalDateTime fecha = LocalTimeAdapter.convertStringToLocalTime(message.getDateMessage()); // Obtener la fecha del mensaje

            if (mensajeMasCercano == null ||
                    Math.abs(fecha.until(ahora, java.time.temporal.ChronoUnit.SECONDS)) <
                            Math.abs(LocalTimeAdapter.convertStringToLocalTime(mensajeMasCercano.getDateMessage())
                                    .until(ahora, java.time.temporal.ChronoUnit.SECONDS))) {

                mensajeMasCercano = message;
            }
        }

        return mensajeMasCercano;
    }

    public static int contarMensajes(User user){
        int contador = 0;

        List<Message> messages = XML_Message.readMessagesFromFile(new File((XML.MESSAGE_XML.getURL())));

        for (Message message : messages) {
            if ((message.getTransmitter().equals(user) && message.getReceiver().equals(Sesion.getSesion().getUser())) ||
                    (message.getTransmitter().equals(Sesion.getSesion().getUser()) && message.getReceiver().equals(user))) {
                contador++;
            }
        }

        return contador;
    }

}
