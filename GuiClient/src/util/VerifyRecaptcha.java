package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL; 
import javax.json.Json; 
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class VerifyRecaptcha {
	
    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
    public static final String secret = "6LdhxkobAAAAAGylbMtj51mansYmYVShl5yKzdxM";
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static boolean verify(String gRecaptchaResponse) throws IOException {			
		if (gRecaptchaResponse == null) {
			return false;
		}
	
		
		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			
			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			System.out.println(con.getOutputStream() + " sax");
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			System.out.println(con.getResponseCode() + " sax");
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			System.out.println(response + " sax");
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}			
			in.close();
			
			// print result 
			System.out.println(response.toString());
			
			//parse JSON response and return 'success' value
			JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			System.out.println(jsonObject + " sax");
			return jsonObject.getBoolean("success");
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
