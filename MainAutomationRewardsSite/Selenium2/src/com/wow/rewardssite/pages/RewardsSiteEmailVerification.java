package com.wow.rewardssite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RewardsSiteEmailVerification {
	WebDriver driver;


	By otp = By.xpath("//input[@ng-model='userCode']");

	By verifyOTP = By.xpath("//button[text()='Verify']");

	//Enter Address	
	By address = By.name("mailingFullAddress");
	
	//Click the JOIN Button
	By joinBtn = By.id("join-btn_single");
	

	public RewardsSiteEmailVerification(WebDriver driver) {
		this.driver = driver;
	}

	public void enter_otp(WebDriver driver, String value) {
		driver.findElement(otp).sendKeys(value);
	}
	public void click_verify_OTP(WebDriver driver) {
		driver.findElement(verifyOTP).click();
	}
	public void enter_address(WebDriver driver, String value) throws InterruptedException {
		Actions build = new Actions(driver);
		
		driver.findElement(address).sendKeys(value);
		Thread.sleep(5000);
		build.moveToElement(driver.findElement(address)).moveByOffset(50, 50).click().build().perform();
		Thread.sleep(1000);
	}
	public void click_joinBtn(WebDriver driver) {
		driver.findElement(joinBtn).click();
	}
}
