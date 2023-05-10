package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.*;

public class TeamsRoundController extends Controller<Season> {

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


    @FXML
    public void initialize() {
        System.out.println(this.model);
    }

}


