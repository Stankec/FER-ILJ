package xyz.stanko.ilj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import javax.xml.xquery.XQStaticContext;

import oracle.xml.scalable.FilePageManager;
import oracle.xml.xquery.OXQDataSource;
import oracle.xml.xquery.OXQPreparedExpression;
import oracle.xml.xquery.OXQView;

import javax.xml.transform.URIResolver;
import net.sf.saxon.Configuration;
import net.sf.saxon.s9api.*;

public class XQuerier {
  private String xml;
  private String query;

  public XQuerier(String xml, String query) {
    this.xml = xml;
    this.query = query;
  }

  public String execute() {
    String resultXML = null;

    try {
      OXQDataSource ds = new OXQDataSource();
      XQConnection con = ds.getConnection();

      XQStaticContext ctx = con.getStaticContext();
      ctx.setBindingMode(XQConstants.BINDING_MODE_DEFERRED);
      con.setStaticContext(ctx);

      XQPreparedExpression expr = con.prepareExpression(this.query);
      expr.bindDocument(new QName("doc"), this.xml, null, null);

      XQSequence result = expr.executeQuery();
      resultXML = result.getSequenceAsString(null);

      result.close();
      expr.close();
      con.close();
    }
    catch(XQException e) {
      System.out.println("ERROR: XQuery error");
      e.printStackTrace();
      System.exit(1);
    }

    return resultXML;
  }
}
