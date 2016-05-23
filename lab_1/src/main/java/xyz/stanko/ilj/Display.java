package xyz.stanko.ilj;

public class Display {
  private int failCounter;
  private int maxFailCount;

  public Display() {
    failCounter = 0;
    maxFailCount = 3;
  }

  public void promptEntry() {
    printMessage("Enter your pin number");
  }

  public void printResult(boolean pinValid) {
    if (pinValid) {
      resetFailCounter();
      printMessage("✅  The entered pin appears to be valid!");
    } else {
      incrementFailCounter();
      printMessage("❌  The pin you entered is invalid!");
    }
  }

  private boolean resetFailCounter() {
    if (maxFailCountReached()) {
      return false;
    }

    failCounter = 0;
    return true;
  }

  private int incrementFailCounter() {
    failCounter++;
    return failCounter;
  }

  private boolean maxFailCountReached() {
    if (failCounter > maxFailCount) {
      return true;
    }

    return false;
  }

  private void printMessage(String message) {
    if (maxFailCountReached()) {
      return;
    }

    System.out.println(message);
  }
}
