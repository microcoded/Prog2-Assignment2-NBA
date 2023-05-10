package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Player;
import model.Teams;

public class ViewPlayersController extends Controller<Teams> {

    @FXML
    private TextField levelTf;

    @FXML
    private TextField nameTf;

    @FXML
    private TextField fromTf;

    @FXML
    private TextField toTf;

    @FXML
    private TableView playersTV;

    @FXML
    private Button closeBtn;

    private Teams getTeams() {
        return this.model;
    }

    @FXML
    public void initialize() {
        ObservableList<Player> players = getTeams().allPlayersList();
        playersTV.setItems(players);

        // Don't allow clicks because they're not needed
        playersTV.setOnMouseClicked(e -> playersTV.getSelectionModel().select(null));

        ObservableList<Player> filteredPlayers = FXCollections.observableArrayList();
        levelTf.textProperty().addListener((o, oL, nL) -> filter(players, filteredPlayers));
        nameTf.textProperty().addListener((o, oN, nN) -> filter(players, filteredPlayers));
        fromTf.textProperty().addListener((o, oF, nF) -> filter(players, filteredPlayers));
        toTf.textProperty().addListener((o, oF, nF) -> filter(players, filteredPlayers));
    }

    private void filter(ObservableList<Player> players, ObservableList<Player> filteredPlayers) {
        String levelInput = levelTf.getText().toLowerCase();
        String nameInput = nameTf.getText().toLowerCase();
        int fromAge = 0;
        int toAge = Integer.MAX_VALUE;

        try {
            if (!fromTf.getText().isEmpty()) fromAge = Integer.parseInt(fromTf.getText());
            if (!toTf.getText().isEmpty()) toAge = Integer.parseInt(toTf.getText());
        } catch (NumberFormatException e) {
            // Do nothing
        }

        filteredPlayers.clear();

        for (Player player : players) {
            boolean levelMatch = levelInput.isEmpty() || player.getLevel().toLowerCase().contains(levelInput);
            boolean nameMatch = nameInput.isEmpty() || player.getName().toLowerCase().contains(nameInput);
            boolean ageIgnore = fromAge == 0 && toAge == 0;
            boolean ageMatch = ageIgnore || player.getAge() >= fromAge && player.getAge() <= toAge;
            if (levelMatch && nameMatch && ageMatch) {
                filteredPlayers.add(player);
            }
        }

        playersTV.setItems(filteredPlayers);
    }


    @FXML
    public void close() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

}

