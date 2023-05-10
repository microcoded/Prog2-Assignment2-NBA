package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.*;

public class TeamsRoundController extends Controller<Season> {
    Season season = getSeason();

    @FXML
    private TableView roundTv;

    @FXML
    private Label roundLbl;

    @FXML
    private Button addBtn;

    @FXML
    private ListView teamsLv;

    @FXML
    private Button arrangeBtn;

    public Season getSeason() {
//        return this.model;
        return SeasonController.season;
    }

    @FXML
    public void initialize() {
        int round = getSeason().round();
        round++;
        season.addTeams(season.getCurrentTeams());
        roundLbl.setText("Round: " + round);
    }

}



