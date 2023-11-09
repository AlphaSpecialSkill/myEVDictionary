package dictionary.myevdictionary.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class HomeViewController {

    @FXML
    private HBox content;

    @FXML
    private AnchorPane homeViewPane;

    @FXML
    private ListView<String> listView;

    @FXML
    private HBox search;

    @FXML
    private TextField searchbar;

    @FXML
    private Button searchbtn;

    @FXML
    private VBox vboxHome;

    @FXML
    private WebView webView;
}