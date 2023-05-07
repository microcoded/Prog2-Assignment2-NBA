package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Player;
import model.Players;
import model.Team;
import model.Teams;

public class ManageTeamController extends Controller<Teams> {
    public Teams getTeams() {
        return this.model;
    }

    @FXML private TableView manageTeamTV;
    @FXML private TextField nameTf;
    @FXML private Button manageButton;
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;

    @FXML
    public void initialize() {
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
        nameTf.setText(TeamsController.teamName);

    }

    private ObservableList<Player> parsePlayers() {
        ObservableList<Player> playerList = FXCollections.observableArrayList();
        Players players = getTeams().getTeam(TeamsController.teamName).getPlayers();
        int i = 0;
        for (Player player : players.getPlayersList()) {
            String name = player.getName();
            Double credit = player.getCredit();
            Integer age = player.getAge();
            Integer No = player.getNo();
            playerList.add(i, new Player(name, credit, age, No));
        }
        return playerList;
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

    }

    @FXML
    public void delete() {

    }
}
