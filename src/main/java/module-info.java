module ve.edu.ucab.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens ve.edu.ucab.application to javafx.fxml;
    exports ve.edu.ucab.application;
    exports ve.edu.ucab.controllers;
    exports ve.edu.ucab.models;
    opens ve.edu.ucab.controllers to javafx.fxml;
}