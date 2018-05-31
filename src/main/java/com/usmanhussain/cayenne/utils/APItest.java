package com.usmanhussain.cayenne.utils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.util.Scanner;


public class APItest {


    public void GetWeatherDetails() {
//        Scanner s = new Scanner(System.in);
//        System.out.println("Enter the Postcode");
//        String input = s.nextLine();

        RestAssured.useRelaxedHTTPSValidation();
        //  RestAssured.baseURI = "https://amends.directline.com/dlg-mta-api/addr/postcode";
        RestAssured.baseURI = "https://dev-amends-directline.dlgdigitalservices.com/dlg-mta-api/addr/postcode/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "br11dp");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals("statuscode", statusCode, 200);

        System.out.println(responseBody.split(":")[2]);


    }


}
