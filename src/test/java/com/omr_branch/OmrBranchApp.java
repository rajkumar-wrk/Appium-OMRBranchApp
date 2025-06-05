package com.omr_branch;

import java.awt.PageAttributes.OriginType;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class OmrBranchApp {

	public static void main(String[] args) {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium:deviceName", "RZCXC09RHRX");
		capabilities.setCapability("appium:platformName", "Android");
		capabilities.setCapability("appium:platformVersion", "15");
		capabilities.setCapability("appium:appPackage", "com.omr_branch");
		capabilities.setCapability("appium:appActivity", "com.omr_branch.MainActivity");
		capabilities.setCapability("appium:automationName", "UIAutomator2");

		WebDriver driver = new AndroidDriver(capabilities);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Email Address']"))
				.sendKeys("raj.officialwrkmail@gmail.com");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Password']")).sendKeys("Raju@081194");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Login']")).click();

		String txtWelcome = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='header_welcome']"))
				.getText();

		System.out.println("Text " + txtWelcome);

		// ExploreHotel
		driver.findElement(By.xpath("//android.widget.TextView[@text='Select State']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Andhra Pradesh']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='Select City']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Tirupati']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='Select Room Type']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Luxury']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='search_select_checkin']")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc='02 June 2025']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='search_select_checkout']")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc='03 June 2025']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='No. Of Room']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='2-Two']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='No. Of Adults']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='2-Two']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='search_no_of_children']")).sendKeys("2");

		driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id='search_button']")).click();

		WebElement txtSelectHotel = driver
				.findElement(By.xpath("//android.widget.TextView[@resource-id=\"select_hotel_text\"]"));
		System.out.println("Text " + txtSelectHotel.getText());

		Dimension size = driver.manage().window().getSize();
		int centerX = (size.getWidth()) / 2;

		int startY = (int) (size.getHeight() * 0.8);

		int endY = (int) (size.getHeight() * 0.2);

		PointerInput pointerInput = new PointerInput(Kind.TOUCH, "Finger1");

		Sequence sequence = new Sequence(pointerInput, 1);

		// move to start position

		sequence.addAction(pointerInput.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), centerX, startY));

		//press
		sequence.addAction(pointerInput.createPointerDown(MouseButton.LEFT.asArg()));
		
		//move to end postions
		sequence.addAction(pointerInput.createPointerMove(Duration.ofSeconds(1),Origin.viewport(), centerX,endY));
		
		//Release
		
		sequence.addAction(pointerInput.createPointerUp(MouseButton.LEFT.asArg()));
		
		
		
		
		List<Sequence> asList = Arrays.asList(sequence);
		
		RemoteWebDriver remoteWebDriver=(RemoteWebDriver) driver;
		remoteWebDriver.perform(asList);
		
		
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();

		driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button2']")).click();

		

		
		
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

		driver.findElement(By.xpath("//android.widget.TextView[@text='Enter GST Details (Optional)']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_registration']"))
				.sendKeys("9043592058");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_company_name']"))
				.sendKeys("Greens Tech OMR Branch");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='book_hotel_company_address']"))
				.sendKeys("Thoraipakkam");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Next']")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='filter_option_late']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Next']")).click();

		AndroidDriver androidDriver = (AndroidDriver) driver;

		System.out.println(androidDriver.getContext());
		Set<String> contextHandles = androidDriver.getContextHandles();

		System.out.println(contextHandles);

		driver.findElement(By.xpath("//android.webkit.WebView")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text=\'Credit/Debit/ATM Card\']")).click();

		driver.findElement(By.xpath("//android.view.View[@resource-id='payment_type']")).click();

		driver.findElement(
				By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Debit Card']"))
				.click();

		driver.findElement(By.xpath("//android.view.View[@resource-id='card_type']")).click();
		driver.findElement(
				By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Visa']")).click();

		driver.findElement(By.xpath("//android.widget.EditText[@resource-id='card_no']")).sendKeys("5555555555552222");

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

		String txtOrderId = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'#')]")).getText();

		System.out.println("OrderNo " + txtOrderId);

		String txtHotelName = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Hotel')]"))
				.getText();

		System.out.println("txtHotelName " + txtHotelName);

	}

}
