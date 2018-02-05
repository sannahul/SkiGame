/* Basic game object that has coordinates, image and
   size (height and width). User can set the image
   and the position, render the Sprite to the screen
   and check if it collides with another Sprite */

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
	
public class Sprite {

  protected Image image;
  protected double positionX;
  protected double positionY;
  protected double width;
  protected double height;
	
  public Sprite(double x, double y) {
    positionX = x;
    positionY = y;    
  }
	
  private void setImage(Image i) {
    image = i;
    width = i.getWidth();
    height = i.getHeight();
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  // Take filename as argument, create a new image and set it as the Sprite image
  public void setImage(String filename) {
    Image i = new Image(filename);
    setImage(i);
  }

  // Render Sprite to screen	
  public void render(GraphicsContext gc) {
    gc.drawImage(image, positionX, positionY);
  }

  // Return the boundary coordinates of the Sprite	
  public Rectangle2D getBoundary() {
    return new Rectangle2D(positionX, positionY, width, height);
  }

  // Check if Sprite collides with another Sprite	
  public boolean intersects(Sprite s){
    return s.getBoundary().intersects(this.getBoundary());
  }

  // Testing
  private void test() {
    assert(positionX == 100);
    assert(positionY == 400);
    assert(positionX != 150);
    assert(positionY != 680);
    Sprite s2 = new Sprite(15, 89);
    assert(s2.positionX == 15);
    assert(s2.positionY == 89);
    assert(s2.positionX != 16);
    assert(s2.positionY != 88);
  }

  public static void main(String[] args) {
    boolean testing = false;
    assert(testing = true);
    Sprite s = new Sprite(100, 400);
    s.test();
    System.out.println("All tests passed.");
  } 
}
