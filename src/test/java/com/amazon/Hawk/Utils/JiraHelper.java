package com.amazon.Hawk.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.File;

public class JiraHelper {

    private static final String JIRA_BASE_URL = "https://write2gowthamraj.atlassian.net/rest/api/2/issue/";

    public static void createIssue(String summary, String description, File screenshotFile) {
        summary = "Failure at " + summary;
        String issueJson = "{"
                + "\"fields\": {"
                + "\"project\": {\"key\": \"WEBQA\"},"
                + "\"summary\": \"" + summary + "\","
                + "\"description\": \"" + description + "\","
                + "\"issuetype\": {\"name\": \"Bug\"}"
                + "}"
                + "}";

        try {
            RestAssured.given()
                    .header("Authorization", "Bearer " + "ATATT3xFfGF0tcwMHZznwSP_ioxGj9Y-kcPvPRn8f-eCR-JfHMr5uhDstimC69g3bTjyGYfNOHkF8iPaSvC346s7ZSo8Blv_P5kKDVl96p1Wx89uHybcqzfoGL3we0tLlgOsqCD4iqYeXNA3wiMdfZNvA5dw2-uvQRHlzEI5nRKB-CSwI0FxGW8=60C2AF84")
                    .multiPart("file", screenshotFile)
                    .multiPart("payload", issueJson, "application/json")  // Include the JSON data directly in multiPart
                    .post(JIRA_BASE_URL);
            System.out.println("Jira creation Successful");
        } catch (Exception e) {
            // Handle the exception as needed
        	System.out.println("Jira creation failed");
            e.printStackTrace();
            // You may want to log the error or throw a custom exception
        }
    }
}



