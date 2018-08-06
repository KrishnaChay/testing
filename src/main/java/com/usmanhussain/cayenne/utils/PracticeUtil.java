package com.usmanhussain.cayenne.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;


public class PracticeUtil {

    static String address = "";

    public static void main(String[] args) throws Throwable {

        APItest apItest = new APItest();
//        String allAddresses = apItest.GetWeatherDetails();
//        JSONObject obj = new JSONObject(allAddresses);
//        JSONArray addresses = (JSONArray) obj.get("addresses");
//        String address = (String) addresses.get(1);
//        System.out.println("address 1 ========== " + address);

    apItest.vehicleDetails();


    }


    public void launchBrowser() {


    }


}
