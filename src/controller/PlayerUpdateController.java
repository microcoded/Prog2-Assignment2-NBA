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

    String mode = ManageTeamController.mode;

    @FXML
    public void initialize() {
        if (mode.equals("update")) {

            nameTf.setText(playerName);
            creditTf.setText(player.getCredit().toString());
            ageTf.setText(player.getAge().toString());
            NoTf.setText(player.getNo().toString());

            updateButton.setDisable(false);
            // Enable update button when text is not empty
            nameTf.textProperty().addListener(
                    (o, oldText, newText) -> updateButton.setDisable(
                            nameTf.getText().equals("")
                                    || creditTf.getText().equals("")
                                    || ageTf.getText().equals("")
                                    || NoTf.getText().equals("")
                    )
            );
            creditTf.textProperty().addListener(
                    (o, oldText, newText) -> updateButton.setDisable(
                            nameTf.getText().equals("")
                                    || creditTf.getText().equals("")
                                    || ageTf.getText().equals("")
                                    || NoTf.getText().equals("")
                    )
            );
            ageTf.textProperty().addListener(
                    (o, oldText, newText) -> updateButton.setDisable(
                            nameTf.getText().equals("")
                                    || creditTf.getText().equals("")
                                    || ageTf.getText().equals("")
                                    || NoTf.getText().equals("")
                    )
            );
            NoTf.textProperty().addListener(
                    (o, oldText, newText) -> updateButton.setDisable(
                            nameTf.getText().equals("")
                                    || creditTf.getText().equals("")
                                    || ageTf.getText().equals("")
                                    || NoTf.getText().equals("")
                    )
            );
        }

        if (mode.equals("add")) {
            addButton.setDisable(true);
            // Enable add button when text is not empty
            nameTf.textProperty().addListener(
                    (o, oldText, newText) -> addButton.setDisable(
                            nameTf.getText().equals("")
                                    || creditTf.getText().equals("")
                                    || ageTf.getText().equals("")
                                    || NoTf.getText().equals("")
                    )
            );
            creditTf.textProperty().addListener(
                    (o, oldText, newText) -> addButton.setDisable(
                            nameTf.getText().equals("")
                                    || creditTf.getText().equals("")
                                    || ageTf.getText().equals("")
                                    || NoTf.getText().equals("")
                    )
            );
            ageTf.textProperty().addListener(
                    (o, oldText, newText) -> addButton.setDisable(
                            nameTf.getText().equals("")
                                    || creditTf.getText().equals("")
                                    || ageTf.getText().equals("")
                                    || NoTf.getText().equals("")
                    )
            );
            NoTf.textProperty().addListener(
                    (o, oldText, newText) -> addButton.setDisable(
                            nameTf.getText().equals("")
                                    || creditTf.getText().equals("")
                                    || ageTf.getText().equals("")
                                    || NoTf.getText().equals("")
                    )
            );
        }
    }

    @FXML
    public void update() {
        String name = nameTf.getText();
        String credit = creditTf.getText();
        String age = ageTf.getText();
        String No = NoTf.getText();
        boolean valid = Association.validator.isValid(name, credit, age, No);
        Association.validator.generateErrors(name, credit, age, No);
        if (valid) {
            int i = 0;
            for (Player p : getTeams().getTeam(teamName).getCurrentPlayers()) {
                if (p == player) {
                    break;
                }
                i++;
            }
            getTeams().getTeam(teamName).getCurrentPlayers().set(i, new Player(name, Double.valueOf(credit), Integer.valueOf(age), Integer.valueOf(No)));
            player.setName(name);
            player.setCredit(Double.valueOf(credit));
            player.setAge(Integer.valueOf(age));
            player.setNo(Integer.valueOf(No));
            Stage stage = (Stage) nameTf.getScene().getWindow();
            stage.setTitle("Updating Player: " + nameTf.getText());
        } else {
            showInputErrors();
        }
    }

    @FXML
    public void add() {
        String name = nameTf.getText();
        String credit = creditTf.getText();
        String age = ageTf.getText();
        String No = NoTf.getText();
        boolean valid = Association.validator.isValid(name, credit, age, No);
        Association.validator.generateErrors(name, credit, age, No);
        if (valid) {
            getTeams().getTeam(teamName).getPlayers().addPlayer(new Player(
                    name,
                    Double.parseDouble(credit),
                    Integer.parseInt(age),
                    Integer.parseInt(No)
                )
            );
            getTeams().getTeam(teamName).getCurrentPlayers();
            close();
        } else {
            showInputErrors();
        }
    }

    private void showInputErrors() {
        try {
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

    @FXML
    public void close() {
        Stage stage = (Stage) nameTf.getScene().getWindow();
        stage.close();
    }
   
}
