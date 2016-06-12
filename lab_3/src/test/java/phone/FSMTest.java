import org.junit.*;
import static org.easymock.EasyMock.*;
import phone.*;

public class FSMTest {
  private AppClass mock;
  private AppClassContext fsm;

  @Before
  public void setUp() throws Exception {
    mock = createStrictMock(AppClass.class);
    fsm = new AppClassContext(mock);
  }

  @Test
  public void ring() {
    mock.ring();
    replay(mock);
    fsm.zvoni();
  }

  @Test
  public void call() {
    mock.call();
    replay(mock);
    fsm.zoviBrojN();
  }

  @Test
  public void answer() {
    mock.ring();
    mock.answer();
    replay(mock);
    fsm.zvoni();
    fsm.javljanje();
  }

  @Test
  public void nonexist() {
    mock.call();
    mock.nonexist();
    replay(mock);
    fsm.zoviBrojN();
    fsm.neispravanBroj();
  }

  @Test
  public void occupied() {
    mock.call();
    mock.analyze();
    mock.occupied();
    replay(mock);
    fsm.zoviBrojN();
    fsm.ispravanBroj();
    fsm.zauzeto();
  }

  @Test
  public void analyze() {
    mock.call();
    mock.analyze();
    replay(mock);
    fsm.zoviBrojN();
    fsm.ispravanBroj();
  }

  @Test
  public void analyzei2() {
    mock.call();
    mock.analyze();
    mock.analyze();
    replay(mock);
    fsm.zoviBrojN();
    fsm.ispravanBroj();
    fsm.zvoni();
  }

  @Test
  public void talk() {
    mock.call();
    mock.analyze();
    mock.analyze();
    mock.talk();
    replay(mock);
    fsm.zoviBrojN();
    fsm.ispravanBroj();
    fsm.zvoni();
    fsm.javljanje();
  }

  @Test
  public void onhook() {
    mock.call();
    mock.analyze();
    mock.analyze();
    mock.talk();
    mock.onhook();
    replay(mock);
    fsm.zoviBrojN();
    fsm.ispravanBroj();
    fsm.zvoni();
    fsm.javljanje();
    fsm.gotovo();
  }

  @Test
  public void reverseAnswer() {
    mock.call();
    mock.analyze();
    mock.analyze();
    mock.talk();
    mock.answer();
    replay(mock);
    fsm.zoviBrojN();
    fsm.ispravanBroj();
    fsm.zvoni();
    fsm.javljanje();
    fsm.javljanje();
  }

  @After
  public void tearDown() {
    verify(mock);
  }
}
