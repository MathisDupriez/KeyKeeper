package KeyKeeper.Tools;
import KeyKeeper.Modele.Password;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Objects;

public class PasswordElement {
    public HBox CreatePasswordElement(Password password) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(57.0);
        hBox.setPrefWidth(950.0);
        hBox.setStyle("-fx-border-color: white; -fx-alignment: center-left;");

        Insets margin = new Insets(10.0, 10.0, 10.0, 10.0);
        System.out.println( password.getName());
        if(Objects.equals(password.getName(), "")) {
            password.setName("No name");
        }
        Label nameLabel = new Label(password.getName());
        nameLabel.setTextFill(Color.WHITE);
        HBox.setMargin(nameLabel, margin);

        Label descriptionLabel = new Label(password.getDescription());
        descriptionLabel.setTextFill(Color.WHITE);
        HBox.setMargin(descriptionLabel, margin);

        Label passwordLabel = new Label(password.getPassword());
        passwordLabel.setTextFill(Color.WHITE);
        HBox.setMargin(passwordLabel, margin);

        Button copyButton = new Button("Copy password");
        copyButton.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(password.getPassword()); // Le mot de passe que vous voulez copier
            clipboard.setContent(content);
        });
        HBox.setMargin(copyButton, margin);

        Button modifyButton = new Button("Modify password");
        HBox.setMargin(modifyButton, margin);

        Button removeButton = new Button("Remove password");
        HBox.setMargin(removeButton, margin);

        hBox.getChildren().addAll(nameLabel, descriptionLabel, passwordLabel, copyButton, modifyButton, removeButton);
        return hBox;
    }
}
