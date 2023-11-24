package dictionary.myevdictionary.controller;

import dictionary.myevdictionary.Model.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import java.util.*;
import java.util.stream.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static dictionary.myevdictionary.Model.Dictionary.words;

public class SearchController implements Initializable {
    private static final String DATA_FILE_PATH = "src/main/resources/dictionary/myevdictionary/data/E_V.txt";

    private static final String SPLITTING_CHARACTERS = "<html>";

    @FXML
    protected VBox content;

    @FXML
    protected AnchorPane homeViewPane;

    @FXML
    protected ListView<String> listView;

    @FXML
    protected VBox search;

    @FXML
    protected TextField searchbar;

    @FXML
    protected HBox hboxHome;

    @FXML
    protected WebView definitionView;

    public AnchorPane getHomeViewPane() {
        return homeViewPane;
    }

    public void setHomeViewPane(AnchorPane homeViewPane) {
        this.homeViewPane = homeViewPane;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public void setListView(ListView<String> listView) {
        this.listView = listView;
    }

    @FXML
    public void search(MouseEvent event) {
        this.listView.getItems().clear();
        this.listView.getItems().addAll(updateSearchList(searchbar.getText(), words));
    }

    public void enterKeyPressed() {
        searchbar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.listView.getItems().clear();
                this.listView.getItems().addAll(updateSearchList(searchbar.getText(), words));
            }
        });
    }

    public void searchbarInput() {
        searchbar.setOnKeyPressed(event -> {
            this.listView.getItems().clear();
            this.listView.getItems().addAll(updateSearchList(searchbar.getText(), words));
        });
    }

    private Set<String> updateSearchList(String searchWords, Map<String, Word> wordsMap) {
        Set<String> wordSet = words.keySet();
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return wordSet.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toSet());
    }

    public void initComponents(AnchorPane view) {
        this.definitionView = (WebView) view.lookup("#definitionView");
        this.listView = (ListView<String>) view.lookup("#listView");
//        HomeViewController context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = words.get(newValue.trim());
                    String definition = selectedWord.getDef();
                    this.definitionView.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    public void loadWordList() {
        this.listView.getItems().addAll(words.keySet());
    }

    public void readData() throws IOException {
        FileReader fis = new FileReader(DATA_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = SPLITTING_CHARACTERS + parts[1];
            Word wordObj = new Word(word, definition);
            words.put(word, wordObj);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initComponents(homeViewPane);
        try {
            readData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        loadWordList();
        enterKeyPressed();
        searchbarInput();
    }
}