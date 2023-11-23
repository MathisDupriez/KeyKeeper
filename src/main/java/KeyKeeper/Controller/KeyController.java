package KeyKeeper.Controller;

import KeyKeeper.Modele.Password;
import KeyKeeper.Tools.DataBase;
import KeyKeeper.Tools.PasswordElement;
import KeyKeeper.Tools.PasswordGenerator;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class KeyController implements Initializable {

    //public component
    public VBox passwordContainerVbox;
    public ToolBar menuHbox;
    public Button newPasswordButton;
    public ComboBox<String> groupComboBOx;
    public HBox passwordWritingHbox;
    public TextField nameTextFiled;
    public TextField descritptionTextField;
    public Button generatePassordButton;
    public Label passwordLabel;
    public Button addPasswordButton;
    public Button removePasswordButton;
    public ScrollPane ScrollPaneVbox;
    public BorderPane borderPaneMain;
    public Button addGroupButton;
    public ProgressIndicator status;
    public MenuButton menuFileButton;

    //private variable for passwordKeeper
    private PasswordKeeper passwordKeeper;
    private final PasswordElement passwordElement = new PasswordElement();
    private PasswordGenerator passwordGenerator = new PasswordGenerator();


    //initialize function
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //event for ToolBar
        menuFileButton.setOnAction(event -> {
            System.out.println("File button pressed");
        });
        addGroupButton.setOnAction(event -> {
            addGroup();
        });

        newPasswordButton.setOnAction(event -> {
            NewPassword();
        });

        //event for password Adding
        generatePassordButton.setOnAction(event -> {
            generatePassword();
        });
        addPasswordButton.setOnAction(event -> {
            addPassword();
        });

        //ToolbarInitialize
        populateMenu();

        //Scrollpane
        ScrollPaneVbox.setFitToHeight(true);
        ScrollPaneVbox.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        ScrollPaneVbox.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //Vbox
        passwordContainerVbox.prefWidthProperty().bind(ScrollPaneVbox.widthProperty());
        passwordContainerVbox.prefHeightProperty().bind(ScrollPaneVbox.heightProperty());
        //Hbox for password writing
        passwordWritingHbox.visibleProperty().addListener((obs, wasVisible, isNowVisible) -> {
            if (isNowVisible) {
                // Si la HBox est visible, restituer son espace
                borderPaneMain.setBottom(passwordWritingHbox);
            } else {
                // Si la HBox n'est pas visible, supprimer son espace
                borderPaneMain.setBottom(null);
            }
        });
        passwordWritingHbox.setVisible(false);
    }

    private void populateMenu() {
        menuFileButton.getItems().clear();
        MenuItem menuItem = new MenuItem("Manage group");
        menuItem.setOnAction(event -> {
            passwordKeeper.MenuFileManageGroup();
        });
        menuFileButton.getItems().add(menuItem);
    }

    public void setGroupComboBox(String []groups) {
        DataBase dataBase = new DataBase();
        groupComboBOx.getItems().clear();
        groupComboBOx.getItems().addAll(groups);
    }

    public void setPasswordContainerVbox(Password[] passwords,int id) {
        passwordContainerVbox.getChildren().clear();
        for(Password password : passwords) {
            AddPasswordElement(password);
        }
    }

    //function for ToolBar
    private void NewPassword() {
        if(passwordKeeper != null) {
            passwordKeeper.newPassord();
            passwordWritingHbox.setPrefHeight(50);
            passwordWritingHbox.setVisible(true);
        }
    }


    private void addGroup() {
        if(passwordKeeper != null) {
            passwordKeeper.addGroup();
        }
    }


    //function for password Adding
    private void addPassword() {
        if(passwordKeeper != null) {
            passwordKeeper.addPassword();
            resetPasswordWritting();
        }
    }


    private void generatePassword() {
        if(passwordKeeper != null) {
            passwordKeeper.generatePassword();
        }
    }




    //view Setter
    public void setPasswordLabel(String password) {
        passwordLabel.setText(password);
    }


    public void AddPasswordElement(Password password) {
        HBox Password = passwordElement.CreatePasswordElement(password);
        passwordContainerVbox.getChildren().add(Password);
    }


    private void StatusIndic(int value) {
        status.setProgress(value);
    }



    //tools

    private void resetPasswordWritting() {
        passwordWritingHbox.setPrefHeight(0);
        passwordWritingHbox.setVisible(false);
        nameTextFiled.setText("");
        descritptionTextField.setText("");
        passwordLabel.setText("Not created");
        passwordWritingHbox.setPrefHeight(0);
    }


    //interfaceGetter
    public String[] GetPasswordWritting(){
        return new String[]{nameTextFiled.getText(), descritptionTextField.getText(), passwordLabel.getText()};
    }


    //interface

    public interface PasswordKeeper {
        //Function for ToolBar
        void newPassord();
        void addGroup();
        //Function for password Adding
        void addPassword();
        void generatePassword();
        void MenuFileManageGroup();
    }
    public void setPasswordKeeper(PasswordKeeper passwordKeeper_) {
        this.passwordKeeper = passwordKeeper_;
    }
}