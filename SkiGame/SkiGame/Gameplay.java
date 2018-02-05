/* Class where all the game logic happens. Takes level
   (number of obstacles), size of the window and image 
   file names for player Sprite and obstacles as arguments.
   Creates a new level, sets the player Sprite and handles
   the game logic. */

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
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.util.*;

public class Gameplay extends Application {

  private int level;
  private int width;
  private int height;
  private String playerImage;
  private String obstImage;
  private Pane root;
  private Scene gameScene;
  private Canvas canvas;
  private GraphicsContext gc;
  private LongValue lastNanoTime;
  private Level background;
  private Movable player;
  private ArrayList<String> input;
  private PauseTransition pause;

  public Gameplay(int level, int width, int height, String playerImage, String obstImage) {
    this.level = level;
    this.width = width;
    this.height = height;
    this.playerImage = playerImage;
    this.obstImage = obstImage;
    root = new Pane();
    gameScene = new Scene(root);
    background = new Level(level, width, height, obstImage);
    canvas = new Canvas(width, height);
    gc = canvas.getGraphicsContext2D();
    player = new Movable(width/2-50, 0);
    input = new ArrayList<String>();
    lastNanoTime = new LongValue(System.nanoTime());
    pause = new PauseTransition(Duration.seconds(3));
  }

  // Set the key events
  private void setEvents() {
    gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent e) {
        String code = e.getCode().toString();
        if (!input.contains(code)) input.add(code);
      }
    });
    gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
      }
    });
  }

  // Set player Sprite's image and velocity
  private void setPlayer() {
    player.setImage(playerImage);
    player.setVelocity(0, 70);
  }

  // Resets the canvas as blank
  public void reset() {
    gc.setFill(Color.WHITE);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
  }

  // Set losing scene
  private void lost(Stage stage) {
    gc.setFill(Color.RED);
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);
    Font theFont = Font.font("Arial", FontWeight.BOLD, 56);
    gc.setFont(theFont);
    gc.fillText("GAME OVER", width/4+50, height/2);
    gc.strokeText("GAME OVER", width/4+50, height/2);
        
    stage.show();
  }

  // Set winning scene
  private void won(Stage stage) {
    gc.setFill(Color.BLUE);
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);
    Font theFont = Font.font("Arial", FontWeight.BOLD, 56);
    gc.setFont(theFont);
    gc.fillText("YOU WIN!", width/4+50, height/2);
    gc.strokeText("YOU WIN!", width/4+50, height/2);
        
    stage.show();
  }

  @Override
  public void start(Stage stage) {
  
    stage.setScene(gameScene);
    root.getChildren().add(canvas);
    background.set();
    setPlayer();
    setEvents();
    pause.setOnFinished(event -> stage.hide());

    new AnimationTimer() {
            
      public void handle(long currentNanoTime) {

        // Calculate time since last update
        double elapsedTime = (currentNanoTime - lastNanoTime.getValue()) / 1000000000.0;
        lastNanoTime.setValue(currentNanoTime);

        if(input.contains("LEFT")) player.addVelocity(-10, 0);
        if(input.contains("RIGHT")) player.addVelocity(10, 0);
 
        reset();
        player.render(gc);
        background.render(gc);
        // Player has hit a tree
        if(background.intersects(player)) {
          stop();
          lost(stage);
          // Wait a few seconds before closing
          pause.play();
        }
        player.update(elapsedTime);
        // Player has reached the end
        if(player.positionY > canvas.getHeight()) {
          stop();
          won(stage);
          // Wait a few seconds before closing
          pause.play();
        }
      }
    }.start();
         
    stage.show();
  }
}
