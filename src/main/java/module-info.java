module org.chatta {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.chatta to javafx.fxml;
    exports org.chatta;
    exports org.chatta.controllers_and_view;
    opens org.chatta.controllers_and_view to javafx.fxml;
}
