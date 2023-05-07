package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Association;
import model.Teams;

public class ErrorController extends Controller<Teams> {
    @FXML
    private Button okButton;

    @FXML
    private Text errorTxt;

    @FXML
    public void initialize() {
        String error = "";
        for (String err : Association.validator.errors()) {
            error += err;
        }
        if (!error.equals("")) {
            errorTxt.setText(error);
        }
        Association.validator.clear();
    }

    @FXML
    public void close() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
