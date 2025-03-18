module com.mycompany.convertidorunidades.sistemas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.convertidorunidades.sistemas to javafx.fxml;
    exports com.mycompany.convertidorunidades.sistemas;
}
