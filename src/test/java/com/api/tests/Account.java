package com.api.tests;


import com.api.utils.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends BaseClass {



    @Test(priority=1)
    public void RetrieveOrganisationsByName(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Organisations By Name and Validate");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"name\": \"O\",\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 100,\n" +
                "    \n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
                "}";

        extentTest.log(Status.INFO," POST request Body : <br /> "+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL + testCase);

        Matcher m = Pattern.compile("([a-zA-Z0-9-]{36})").matcher(response.asString());
        while (m.find()) {
//            System.out.println(m.group());
            organisationId = m.group();
        }

        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            extentTest.log(Status.PASS," Check POST response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            extentTest.log(Status.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            extentTest.log(Status.PASS," Check POST response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            extentTest.log(Status.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }

    }

    @Test(priority=2)
    public void UpdateOrganisation(Method method) {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String todayString = formatter.format(todayDate);


        testCase = method.getName();
        extentTest = extent.createTest("Update Organisation and Validate");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"clearOrganisationDataForEmptyParams\": true,\n" +
                " \"id\": \" " + organisationId + "\",\n" +
                "  \"name\": \"GV Organisation"+todayString+"\"\n" +
                "}";

        extentTest.log(Status.INFO," POST request Body : <br /> "+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL + testCase);

             try {
            Assert.assertEquals(response.getStatusCode(), 200);
            extentTest.log(Status.PASS," Check POST response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            extentTest.log(Status.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            extentTest.log(Status.PASS," Check POST response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            extentTest.log(Status.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }

    }



}
