package com.usmanhussain.cayenne.utils;

import com.usmanhussain.habanero.framework.AbstractPage;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class APItest {


    public void GetWeatherDetails() {

        //RestAssured.baseURI = "https://amends.directline.com/dlg-mta-api/addr/postcode";
         RestAssured.baseURI = "https://dev-amends-directline.dlgdigitalservices.com/dlg-mta-api/addr/postcode";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/BR11DP");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

        int statusCode = response.getStatusCode();
        Assert.assertEquals("statuscode",statusCode,200);


    }







}
