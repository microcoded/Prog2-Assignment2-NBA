package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Teams;

public class ErrorController extends Controller<Teams> {
    @FXML
    private Button okButton;

    @FXML
    private Text errorLbl;

    @FXML
    public void initialize() {

    }

    @FXML
    public void setErrorLbl(String err) {
        errorLbl.setText(err);
    }

    public void close() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
