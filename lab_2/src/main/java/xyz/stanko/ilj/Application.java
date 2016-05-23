package xyz.stanko.ilj;

import java.util.*;

public class Application {
  private RemoteFileReader trainTransitXMLReader;
  private RemoteFileReader personXMLReader;
  private String [] args;
  private int tripNumber;
  private int month;
  private String username;
  private String password;

  public Application(String [] args) {
    this.args = args;
  }

  public void run() {
    if (this.args.length < 4) {
      System.out.println("Pass USERNAME, PASSWORD, TRIP_NUMBER, MONTH");
      System.exit(1);
    }

    this.username = this.args[0];
    this.password = this.args[1];
    this.tripNumber = Integer.parseInt(this.args[2]);
    this.month = Integer.parseInt(this.args[3]);

    this.trainTransitXMLReader = new RemoteFileReader(
      "https://ilj.tel.fer.hr/lab2/PutovanjeVlakom",
      this.username,
      this.password
    );

    this.personXMLReader = new RemoteFileReader(
      "https://ilj.tel.fer.hr/lab2/Osobe",
      this.username,
      this.password
    );

    TrainTransitXMLParser trainTransitXMLParser = new TrainTransitXMLParser(
      trainTransitXMLReader.read(), this.tripNumber, this.month
    );
    List<String> ids = trainTransitXMLParser.parsePeopleIDs();

    PersonXMLBuilder xml = new PersonXMLBuilder(
      this.personXMLReader.read(),
      ids
    );

    String result = xml.build();

    System.out.println("||---- RESULTING XML ----||");
    System.out.println(result);
  }
}
