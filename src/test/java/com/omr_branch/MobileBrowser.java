package com.omr_branch;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.android.AndroidDriver;

public class MobileBrowser {

	static DesiredCapabilities capabilities;
	static WebDriver driver;
	static AndroidDriver androidDriver;
	static JavascriptExecutor js;
	static String txtOrderNo;
	static String txtHotelName;

	public static void login() {

		capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium:deviceName", "RZCXC09RHRX");
		capabilities.setCapability("appium:platformName", "Android");
		capabilities.setCapability("appium:platformVersion", "15");

		capabilities.setCapability("appium:browserName", "Chrome");
		capabilities.setCapability("appium:automationName", "UIAutomator2");
		capabilities.setCapability("appium:chromedriverExecutable",
				"C:\\Users\\raj81\\.appium\\node_modules\\appium-uiautomator2-driver\\node_modules\\appium-chromedriver\\chromedriver\\chromedriver-win64_v137.0.7151.68.exe");


		
		driver = new AndroidDriver(capabilities);

		driver.get("https://www.omrbranch.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		androidDriver = (AndroidDriver) driver;
		System.out.println(androidDriver.getContext());
		Set<String> contextHandles = androidDriver.getContextHandles();
		System.out.println(contextHandles);

		for (String x : contextHandles) {
			if (x.equals("NATIVE_APP")) {

				androidDriver.context(x);
			}
		} // tab
		driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\'com.android.chrome:id/tab_thumbnail\']"))
				.click();

		// alert
		driver.findElement(By.xpath(
				"//android.view.ViewGroup[@resource-id=\'com.android.chrome:id/coordinator\']/android.view.View"))
				.click();

		// map location
		driver.findElement(By.xpath(
				"//android.widget.Button[@resource-id=\'com.android.permissioncontroller:id/permission_allow_foreground_only_button\']"))
				.click();

		Set<String> contextHandles2 = androidDriver.getContextHandles();
		for (String x : contextHandles2) {
			if (x.equals("CHROMIUM")) {
				androidDriver.context(x);
				;

			}

		}
		driver.findElement(By.id("email")).sendKeys("raj.officialwrkmail@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Raju@081194");
		driver.findElement(By.xpath("//button[text()='Login']")).click();

	}

	static public void hotelHomePage() throws InterruptedException {

		driver.findElement(By.xpath("//a[text()='Book Hotel']")).click();

		System.out.println(driver.findElement(By.xpath("//a[contains(text(),'Welcome ')]")).getText());

		WebElement ddnState = driver.findElement(By.xpath("//select[@id='state']"));
		Select s = new Select(ddnState);
		s.selectByVisibleText("Tamil Nadu");

		driver.findElement(By.xpath("//span[text()='Select City *']")).click();
		driver.findElement(By.xpath("//li[text()='Chennai']")).click();

		WebElement txtSearchBox = driver.findElement(By.xpath("//textarea[@role='searchbox']"));

		String[] string = { "Standard", "Deluxe", "Suite", "Luxury", "Studio" };
		for (String x : string) {
			txtSearchBox.click();
			WebElement element = driver.findElement(By.xpath("//li[text()='" + x + "']"));
			element.click();
			Thread.sleep(1000);

		}
		// Scanner scanner=new Scanner(System.in);
		// System.out.println("Enter Checkin date");
		// int checkIn = scanner.nextInt();

		driver.findElement(By.xpath("//input[@name='check_in']")).click();
		driver.findElement(By.xpath("//a[text()='30']")).click();

		// System.out.println("Enter CheckOut date");
		// int checkOut = scanner.nextInt();

		driver.findElement(By.xpath("//input[@name='check_out']")).click();
		driver.findElement(By.xpath("//a[text()='3']")).click();

		WebElement ddnNORoom = driver.findElement(By.xpath("//select[@id='no_rooms']"));
		Select s1 = new Select(ddnNORoom);
		s1.selectByVisibleText("3-Three");

		WebElement ddnNoAdults = driver.findElement(By.xpath("//select[@id='no_adults']"));
		Select s2 = new Select(ddnNoAdults);
		s2.selectByVisibleText("3-Three");

		driver.findElement(By.xpath("//input[@id='no_child']")).sendKeys("1");

		WebElement frame = driver.findElement(By.xpath("//iframe[@class='iframe']"));

		driver.switchTo().frame(frame);

		WebElement btnSearch = driver.findElement(By.xpath("//button[text()='Search']"));

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", btnSearch);

		driver.switchTo().defaultContent();

	}

	static public void selectHotel() throws InterruptedException {

		System.out.println(driver.findElement(By.xpath("//h5[text()='Select Hotel']")).getText());

		WebElement hotelList = driver.findElement(By.xpath("//div[@id='hotellist']"));
		List<WebElement> elements = hotelList.findElements(By.tagName("h5"));
		for (WebElement x : elements) {
			System.out.println("Hotel names.." + x.getText());
		}

		List<WebElement> txtPrice = hotelList.findElements(By.tagName("strong"));
		for (WebElement x : txtPrice) {

			System.out.println("Hotel Price.." + x.getText());

		}

		List<WebElement> btncont = hotelList.findElements(By.tagName("a"));
		btncont.get(btncont.size() - 1).click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	static public void bookHotel() {
		System.out.println(driver.findElement(By.xpath("//h2[contains(text(),'Book ')]")).getText());

		WebElement radioBtnMyself = driver.findElement(By.xpath("//label[text()='Myself']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", radioBtnMyself);

		WebElement ddnSalutation = driver.findElement(By.xpath("//select[@id='user_title']"));
		Select s = new Select(ddnSalutation);
		s.selectByVisibleText("Mr.");

		driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("raj");

		driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("kumar");

		driver.findElement(By.xpath("//input[@id='user_phone']")).sendKeys("1234567898");

		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys("123@gmail.com");

		WebElement gst = driver.findElement(By.xpath("//label[contains(text(),'Enter ')]"));

		js.executeScript("arguments[0].click()", gst);

		driver.findElement(By.xpath("//input[@id='gst_registration']")).sendKeys("9043592058");
		driver.findElement(By.xpath("//input[@id='company_name']")).sendKeys("Greens Tech OMR Branch");
		driver.findElement(By.xpath("//input[@id='company_address']")).sendKeys("Thoraipakkam");

		WebElement element2 = driver.findElement(By.xpath("//button[@id='step1next']"));

		js.executeScript("arguments[0].click()", element2);

		WebElement chkEarly = driver.findElement(By.xpath("//label[text()='Early Check-in']"));
		js.executeScript("arguments[0].click()", chkEarly);

		WebElement chkLargeBed = driver.findElement(By.xpath("//label[text()='Large bed']"));
		js.executeScript("arguments[0].click()", chkLargeBed);

		driver.findElement(By.xpath("//textarea[@id='other_request']"))
				.sendKeys("Make sure the room is ready for checkin");

		WebElement btnNext = driver.findElement(By.xpath("//button[@id='step2next']"));
		js.executeScript("arguments[0].click()", btnNext);

		driver.findElement(By.xpath("//h5[contains(text(),'Credit')]")).click();

		WebElement ddnCardType = driver.findElement(By.xpath("//select[@id='payment_type']"));
		Select s2 = new Select(ddnCardType);
		s2.selectByVisibleText("Debit Card");

		WebElement ddnCard = driver.findElement(By.xpath("//select[@id='card_type']"));
		Select s3 = new Select(ddnCard);
		s3.selectByVisibleText("Visa");

		driver.findElement(By.xpath("//input[@id='card_no']")).sendKeys("5555555555552222");
		driver.findElement(By.xpath("//input[@id='card_name']")).sendKeys("raj kumar");

		WebElement ddnMonth = driver.findElement(By.xpath("//select[@id='card_month']"));
		Select s4 = new Select(ddnMonth);
		s4.selectByVisibleText("April");

		WebElement ddnYear = driver.findElement(By.xpath("//select[@id='card_year']"));
		Select s5 = new Select(ddnYear);
		s5.selectByVisibleText("2029");

		driver.findElement(By.xpath("//input[@id='cvv']")).sendKeys("666");

		WebElement btnSubmit = driver.findElement(By.xpath("//button[@id='submitBtn']"));

		js.executeScript("arguments[0].click()", btnSubmit);

//		String txtOrderId = driver.findElement(By.xpath("//strong[contains(text(),'#')]")).getText();
//		System.out.println("order id..."+txtOrderId);

		System.out.println(driver.findElement(By.xpath("//h2[contains(text(),'Booking ')]")).getText());

		txtHotelName = driver.findElement(By.xpath("//p[contains(text(),'Hotel ')]")).getText();
		System.out.println("hotel name " + txtHotelName);

		String txtOrderId = driver.findElement(By.xpath("//strong[starts-with(text(),'#')]")).getText();
		System.out.println("order no " + txtOrderId);
		txtOrderNo = txtOrderId.substring(1);

	}

	public static void changeBooking() throws InterruptedException {

		System.out.println("change booking....................................");

		driver.findElement(By.xpath("//button[text()='My Booking']")).click();

		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(txtOrderNo);
		Thread.sleep(2000);
		System.out.println("order no " + txtOrderNo);

		List<WebElement> hotelName = driver.findElements(By.tagName("h5"));
		String h = txtHotelName.substring(6, 19);
		for (WebElement x : hotelName) {
			if (x.getText().startsWith(h)) {
				System.out.println("hotel name " + x.getText());
				break;

			}

		}
		String txtPrice = driver.findElement(By.xpath("//strong[@class='total-prize']")).getText();
		System.out.println("total price " + txtPrice);

		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[text()='Edit']")).click();

		driver.findElement(By.xpath("//input[@name='check_in']")).click();

		driver.findElement(By.xpath("//a[text()='29']")).click();

		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		String txtUpdate = driver.findElement(By.xpath("//li[starts-with(text(),'Booking ')]")).getText();
		System.out.println("text " + txtUpdate);

	}

	public static void cancelBooking() throws InterruptedException {

		System.out.println("cancel booking................................................");
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(txtOrderNo);

		Thread.sleep(2000);
		System.out.println("order no " + txtOrderNo);

		List<WebElement> hotelName = driver.findElements(By.tagName("h5"));
		String h = txtHotelName.substring(6, 19);
		for (WebElement x : hotelName) {
			if (x.getText().startsWith(h)) {
				System.out.println("hotel name " + x.getText());
				break;

			}

		}
		String txtPrice = driver.findElement(By.xpath("//strong[@class='total-prize']")).getText();
		System.out.println("total price " + txtPrice);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Cancel']")).click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		String txtCancelled = driver.findElement(By.xpath("//li[starts-with(text(),'Your ')]")).getText();

		System.out.println("txt " + txtCancelled);

		List<WebElement> elements = driver.findElements(By.tagName("button"));
		for (WebElement x : elements) {

			if (x.getText().equals("Cancelled")) {

				System.out.println("text " + x.getText());
				break;

			}

		}

	}

	public static void main(String[] args) throws InterruptedException {
		login();
		hotelHomePage();
		selectHotel();
		bookHotel();
		changeBooking();
		cancelBooking();
	}

}
