module creativehunters.creativehunters {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens creativehunters.creativehunters to javafx.fxml;
    opens controladores to javafx.fxml;
    opens metodos to javafx.fxml;
    exports controladores;
    exports creativehunters.creativehunters;
    exports metodos;
}
