package controller;


import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import model.Players;
import model.Team;
import model.Teams;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageTeamController extends Controller<Teams> {

    public static String playerName = "";
    public Teams getTeams() {
        return this.model;
    }

    @FXML private TableView manageTeamTV;
    @FXML private TextField nameTf;
    @FXML private Button manageButton;
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;
    @FXML private TableColumn name;
    @FXML private TableColumn credit;
    @FXML private TableColumn age;
    @FXML private TableColumn No;
    private String teamName = "";
    public static Player selectedPlayer;

    @FXML
    public void initialize() {
        teamName = TeamsController.teamName;
        manageTeamTV.setItems(parsePlayers());

        // Select no row by default
        manageTeamTV.getSelectionModel().select(null);
        manageTeamTV.getProperties().put("selectFocusOnGrain", false);

        // Disable manage and delete buttons when nothing is selected
        // Disable add button when something is selected
        manageTeamTV.getSelectionModel().selectedItemProperty().addListener(
                (o, oldTeam, newTeam) -> {
                    manageButton.setDisable(manageTeamTV.getSelectionModel().selectedItemProperty() == null);
                    deleteButton.setDisable(manageTeamTV.getSelectionModel().selectedItemProperty() == null);
                    addButton.setDisable(manageTeamTV.getSelectionModel().selectedItemProperty() != null);
                }
        );

        // Enable buttons except when no text is entered in the TextField
        nameTf.textProperty().addListener(
                (o, oldText, newText) -> addButton.setDisable(nameTf.getText().equals(""))
        );

        // Set text as default to selected team
        nameTf.setText(teamName);

        // Deselect cell if clicked twice
        manageTeamTV.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                deselect();
            }
        });

    }

    private ObservableList<Player> parsePlayers() {
        ObservableList<Player> playerList = getTeams().getTeam(teamName).getPlayers().getPlayersList();
        return playerList;
    }

    public String selectedPlayer() {
        selectedPlayer = (Player) manageTeamTV.getSelectionModel().getSelectedItem();
        return selectedPlayer.getName();
    }

    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void add() {

    }

    @FXML
    public void update() {
        try {
            playerName = selectedPlayer();
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            stage.setResizable(false);
            stage.setOnHidden(event -> refreshTable());
            ViewLoader.showStage(getTeams(), "/view/PlayerUpdateView.fxml", "Updating Player: " + playerName, stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void delete() {
        getTeams().getTeam(teamName).getPlayers().removePlayer(getPlayer());
        refreshTable();
    }

    public void deselect() {
        manageTeamTV.getSelectionModel().select(null);
        manageButton.setDisable(true);
        deleteButton.setDisable(true);
        addButton.setDisable(false);
    }

    public void refreshTable() {
        manageTeamTV.setItems(parsePlayers());
        deselect();
    }


    public Player getPlayer() {
        return (Player) manageTeamTV.getSelectionModel().getSelectedItem();
    }
}
