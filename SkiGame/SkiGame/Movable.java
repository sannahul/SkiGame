/* An extension of Sprite class with velocity */
	
public class Movable extends Sprite {
    
  private double velocityX;
  private double velocityY;
	
  public Movable(double x, double y) {
    super(x, y);
    velocityX = 0;
    velocityY = 0;
  }
	
  public void setVelocity(double x, double y) {
    velocityX = x;
    velocityY = y;
  }
	
  public void addVelocity(double x, double y) {
    velocityX += x;
    velocityY += y;
  }

  // Update the position based on current velocity and elapsed time	
  public void update(double time) {
    double x = positionX += velocityX * time;
    double y = positionY += velocityY * time;
    positionX = x;
    positionY = y;
  }

  // Testing
  private void test() {
    assert(positionX == 10);
    assert(positionY == 40);
    assert(velocityX == 0);
    assert(velocityY == 0);
    assert(velocityX != 10);
    setVelocity(20, 0);
    assert(velocityX == 20);
    assert(velocityY == 0);
    update(2);
    assert(positionX == 50);
    assert(positionY == 40);
    addVelocity(0, 50);
    assert(velocityX == 20);
    assert(velocityY == 50);
    update(1);
    assert(positionX == 70);
    assert(positionY == 90);
  }

  public static void main(String[] args) {
    boolean testing = false;
    assert(testing = true);
    Movable m = new Movable(10, 40);
    m.test();
    System.out.println("All tests passed.");
  } 
}
