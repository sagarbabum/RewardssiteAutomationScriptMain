package com.wow.rewardssite.cases;

import java.util.Random;
//import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.Assert;
import org.testng.annotations.Test;

import com.wow.rewardssite.pages.RewardsSiteEmailVerification;
import com.wow.rewardssite.pages.RewardsSiteRegistration;
import com.wow.rewardssite.pages.TempEmailAndOTP;

import net.bytebuddy.utility.RandomString;

public class EnterRegistrationDetails {

	@Test
	public void registration() throws InterruptedException {



		//Launching chrome driver
		System.setProperty("webdriver.chrome.driver","C:/chromedriver2/chromedriver.exe");

		//Chrome Options
		//Enable automation
		ChromeOptions options = new ChromeOptions();				
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		//Disable Infobars
		options.addArguments("disable-infobars");
		//Accept certificates
		options.setAcceptInsecureCerts(true);				
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

		//Open Browser for rewards site
		// Initialize browser
		WebDriver driver=new ChromeDriver(options);
		// Maximize browser
		driver.manage().window().maximize();
		// Open rewards site
		driver.get("https://www.woolworthsrewards.com.au/");
		Thread.sleep(5000);	

		//Create objects for page classes
		RewardsSiteRegistration regPage = new RewardsSiteRegistration(driver);
		TempEmailAndOTP tempEmailPage = new TempEmailAndOTP(driver);
		RewardsSiteEmailVerification emailVerify = new RewardsSiteEmailVerification(driver);

		//Random username generator
		String strUserAN = RandomString.make(10);		
		String strUserAlpha = strUserAN.replaceAll("\\d","");	
		//Random mobile number generator
		Random rand = new Random();
		String strran = String.format("%04d",rand.nextInt(10000));
		
		//Test data
		String firstName = strUserAlpha;
		String lastName = "Lataad";
		String phoneNo = "041234" + strran;
		String dob = "25101985";
		String gender = "Male";
		String pswd = "Rewards@123";
		String fullAddress = "175 Fechner Rd, KOO WEE RUP VIC";

		//Rewards site registartion first page........................................./
		regPage.click_joinlink(driver);		Thread.sleep(5000);
		regPage.enter_firstName(driver, firstName);
		regPage.enter_lastName(driver, lastName);
		regPage.enter_phoneNo(driver, phoneNo);
		regPage.enter_dob(driver, dob);
		regPage.select_gender(driver, gender);

		
		//Temporary mail and OTP Generation.........................................../
		//Open Browser for temporary mail/
		// Initialize browser
		WebDriver driver2=new ChromeDriver(options);
		// Maximize browser
		driver2.manage().window().maximize();
		// Open rewards site
		driver2.get("https://temp-mail.org/en/");
		Thread.sleep(10000);

		//Create the browser object	
		String tempEmailID = tempEmailPage.get_tempEmail(driver2);

		//Rewards site registartion first page........................................./
		regPage.enter_email(driver, tempEmailID);
		regPage.enter_password(driver, pswd);
		regPage.enter_confirmpassword(driver, pswd);
		regPage.click_acceptPolicy(driver);
		regPage.click_registerBtn(driver);
		Thread.sleep(35000);

		//Calling functions from Temporary mail page.........................................../
		tempEmailPage.scroll_viewMail(driver2);
		tempEmailPage.click_otpLink(driver2);
		String OTPno = tempEmailPage.get_OTP(driver2);

		//Close the Temporary browser
		driver2.close();

		//Rewards site email verification page........................................./
		emailVerify.enter_otp(driver, OTPno);
		emailVerify.click_verify_OTP(driver);
		Thread.sleep(10000);
		emailVerify.enter_address(driver, fullAddress);
		Thread.sleep(3000);
		emailVerify.click_joinBtn(driver);

		Thread.sleep(10000);

		//Verify Submission
		try {
			String endPageText = driver.findElement(By.xpath("(//h3//*[text()='Thanks for joining Woolworths Rewards'])[1]")).getText();
			System.out.println(endPageText);
			System.out.println("Member got Registered");

			//Verify Mobile No
			String VerifyMobileNo = driver.findElement(By.xpath("//label[contains(text(),'We will SMS your Rewards card')]/b")).getText();

			WebElement ele2 = driver.findElement(By.xpath("//label[contains(text(),'We will SMS your Rewards card')]/b"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);",ele2);
			
			if(VerifyMobileNo.equalsIgnoreCase(phoneNo)) {
				System.out.println("Mobile Number is correct");
			}else {
				System.out.println("Incorrect Mobile Number");
			}
		}catch(Exception e) {
			System.out.println("Member not Registered");
		}
		
		Thread.sleep(3000);
		driver.close();		//Close the Main browser
	}

}
