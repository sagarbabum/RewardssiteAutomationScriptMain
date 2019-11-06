package com.wow.rewardssite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TempEmailAndOTP {
	
	WebDriver driver;
	
	//Define the Object properties
	By tempMail = By.xpath("//input[@id='mail']");
	By viewMail = By.xpath("//div[contains(text(),'View')]");
	By otpLink = By.xpath("//a[contains(@href,'https://temp-mail.org/en//')][@class='viewLink title-subject']");
	By getOTP = By.xpath("//*[contains(text(),'Your one time')]/strong");
			
		
	//Define the TempEmailAndOTP methods	
	public TempEmailAndOTP(WebDriver driver) {
		this.driver = driver;
	}	
	public String get_tempEmail(WebDriver driver) {
		return driver.findElement(tempMail).getAttribute("value");
	}
	public void scroll_viewMail(WebDriver driver) {
		//driver.findElement(viewMail).click();
		
		WebElement ele = driver.findElement(viewMail);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",ele);
	}	
	public void click_otpLink(WebDriver driver) {
		driver.findElement(otpLink).click();
	}	
	public String get_OTP(WebDriver driver) {
		return driver.findElement(getOTP).getText();
	}
		
}
