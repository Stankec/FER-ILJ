package xyz.stanko.ilj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
  private Database database;
  private Display display;

  public Application() {
    database = new Database();
    display = new Display();
  }

  public void run() {
    database.registerPin("1234");

    while(true) {
      challange();
    }
  }

  private void challange() {
    display.promptEntry();
    try {
      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));
      String input;

      while((input = br.readLine()) != null) {
        boolean pinValid = database.checkPin(input);
        display.printResult(pinValid);
        return;
      }
    }
    catch(IOException io) {
      io.printStackTrace();
    }
  }
}
