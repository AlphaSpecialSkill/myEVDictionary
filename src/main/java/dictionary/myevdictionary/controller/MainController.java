package dictionary.myevdictionary.controller;

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

    @FXML
    protected AnchorPane mainPane;
    @FXML
    protected AnchorPane searchPane;
    @FXML
    protected AnchorPane bookmardPane;
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
        setMainContent(bookmardPane);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search-view.fxml"));
        AnchorPane view = null;
        try {
            view = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);

        SearchController searchController = loader.getController();
        searchController.setHomeViewPane(view);
        searchController.initComponents(view);
        try {
            searchController.readData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        searchController.loadWordList();
    }
}