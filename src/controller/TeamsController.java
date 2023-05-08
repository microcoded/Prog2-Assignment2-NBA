package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;

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

    @FXML
    private TableColumn<Team, Double> age;

    public static String teamName;

    public Teams getTeams() {
        return this.model;
    }

    @FXML
    public void initialize() {
        teamsTV.setItems(parseTeams());

        // Select no row by default
        teamsTV.getSelectionModel().select(null);
        teamsTV.getProperties().put("selectFocusOnGrain", false);

        // Disable manage and delete buttons when nothing is selected
        // Disable add button when something is selected
        teamsTV.getSelectionModel().selectedItemProperty().addListener(
                (o, oldTeam, newTeam) -> {
                    manageButton.setDisable(teamsTV.getSelectionModel().selectedItemProperty() == null);
                    deleteButton.setDisable(teamsTV.getSelectionModel().selectedItemProperty() == null);
                    addButton.setDisable(teamsTV.getSelectionModel().selectedItemProperty() != null);
                    teamName = selectedTeam();
                }
        );

        // Deselect cell if clicked twice
        teamsTV.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                deselect();
            }
        });
    }

    private ObservableList<Team> parseTeams() {
        ObservableList<Team> teamList = getTeams().currentTeams();
        players.setCellValueFactory(count -> count.getValue().countPlayerProperty().asObject());
        credit.setCellValueFactory(avgCredit -> avgCredit.getValue().countAvgCreditProperty().asObject());
        age.setCellValueFactory(avgAge -> avgAge.getValue().countAvgAgeProperty().asObject());
        return teamList;
    }

    public void refreshTable() {
        teamsTV.setItems(parseTeams());
        deselect();
    }

    @FXML
    public void add() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            stage.setResizable(false);
            stage.setOnHidden(event -> refreshTable());
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deselect() {
        teamsTV.getSelectionModel().select(null);
        manageButton.setDisable(true);
        deleteButton.setDisable(true);
        addButton.setDisable(false);
    }

    public String selectedTeam() {
        Team selectedTeam = (Team) teamsTV.getSelectionModel().getSelectedItem();
        if (selectedTeam == null) {
            return teamName;
        }
        return selectedTeam.getName();
    }

    @FXML
    public void manage() {
        try {
            String name = selectedTeam();
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            stage.setResizable(false);
            stage.setOnHidden(event -> refreshTable());
            ViewLoader.showStage(getTeams(), "/view/ManageTeamView.fxml", "Managing Team: " + name, stage);
            // Deselect buttons
            deselect();
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void delete() {
        getTeams().remove(getTeams().getTeam(selectedTeam()));
        deselect();
    }

    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}

