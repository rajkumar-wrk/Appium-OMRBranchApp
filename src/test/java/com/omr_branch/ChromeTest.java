package com.omr_branch;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class ChromeTest {
    public static void main(String[] args) throws URISyntaxException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Basic capabilities
    	capabilities.setCapability("appium:deviceName", "RZCXC09RHRX");
		capabilities.setCapability("appium:platformName", "Android");
		capabilities.setCapability("appium:platformVersion", "15");

		capabilities.setCapability("appium:browserName", "Chrome");
		capabilities.setCapability("appium:automationName", "UIAutomator2");

        // Appium-specific: enable chromedriver autodownload
		capabilities.setCapability("appium:chromedriver_autodownload", true);

        
            AndroidDriver driver = new AndroidDriver( capabilities);

            // Your test code here
            driver.get("https://www.google.com");
            System.out.println("Title: " + driver.getTitle());

        
    }
}
