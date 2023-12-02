package com.amazon.Hawk.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class CaptureScreenshot {
    public static void captureScreenshot(WebDriver driver, String methodName) throws IOException {
        // Get the method name
        // Take screenshot
    	System.out.println("screenshot capturing");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // Define the destination path
        Path destinationDirectory = Paths.get("C:\\Users\\gowthrj\\eclipse-workspace\\Hawk\\src\\test\\resources\\Screenshots");//, methodName + System.currentTimeMillis() + ".png");
        if (!Files.exists(destinationDirectory)) {
            Files.createDirectories(destinationDirectory);
        }
        Path destinationPath = destinationDirectory.resolve(methodName + System.currentTimeMillis() + ".png");
        System.out.print(source.toPath()+ " "+destinationPath);
        try {
            // Copy the screenshot to the specified path
            Files.copy(source.toPath(), destinationPath);
            Reporter.log("<br><img src='" + destinationPath.toAbsolutePath() + "' height='300' width='400'/><br>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}