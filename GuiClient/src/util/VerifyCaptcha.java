package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class VerifyCaptcha {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
  
  public static String verify(String response) throws IOException, JSONException {
	  if (response == null || response.length() < 1) {
			return "false";
		}	  
		String secret = "6LdhxkobAAAAAGylbMtj51mansYmYVShl5yKzdxM";
	    JSONObject json = readJsonFromUrl("https://www.google.com/recaptcha/api/siteverify?secret=" + secret + "&response=" + response);
	    System.out.println(json.toString());
	    return json.get("success").toString();
  }
}
