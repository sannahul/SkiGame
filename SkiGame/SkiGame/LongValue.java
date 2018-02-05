/* Adapted from the LongValue class created by Lee Stemkoski
   here: https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development
   LongValue object is used in AnimationTimer's handle method to calculate
   time since last update. */

public class LongValue {
  private long value;
    
  public LongValue(long i) {
    value = i;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long i) {
    value = i;
  }

  // Testing
  private void test() {
    assert(value == 10);
    setValue(70);
    assert(value == 70);
    assert(value != 10);
    setValue(55);
    assert(value == 55);
    assert(value != 70);
  }

  public static void main(String[] args) {
    boolean testing = false;
    assert(testing = true);
    LongValue l = new LongValue(10);
    l.test();
    System.out.println("All tests passed.");
  } 
}
