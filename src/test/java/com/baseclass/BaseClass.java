package com.baseclass;



import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	WebDriver driver;
	Actions actions;
	Alert alert;
	JavascriptExecutor executor;
	TakesScreenshot takesScreenshot;
	Select select;

	public void switchToContext(String view) {
		AndroidDriver androidDriver = (AndroidDriver) driver;
		androidDriver.context(view);
	}

	public void enterApplnUrl(String url) {
		driver.get(url);
	}

	public List<String> getAllOptions(WebElement element) {
		List<String> allOptionsText = new ArrayList<>();

		select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			String text = webElement.getText();
			allOptionsText.add(text);

		}
		return allOptionsText;
	}

	public void selectOptionByText(WebElement element, String text) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void selectOptionByIndex(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectOptionByValue(WebElement element, String text) {
		select = new Select(element);
		select.selectByValue(text);
	}

	public void scroll(WebElement element) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView()", element);
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void dragAndDrop(WebElement source, WebElement dest) {
		actions = new Actions(driver);
		actions.dragAndDrop(source, dest).perform();
	}

	public void screenshot(WebElement element, String fileName) throws IOException {
		File screenshotAs = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(
				"C:\\Users\\Velmurugan\\eclipse-workspace\\FrameworkClass930AMBatch\\output\\" + fileName + ".png"));
	}

	public void screenshot(String fileName) throws IOException {
		takesScreenshot = (TakesScreenshot) driver;
		File screenshotAs = takesScreenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(
				"C:\\Users\\Velmurugan\\eclipse-workspace\\FrameworkClass930AMBatch\\output\\" + fileName + ".png"));
	}

	public void switchToChildWindow() {
		String windowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String eachWindowId : windowHandles) {
			if (!windowHandle.equals(eachWindowId)) {
				driver.switchTo().window(eachWindowId);
			}
		}
	}

	public boolean elementIsEnabled(WebElement element) {
		boolean enabled = element.isEnabled();
		return enabled;
	}

	public boolean elementIsDisplayed(WebElement element) {
		boolean displayed = element.isDisplayed();
		return displayed;
	}

	public void visibilityOfElement(WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driverWait.until(ExpectedConditions.visibilityOf(element));

	}

	public String elementGetText(WebElement element) {
		String text = element.getText();
		return text;
	}

	public List<WebElement> findLocatorsBy(By by) {
		List<WebElement> elements = driver.findElements(by);
		return elements;

	}

	public WebElement findLocatorBy(By by) {
		WebElement element = driver.findElement(by);
		return element;
	}

	public void swipeToElement(By by) {
		implicitWait(5);

		List<WebElement> elements = findLocatorsBy(by);
		while (elements.size() == 0) {
			swipe();
			elements = findLocatorsBy(by);

		}
		implicitWait();

	}

	public void swipe() {
		// Get the mobile Dimension (1080, 2400)
		Dimension size = driver.manage().window().getSize();
		// Get Width 1080
		int width = size.getWidth();
		// Get Height 2400
		int height = size.getHeight();
		// Find the Center X
		int centerX = width / 2;
		// Find Start Y
		int startY = (int) (height * 0.80);
		// Find End Y
		int endY = (int) (height * 0.20);

		// 1. Move to Start position
		// a1. Create the Object for PointerInput Class
		PointerInput pointerInput = new PointerInput(Kind.TOUCH, "finger1");

		// a2. Create Object for Sequence Class
		Sequence sequence = new Sequence(pointerInput, 1);

		// a3. Move to Start position (width, start Y)
		sequence.addAction(pointerInput.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), centerX, startY));

		// 2. Press
		sequence.addAction(pointerInput.createPointerDown(MouseButton.LEFT.asArg()));

		// 3. Move To End Position
		sequence.addAction(pointerInput.createPointerMove(Duration.ofSeconds(1), Origin.viewport(), centerX, endY));

		// 4. Release
		sequence.addAction(pointerInput.createPointerUp(MouseButton.LEFT.asArg()));

		List<Sequence> asList = Arrays.asList(sequence);

		RemoteWebDriver remoteWebDriver = (RemoteWebDriver) driver;
		remoteWebDriver.perform(asList);

	}

	public byte[] screenshot() {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		byte[] b = screenshot.getScreenshotAs(OutputType.BYTES);
		return b;
	}

	public void implicitWait(int secs) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secs));
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public void elementSendKeys(WebElement element, String data) {
		visibilityOfElement(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys(data);
		}
	}

	public String getApplnUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	// 99%-->value is fixed
	public String getDomProperty(WebElement element) {
		visibilityOfElement(element);
		String domProperty = element.getDomProperty("value");
		return domProperty;
	}

	// 1%-->value is NOT fixed
	public String getDomProperty(WebElement element, String attributeName) {
		visibilityOfElement(element);
		String domProperty = element.getDomProperty(attributeName);
		return domProperty;
	}

	public void closeWindow() {
		driver.quit();
	}

	public void elementSendKeysEnter(WebElement element, String data) {
		visibilityOfElement(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys(data, Keys.ENTER);
		}
	}

	public void elementSendKeysJs(WebElement element, String data) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value','" + data + "')", element);
	}

	public void elementClick(WebElement element) {
		visibilityOfElement(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.click();
		}
	}

	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public void appLaunch(String deviceName, String platformName, String platformVersion, String appPackage,
			String appActivity, String automationName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium:deviceName", deviceName);
		capabilities.setCapability("appium:platformName", platformName);
		capabilities.setCapability("appium:platformVersion", platformVersion);
		capabilities.setCapability("appium:appPackage", appPackage);
		capabilities.setCapability("appium:appActivity", appActivity);
		capabilities.setCapability("appium:automationName", automationName);
		driver = new AndroidDriver(capabilities);

	}

	public void mobileBrowserLaunch(String deviceName, String platformName, String platformVersion, String browserName,
			String automationName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium:deviceName", deviceName);
		capabilities.setCapability("appium:platformName", platformName);
		capabilities.setCapability("appium:platformVersion", platformVersion);
		capabilities.setCapability("appium:browserName", browserName);
		capabilities.setCapability("appium:automationName", automationName);
		driver = new AndroidDriver(capabilities);

	}

}

