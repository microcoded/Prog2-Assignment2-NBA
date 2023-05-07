package controller;


import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Association;
import model.Player;
import model.Players;
import model.Teams;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerUpdateController extends Controller<Teams> {
    public Teams getTeams() {
        return this.model;
    }
    @FXML private Button closeButton;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private TextField nameTf;
    @FXML private TextField creditTf;
    @FXML private TextField ageTf;
    @FXML private TextField NoTf;

    String playerName = ManageTeamController.playerName;
    String teamName = TeamsController.teamName;
    Player player = ManageTeamController.selectedPlayer;

    @FXML
    public void initialize() {
        nameTf.setText(playerName);
        creditTf.setText(player.getCredit().toString());
        ageTf.setText(player.getAge().toString());
        NoTf.setText(player.getNo().toString());

        updateButton.setDisable(false);
        // Enable update button when text is not empty
        nameTf.textProperty().addListener(
                (o, oldText, newText) -> updateButton.setDisable(
                        nameTf.getText().equals("")
                )
        );
        creditTf.textProperty().addListener(
                (o, oldText, newText) -> updateButton.setDisable(
                        creditTf.getText().equals("")
                )
        );
        ageTf.textProperty().addListener(
                (o, oldText, newText) -> updateButton.setDisable(
                        ageTf.getText().equals("")
                )
        );
        NoTf.textProperty().addListener(
                (o, oldText, newText) -> updateButton.setDisable(
                        NoTf.getText().equals("")
                )
        );
    }

    @FXML
    public void update() {
        boolean valid = true;
        if (valid) {
            player.setName(nameTf.getText());
            player.setCredit(Double.valueOf(creditTf.getText()));
            player.setAge(Integer.parseInt(ageTf.getText()));
            player.setNo(Integer.parseInt(NoTf.getText()));
            Stage stage = (Stage) nameTf.getScene().getWindow();
            stage.setTitle("Updating Player: " + nameTf.getText());
        } else {
            try {
                Association.validator.addError("Not implemented");
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/nba.png"));
                stage.setResizable(false);
                ViewLoader.showStage(getTeams(), "/view/error.fxml", "Input Errors", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void add() {

    }

    @FXML
    public void close() {
        Stage stage = (Stage) nameTf.getScene().getWindow();
        stage.close();
    }
   
}
