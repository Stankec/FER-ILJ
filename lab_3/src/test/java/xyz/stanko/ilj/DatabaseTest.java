import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import xyz.stanko.ilj.Database;

public class DatabaseTest {
  @Test
  public void registerPinRegistersAStringPin() {
    Database database = new Database();

    boolean pinAdded = database.registerPin("1234");
    assertEquals(true, pinAdded);
  }

  @Test
  public void checkPinReturnsTrueIfPinWasRegisterdBeforehand() {
    Database database = new Database();

    database.registerPin("1234");
    assertTrue(database.checkPin("1234"));
  }

  @Test
  public void checkPinReturnsFalseIfPinWasntRegisterdBeforehand() {
    Database database = new Database();

    database.registerPin("4321");
    assertFalse(database.checkPin("1234"));
  }
}
