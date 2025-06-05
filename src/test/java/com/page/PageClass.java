package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageClass {
	
	WebDriver driver;
	By userName=By.xpath("//input[@id='email']");
	public PageClass(WebDriver driver) {
	//	super();
		this.driver = driver;
	}
	
	
	
	
	
	
	
	

	

}
