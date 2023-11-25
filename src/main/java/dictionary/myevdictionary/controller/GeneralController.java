package dictionary.myevdictionary.controller;

import dictionary.myevdictionary.Model.DictionaryManagement;
import dictionary.myevdictionary.Model.NewDictionary;
import dictionary.myevdictionary.Model.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GeneralController extends MainController implements Initializable {
    private static final String EV_PATH = "src/main/resources/dictionary/myevdictionary/data/E_V.txt";
    private static final String VE_PATH = "src/main/resources/dictionary/myevdictionary/data/V_E.txt";
    private static final String EV_RECENT_PATH = "src/main/resources/dictionary/myevdictionary/data/E_V_Recent.txt";
    private static final String VE_RECENT_PATH = "src/main/resources/dictionary/myevdictionary/data/V_E_.txt";
    private static final String EV_BOOKMARK_PATH = "src/main/resources/dictionary/myevdictionary/data/E_V.txt";
    private static final String VE_BOOKMARK_PATH = "src/main/resources/dictionary/myevdictionary/data/E_V.txt";

    protected ObservableList<String> bookmarkSearch = FXCollections.observableArrayList();
    protected final ObservableList<String> searchList = FXCollections.observableArrayList();
    protected ObservableList<String> historySearch = FXCollections.observableArrayList();

    @FXML
    protected TextField searchField;
    @FXML
    protected WebView definitionView;
    @FXML
    protected ListView<String> listView;
    @FXML
    protected HTMLEditor editDefinition;
    @FXML
    protected Button saveChangeButton;
    @FXML
    protected Button bookmarkTrue;
    @FXML
    protected Button bookmarkFalse;
    @FXML
    protected Button removeButton;
    @FXML
    protected Button editButton;
    @FXML
    protected Button speaker1;
    @FXML
    protected Button speaker2;
    @FXML
    protected Button transLanguageEV;
    @FXML
    protected Button transLanguageVE;
    @FXML
    protected Label headText;
    @FXML
    protected Label speaker1Language;
    @FXML
    protected Label speaker2Language;
    @FXML
    protected ChoiceBox<String> choiceBoxUK;
    @FXML
    protected ChoiceBox<String> choiceBoxUS;
    @FXML
    protected Slider slider;
    protected boolean isOnEditDefinition = false;
    protected static boolean isEVDic;

    protected static NewDictionary evDic = new NewDictionary(EV_PATH, EV_RECENT_PATH, EV_BOOKMARK_PATH);
    protected static NewDictionary veDic = new NewDictionary(VE_PATH, VE_RECENT_PATH, VE_BOOKMARK_PATH);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void showWarningAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Không có từ nào được chọn");
        alert.showAndWait();
    }

    public void clearPane() {
        searchField.clear();
        definitionView.getEngine().loadContent("");
        searchList.clear();
        headText.setText("Nghĩa của từ");
        listView.getItems().clear();
        for (Map.Entry<String, Word> entry : getCurrentDic().getNewWords().entrySet()){
            searchList.add(entry.getKey());
        }
        listView.setItems(searchList);
    }

    public void setLanguage() {
        if (!isEVDic) {
            transLanguageEV.setVisible(false);
            transLanguageVE.setVisible(true);
            speaker1Language.setText("VIE");
            speaker2.setVisible(false);
            speaker2Language.setVisible(false);
        } else {
            transLanguageEV.setVisible(true);
            transLanguageVE.setVisible(false);
            speaker1Language.setText("UK");
            speaker2.setVisible(true);
            speaker2Language.setVisible(true);
        }
    }

    @FXML
    public void handleClickTransButton() {
        isEVDic = !isEVDic;
        setLanguage();
        clearPane();
    }

    public NewDictionary getCurrentDic() {
        if (isEVDic) return evDic;
        else return veDic;
    }

    public void updateWordInListView(String word, int index, HashMap<String, Word> res, HashMap<String, Word> des) {
        List<String> resKeys = new ArrayList<>(res.keySet());
        if (index < 0) {
            return;
        }
        int j = index;
        while (j >= 0) {
            if (DictionaryManagement.isContain(word, resKeys.get(j)) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            Word temp = new Word(resKeys.get(i), res.get(resKeys.get(i)).getDef());
            des.put(resKeys.get(i), temp);
        }

        for (int i = index + 1; i < res.size(); i++) {
            if (DictionaryManagement.isContain(word, resKeys.get(i)) == 0) {
                Word temp = new Word(resKeys.get(i), res.get(resKeys.get(i)).getDef());
                des.put(resKeys.get(i), temp);
            } else {
                break;
            }
        }
    }

    public void handleClickListView() {
        String word = listView.getSelectionModel().getSelectedItem();
        if (word == null) {
            return;
        }
        searchField.setText(word);
        String selectedWord = null;
        for (Map.Entry<String, Word> entry : getCurrentDic().getBookmarkNewWords().entrySet()) {
            if (entry.getKey().equals(word)) {
                selectedWord = entry.getKey();
                break;
            }
        }
        if (selectedWord != null) {
            bookmarkFalse.setVisible(false);
            bookmarkTrue.setVisible(true);
        } else {
            bookmarkFalse.setVisible(true);
            bookmarkTrue.setVisible(false);
        }
        int index = Collections.binarySearch(getCurrentDic().getNewKeys(), word);
        if (isEVDic) {
            headText.setText(word);
        } else {
            String meaning = veDic.getNewWords().get(getCurrentDic().getNewKeys().get(index)).getDef().substring(9, 9 + word.length());
            headText.setText(meaning);
        }
    }
    protected void addBookmark(Word word) throws IOException {
        bookmarkFalse.setVisible(false);
        bookmarkTrue.setVisible(true);
        getCurrentDic().getBookmarkNewWords().put(word.getWord(), word);
        getCurrentDic().addWordToFile(word.getWord(), word.getDef(), getCurrentDic().getBOOKMARK_PATH());
    }

    protected void removeBookmark(Word word) throws IOException {
        bookmarkFalse.setVisible(true);
        bookmarkTrue.setVisible(false);
        getCurrentDic().getBookmarkNewWords().remove(word);
        getCurrentDic().updateWordToFile(getCurrentDic().getBOOKMARK_PATH(), getCurrentDic().getBookmarkNewWords());
    }

    @FXML
    public void handleClickBookmarkButton() throws IOException {
        List<String> bookmarkKeys = new ArrayList<>(getCurrentDic().getBookmarkNewWords().keySet());
        String word = searchField.getText();
        if (word.equals("")) {
            showWarningAlert();
            return;
        }

        int _index = Collections.binarySearch(getCurrentDic().getNewKeys(), word);
        String selectedWordInDic = getCurrentDic().getNewKeys().get(_index);
        int index = Collections.binarySearch(bookmarkKeys, word);
        String selectedWordinBookmark = bookmarkKeys.get(_index);
        if (index < 0) {
            addBookmark(getCurrentDic().getNewWords().get(selectedWordInDic));
        } else {
            removeBookmark(getCurrentDic().getBookmarkNewWords().get(selectedWordinBookmark));
        }
    }

    @FXML
    public void handleClickEditButton() {
        String word = searchField.getText();
        if (word.equals("")) {
            showWarningAlert();
            return;
        }
        if (isOnEditDefinition) {
            isOnEditDefinition = false;
            editDefinition.setVisible(false);
            saveChangeButton.setVisible(false);
            return;
        }
        isOnEditDefinition = true;
        saveChangeButton.setVisible(true);
        editDefinition.setVisible(true);
        int index = Collections.binarySearch(getCurrentDic().getNewKeys(),word);
        String selectedWord = getCurrentDic().getNewKeys().get(index);
        String definition = getCurrentDic().getNewWords().get(selectedWord).getDef();
        editDefinition.setHtmlText(definition);
    }

    @FXML
    public void handleClickSaveButton() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Sửa từ thành công!");
        alert.showAndWait();
        editDefinition.setVisible(false);
        isOnEditDefinition = false;
        saveChangeButton.setVisible(false);
        String newDef = editDefinition.getHtmlText().replace(" dir=\"ltr\"", "");
        String word = searchField.getText();
        saveWordToFile(getCurrentDic().getPATH(), getCurrentDic().getNewWords(), word, newDef);
        saveWordToFile(getCurrentDic().getHISTORY_PATH(), getCurrentDic().getHistoryNewWords(), word, newDef);
        saveWordToFile(getCurrentDic().getBOOKMARK_PATH(), getCurrentDic().getBookmarkNewWords(), word, newDef);
        definitionView.getEngine().loadContent(newDef, "text/html");
    }

    private void saveWordToFile(String path, Map<String, Word> temp, String word, String def) throws IOException {
        List<String> tempKeys = new ArrayList<>(temp.keySet());
        int index = Collections.binarySearch(tempKeys, word);
        if (index >= 0) {
            temp.get(word).setDef(def);
            getCurrentDic().updateWordToFile(path, temp);
        }
    }

    @FXML
    public void handleClickRemoveButton() throws IOException {
        String spelling = searchField.getText();
        if (spelling.equals("")) {
            showWarningAlert();
            return;
        }
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == yes) {
            getCurrentDic().removeWord(spelling, getCurrentDic().getPATH(), getCurrentDic().getNewWords());
            getCurrentDic().removeWord(spelling, getCurrentDic().getHISTORY_PATH(), getCurrentDic().getHistoryNewWords());
            getCurrentDic().removeWord(spelling, getCurrentDic().getBOOKMARK_PATH(), getCurrentDic().getBookmarkNewWords());
            headText.setText("Nghĩa của từ");
            searchField.clear();
            definitionView.getEngine().loadContent("");
        }
    }
}