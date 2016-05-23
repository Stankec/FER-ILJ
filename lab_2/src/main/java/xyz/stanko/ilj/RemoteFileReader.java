package xyz.stanko.ilj;

import java.net.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

public class RemoteFileReader {
  private String url;
  private String username;
  private String password;

  public RemoteFileReader(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public String read() {
    // Create a trust manager that does not validate certificate chains
    TrustManager[] trustAllCerts = new TrustManager[]{
      new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return null;
        }
        public void checkClientTrusted(
          java.security.cert.X509Certificate[] certs, String authType) {
        }
        public void checkServerTrusted(
          java.security.cert.X509Certificate[] certs, String authType) {
        }
      }
    };

    // Install the all-trusting trust manager
    try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } catch (Exception e) {
    }

    URL website = null;
    try {
      website = new URL(url);
    }
    catch(MalformedURLException e) {
      System.out.println("ERROR: Malformed url");
      System.exit(1);
    }

    URLConnection connection = null;
    try {
      connection = website.openConnection();
    }
    catch(IOException e) {
      System.out.println("ERROR: IO eception while opening connection");
      System.exit(1);
    }

    try {
      byte[] authorization = (username+":"+password).getBytes("UTF-8");
      String encoded = DatatypeConverter.printBase64Binary(authorization);
      connection.setRequestProperty("Authorization", "Basic "+encoded);
    }
    catch(UnsupportedEncodingException e) {
      System.out.println("ERROR: Unknown encoding");
      System.exit(1);
    }


    BufferedReader in = null;
    try {
      in = new BufferedReader(
      new InputStreamReader(
        connection.getInputStream()
      )
    );
    }
    catch(IOException e) {
      System.out.println("ERROR: IO eception while building xml");
      e.printStackTrace();
      System.exit(1);
    }

    StringBuilder response = new StringBuilder();
    String inputLine;

    try {
      while ((inputLine = in.readLine()) != null)
          response.append(inputLine);
    }
    catch(IOException e) {
      System.out.println("ERROR: IO eception while building xml");
      System.exit(1);
    }

    try {
    in.close();
    }
    catch(IOException e) {
      System.out.println("ERROR: IO eception while closing stream");
      System.exit(1);
    }

    return response.toString();
  }
}
