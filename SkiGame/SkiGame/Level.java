/* Level class defining the positions of obstacle
   Sprites. Variable num defines the number of obstacles,
   width and height the size of the window and imagefile
   the image used for the obstacle Sprites. */

import java.io.*;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

public class Level {

  private ArrayList<Sprite> obstacles;
  private int num;
  private int height;
  private int width;
  private String imagefile;

  public Level(int num, int width, int height, String imagefile) {
    this.num = num;
    this.width = width;
    this.height = height;
    this.imagefile = imagefile;
    obstacles = new ArrayList<Sprite>();
  }

  // Set Sprites as obstacles
  public void set() {
    // Set left border obstacles
    double px = -5;
    double py = 0; 
    for (int i = 0; i < height / 6; i++) {
      Sprite s = new Sprite(px, py);
      s.setImage(imagefile);
      obstacles.add(s);
      py += s.getHeight() / 2;
    }

    // Set right border obstacles    
    px = width - 80;
    py = 0;
    for (int i = 0; i < height / 6; i++) {
      Sprite s = new Sprite(px, py);
      s.setImage(imagefile);
      obstacles.add(s);
      py += s.getHeight() / 2;
    }

    // Set random obstacles in the middle
    int maxX = width - 150;
    int maxY = height - 100;
    int minX = 100;
    int minY = 150;
    Random rand = new Random();
    for (int i = 0; i < num; i++) {
      px = rand.nextInt((maxX - minX) + 1) + minX;
      py = rand.nextInt((maxY - minY) + 1) + minY;
      Sprite s = new Sprite(px, py);
      s.setImage(imagefile);
      obstacles.add(s);
    }
  }

  // Render all obstacles
  public void render(GraphicsContext gc) {
    for (Sprite s : obstacles) {
      s.render(gc);
    }
  }

  // Check if any obstacle Sprites collide with the Sprite given as argument
  public boolean intersects(Sprite s) {
    for (Sprite o : obstacles) {
      if(o.intersects(s)) return true;
    }
    return false;
  }

  // Testing
  private void test() {
    assert(num == 5);
    assert(width == 800);
    assert(height == 600);
    assert(imagefile.equals("tree.png"));
    assert(num != 6);
    assert(width != 810);
    assert(height != 500);
    assert(!imagefile.equals("pengu.png"));
  }

  public static void main(String[] args) {
    boolean testing = false;
    assert(testing = true);
    Level l = new Level(5, 800, 600, "tree.png");
    l.test();
    System.out.println("All tests passed.");
  }
}
