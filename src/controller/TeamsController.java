package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Association;
import model.Players;
import model.Team;
import model.Teams;

import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamsController extends Controller<Teams> {
    @FXML
    private Button addButton;

    @FXML
    private Button manageButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button closeButton;

    @FXML
    private GridPane buttonGrid;

    @FXML
    private TableView teamsTV;

    @FXML
    private TableColumn<Team, String> name;

    @FXML
    private TableColumn<Team, Integer> players;

    @FXML
    private TableColumn<Team, Double> credit;

    @FXML TableColumn<Team, Double> age;

    public Teams getTeams() {
        return this.model;
    }

    public class TeamInList {
        String name;
        Integer playerCount;
        Double avgCredit;
        Double age;
        public TeamInList(String name, Integer playerCount, Double avgCredit, Double age) {
            this.name = name;
            this.playerCount = playerCount;
            this.avgCredit = avgCredit;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getPlayers() {
            return playerCount;
        }

        public String getCredit() {
            return formatDouble(avgCredit);
        }

        public String getAge() {
            return formatDouble(age);
        }

        private String formatDouble(double value) {
            return new DecimalFormat("#.00").format(value);
        }
    }
    @FXML
    public void initialize() {
        teamsTV.setItems(parseTeams());

        // Select no row by default
        teamsTV.getSelectionModel().select(null);

        // Disable  buttons when no item is selected
        teamsTV.getSelectionModel().selectedItemProperty().addListener(
                (o, oldTeam, newTeam) -> {
                    manageButton.setDisable(teamsTV.getSelectionModel().selectedItemProperty() == null);
                    deleteButton.setDisable(teamsTV.getSelectionModel().selectedItemProperty() == null);
                }
        );

    }

    private ObservableList<TeamInList> parseTeams() {
        ObservableList<TeamInList> teamsList = FXCollections.observableArrayList();
        int i = 0;
        for (Team team : getTeams().currentTeams()) {
            String name = team.getName();
            Integer players = team.getPlayers().getPlayersList().size();
            Double credit = team.countAvgCreditProperty().get();
            Double age = team.countAvgAgeProperty().get();
            teamsList.add(i, new TeamInList(name, players, credit, age));
            i++;
        }
        return teamsList;
    }

    @FXML
    public void add() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            stage.setResizable(false);
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deselect() {
        teamsTV.getSelectionModel().select(null);
        manageButton.setDisable(true);
        deleteButton.setDisable(true);
    }
    @FXML
    public void manage() {
        try {
            TeamInList selectedTeam = (TeamInList) teamsTV.getSelectionModel().getSelectedItem();
            String name = selectedTeam.getName();

            // Might need https://stackoverflow.com/questions/14187963/passing-parameters-javafx-fxml

            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            stage.setResizable(false);
            ViewLoader.showStage(getTeams(), "/view/ManageTeamView.fxml", "Managing Team: " + name, stage);
            // Deselect buttons
            deselect();
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void delete() {
        deselect();
    }

    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}

