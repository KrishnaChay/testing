package com.usmanhussain.cayenne.utils;
import org.json.JSONObject;
import org.junit.Assert;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * Standalone Java program to Activate the user without accessing DynamoDB.
 * <p>
 * CAUTION:
 * Make sure your email id has the domain "@qa.dlgdigitalapi.com" while registering,
 * else due to too many email delivery failures, AWS has chances of being shutdown by Amazon.
 * Approval for exception has been obtained from Amazon AWS team only for the domain "@qa.dlgdigitalapi.com".
 */

public class SSO {

    private static String baseURL = "https://digidev2.directline.com";
    private static String brand = "directline";

    public static void main(String[] args) {
        try {
            activateOnly("useractivation@qa.dlgdigitalapi.com"); // double check the email domain to match "@qa.dlgdigitalpi.com"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void activateOnly(String userName) throws Exception {
        String[] cookieData = initialize(baseURL);
        JsonObject userDetails = getUserDetails(userName, cookieData);
        activateUser(userDetails, cookieData);

    }

    public static String[] initialize(String baseURL) throws Exception {
        String xsrfToken = null;
        String uncutxsrf = null;
        String uncutsession = null;
        String initializeURL = baseURL + "/dlg-gateway-routing/sso/metadata/" + brand + "/initialize";
        URL connectionObject = new URL(initializeURL);
        HttpURLConnection req = (HttpURLConnection) connectionObject.openConnection();
        req.setRequestMethod("POST");
        req.connect();
        String ssoJourneyContextId = req.getHeaderField("sso-journey-context-id");
        List<String> cookies = req.getHeaderFields().get("Set-Cookie");
        for (int i = 0; i < cookies.size(); i++) {
            if (cookies.get(i).contains("XSRF")) {
                uncutxsrf = cookies.get(i).split("\\;")[0];
                xsrfToken = cookies.get(i).split("\\;")[0].split("\\=")[1];
            } else if (cookies.get(i).contains("SESSION")) {
                uncutsession = cookies.get(i).split("\\;")[0];
            }
        }
        req.disconnect();
        String[] initializedData = {xsrfToken, ssoJourneyContextId, uncutxsrf, uncutsession};
        return initializedData;
    }

    public static JsonObject getUserDetails(String userName, String[] cookieData) throws Exception {
        BufferedReader bufferedReader;
        String userDetailURL = baseURL + "/dlg-gateway-routing/sso/user-registration/api/user/" + userName;
        URL connectionObject = new URL(userDetailURL);
        HttpURLConnection req = (HttpURLConnection) connectionObject.openConnection();
        req.setRequestMethod("GET");
        req.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        req.setRequestProperty("x-xsrf-token", cookieData[0]);
        req.setRequestProperty("sso-journey-context-id", cookieData[1]);
        req.addRequestProperty("Cookie", cookieData[2] + ";" + cookieData[3]);

        if (200 <= req.getResponseCode() && req.getResponseCode() <= 299) {
            bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(req.getErrorStream()));
        }
        JsonReader jsonReader = Json.createReader(new StringReader(bufferedReader.readLine()));
        JsonObject returnData = jsonReader.readObject();
        req.disconnect();
        bufferedReader.close();
        return returnData;
    }

    public static void activateUser(JsonObject userData, String[] cookieData) throws Exception {
        JSONObject userActivationDetails = new JSONObject();
        userActivationDetails.put("username", userData.getString("username"));
        userActivationDetails.put("token", userData.getString("registrationToken"));
        String activationURL =baseURL + "/dlg-gateway-routing/sso/sso-email/email/validate/registrationconfirmation";
        URL connectionObject = new URL(activationURL);

        HttpURLConnection connection = (HttpURLConnection) connectionObject.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.setRequestProperty("x-xsrf-token", cookieData[0]);
        connection.setRequestProperty("sso-journey-context-id", cookieData[1]);
        connection.addRequestProperty("Cookie", cookieData[2] + ";" + cookieData[3]);
        connection.getOutputStream().write(userActivationDetails.toString().getBytes("UTF-8"));
        connection.connect();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        if (responseCode == 200)
            Assert.assertTrue("User successfully Activated.", true);
        else
            Assert.fail("Unable to activate the user.");
    }
}