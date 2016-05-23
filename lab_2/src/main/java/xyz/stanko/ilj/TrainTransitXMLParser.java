package xyz.stanko.ilj;

import java.util.*;

public class TrainTransitXMLParser {
  private String rawXML;
  private int tripCount;
  private int month;

  public TrainTransitXMLParser(String rawXML, int tripCount, int month) {
    this.rawXML = rawXML;
    this.tripCount = tripCount;
    this.month = month;
  }

  public List<String> parsePeopleIDs() {
    XQuerier xQuerier = new XQuerier(this.rawXML, query());
    String resultXML = xQuerier.execute();

    resultXML = resultXML.replace("<oib/>", "");
    resultXML = resultXML.replace("<oib>", "");


    List<String> ids = new ArrayList<String>(Arrays.asList(resultXML.split("</oib>")));

    return ids;
  }

  private String query() {
    return "declare variable $doc external;\n" +
      "for $item in $doc//items[" +
        "count(" +
          "index-of(" +
            "$doc//mjesecPutovanja/text(), mjesecPutovanja/text()" +
          ")" +
        ") = " + tripCount + "]\n" +
      "return if ($item/mjesecPutovanja/text() = \"" + month + "\")\n" +
      "then <oib>{$item/id/oib/text()}</oib>\n" +
      "else <oib></oib>\n";
  }
}
