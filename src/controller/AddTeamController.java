package controller;


import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Association;
import model.Team;
import model.Teams;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String name = nameTf.getText();
        if (getTeams().hasTeam(name)) {
            try {
                Association.validator.addError(name + " Team already exists");
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/nba.png"));
                stage.setResizable(false);
                ViewLoader.showStage(getTeams(), "/view/error.fxml", "Error!", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            getTeams().addTeam(new Team(name));
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }


}
