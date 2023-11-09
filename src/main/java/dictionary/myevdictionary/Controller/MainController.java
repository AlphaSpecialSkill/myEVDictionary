package dictionary.myevdictionary.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Button homebtn;

    @FXML
    private Button informationbtn;

    @FXML
    private AnchorPane root;

    @FXML
    private Button settingbtn;

    @FXML
    private Button userbtn;

    @FXML
    private VBox navBar;

    public void pressedHome(ActionEvent e) throws IOException {
        AnchorPane homePage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View/homeView.fxml")));
        borderPane.setCenter(homePage);
    }

    public void pressedUser(ActionEvent e) throws IOException {

    }

    public void pressedInformation(ActionEvent e) throws IOException {
    }

    public void pressedSetting(ActionEvent e) throws IOException {
    }
}