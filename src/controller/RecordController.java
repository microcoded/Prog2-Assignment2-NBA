package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Record;
import model.Season;

public class RecordController extends Controller<Season> {

    @FXML
    private TableView<Record> recordTv;

    @FXML
    private TableColumn<Record, Integer> roundCol;

    @FXML
    private TableColumn<Record, Integer> gameCol;

    @FXML
    private TableColumn<Record, String> winTeamCol;

    @FXML
    private TableColumn<Record, String> loseTeamCol;

    public Season getSeason() {
        return this.model;
    }

    @FXML
    public void initialize() {
        recordTv.setItems(getSeason().record());
        recordTv.getSelectionModel().select(null);
    }

    @FXML
    public void close() {
        stage.close();
    }
}







