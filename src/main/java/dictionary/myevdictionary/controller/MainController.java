package dictionary.myevdictionary.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    protected AnchorPane mainPane;
    @FXML
    protected AnchorPane searchPane;
    @FXML
    protected AnchorPane bookmarkPane;
    @FXML
    protected AnchorPane historyPane;
    @FXML
    protected AnchorPane translatePane;
    @FXML
    protected AnchorPane settingPane;


    @FXML
    protected BorderPane borderPane;


    @FXML
    private SearchController searchController;
    @FXML
    private BookmarkController bookmarkController;
    @FXML
    private HistoryController historyController;


    @FXML
    protected VBox navBar;


    @FXML
    protected Button searchbtn;
    @FXML
    protected Button bookmarkbtn;
    @FXML
    protected Button historybtn;
    @FXML
    protected Button translatebtn;
    @FXML
    protected Button settingbtn;

    protected void setMainContent(AnchorPane anchorPane){
        borderPane.setCenter(mainPane);
    }

    protected void resetStyleNav(){
        searchbtn.getStyleClass().removeAll("active");
        bookmarkbtn.getStyleClass().removeAll("active");
        historybtn.getStyleClass().removeAll("active");
        translatebtn.getStyleClass().removeAll("active");
        settingbtn.getStyleClass().removeAll("active");
    }

    @FXML
    public void showSearchPane() throws IOException {
        resetStyleNav();
        searchbtn.getStyleClass().add("active");
        searchController.initSearchListView();
        setMainContent(searchPane);
    }

    @FXML
    void showTranslatePane() throws IOException {
        resetStyleNav();
        translatebtn.getStyleClass().add("active");
        setMainContent(translatePane);
    }

    @FXML
    void showBookmarkPane() throws IOException {
        resetStyleNav();
        bookmarkbtn.getStyleClass().add("active");
        bookmarkController.initBookmarkListView();
        setMainContent(bookmarkPane);
    }

    @FXML
    void showHistoryPane() throws IOException {
        resetStyleNav();
        historybtn.getStyleClass().add("active");
        historyController.initBookmarkListView();
        setMainContent(historyPane);
    }
    @FXML
    void showSettingPane() throws IOException{
        resetStyleNav();
        settingbtn.getStyleClass().removeAll("active");
        setMainContent(settingPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search-view.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookmark-view.fxml"));
            bookmarkPane = loader.load();
            bookmarkController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history-view.fxml"));
            historyPane = loader.load();
            historyController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("setting-view.fxml"));
            settingPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("translate-view.fxml"));
            translatePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchbtn.getStyleClass().add("active");
        mainPane.getChildren().setAll(searchPane);
    }
}