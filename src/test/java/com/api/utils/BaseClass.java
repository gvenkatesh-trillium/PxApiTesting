package com.api.utils;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

    public static String BASE_URL = "https://trilliumx.azurewebsites.net/TrilliumX_Dev/v1.0/Services/TrilliumWebAPI/";
    //    public static String BASE_URL = "https://rcr-uat-services.azurewebsites.net/TrilliumWebAPI/";
    public static RequestSpecification request = RestAssured.given().auth().basic("77628226-A277-47B5-9409-69D0F26A2DC2", "qwerty123");

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest extentTest;

    public static Response response;

    public static String contactId;
    public static String membershipId;
    public static String organisationId;
    public static String addressId;
    public static String eMail;
    public static String testCase;
    public static String jsonBody;
    public static String rNum;


    @BeforeTest
    public void setExtent() {

        htmlReporter = new ExtentHtmlReporter("c:/work/report/myReport.html");

        htmlReporter.config().setDocumentTitle("Project X");
        htmlReporter.config().setReportName("Project X API Testing report");
//        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);



        extent.setSystemInfo("Host name", "DESKTOP-QK7P72A");
        extent.setSystemInfo("Environemnt", "Dev");
        extent.setSystemInfo("User", "Gopinath");


    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }

    @BeforeMethod
    public void beforeMethod() {


    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
//            extentTest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
//            extentTest.log(Status.PASS, "Test Case PASSED IS " + result.getName());
        }

    }
}
