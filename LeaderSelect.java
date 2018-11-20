package tests;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class LeaderSelect {
public static AppiumDriver<MobileElement> driver;

@BeforeClass
public void setUp() throws MalformedURLException{
	//Set up desired capabilities and pass the Android app-activity and app-package to Appium
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("BROWSER_NAME", "Android");
	capabilities.setCapability("VERSION", "6.0"); 
	capabilities.setCapability("deviceName","emulator-5554");
	capabilities.setCapability("platformName","Android");
 
   
   capabilities.setCapability("appPackage", "com.fivemobile.thescore");
// This package name of your app (you can get it from apk info app)
	capabilities.setCapability("appActivity","com.fivemobile.thescore.SplashActivity"); // This is Launcher activity of your app (you can get it from apk info app)
//Create RemoteWebDriver instance and connect to the Appium server
 //It will launch the Score App in Android Device using the configurations specified in Desired Capabilities
   driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
   
}

@Test
public void selectRandomPlayer() throws Exception {
	
	//Webdriver wait to wait up to 40 seconds for the element expected to be on the page to be visible and to check every 5 seconds if the condition is true
	WebDriverWait wait = new WebDriverWait(driver, 40);
	
	//Click Get Started Button 
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.fivemobile.thescore:id/btn_get_started"))).click();
	
	//Click Next Button(s) and allow permissions
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.fivemobile.thescore:id/btn_next"))).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.fivemobile.thescore:id/btn_allow"))).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.fivemobile.thescore:id/btn_next"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.fivemobile.thescore:id/btn_allow"))).click();
	
	//Home screen for Fresh User - Click Leagues tab from bottom navigation bar
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.fivemobile.thescore:id/action_leagues"))).click();
	
	//Select NBA Basketball league
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='NBA Basketball']"))).click();
	
	//Double click Leaders tab because single click is not working intermediately
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Leaders']"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Leaders']"))).click();
	
	//Generate random number from 1 to inclusive
	String randomNum = String.valueOf((int)(Math.random() * 4 + 1));
	
	//if random number generated is 1 then click element with this id 
	if (randomNum == "1"){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.fivemobile.thescore:id/txt_leader"))).click();


	}
	//else click element with this id that is dynamic
	else {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.fivemobile.thescore:id/leader"+randomNum+""))).click();
		
	}
	TimeUnit.SECONDS.sleep(25);

}

@AfterClass
public void teardown(){
	//close the App
	driver.quit();
}
}
