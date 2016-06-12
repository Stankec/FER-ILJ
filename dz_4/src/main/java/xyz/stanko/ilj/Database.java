package xyz.stanko.ilj;
import java.util.ArrayList;

public class Database {
  private ArrayList<String> store;

  public Database() {
    store = new ArrayList<String>();
  }

  public boolean registerPin(String pin) {
    return store.add(pin);
  }

  public boolean checkPin(String pin) {
    return store.contains(pin);
  }
}
