package dictionary.myevdictionary.controller;

import dictionary.myevdictionary.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Button currentlyChosenButton;
    @FXML
    protected BorderPane borderPane;

    @FXML
    protected Button homebtn;

    @FXML
    protected Button informationbtn;

    @FXML
    protected AnchorPane root;

    @FXML
    protected Button googlebtn;

    @FXML
    protected Button historybtn;

    @FXML
    protected VBox navBar;

    @FXML
    public void pressedHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        AnchorPane view = loader.load();
        borderPane.setCenter(view);

        HomeViewController homeViewController = loader.getController();
        homeViewController.setHomeViewPane(view);
        homeViewController.initComponents(view);
        homeViewController.readData();
        homeViewController.loadWordList();
    }

    @FXML
    void pressedHistory(MouseEvent event) throws IOException {
        AnchorPane historyPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("history-view.fxml")));
        borderPane.setCenter(historyPage);
    }

    @FXML
    void pressedInformation(MouseEvent event) throws IOException {
        AnchorPane informationPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("information-view.fxml")));
        borderPane.setCenter(informationPage);
    }

    @FXML
    void pressedGoogle(MouseEvent event) throws IOException {
        AnchorPane APIpage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("googleAPI-view.fxml")));
        borderPane.setCenter(APIpage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        AnchorPane view = null;
        try {
            view = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);

        HomeViewController homeViewController = loader.getController();
        homeViewController.setHomeViewPane(view);
        homeViewController.initComponents(view);
        try {
            homeViewController.readData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        homeViewController.loadWordList();
    }

}