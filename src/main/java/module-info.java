module dictionary.myevdictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens dictionary.myevdictionary to javafx.fxml;
    exports dictionary.myevdictionary;
    exports dictionary.myevdictionary.Controller;
    opens dictionary.myevdictionary.Controller to javafx.fxml;
}