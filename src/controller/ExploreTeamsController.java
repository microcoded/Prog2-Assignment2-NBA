package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Association;
import model.Teams;

public class ExploreTeamsController extends Controller<Teams> {

    @FXML
    private Button teamsMenuButton;

    @FXML
    private Button viewPlayersButton;

    @FXML
    private Button closeButton;

    @FXML
    private GridPane buttonGrid;
    
    public Teams getTeams(){
        return this.model;
    }
    
    @FXML
    public void teamsMenu() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            stage.setResizable(false);
            ViewLoader.showStage(getTeams(), "/view/TeamsTable.fxml", "Teams Menu", stage);
        } catch (IOException ex) {
            Logger.getLogger(ExploreTeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void viewPlayers() {
      try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            stage.setResizable(false);
            ViewLoader.showStage(getTeams(), "/view/PlayersView.fxml", "Players", stage);
        } catch (IOException ex) {
            Logger.getLogger(ExploreTeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
