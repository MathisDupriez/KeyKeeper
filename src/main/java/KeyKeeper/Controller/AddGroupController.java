package KeyKeeper.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddGroupController implements Initializable {

    public TextField groupeNameTextField;
    public Button addGroupButton;
    public Button cancelButton;
    private PasswordKeeper_AddGroup passwordKeeper_addGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addGroupButton.setOnAction(event -> {
            setAddGroupButton();
        });
        cancelButton.setOnAction(event -> {
            setCancelButton();
        });
    }

    public interface PasswordKeeper_AddGroup {
        void addGroup(String name);
        void cancel();
    }
    public void setAddGroupButton() {
        if(passwordKeeper_addGroup != null) {
            passwordKeeper_addGroup.addGroup(groupeNameTextField.getText());
        }
    }
    public void setCancelButton() {
        if(passwordKeeper_addGroup != null) {
            passwordKeeper_addGroup.addGroup("");
        }
     }
    public void setPasswordKeeper_addGroup(PasswordKeeper_AddGroup passwordKeeper_addGroup) {
        this.passwordKeeper_addGroup = passwordKeeper_addGroup;
    }

}
