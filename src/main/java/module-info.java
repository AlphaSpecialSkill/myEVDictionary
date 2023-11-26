module dictionary.myevdictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires jlayer;

    exports dictionary.myevdictionary.controller;

    opens dictionary.myevdictionary.controller to javafx.fxml;
    exports dictionary.myevdictionary;
    opens dictionary.myevdictionary to javafx.fxml;
}