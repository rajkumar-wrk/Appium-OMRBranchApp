package com.baseclass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlobalLib {
	public static WebDriver driver;
	Wait wait;
	Actions action;
	Select select;
	JavascriptExecutor js;
	Alert alert;
	TakesScreenshot screenshot;
	
	public byte[] screenshot() {
		 screenshot=(TakesScreenshot) driver;
		 byte[] b = screenshot.getScreenshotAs(OutputType.BYTES);
         return b;
	}

	public String propertyReadValues(String key) throws IOException {

		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Configuration\\Config.property");

		FileInputStream fileInputStream = new FileInputStream(file);

		Properties properties = new Properties();
		properties.load(fileInputStream);

		String values = properties.getProperty(key);

		System.out.println(key + " : " + properties.getProperty(key));
		return values;
	}

	public static void fireFoxDriver(String url) {
		driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

	}

	public static void ChromeDriver(String url) {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

	}

	public static void edgeDriver(String url) {
		driver = new EdgeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

	}

	public void enterApplnUrl(String url) {
		driver.get(url);
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void elementSendKeys(WebElement element, String data) {
		element.sendKeys(data);
	}

	public String getCurrentUrl() {

		String currentUrl = driver.getCurrentUrl();
		return currentUrl;

	}

	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public WebElement findLocatorById(String attributeValue) {
		WebElement element = driver.findElement(By.id(attributeValue));
		return element;
	}

	public WebElement findLocatorByName(String attributeValue) {
		WebElement element = driver.findElement(By.name(attributeValue));
		return element;
	}

	public WebElement findLocatorByClassName(String attributeValue) {
		WebElement element = driver.findElement(By.className(attributeValue));
		return element;
	}

	public WebElement findLocatorByXpath(String exp) {
		WebElement element = driver.findElement(By.xpath(exp));
		return element;
	}

	public String getApplnUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static void closeWindow() {
		driver.quit();
	}

	public String elementGetText(WebElement element) {
		String text = element.getText();
		return text;
	}

//99%-->value is fixed
	public String getDomProperty(WebElement element) {
		String domProperty = element.getDomProperty("value");
		return domProperty;
	}

	// 1%-->value is NOT fixed
	public String getDomProperty(WebElement element, String attributeName) {
		String domProperty = element.getDomProperty(attributeName);
		return domProperty;
	}

	public void jsSendKeys(WebElement element, String text) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);

	}

	public String jsGetAttribute(WebElement element) {
		js = (JavascriptExecutor) driver;

		Object executeScript = js.executeScript("return arguments[0].getAttrubute('value')", element);
		String txt = executeScript.toString();
		return txt;

	}

	public void jsClick(WebElement element) {
		js = (JavascriptExecutor) driver;

		if (isElementDisplayed(element) && isElementEnabled(element)) {
			js.executeScript("arguments[0].click()", element);
		}

	}

	public void scrollUp(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element);

	}

	public void scrollDown(WebElement element) {
		visibilityOfElement(element);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);

	}

	public void mouseOverAction(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).perform();

	}

	public void dragAndDrop(WebElement src, WebElement des) {
		action = new Actions(driver);
		action.dragAndDrop(src, des).perform();
	}

	public void doubleClick(WebElement element) {
		action = new Actions(driver);
		action.doubleClick(element).perform();

	}

	public void rightClick(WebElement element) {
		action = new Actions(driver);
		action.contextClick(element).perform();

	}

	public File getScreenshot(String fileName) throws IOException {
		TakesScreenshot t = (TakesScreenshot) driver;
		File screenshotAs = t.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\Screenshot\\baseClass\\" + fileName + ".png");
		FileUtils.copyFile(screenshotAs, file);
		return file;

	}

	public File elementScreenshot(WebElement element, String fileName) throws IOException {

		File screenshotAs = element.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\Screenshot\\baseClass\\" + fileName + ".png");
		FileUtils.copyFile(screenshotAs, file);
		return file;

	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

	}

	public WebElement explicitFindElement(String attribute) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement element = w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attribute)));
		return element;

	}

	public WebElement fluentFindElement(String attribute) {
		wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(Throwable.class);
		Object until = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attribute)));

		WebElement element = (WebElement) until;

		return element;
	}

	public void visibilityOfElement(WebElement element) {
		wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(Throwable.class);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public Boolean isElementEnabled(WebElement element) {
		boolean enabled = element.isEnabled();
		return enabled;

	}

	public Boolean isElementDisplayed(WebElement element) {
		boolean displayed = element.isDisplayed();
		return displayed;

	}

	public void sendKeys(WebElement element, String txt) {
		visibilityOfElement(element);

		if (isElementDisplayed(element) && isElementEnabled(element)) {
			element.sendKeys(txt);
		}
	}
	
	public void sendkeysEnter(WebElement element, String txt) {
		
		visibilityOfElement(element);
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			element.sendKeys(txt,Keys.ENTER);
		}
		

	}

	public void elementClick(WebElement element) {
		visibilityOfElement(element);
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			element.click();
		}

	}

	public List<String> getOptionsText(WebElement element) {
		select = new Select(element);
		List<WebElement> options = select.getOptions();
		List<String> l = new ArrayList<String>();
		for (WebElement x : options) {
			String text = x.getText();
			l.add(text);

		}
		return l;

	}

	public List<String> getOptionsValue(WebElement element) {
		select = new Select(element);
		List<WebElement> options = select.getOptions();
		List<String> l = new ArrayList<String>();
		for (WebElement x : options) {

			String domAttribute = x.getDomAttribute("value");
			l.add(domAttribute);

		}
		return l;

	}

	public List<String> getFirstSelectedOptions(WebElement element) {
		select = new Select(element);
		List<WebElement> options = (List<WebElement>) select.getFirstSelectedOption();
		List<String> l = new ArrayList<String>();
		for (WebElement x : options) {
			String text = x.getText();
			l.add(text);

		}
		return l;

	}

	public void selectByText(WebElement element, String text) {

		select = new Select(element);
		select.selectByVisibleText(text);

	}

	public void deSelectByText(WebElement element, String text) {

		select = new Select(element);
		select.deselectByVisibleText(text);

	}

	public void selectByValue(WebElement element) {
		select = new Select(element);
		String domAttribute = element.getDomAttribute("value");
		select.selectByValue(domAttribute);

	}

	public void deSelectByValue(WebElement element) {
		select = new Select(element);
		String domAttribute = element.getDomAttribute("value");
		select.deselectByValue(domAttribute);

	}

	public void selectByIndex(WebElement element, int Index) {
		select = new Select(element);

		select.deselectByIndex(Index);

	}

	public void deSelectByIndex(WebElement element, int Index) {
		select = new Select(element);

		select.selectByIndex(Index);

	}

	public void deSelectAll(WebElement element) {
		select = new Select(element);

		select.deselectAll();

	}

	public boolean isMultiSelect(WebElement element) {

		select = new Select(element);

		boolean multiple = select.isMultiple();
		return multiple;

	}

	public void switchWindow(int index) {
		String parentId = driver.getWindowHandle();
		List<String> allID = (List<String>) driver.getWindowHandles();
		int count = 0;
		for (String x : allID) {
			if (count == index) {
				driver.switchTo().window(x);

			}
			count++;

		}

	}

	public void switchFrameByIndex(int index) {
		driver.switchTo().frame(index);

	}

	public void switchFrameByIndex(String attribute) {
		driver.switchTo().frame(attribute);

	}

	public void switchFrameByIndex(WebElement element) {
		driver.switchTo().frame(element);

	}

	public WebElement findElementById(String attribute) {
		WebElement element = driver.findElement(By.id(attribute));
		return element;

	}

	public WebElement findElementByName(String attribute) {
		WebElement element = driver.findElement(By.name(attribute));
		return element;

	}

	public WebElement findElementByClass(String attribute) {
		WebElement element = driver.findElement(By.className(attribute));
		return element;

	}

	public WebElement findElementByXpath(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element;

	}

	public void alertAccept() {
		alert = driver.switchTo().alert();
		alert.accept();

	}

	public void alertDismiss() {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);

	}

	public void navigateBack() {
		driver.navigate().back();

	}

	public void navigateForward() {
		driver.navigate().forward();

	}

	public void navigateRefresh() {
		driver.navigate().refresh();

	}

}
