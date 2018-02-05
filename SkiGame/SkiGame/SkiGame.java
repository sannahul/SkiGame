/* Main class to run the game. Defines the level buttons
   and passes them to the menu object */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class SkiGame extends Application {
  private Gameplay gp;
  private Menu menu;
  private List<Button> btns;

  // Set level buttons' names and actions
  private void setButtons(Stage stage) {
    btns = new ArrayList<Button>();
    Button btn1 = new Button("EASY");
    // Create a gameplay object with 3 obstacles
    btn1.setOnAction(e -> {
      gp = new Gameplay(3, 800, 600, "pengu.png", "tree.png");
      stage.hide();
      gp.start(stage);
    });
    btns.add(btn1);
    Button btn2 = new Button("MEDIUM");
    // Create a gameplay object with 5 obstacles
    btn2.setOnAction(e -> {
      gp = new Gameplay(5, 800, 600, "pengu.png", "tree.png");
      stage.hide();
      gp.start(stage);
    });
    btns.add(btn2);
    Button btn3 = new Button("HARD");
    // Create a gameplay object with 7 obstacles
    btn3.setOnAction(e -> {
      gp = new Gameplay(7, 800, 600, "pengu.png", "tree.png");
      stage.hide();
      gp.start(stage);
    });
    btns.add(btn3);
    for(Button b : btns) {
      b.setStyle("-fx-font: bold 16 arial; -fx-base: #80bfff;");
    }
  }

  @Override
  public void start(Stage stage) {
    setButtons(stage);
    menu = new Menu(btns, "Play Ski Game!", 800, 600, "mountain.jpg");
    menu.start(stage);
  }
}
