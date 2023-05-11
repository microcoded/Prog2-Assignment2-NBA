package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.*;

public class CurrentRoundTeamsController extends Controller<Season> {

    @FXML
    private Label roundLbl;

    @FXML
    private TableView roundTv;

    @FXML
    private TableColumn<Game, String> team1Col;

    @FXML
    private TableColumn<String, String> vsCol;

    @FXML
    private TableColumn<Game, String> team2Col;


    public Season getSeason() {
        return this.model;
    }

    @FXML
    public void initialize() {
        roundLbl.setText("Round: " + (getSeason().round() + 1));

        roundTv.setItems(getSeason().getCurrentSchedule());
        team1Col.setCellValueFactory(v -> v.getValue().team1());
        team2Col.setCellValueFactory(v -> v.getValue().team2());
        ObservableValue<String> vs = new SimpleStringProperty("VS");
        vsCol.setCellValueFactory(s -> vs);
    }

    @FXML
    public void close() {
        stage.close();
    }
  
}







