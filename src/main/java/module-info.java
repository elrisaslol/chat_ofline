module org.chatta {
    requires javafx.fxml;
    requires java.xml.bind;
    requires com.gluonhq.charm.glisten;

    opens org.chatta to javafx.fxml;
    exports org.chatta;
    exports org.chatta.controllers_and_view;
    opens org.chatta.controllers_and_view to javafx.fxml;
}
