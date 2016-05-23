package xyz.stanko.ilj;

import java.util.*;

public class PersonXMLBuilder {
  private String xml;
  private List<String> ids;

  public PersonXMLBuilder(String xml, List<String>ids) {
    this.xml = xml;
    this.ids = ids;
  }

  public String build() {
    XQuerier xQuerier = new XQuerier(this.xml, query());
    String resultXML = xQuerier.execute();

    return "<listWrapper>" + resultXML + "</listWrapper>";
  }

  private String query() {
    String ids = join(quoteIDs(this.ids), ", ");

    return "declare variable $doc external;\n" +
    "for $item in $doc//items\n" +
    "return if (index-of((" + ids + "), $item/oib/text()))\n" +
    "then $item\n" +
    "else ()\n";
  }

  private String join(List<String> list, String delim) {
    StringBuilder sb = new StringBuilder();
    String loopDelim = "";

    for(String s : list) {
        sb.append(loopDelim);
        sb.append(s);
        loopDelim = delim;
    }

    return sb.toString();
  }

  private List<String> quoteIDs(List<String> list) {
    List<String> newList = new ArrayList<String>();

    for(Iterator<String> i = list.iterator(); i.hasNext(); ) {
      String item = i.next();
      newList.add("\"" + item + "\"");
    }

    return newList;
  }
}
