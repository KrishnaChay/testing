package com.usmanhussain.cayenne.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;

public class JavaAPITest {
    private static String baseURL = "https://digidev2.directline.com";
    private static String brand = "directline";

    public void apitest() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the city name:");
        String input = s.nextLine();
        try {
            URL url = new URL("http://restapi.demoqa.com/utilities/weather/city/" + input);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            System.out.println(conn.getResponseCode());
            System.out.println(conn.getHeaderField(1));
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
