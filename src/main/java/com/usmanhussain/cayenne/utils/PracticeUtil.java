package com.usmanhussain.cayenne.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PracticeUtil {




    public static void main(String[] args) throws Throwable {
        APItest apitest = new APItest();
        JavaAPITest javaAPITest = new JavaAPITest();

//        System.setProperty("webdriver.chrome.driver", "/Users/CYAX/Automation/dependency/chromedriver");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.google.com");


        apitest.GetWeatherDetails();
       // javaAPITest.apitest();

    }



    public void launchBrowser() {





    }



}
