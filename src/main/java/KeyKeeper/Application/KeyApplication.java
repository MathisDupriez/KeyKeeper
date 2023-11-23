package KeyKeeper.Application;
import KeyKeeper.Controller.AddGroupController;
import KeyKeeper.Controller.KeyController;
import KeyKeeper.Controller.ManageGroupController;
import KeyKeeper.Modele.Password;
import KeyKeeper.Tools.DataBase;
import KeyKeeper.Tools.PasswordGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class KeyApplication extends Application {
    private KeyController ThiskeyController;
    private AddGroupController ThisAddGroupController;
    private ManageGroupController ThisManageGroupController;
    private Stage AddGroupStage;
    private  Stage ManageGroupStage;
    private String [] group;
    private Password [] password;
    private DataBase dataBase = new DataBase();
    private Connection connection = dataBase.connect();
    @Override
    public void start(Stage stage) throws IOException {
        //Main app
        FXMLLoader MainAppLoader = new FXMLLoader(KeyApplication.class.getResource("/KeyKeeperFxml/KeyKeeperView.fxml"));
        Scene MainScene = new Scene(MainAppLoader.load(), 800, 600);
        stage.setTitle("Password Keeper");
        stage.setScene(MainScene);
        stage.show();
        ThiskeyController = MainAppLoader.getController();
        ThiskeyController.setPasswordKeeper(getPasswordKeeper());


        //Add group
        FXMLLoader AddgroupLoard = new FXMLLoader(KeyApplication.class.getResource("/KeyKeeperFxml/GroupAdding.fxml"));
        Scene AddGroupScene = new Scene(AddgroupLoard.load(), 264, 142);
        AddGroupStage = new Stage();
        AddGroupStage.setResizable(false);
        AddGroupStage.setTitle("Add group");
        AddGroupStage.setScene(AddGroupScene);
        initialize();
        ThisAddGroupController = AddgroupLoard.getController();
        ThisAddGroupController.setPasswordKeeper_addGroup(getPasswordKeeper_addGroup());

        //Manage group
        FXMLLoader ManageGroupLoader = new FXMLLoader(KeyApplication.class.getResource("/KeyKeeperFxml/ManageGroup.fxml"));
        Scene ManageGroupScene = new Scene(ManageGroupLoader.load(), 527, 525);
        ManageGroupStage = new Stage();
        ManageGroupStage.setResizable(false);
        ManageGroupStage.setTitle("Manage group");
        ManageGroupStage.setScene(ManageGroupScene);
        ThisManageGroupController = ManageGroupLoader.getController();
        ThisManageGroupController.SetListener(getPasswordKeeper_manageGroup());


    }
    private void initialize() {
        group = dataBase.fetchGroupData().toArray(new String[0]);
        ThiskeyController.setGroupComboBox(group);
        password = dataBase.fetchPasswordData().toArray(new Password[0]);
    }
    private KeyController.PasswordKeeper getPasswordKeeper() {
        return new KeyController.PasswordKeeper() {
            @Override
            public void addPassword() {
                String [] PasswordInWiew = ThiskeyController.GetPasswordWritting();
                Password password = new Password(PasswordInWiew[0],PasswordInWiew[1],PasswordInWiew[2]);
                ThiskeyController.AddPasswordElement(password);
            }

            @Override
            public void newPassord() {
                System.out.println("New password");
            }

            @Override
            public void addGroup() {
                AddGroupStage.show();
            }

            @Override
            public void generatePassword( ) {
                ThiskeyController.setPasswordLabel(PasswordGenerator.generateRandomPassword(35));
            }

            @Override
            public void MenuFileManageGroup() {
                ManageGroupStage.show();
            }
        };
    }
    private AddGroupController.PasswordKeeper_AddGroup getPasswordKeeper_addGroup() {
        return new AddGroupController.PasswordKeeper_AddGroup() {
            @Override
            public void addGroup(String name) {
                System.out.println("Add group ");
                AddGroupStage.close();
                dataBase.insertGroup(name);
                initialize();
            }
            @Override
            public void cancel() {
                System.out.println("Cancel");
                AddGroupStage.close();
            }
        };
    }
    private ManageGroupController.PasswordKeeper_ManageGroup getPasswordKeeper_manageGroup() {
        return new ManageGroupController.PasswordKeeper_ManageGroup() {
            @Override
            public void deleteGroup(String name) {
                System.out.println("Delete group");
            }

            @Override
            public void modifyGroup(String name) {
                System.out.println("Modify group");
            }
        };
    }
    public static void main(String[] args) {
        launch();
    }


}