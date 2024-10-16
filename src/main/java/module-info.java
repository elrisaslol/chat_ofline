module org.chatta {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.chatta to javafx.fxml;
    exports org.chatta;
}
