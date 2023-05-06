package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Teams;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTeamController extends Controller<Teams> {

    @FXML
    private Button addButton;

    @FXML
    private TextField nameTf;

    public Teams getTeams() {
        return this.model;
    }

    @FXML
    public void initialize() {
        // Enable buttons except when no text is entered in the TextField
        nameTf.textProperty().addListener(
                (o, oldText, newText) -> addButton.setDisable(nameTf.getText().equals(""))
        );
    }

    @FXML
    public void add() {
        
    }


}
