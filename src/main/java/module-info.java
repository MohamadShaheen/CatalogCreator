module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;

    opens org.example to javafx.fxml;
    exports org.example;
    exports Controller;
    opens Controller to javafx.fxml;
}
