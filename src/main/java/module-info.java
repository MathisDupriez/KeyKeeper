module Keykeeperv1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    exports KeyKeeper.Controller to javafx.graphics;
    opens KeyKeeper.Controller to javafx.fxml;
    exports KeyKeeper.Application to javafx.graphics;
    opens KeyKeeper.Application to javafx.fxml;
    exports KeyKeeper.Modele to javafx.graphics;
    opens KeyKeeper.Modele to javafx.fxml;
    exports KeyKeeper.Tools to javafx.graphics;
    opens KeyKeeper.Tools to javafx.fxml;
}