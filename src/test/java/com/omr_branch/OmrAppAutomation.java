package com.omr_branch;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.baseclass.GlobalLib;

import io.appium.java_client.android.AndroidDriver;

public class OmrAppAutomation {

	static WebDriver driver;
	static AndroidDriver androidDriver;
	static JavascriptExecutor js;

	public static void appLaunch() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium:deviceName", "RZCXC09RHRX");
		capabilities.setCapability("appium:platformName", "Android");
		capabilities.setCapability("appium:platformVersion", "15");
		capabilities.setCapability("appium:appPackage", "com.omr_branch");
		capabilities.setCapability("appium:appActivity", "com.omr_branch.MainActivity");
		capabilities.setCapability("appium:automationName", "UIAutomator2");

		capabilities.setCapability("appium:chromedriverExecutable",
				System.getProperty("user.dir")+"\\chromeDriver\\chromedriver-win64_v137.0.7151.68.exe");

		driver = new AndroidDriver(capabilities);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public static void login() {
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Email Address']"))
				.sendKeys("raj.officialwrkmail@gmail.com");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Password']")).sendKeys("Raju@081194");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Login']")).click();

		String txtWelcome = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='header_welcome']"))
				.getText();

		System.out.println("Text " + txtWelcome);
	}

	public static void swipes(int startY, int EndY, int centerX) {
		PointerInput input = new PointerInput(Kind.TOUCH, "finger1");

		Sequence sequence = new Sequence(input, 1);

		sequence.addAction(input.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), centerX, startY));

		sequence.addAction(input.createPointerDown(MouseButton.LEFT.asArg()));

		sequence.addAction(input.createPointerMove(Duration.ofSeconds(1), Origin.viewport(), centerX, EndY));

		sequence.addAction(input.createPointerUp(MouseButton.LEFT.asArg()));

		List<Sequence> asList = Arrays.asList(sequence);

		RemoteWebDriver d = (RemoteWebDriver) driver;
		d.perform(asList);

	}

	public static void searchHotels() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Select State']")).click();

		Rectangle ddnRectState = driver
				.findElement(By.xpath("//android.widget.ScrollView[@content-desc='search_select_state flatlist']"))
				.getRect();

		int x = ddnRectState.getX();
		int y = ddnRectState.getY();
		int centerX = (ddnRectState.getWidth()) / 2 + x;

		int startY = (int) (ddnRectState.getHeight() * 0.80 + y);
		int EndY = (int) (ddnRectState.getHeight() * 0.20 + y);

		swipes(startY, EndY, centerX);

		driver.findElement(By.xpath("//android.widget.TextView[@text='Tamil Nadu']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='Select City']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='Chennai']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='Select Room Type']")).click();

		Rectangle ddnRectRoomType = driver
				.findElement(By.xpath("//android.widget.ScrollView[@content-desc='search_room_type flatlist']"))
				.getRect();

		int centerXRoom = (ddnRectRoomType.getWidth()) / 2 + ddnRectRoomType.getX();

		int startYRoom = (int) (ddnRectRoomType.getHeight() * 0.80 + ddnRectRoomType.getY());
		int EndYRoom = (int) (ddnRectRoomType.getHeight() * 0.20 + ddnRectRoomType.getY());

		swipes(startYRoom, EndYRoom, centerXRoom);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Luxury']")).click();

		WebElement ddnCheckInDate = driver
				.findElement(By.xpath("//android.widget.EditText[@resource-id='search_select_checkin']"));
		ddnCheckInDate.click();

//		Rectangle dateRect = driver.findElement(By.xpath("//android.widget.DatePicker[@resource-id='android:id/datePicker']/android.widget.LinearLayout")).getRect();
//
//		
//		int centerY=(dateRect.getHeight()/2)+dateRect.getY();
//		
//		int startX=(int) ((dateRect.getWidth()*0.80)+dateRect.getX());
//		
//		int endX=(int) ((dateRect.getWidth()*0.20)+dateRect.getX());
//		
//		swipes(startX, endX, centerY);
//		
//		driver.findElement(By.xpath("//android.view.View[@content-desc='16 July 2025']")).click();

		driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']")).click();

		WebElement ddnCheckOutDate = driver
				.findElement(By.xpath("//android.widget.EditText[@resource-id='search_select_checkout']"));
		ddnCheckOutDate.click();

		// swipes(startX, endX, centerY);

		// driver.findElement(By.xpath("//android.view.View[@content-desc='30 July
		// 2025']")).click();

		driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='No. Of Room']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='2-Two']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='No. Of Adults']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='2-Two']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='search_no_of_children']")).sendKeys("2");

		driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id='search_button']")).click();
	}

	public static void printSelectHotelText() {
		WebElement txtSelectHotel = driver
				.findElement(By.xpath("//android.widget.TextView[@resource-id=\"select_hotel_text\"]"));
		System.out.println("Text " + txtSelectHotel.getText());

	}

	public static void swipe() {
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

	public static void swipeToElement(By by) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> elements = driver.findElements(by);

		while (elements.size() == 0) {
			swipe();
			elements = driver.findElements(by);

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public static void selectHotelName(By by) {

		String hotelName = driver.findElement(by).getText();
		if (hotelName.equals("Taj Connemara Luxury")) {

			driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();

			driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button2']")).click();
		}

	}

	public static void addGuestDetails() {

		driver.findElement(By.xpath("//android.widget.TextView[@text='Myself']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='Select Salutation']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Mr.']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_first_name']"))
				.sendKeys("raj");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_last_name']")).sendKeys("j");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_mobile_no']"))
				.sendKeys("7010778418");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_email']"))
				.sendKeys("raj.officialwrkmail@gmail.com");

	}

	public static void addGstDetails() {

		driver.findElement(By.xpath("//android.widget.TextView[@text='Enter GST Details (Optional)']")).click();

		By txtAddress = By.xpath("//android.widget.EditText[@content-desc='book_hotel_company_address']");

		swipeToElement(txtAddress);

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_registration']"))
				.sendKeys("9043592058");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_company_name']"))
				.sendKeys("Greens Tech OMR Branch");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_company_address']"))
				.sendKeys("Thoraipakkam");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Next']")).click();

	}

	public static void addSpecilaReq() {

		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='filter_option_late']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Next']")).click();

	}

	public static void addPayment(By by) throws InterruptedException {

		androidDriver = (AndroidDriver) driver;

		Set<String> context = androidDriver.getContextHandles();

		System.out.println("available views" + context);

		for (String x : context) {

			if (!x.equals("NATIVE_APP")) {

				androidDriver.context(x);

			}

		}

		driver.findElement(By.xpath("//h5[contains(text(),'Credit')]")).click();

		WebElement ddnCardType = driver.findElement(By.xpath("//select[@id='payment_type']"));
		Select s2 = new Select(ddnCardType);
		s2.selectByValue("debit_card");

		for (String x : context) {

			if (x.equals("NATIVE_APP")) {
				androidDriver.context(x);

			}

		}

		driver.findElement(By.xpath("//android.view.View[@resource-id='card_type']")).click();
		driver.findElement(
				By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Visa']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@resource-id='card_no']")).sendKeys("5555555555552222");

		swipeToElement(by);

		driver.findElement(By.xpath("//android.widget.EditText[@resource-id='card_name']")).sendKeys("raj");

		driver.findElement(By.xpath("//android.view.View[@resource-id='card_month']")).click();
		driver.findElement(
				By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='August']"))
				.click();

		driver.findElement(By.xpath("//android.view.View[@resource-id='card_year']")).click();
		driver.findElement(
				By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='2032']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@resource-id='cvv']")).sendKeys("321");

		driver.findElement(By.xpath("//android.widget.Button[@resource-id='submitBtn']")).click();

	}

	public static void getOrderId() {

		String txtOrderId = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'#')]")).getText();

		System.out.println("OrderNo " + txtOrderId);

		String txtHotelName = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Hotel')]"))
				.getText();

		System.out.println("txtHotelName " + txtHotelName);

	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException, URISyntaxException {

		appLaunch();

		login();

		searchHotels();

		printSelectHotelText();

		By xpath = By.xpath("//*[@text='Taj Connemara Luxury']");
		swipeToElement(xpath);

		selectHotelName(xpath);

		By rdoMyself = By.xpath("//android.widget.TextView[@text='Myself']");

		swipeToElement(rdoMyself);

		addGuestDetails();

		addGstDetails();

		addSpecilaReq();
		By txtCardYear = By.xpath("//android.view.View[@resource-id='card_year']");
		addPayment(txtCardYear);

		getOrderId();

	}

//	
//	
//	private void swipetoEle(By By) {
//		
//		List<WebElement> elements = driver.findElements(By);
//		
//		while (elements.size()==0) {
//			swipe();
//			 elements = driver.findElements(By);
//			
//		}
//		
//
//	}

}
