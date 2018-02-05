/* Menu class showing the title of the game
   and buttons to choose level. */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import java.util.*;
import javafx.scene.control.Button;

public class Menu extends Application {
  private Scene scene;
  private Pane root;
  private Canvas canvas;
  private GraphicsContext gc;
  private List<Button> buttons;
  private String title;
  private int width;
  private int height;
  private String imagefile;

  public Menu(List<Button> btns, String title, int width, int height, String imagefile) {
    this.title = title;
    this.width = width;
    this.height = height;
    this.imagefile = imagefile;
    buttons = new ArrayList<Button>();
    for(Button b : btns) buttons.add(b);
  }

  // Set the menu screen
  private void set() {
    root = new Pane();
    scene = new Scene(root);
    canvas = new Canvas(width, height);
    gc = canvas.getGraphicsContext2D();

    root.getChildren().add(canvas);
    Image mountain = new Image(imagefile, width, height, false, false);
    gc.drawImage(mountain, 0, 0);

    // Place buttons
    int p = width / (buttons.size() + 1);
    int i = p;
    for(Button b : buttons) {
      b.setLayoutX(i);
      b.setLayoutY(height / 3 * 2);
      root.getChildren().add(b);
      i += p;
    }

    // Place the title text
    gc.setFill(Color.BLUE);
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);
    Font theFont = Font.font("Arial", FontWeight.BOLD, 48);
    gc.setFont(theFont);
    gc.fillText(title, width / 4, height / 3);
    gc.strokeText(title, width / 4, height / 3);
  }

  @Override
  public void start(Stage stage) {
    set();
    stage.setScene(scene);
    stage.show();
  }
}
