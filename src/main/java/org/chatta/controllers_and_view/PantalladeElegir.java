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

import java.io.IOException;

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

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getUltimoMensaje() { return ultimoMensaje; }
        public void setUltimoMensaje(String ultimoMensaje) { this.ultimoMensaje = ultimoMensaje; }

        public String getHora() { return hora; }
        public void setHora(String hora) { this.hora = hora; }

        public int getNumeroMensajes() { return numeroMensajes; }
        public void setNumeroMensajes(int numeroMensajes) { this.numeroMensajes = numeroMensajes; }
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

    // Método de inicialización
    @FXML
    public void initialize() {
        // Configurar las columnas con las propiedades del modelo
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ultimoMensajeColumn.setCellValueFactory(new PropertyValueFactory<>("ultimoMensaje"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        numeroMensajesColumn.setCellValueFactory(new PropertyValueFactory<>("numeroMensajes"));

        // Crear lista de datos
        ObservableList<DATA> dataList = FXCollections.observableArrayList(
                new DATA("Juan", "Hola", "10:30 AM", 5),
                new DATA("Maria", "Adiós", "11:00 AM", 3),
                new DATA("Maria", "Adiós", "11:00 AM", 3),
                new DATA("Maria", "Adiós", "11:00 AM", 3),
                new DATA("Maria", "Adiós", "11:00 AM", 3),
                new DATA("Maria", "Adiós", "11:00 AM", 3),
                new DATA("Luis", "Nos vemos", "12:45 PM", 7)
        );

        // Agregar los datos a la tabla
        tableView.setItems(dataList);

        // Añadir un listener para detectar el clic en una fila de la tabla
        tableView.setRowFactory(tv -> {
            TableRow<DATA> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    DATA rowData = row.getItem();
                    System.out.println("Nombre seleccionado: " + rowData.getNombre()); // Para pruebas
                    try {
                        // Cambiar a la pantalla de inicio
                        SwitchTopantalladeinicio();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    private void SwitchTopantalladeinicio() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
    }
}
