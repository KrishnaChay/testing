package com.usmanhussain.cayenne.utils;

import com.usmanhussain.habanero.framework.AbstractPage;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




public class APItest {


    public void GetWeatherDetails() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/Hyderabad");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

        int statusCode = response.getStatusCode();

        Assert.assertEquals("statuscode",statusCode,200);


    }







}
