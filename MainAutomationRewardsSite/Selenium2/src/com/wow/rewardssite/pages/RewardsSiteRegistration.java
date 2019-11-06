package com.wow.rewardssite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RewardsSiteRegistration {
	WebDriver driver;

	//Click Join Button
	By joinlink = By.linkText("JOIN");

	//Enter First name and Last name
	By firstName = By.name("firstName");
	By lastName = By.name("lastName");

	//Enter Mobile Number
	//String mobilePhone = "0412345012";
	By mobilePhone = By.name("mobilePhone");

	//Enter Date of Birth
	By birthDate = By.name("birthDate");

	//Select the Gender
	By gender = By.xpath("//*[@id='gender']//input");






	//Enter email
	//Enter Date of Birth
	By email = By.name("email");

	//Create the password
	By password = By.name("password");
	By confirmpassword = By.name("confirmpassword");

	//Click Accept Button
	By acceptPolicy = By.xpath("//*[text()='Privacy Policy and Collection Notice']/..");

	//Click the next page button
	By registerBtn = By.id("register-btn_single");


	
	public RewardsSiteRegistration(WebDriver driver) {
		this.driver = driver;
	}

	public void click_joinlink(WebDriver driver) {
		driver.findElement(joinlink).click();
	}
	public void enter_firstName(WebDriver driver, String value) {
		driver.findElement(firstName).sendKeys(value);
	}
	public void enter_lastName(WebDriver driver, String value) {
		driver.findElement(lastName).sendKeys(value);
	}
	public void enter_phoneNo(WebDriver driver, String value) {
		driver.findElement(mobilePhone).sendKeys(value);
	}
	public void enter_dob(WebDriver driver, String value) {
		driver.findElement(birthDate).sendKeys(value);
	}
	public void select_gender(WebDriver driver, String value) {
		driver.findElement(gender).click();				
		By genderValue = By.xpath("//*[@id='gender']//span[text()='"+value+"']");
		driver.findElement(genderValue).click();
	}
	public void enter_email(WebDriver driver, String value) {
		driver.findElement(email).sendKeys(value);
	}
	public void enter_password(WebDriver driver, String value) {
		driver.findElement(password).sendKeys(value);
	}
	public void enter_confirmpassword(WebDriver driver, String value) {
		driver.findElement(confirmpassword).sendKeys(value);
	}
	public void click_acceptPolicy(WebDriver driver) {
		driver.findElement(acceptPolicy).click();
	}
	public void click_registerBtn(WebDriver driver) {
		driver.findElement(registerBtn).click();
	}

}
