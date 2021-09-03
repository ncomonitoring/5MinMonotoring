package Test;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.remote.ErrorHandler.*

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;


import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

public class MS1_NcoWebClientSanity  extends Base {


	//	@BeforeTest
	public void outputfile() throws IOException, InterruptedException {
		NetsfereActivity.killAllBrowserSessions();
		//		String udate = new SimpleDateFormat("dd-MM-yy-HHmmss").format(new java.util.Date());
		//		String proj_dir = System.getProperty("user.dir");
		//		System.setOut(new PrintStream(new FileOutputStream("\\Y:\\NS_Automon_logs\\"+"Monitoring2-" + udate +".txt")));
		////		System.setOut(new PrintStream(new FileOutputStream("Monitoring-" + udate +".txt")));
		//		System.out.println("Output File Created");
	}

	@SuppressWarnings({ "rawtypes", "unused", "deprecation" })	
	//	@Test(priority=4)
	public void Ms1_Netsfere_Cleanup() throws InterruptedException, IOException {

		List<String> userIds = new ArrayList<String>();

		if(!(Config.getInstance().isDebug())) {
			userIds.add(Config.getInstance().getMs1WebId());
			userIds.add(Config.getInstance().getMs1ChromeId());
			userIds.add(Config.getInstance().getMs1FirefoxId());
		} else {
			userIds.add(Config.getInstance().getMs1WebIdTest());
			userIds.add(Config.getInstance().getMs1ChromeIdTest());
			userIds.add(Config.getInstance().getMs1FirefoxIdTest());
		}
		for ( String userId: userIds ) {
			int rc = NetsfereActivity.AccountCleanUp(userId);
			if ( rc == 1 ) {
				System.out.println("Account Cleanup for :"+userId+ "   SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ "   FAILED");
			}
		}
		NetsfereActivity.killAllBrowserSessions();
		System.out.println("Ms1 Cleanup has been completed: Success*******.");
	}

	@Test(priority=5)
	public void Ms1_Netsfere_Message_Call_Check() throws InterruptedException, IOException , AWTException {		

		System.out.println("Start Time : " + ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));		

		NetsfereActivity.killAllBrowserSessions();	

		System.out.println("Ms1 REGION - Basic health checks started..."); 	

		String  webUserId = "";
		String  webUserDisplayName = "";
		String  chromeUserId = "";
		String  chromeUserDisplayName = "";
		String  firefoxUserId = "";
		String  firefoxUserDisplayName = "";

		if( Config.getInstance().isDebug()) {
			webUserId = Config.getInstance().getMs1WebIdTest();
			webUserDisplayName = Config.getInstance().getMs1WebDisplayNameTest();
			chromeUserId = Config.getInstance().getMs1ChromeIdTest();
			chromeUserDisplayName = Config.getInstance().getMs1ChromeDisplayNameTest();
			firefoxUserId =  Config.getInstance().getMs1FirefoxIdTest();
			firefoxUserDisplayName = Config.getInstance().getMs1FirefoxDisplayNameTest();

		} else {
			webUserId = Config.getInstance().getMs1WebId();
			webUserDisplayName = Config.getInstance().getMs1WebDisplayName();
			chromeUserId = Config.getInstance().getMs1ChromeId();
			chromeUserDisplayName = Config.getInstance().getMs1ChromeDisplayName();
			firefoxUserId =  Config.getInstance().getMs1FirefoxId();
			firefoxUserDisplayName = Config.getInstance().getMs1FirefoxDisplayName();
		}


		// Login -- Web
		System.out.println("Login to Webuser...");
		ChromeDriver webDriver = chromeDriverInitialize();
		WebDriverWait webWait5 = new WebDriverWait(webDriver, 5);
		WebDriverWait webWait10 = new WebDriverWait(webDriver, 10);
		WebDriverWait webWait20 = new WebDriverWait(webDriver, 20);
		WebDriverWait webWait60 = new WebDriverWait(webDriver, 60);
		WebDriverWait webWait30 = new WebDriverWait(webDriver, 30);
		WebDriverWait webWait120 = new WebDriverWait(webDriver, 120);
		JavascriptExecutor jsweb = (JavascriptExecutor) webDriver;

		int rc = NetsfereActivity.webClientLogin(webDriver, webUserId);		
		if( rc != 0 ) {
			System.out.println("ERROR -netsfere Login page Not Loaded. Pls Check Internet Connection...");	        
			System.out.println("exiting the programe...");			
			webDriver.close();
			Assert.assertFalse(true,"Error While Logging in to Ms1 Web");
			System.exit(0);
		}	

		//Launch browser and login to netsfere with firefox user		
		System.out.println("Login to Firefoxuser...");
		ChromeDriver foxDriver = chromeDriverInitialize();
		//		Thread.sleep(3000);
		String firefoxWindow = foxDriver.getWindowHandle();
		WebDriverWait foxWait5 = new WebDriverWait(foxDriver, 5);
		WebDriverWait foxWait10 = new WebDriverWait(foxDriver,10);
		WebDriverWait foxWait60 = new WebDriverWait(foxDriver, 60);
		WebDriverWait foxWait30 = new WebDriverWait(foxDriver, 30);
		WebDriverWait foxWait120 = new WebDriverWait(foxDriver, 120);
		foxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor jsfirefox = (JavascriptExecutor) foxDriver;

		rc = NetsfereActivity.webClientLogin(foxDriver, firefoxUserId);		
		if( rc != 0 ) {
			System.out.println("ERROR -netsfere Login page Not Loaded. Pls Check Internet Connection...");	        
			System.out.println("exiting the programe...");
			webDriver.close();
			Assert.assertFalse(true,"Error While Logging in to Ms1 Firefox");
			System.exit(0);
		}	

		System.out.println("TIme at both user Login : "+ ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));
		// ******************************************************************************
		// Creating a conversation from Webuser to Firefoxuser and sending message in it.
		// *******************************************************************************

		String conversationTitle = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		System.out.println("ChromMs1ser Starting Conversation With Firefoxuser.");
		String WebGroupConversationTitle = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()); 
		String Message1 = "";
		String Message2 = "";

		try {

			webDriver.findElementByXPath("//button[@title='Start Conversation']").click();
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Create')]")));		
			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").click();
			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").sendKeys(conversationTitle);		
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(firefoxUserDisplayName);	
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);			
			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+firefoxUserDisplayName+"']")));			
			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+firefoxUserDisplayName+"']").click();
			Thread.sleep(1000);
			webWait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Create')]")));
			webDriver.findElementByXPath("//span[contains(text(),'Create')]").click();
			try {
				webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='screenOverlayText' and contains(text(),'Creating conversation...')]")));
				webWait60.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='screenOverlayText' and contains(text(),'Creating conversation...')]")));
			} catch (Exception e) {
				System.out.println("Webuser Unable to create conversation with Firefoxuser.");
				Assert.assertFalse(true,"Error Ms1 WebUser Unable to Create COnversation with Ms1 Firefox User");
			}

			// Check if created COnversation Exist.
			try {
				webDriver.findElementByXPath("//button[@title='Start Conversation']/../div/input").sendKeys(conversationTitle);					
				webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+conversationTitle+"')]")));				
				webDriver.findElementByXPath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+conversationTitle+"')]").click();				
			} catch (Exception e) {
				System.out.println("Webuser Unable to create conversation with Firefoxuser.");
				Assert.assertFalse(true,"Error Ms1 WebUser Unable to Create COnversation with Ms1 Firefox User");
			}
			webWait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@class='namegenTextLong']")));
			if(webDriver.findElementByXPath("//textarea[@class='namegenTextLong']") != null) {
				System.out.println("Webuser created conversation with Firefoxuser successfully.");				
				System.out.println("Sending message in 1-1 conversation");
				Message1= new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				Message1 = Message1 +" : Ms1 - Hello from Webuser";
				webDriver.findElement(By.xpath("//textarea[@class='namegenTextLong']")).sendKeys(Message1);
				webDriver.findElementByXPath("//span[@title='Send Message']").click();				
				webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bubbleWrap']/div[.//span[contains(text(),'"+Message1+"')]]/../following-sibling::div/div//span[@class='fa fa-check']")));
				WebElement sentStaus = webDriver.findElementByXPath("//div[@class='bubbleWrap']/div[.//span[contains(text(),'"+Message1+"')]]/../following-sibling::div/div//span[@class='fa fa-check']");				
				if (sentStaus.getAttribute("title").contains("Read") || sentStaus.getAttribute("title").contains("Sent") ) {
					System.out.println("Webuser sent the message");
				} else {
					System.out.println("Webuser failed to send the message");
					Assert.assertFalse(true,"Error Ms1 WebUser Unable to send message");
				}
			}
		} catch(Exception e) {
			System.out.println("Webuser Unable to create conversation with Firefoxuser.");
			Assert.assertFalse(true,"Error Ms1 WebUser Unable to Create COnversation with Ms1 Firefox User");
		}		

		System.out.println("Firefoxuser validates the created conversation");	
		foxDriver.navigate().refresh();
		Thread.sleep(2000);

		try {
			foxDriver.findElementByXPath("//button[@title='Start Conversation']/../div/input").sendKeys(conversationTitle);			
			foxWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+conversationTitle+"')]")));			
		} catch (Exception e) {
			System.out.println("Firefoxuser did not get the conversation created by webUser.");
			if ( foxDriver.findElementByXPath("//div[@class='scrollbox']//div[@class='table-view']/div[2]").getText().contains("No Conversations Matching Search")) {
				foxDriver.findElementByXPath("//button//span[@class='material-icons']").click();
				foxDriver.navigate().refresh();
				Thread.sleep(2000);
				foxDriver.findElementByXPath("//button[@title='Start Conversation']/../div/input").sendKeys(conversationTitle);						
				foxWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+conversationTitle+"')]")));											
			}
		}
		try {
			foxDriver.findElementByXPath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+conversationTitle+"')]").click();
			foxWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@chatmode]/span[contains(text(),'"+conversationTitle+"')]")));
			System.out.println("Conversation created by Webuser with Firefoxuser exists on Firefoxuser side.");			
			foxWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bubbleWrap']//div//span//span[contains(text(),'"+Message1+"')]")));
			System.out.println("Message Received by Firefoxuser ");

		} catch(Exception e) {
			System.out.println("ERROR- Conversation created by Webuser with Firefoxuser does not exists on Firefoxuser side Or Firefoxuser did not receive the message sent by ChromMs1ser.");
			Assert.assertFalse(true,"ERROR - Ms1 Firefox user did not get the message");
		}



		//******************************
		//  Attachment in Conversation 
		//******************************
		// Firefoxuser To send JPEG IMAGE
		try {
			foxDriver.findElementByXPath("//span[@class='icon ion-plus-circled']").click();
			foxWait10.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Add Attachment')]/../following-sibling::div[@title='From Device']"))));
			foxWait10.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Add Attachment')]/../following-sibling::div[@title='From Device']")));
			foxDriver.findElementByXPath("//div[contains(text(),'Add Attachment')]/../following-sibling::div[@title='From Device']").click();
			Thread.sleep(1000);		

			StringSelection sel = new StringSelection(Config.getInstance().getImageAttachment());		
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
			System.out.println("selection" +sel);		
			Robot robot = new Robot();
			Thread.sleep(1000);

			if ( !NetsfereActivity.isMac()) {
				//				NetsfereActivity.attachImage_win();
				NetsfereActivity.attachImage_win_Robot();
			} else {
				//TO-DO
			}
		} catch(Exception e) {		
			System.out.println("Trying to send image from firefox");
			Assert.assertFalse(true,"ERROR - Firefox User Unable to send attachment. ");
		}

		try {	
			foxWait120.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[./img[@src]]/../following-sibling::button/span[contains(text(),'Send')]")));
			if ( foxDriver.findElementByXPath("//div[@class='click-ripple']/following-sibling::span[contains(text(),'Send')]") != null ) {
				foxDriver.findElementByXPath("//div[@class='click-ripple']/following-sibling::span[contains(text(),'Send')]").click();				
				foxWait120.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[./img[@src]]/../following-sibling::button/span[contains(text(),'Send')]/../..")));
			}
		}  catch ( NoSuchElementException e) {			
			System.out.println("ERROR -Firefoxuser Unable to Attach Image in Conversation...");
			Assert.assertFalse(true,"ERROR - Firefox User Unable to send attachment. ");
		}		

		// Firefoxuser to assert the sent Image. 

		try {				

			foxWait120.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bubbleWrap'][.//div[contains(@style,'blob')]]/following-sibling::div/div//span[contains(@class,'fa fa-check')]")));
			List<WebElement> elems3 = foxDriver.findElementsByXPath("//div[@class='bubbleWrap'][.//div[contains(@style,'blob')]]/following-sibling::div/div//span[contains(@class,'fa fa-check')]");
			if(elems3.size() > 0) {
				WebElement el2 = elems3.get(elems3.size() - 1);
				if ( el2 != null ) {						
					System.out.println("Image Sent Status : "+ el2.getAttribute("title"));							
				} 
			}	
		}  catch ( NoSuchElementException e) {			
			System.out.println("ERROR -Firefoxuser Unable to Attach Image in Conversation...");
			Assert.assertFalse(true,"ERROR - Firefox User Unable to send attachment. ");
		}


		// Webuser to assert the received Image. 
		try {
			webWait120.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@style,'blob')]")));
			List<WebElement> elems4 = webDriver.findElementsByXPath("//div[@class='resize-sensor']/../following-sibling::div/div//div[@class='bubbleWrap']");
			if(elems4.size() > 0) {
				webWait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@style,'blob')]")));
				WebElement el1 = elems4.get(elems4.size() - 1).findElement(By.xpath("//div[contains(@style,'blob')]")) ;
				if ( el1 != null ) {					
					System.out.println("ChromMs1ser Received Image");
				}
			}
		} catch ( NoSuchElementException e) {		
			System.out.println("ChromMs1ser DID NOT recieve the IMAGE from ChromMs1ser....  ");
			Assert.assertFalse(true,"ERROR - WebUser Didn't receive the attachment. ");
		}



		//******************************
		//  HD 1-1 Call 
		//******************************


		// Webuser make a call to webMs1ser2..
		System.out.println("HD call test case execution started: from webUser to foxUser ");

		webDriver.findElementByXPath("//span[@class='icon ion-person-stalker']").click();
		webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[./input[@type='text']]")));
		webDriver.findElementByXPath("//div[./input[@type='text']]").click();
		webDriver.findElementByXPath("//input[@type='text']").sendKeys(firefoxUserDisplayName);
		webWait10.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='table-view']//div[@displayname='"+firefoxUserDisplayName+"']")));
		webDriver.findElementByXPath("//div[@class='table-view']//div[@displayname='"+firefoxUserDisplayName+"']").click();
		webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='icon ion-android-call' and @title='Call']")));
		webDriver.findElementByXPath("//span[@class='icon ion-android-call' and @title='Call']").click();

		try {
			webWait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Ringing...')]")));		
			if ( webDriver.findElementByXPath("//span[contains(text(),'Ringing...')]") != null ) {			
				System.out.println("HD call is ringing..");
			}
		} catch ( NoSuchElementException e) {			
			System.out.println("Web Can not establish the HD  call");
			Assert.assertFalse(true,"ERROR - Web Can not establish the HD  call");
		}

		//Firefoxuser to receive call from Webuser
		try {
			foxWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Incoming call...')]")));

			if ( foxDriver.findElementByXPath("//span[contains(text(),'Incoming call...')]") != null ) {				
				System.out.println("Firefoxuser Received the HD call from ChromMs1ser");
			}

			if(foxDriver.findElementByXPath("//button[@title='Accept']") != null) {
				foxDriver.findElementByXPath("//button[@title='Accept']").click();			
				System.out.println("Firefoxuser Accepted the call");
				Thread.sleep(5000);				
				System.out.println("Firefoxuser is Terminating the HD Call...");
				foxDriver.findElementByXPath("//button[@title='End Call']").click();
				System.out.println("HD call test case execution Completed: from User A to User B  ");
				Thread.sleep(2000);				
			}
		} catch ( NoSuchElementException e) {
			System.out.println("ERROR -Error occurred while making call from Webuser to Firefoxuser.. ");
			Assert.assertFalse(true,"ERROR - Error occurred while making call from Webuser to Firefoxuser ");
		}

		try {
			WebElement CancelContactSearch = webDriver.findElementByXPath("//div[contains(text(),'Cancel')]");
			jsweb.executeScript("arguments[0].scrollIntoView();", CancelContactSearch);
			jsweb.executeScript("arguments[0].click();", CancelContactSearch);
			jsweb.executeScript("arguments[0].click();",webDriver.findElementByXPath("//div[@class='sideBarMenuButton' and @i='ios-chatbubble']/button"));
		} catch ( NoSuchElementException e) {
			System.out.println("Unable to cancel the Contact Search from WebUser");	
			Assert.assertFalse(true,"ERROR - Unable to cancel the Contact Search from WebUser");
		}

		try {
			WebElement CancelConversationSearch = foxDriver.findElementByXPath("//div[contains(text(),'Cancel')]");
			jsfirefox.executeScript("arguments[0].scrollIntoView();", CancelConversationSearch);
			jsfirefox.executeScript("arguments[0].click();", CancelConversationSearch);
			jsfirefox.executeScript("arguments[0].click();",foxDriver.findElementByXPath("//div[@class='sideBarMenuButton' and @i='ios-chatbubble']/button"));
		} catch ( NoSuchElementException e) {
			System.out.println("Unable to cancel the Conversation Search from firefox User");
			Assert.assertFalse(true,"ERROR - Unable to cancel the Contact Search from WebUser");
		}




		//=====Second chrome user
		ChromeDriver chromeDriver = (ChromeDriver) chromeDriverInitialize();
		WebDriverWait chromeWait120 = new WebDriverWait(chromeDriver, 120);
		WebDriverWait chromeWait60 = new WebDriverWait(chromeDriver, 60);
		WebDriverWait chromeWait5 = new WebDriverWait(chromeDriver, 5);
		WebDriverWait chromeWait10 = new WebDriverWait(chromeDriver, 10);

		JavascriptExecutor jschrome = (JavascriptExecutor) chromeDriver;
		System.out.println("Login to Second ChromMs1ser...");
		String anotherchromeWindow = chromeDriver.getWindowHandle();

		rc = NetsfereActivity.webClientLogin(chromeDriver, chromeUserId);		
		if( rc != 0 ) {
			System.out.println("ERROR -netsfere Login page Not Loaded. Pls Check Internet Connection...");	        
			System.out.println("exiting the programe...");
			webDriver.close();
			Assert.assertFalse(true,"Unable to login to ChromMs1ser");
			System.exit(0);
		}		


		//******************************
		// Group Conversation
		//******************************
		String groupConversationTitle = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()); 
		System.out.println("Web User Create group conversation to initiate group call");		

		try {
			webDriver.findElementByXPath("//span[@class='icon ion-ios-chatbubble']").click();
			webWait10.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Start Conversation']")));
			webDriver.findElementByXPath("//button[@title='Start Conversation']").click();
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/input[contains(@class,'namegenTitleReplace')]")));
			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").click();
			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").sendKeys(groupConversationTitle);		
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(firefoxUserDisplayName);	
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);			
			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+firefoxUserDisplayName+"']")));			
			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+firefoxUserDisplayName+"']").click();
			Thread.sleep(1000);
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(chromeUserDisplayName);	
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);					
			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+chromeUserDisplayName+"']")));			
			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+chromeUserDisplayName+"']").click();
			Thread.sleep(1000);					
			webWait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Create')]")));
			webDriver.findElementByXPath("//span[contains(text(),'Create')]").click();			
			webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='screenOverlayText' and contains(text(),'Creating conversation...')]")));
			webWait60.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='screenOverlayText' and contains(text(),'Creating conversation...')]")));
		} catch (Exception e) {
			System.out.println("Webuser Unable to create Groupconversation .");
			Assert.assertFalse(true,"ERROR - Webuser Unable to create Groupconversation ");
		}

		// Ccheck if created Group Conversation Exist.
		try {
			webDriver.findElementByXPath("//button[@title='Start Conversation']/../div/input").sendKeys(groupConversationTitle);					
			webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+groupConversationTitle+"')]")));				
			webDriver.findElementByXPath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+groupConversationTitle+"')]").click();				
		} catch (Exception e) {
			System.out.println("Webuser Unable to create Groupconversation");
			Assert.assertFalse(true,"ERROR - Webuser Unable to create Groupconversation ");
		}

		//**********************
		// Group Audio Call
		//**********************
		System.out.println("Initiate group call from webUser");

		try {
			if(webDriver.findElementByXPath("//button[@title='Make Call']").isDisplayed()){
				webDriver.findElementByXPath("//button[@title='Make Call']").click();
			}

			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[./span[contains(text(),'Select Ring Participants')]]/../following-sibling::div//button[@title='Place call']")));			
			webDriver.findElementByXPath("//div[./span[contains(text(),'Select Ring Participants')]]/../following-sibling::div//button[@title='Place call']").click();

			webWait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Group Call')]")));
		} catch ( NoSuchElementException e) {			
			System.out.println("Webuser unable to initiate group call");
			Assert.assertFalse(true,"ERROR - Webuser Unable to initiate Group Call ");
		}

		try {
			foxWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Incoming call...')]")));			
			if ( foxDriver.findElementByXPath("//div[contains(text(),'Incoming call...')]") != null ) {	
				System.out.println("Firefoxuser Received the group call from SecondChromMs1ser");
			}

			if(foxDriver.findElementByXPath("//button[@title='Accept']") != null) {
				foxDriver.findElementByXPath("//button[@title='Accept']").click();				
				System.out.println("Firefoxuser Accepted the call");

			}
		} catch ( NoSuchElementException e) {		
			System.out.println("ERROR -Firefox User unable to accept the Group call ");
			Assert.assertFalse(true,"ERROR - Firefox User unable to accept the Group call ");
		}	

		try {
			chromeWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Incoming call...')]")));			
			if ( chromeDriver.findElementByXPath("//div[contains(text(),'Incoming call...')]") != null ) {	
				System.out.println("ChromMs1ser Received the group call from Webuser");
			}

			if(chromeDriver.findElementByXPath("//button[@title='Accept']") != null) {
				chromeDriver.findElementByXPath("//button[@title='Accept']").click();				
				System.out.println("ChromMs1ser Accepted the call");								
			}
		} catch ( NoSuchElementException e) {		
			System.out.println("ERROR - chrome User unable to accept the Group call ");
			Assert.assertFalse(true,"ERROR - chrome User unable to accept the Group call ");
		}		

		System.out.println("End Group call");			

		try{
			Thread.sleep(5000);
			webWait10.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='table-view']//..//button[@title='End call']")));
			webDriver.findElementByXPath("//div[@class='table-view']//..//button[@title='End call']").click();
			webWait10.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[./div[contains(text(),'End or leave group call?')]]//div/button//span[contains(text(),'End Group Call')]")));
			webDriver.findElementByXPath("//div[./div[contains(text(),'End or leave group call?')]]//div/button//span[contains(text(),'End Group Call')]").click();			
			webWait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[./div[contains(text(),'End or leave group call?')]]")));
			webWait30.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(),'Group Call')]")));			

		} catch(Exception e){
			System.out.println("Unable to end group call");
			Assert.assertFalse(true,"ERROR - Webuser Unable to end group call ");
		}

		webDriver.findElementByXPath("//span[@class='icon ion-ios-chatbubble']").click();
		//Webuser leaves the conversation		
		if ( NetsfereActivity.destroyConversation(webDriver, groupConversationTitle) ==0 ) {
			System.out.println("webuser Destroyed conversation :"+groupConversationTitle );			
		} else {
			System.out.println("webuser Destroyed conversation Failed :"+conversationTitle );			
		}	
		//		if ( NetsfereActivity.destroyConversation(webDriver, conversationTitle) ==0 ) {
		//			System.out.println("webuser Destroyed conversation :"+conversationTitle );			
		//		} else {
		//			System.out.println("webuser Destroyed conversation Failed :"+conversationTitle );			
		//		}


		try {
			if ( NetsfereActivity.WebLogout(webDriver) ==0 ) {
				System.out.println("ChromAPSEser logged out.");
				webDriver.quit();
			} else {
				System.out.println("WebAPSEser logged out failed.");
				webDriver.quit();
			}	
			if( foxDriver != null ) {
				foxDriver.quit();
			}
			if(chromeDriver != null) {
				chromeDriver.quit();
			}
		} catch ( Exception e) {
			System.out.println("Unable to Logiut and quit");
		}

		System.out.println("Time at the END of the Test Execution : " + ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));
		System.out.println("************* Ms1 REGION | Vault check | SUCCESS ************");


	}	

}

