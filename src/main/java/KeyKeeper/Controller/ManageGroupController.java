package KeyKeeper.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageGroupController implements Initializable {


    public ComboBox groupComboBox;
    public Label SelectedGroupLabel;
    public Button deleteGroupButton;
    public Button modifyGroupButton;
    public HBox GroupModifyHBox;
    public TextField groupNameTextField;
    public Button confirmButton;
    private PasswordKeeper_ManageGroup passwordKeeper_manageGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    public interface PasswordKeeper_ManageGroup {
        void deleteGroup(String name);
        void modifyGroup(String name);
    }
    public void SetListener(PasswordKeeper_ManageGroup passwordKeeper_manageGroup) {
        this.passwordKeeper_manageGroup = passwordKeeper_manageGroup;
    }
}
