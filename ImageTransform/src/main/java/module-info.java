module com.example.imagetransform {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.imagetransform to javafx.fxml;
    exports com.example.imagetransform;
}