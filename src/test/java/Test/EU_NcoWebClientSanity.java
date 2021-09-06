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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.remote.ErrorHandler.*
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import org.json.simple.JSONObject;
import io.restassured.RestAssured;
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
import com.fasterxml.jackson.annotation.*; 
import com.fasterxml.jackson.databind.*;

public class EU_NcoWebClientSanity  extends Base {
	//	@BeforeClass
	public void precleanup() throws IOException, InterruptedException {
		NetsfereActivity.killAllBrowserSessions();
		String udate = new SimpleDateFormat("dd-MM-yy-HHmmss").format(new java.util.Date());
		//		String proj_dir = System.getProperty("user.dir");
		System.setOut(new PrintStream(new FileOutputStream("\\X:\\"+"Monitoring2-" + udate +".txt")));
		//		System.setOut(new PrintStream(new FileOutputStream("Monitoring-" + udate +".txt")));
		System.out.println("Output File Created");
	}

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
	public void EU_Netsfere_Cleanup() throws InterruptedException, IOException {

		List<String> userIds = new ArrayList<String>();
		
		if(!(Config.getInstance().isDebug())) {
			userIds.add(Config.getInstance().getEUWebId());
			userIds.add(Config.getInstance().getEUChromeId());
			userIds.add(Config.getInstance().getEUFirefoxId());
		} else {
			userIds.add(Config.getInstance().getEUWebIdTest());
			userIds.add(Config.getInstance().getEUChromeIdTest());
			userIds.add(Config.getInstance().getEUFirefoxIdTest());
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
		System.out.println("EU Cleanup has been completed: Success*******.");
	}

	@Test(priority=5)
	public void EU_Netsfere_Message_Call_Check() throws InterruptedException, IOException , AWTException {		

		System.out.println("Start Time : " + ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));		
		HashMap<String , Integer> EUVault_OldData_map = new HashMap<String, Integer>();		
		String EUVaultId = "";
		if( Config.getInstance().isDebug()) {
			EUVaultId = Config.getInstance().getEUVaultIdTest();
		} else {
			EUVaultId = Config.getInstance().getEUVaultId() ;
		}
		EUVault_OldData_map =  NetsfereActivity.getVaultData(EUVaultId);
		if(EUVault_OldData_map.get("RC")  == 0  || EUVault_OldData_map.get("RC")  == 2) {
			System.out.println("Conversations Acrchived Count :" + EUVault_OldData_map.get("ConvCount"));
			System.out.println("Message Archive Count :"+EUVault_OldData_map.get("MesgCount"));
			System.out.println("Report Generated Count :"+EUVault_OldData_map.get("ReportCount") );
			//			System.out.println("Used Storage in MB :"+EUVault_OldData_map.get("UsedStorage"));
			if(EUVault_OldData_map.get("RC")  == 2 ) {
				System.out.println("Error in Logging out from Vault...");
			}
		} else if (EUVault_OldData_map.get("RC")  == 1 ) { 
			System.out.println("Error in acccessing Vault....");			
		}
		NetsfereActivity.killAllBrowserSessions();	

		System.out.println("=========================================================================");
		System.out.println("******** EU Region - Basic health checks started... ************"); 
		System.out.println("TIme at the START of the Test Execution : " + ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));
		System.out.println("==========================================================================");
		
		String  webUserId = "";
		String  webUserDisplayName = "";
		String  chromeUserId = "";
		String  chromeUserDisplayName = "";
		String  firefoxUserId = "";
		String  firefoxUserDisplayName = "";
		
		if( Config.getInstance().isDebug()) {
			webUserId = Config.getInstance().getEUWebIdTest();
			webUserDisplayName = Config.getInstance().getEUWebDisplayNameTest();
			chromeUserId = Config.getInstance().getEUChromeIdTest();
			chromeUserDisplayName = Config.getInstance().getEUChromeDisplayNameTest();
			firefoxUserId =  Config.getInstance().getEUFirefoxIdTest();
			firefoxUserDisplayName = Config.getInstance().getEUFirefoxDisplayNameTest();
			
		} else {
			webUserId = Config.getInstance().getEUWebId();
			webUserDisplayName = Config.getInstance().getEUWebDisplayName();
			chromeUserId = Config.getInstance().getEUChromeId();
			chromeUserDisplayName = Config.getInstance().getEUChromeDisplayName();
			firefoxUserId =  Config.getInstance().getEUFirefoxId();
			firefoxUserDisplayName = Config.getInstance().getEUFirefoxDisplayName();
		}

		// Login -- Web
		System.out.println("Login to Webuser...");
		ChromeDriver webDriver = chromeDriverInitialize();
//		EdgeDriver webDriver = EdgeDriverInitialize();
		WebDriverWait webWait5 = new WebDriverWait(webDriver, 5);
		WebDriverWait webWait10 = new WebDriverWait(webDriver, 10);
		WebDriverWait webWait20 = new WebDriverWait(webDriver, 20);
		WebDriverWait webWait60 = new WebDriverWait(webDriver, 60);
		WebDriverWait webWait30 = new WebDriverWait(webDriver, 30);
		WebDriverWait webWait120 = new WebDriverWait(webDriver, 120);
		JavascriptExecutor jsweb = (JavascriptExecutor) webDriver;

//		int rc = NetsfereActivity.webClientLogin(webDriver, Config.getInstance().getEUWebId());		
		int rc = NetsfereActivity.webClientLogin(webDriver, webUserId);	
		if( rc != 0 ) {
			System.out.println("ERROR -netsfere Login page Not Loaded. Pls Check Internet Connection...");	        
			System.out.println("exiting the programe...");			
			webDriver.close();
			Assert.assertFalse(true,"Error While Logging in to EU Web");
			System.exit(0);
		}	

		//Launch browser and login to netsfere with firefox user		
		System.out.println("Login to Firefoxuser...");
		ChromeDriver foxDriver = chromeDriverInitialize();
		String firefoxWindow = foxDriver.getWindowHandle();
		WebDriverWait foxWait5 = new WebDriverWait(foxDriver, 5);
		WebDriverWait foxWait10 = new WebDriverWait(foxDriver,10);
		WebDriverWait foxWait60 = new WebDriverWait(foxDriver, 60);
		WebDriverWait foxWait30 = new WebDriverWait(foxDriver, 30);
		WebDriverWait foxWait120 = new WebDriverWait(foxDriver, 120);
		foxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor jsfirefox = (JavascriptExecutor) foxDriver;

//		rc = NetsfereActivity.webClientLogin(foxDriver, Config.getInstance().getEUFirefoxId());		
		rc = NetsfereActivity.webClientLogin(foxDriver,firefoxUserId);
		if( rc != 0 ) {
			System.out.println("ERROR -netsfere Login page Not Loaded. Pls Check Internet Connection...");	        
			System.out.println("exiting the programe...");
			webDriver.close();
			Assert.assertFalse(true,"Error While Logging in to EU Firefox");
			System.exit(0);
		}	

		System.out.println("TIme at both user Login : "+ ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));
		// ******************************************************************************
		// Creating a conversation from Webuser to Firefoxuser and sending message in it.
		// *******************************************************************************

		String conversationTitle = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		System.out.println("Chromeuser Starting Conversation With Firefoxuser.");
		String WebGroupConversationTitle = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()); 
		String Message1 = "";
		String Message2 = "";

		try {
			
			webDriver.navigate().refresh();
			webWait120.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Welcome to NetSfere')]")));
			webDriver.findElementByXPath("//div[@class='sideBarMenuButton']//button/span[@class='icon ion-person-stalker']").click();
			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @item='contacts']/following-sibling::div/span[contains(text(),'Search Contacts')]")));
			webDriver.findElementByXPath("//div[@class='sideBarMenuButton']//button/span[@class='icon ion-ios-chatbubble']").click();
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Start Conversation']")));
			webDriver.findElementByXPath("//button[@title='Start Conversation']").click();
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Create')]")));		
			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").click();
			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").sendKeys(conversationTitle);		
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Config.getInstance().getEUFirefoxDisplayName());	
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(firefoxUserDisplayName);
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);
			Thread.sleep(5000);
			try {
//				webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']]")));
//				webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']]").click();
				
				webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+firefoxUserDisplayName+"']]")));
				webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+firefoxUserDisplayName+"']]").click();
				
//				webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getUKFFirefoxDisplayName()+"']")));			
//				webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getUKFFirefoxDisplayName()+"']").click();
			} catch (Exception e1) {
				webDriver.navigate().refresh();
				webWait120.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Welcome to NetSfere')]")));
				webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.CONTROL,"a");
				webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.DELETE);
//				webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Config.getInstance().getEUFirefoxDisplayName());	
				webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(firefoxUserDisplayName);	
				webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);
//				webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']")));			
//				webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']").click();
				webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+firefoxUserDisplayName+"']")));			
				webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+firefoxUserDisplayName+"']").click();
			}

//			webDriver.findElementByXPath("//button[@title='Start Conversation']").click();
//			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Create')]")));		
//			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").click();
//			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").sendKeys(conversationTitle);		
//			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Config.getInstance().getEUFirefoxDisplayName());	
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);			
//			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']")));			
//			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']").click();
//			Thread.sleep(1000);
			webWait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Create')]")));
			webDriver.findElementByXPath("//span[contains(text(),'Create')]").click();
			try {
				webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='screenOverlayText' and contains(text(),'Creating conversation...')]")));
				webWait60.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='screenOverlayText' and contains(text(),'Creating conversation...')]")));
			} catch (Exception e) {
				System.out.println("EU Webuser Unable to create conversation with EU Firefoxuser.");
				Assert.assertFalse(true,"Error EU WebUser Unable to Create COnversation with EU Firefox User");
			}

			// Check if created COnversation Exist.
			try {
				webDriver.findElementByXPath("//button[@title='Start Conversation']/../div/input").sendKeys(conversationTitle);					
				webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+conversationTitle+"')]")));				
				webDriver.findElementByXPath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+conversationTitle+"')]").click();				
			} catch (Exception e) {
				System.out.println("Webuser Unable to create conversation with Firefoxuser.");
				Assert.assertFalse(true,"Error EU WebUser Unable to Create COnversation with EU Firefox User");
			}
			webWait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@class='namegenTextLong']")));
			if(webDriver.findElementByXPath("//textarea[@class='namegenTextLong']") != null) {
				System.out.println("Webuser created conversation with Firefoxuser successfully.");				
				System.out.println("Sending message in 1-1 conversation");
				Message1= new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				Message1 = Message1 +" : EU - Hello from Webuser";
				webDriver.findElement(By.xpath("//textarea[@class='namegenTextLong']")).sendKeys(Message1);
				webDriver.findElementByXPath("//span[@title='Send Message']").click();				
				webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bubbleWrap']/div[.//span[contains(text(),'"+Message1+"')]]/../following-sibling::div/div//span[@class='fa fa-check']")));
				WebElement sentStaus = webDriver.findElementByXPath("//div[@class='bubbleWrap']/div[.//span[contains(text(),'"+Message1+"')]]/../following-sibling::div/div//span[@class='fa fa-check']");				
				if (sentStaus.getAttribute("title").contains("Read") || sentStaus.getAttribute("title").contains("Sent") ) {
					System.out.println("Webuser sent the message");
				} else {
					System.out.println("Webuser failed to send the message");
					Assert.assertFalse(true,"Error EU WebUser Unable to send message");
				}
			}
		} catch(Exception e) {
			System.out.println("Webuser Unable to create conversation with Firefoxuser.");
			Assert.assertFalse(true,"Error EU WebUser Unable to Create COnversation with EU Firefox User");
		}		

		System.out.println("Firefoxuser validates the created conversation");	
		foxDriver.navigate().refresh();
//		Thread.sleep(2000);
		foxWait120.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Welcome to NetSfere')]")));

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
			System.out.println("ERROR- Conversation created by Webuser with Firefoxuser does not exists on Firefoxuser side Or Firefoxuser did not receive the message sent by Chromeuser.");
			Assert.assertFalse(true,"ERROR - EU Firefox user did not get the message");
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
				NetsfereActivity.attachImage_win();
//				NetsfereActivity.attachImage_win_Robot();
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
					System.out.println("Chromeuser Received Image");
				}
			}
		} catch ( NoSuchElementException e) {		
			System.out.println("Chromeuser DID NOT recieve the IMAGE from Chromeuser....  ");
			Assert.assertFalse(true,"ERROR - WebUser Didn't receive the attachment. ");
		}



		//******************************
		//  HD 1-1 Call 
		//******************************

		// Webuser make a call to webeuser2..
		System.out.println("HD call test case execution started: from webUser to foxUser ");

		webDriver.findElementByXPath("//span[@class='icon ion-person-stalker']").click();
		webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[./input[@type='text']]")));
		webDriver.findElementByXPath("//div[./input[@type='text']]").click();
//		webDriver.findElementByXPath("//input[@type='text']").sendKeys(Config.getInstance().getEUFirefoxDisplayName());
		webDriver.findElementByXPath("//input[@type='text']").sendKeys(firefoxUserDisplayName);
		//		webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='material-icons' and contains(text(),'search')]/../../..//input")));
		//		
		//		webDriver.findElementByXPath("//span[@class='material-icons' and contains(text(),'search')]/../../..//input").sendKeys(Config.getInstance().getEUFirefoxDisplayName());
//		webWait10.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='table-view']//div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']")));
//		webDriver.findElementByXPath("//div[@class='table-view']//div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']").click();
		webWait10.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='table-view']//div[@displayname='"+firefoxUserDisplayName+"']")));
		webDriver.findElementByXPath("//div[@class='table-view']//div[@displayname='"+firefoxUserDisplayName+"']").click();
//		webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='icon ion-android-call' and @title='Call']")));
//		webDriver.findElementByXPath("//span[@class='icon ion-android-call' and @title='Call']").click();
		webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Call']/preceding-sibling::button[./span[@class='icon ion-android-call']]")));
		webDriver.findElementByXPath("//div[text()='Call']/preceding-sibling::button[./span[@class='icon ion-android-call']]").click();

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
				System.out.println("Firefoxuser Received the HD call from Chromeuser");
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
		System.out.println("Login to Second Chromeuser...");
		//		String anotherchromeWindow = chromeDriver.getWindowHandle();

//		rc = NetsfereActivity.webClientLogin(chromeDriver, Config.getInstance().getEUChromeId());	
		rc = NetsfereActivity.webClientLogin(chromeDriver, chromeUserId);	
		if( rc != 0 ) {
			System.out.println("ERROR -netsfere Login page Not Loaded. Pls Check Internet Connection...");	        
			System.out.println("exiting the programe...");
			webDriver.close();
			Assert.assertFalse(true,"Unable to login to ChromeUser");
			System.exit(0);
		}		


		//******************************
		// Group Conversation
		//******************************
		String groupConversationTitle = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()); 
		System.out.println("Web User Create group conversation to initiate group call");		

		try {
			webDriver.findElementByXPath("//div[@class='sideBarMenuButton']//button/span[@class='icon ion-person-stalker']").click();
			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @item='contacts']/following-sibling::div/span[contains(text(),'Search Contacts')]")));
			webDriver.findElementByXPath("//div[@class='sideBarMenuButton']//button/span[@class='icon ion-ios-chatbubble']").click();
			webDriver.navigate().refresh();
			webWait120.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Welcome to NetSfere')]")));
			
//			webDriver.findElementByXPath("//div[@class='sideBarMenuButton']//button/span[@class='icon ion-person-stalker']").click();
//			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @item='contacts']/following-sibling::div/span[contains(text(),'Search Contacts')]")));
			webDriver.findElementByXPath("//div[@class='sideBarMenuButton']//button/span[@class='icon ion-ios-chatbubble']").click();
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Start Conversation']")));
			webDriver.findElementByXPath("//button[@title='Start Conversation']").click();
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Create')]")));		
			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").click();
//			webDriver.navigate().refresh();
//			webWait120.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Welcome to NetSfere')]")));
			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").sendKeys(groupConversationTitle);		
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Config.getInstance().getEUFirefoxDisplayName());	
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(firefoxUserDisplayName);
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);
//			Thread.sleep(5000);
//			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']]")));
//			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']]").click();
//			
			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+firefoxUserDisplayName+"']]")));
			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+firefoxUserDisplayName+"']]").click();
//			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getUMRFirefoxDisplayName()+"']")));			
//			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getUMRFirefoxDisplayName()+"']").click();
			
			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Config.getInstance().getEUChromeDisplayName());	
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(chromeUserDisplayName);
			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);	
//			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+Config.getInstance().getEUChromeDisplayName()+"']]")));
//			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+Config.getInstance().getEUChromeDisplayName()+"']]").click();
//			
			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+chromeUserDisplayName+"']]")));
			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']/div[.//div[@displayname='"+chromeUserDisplayName+"']]").click();
			
//			webDriver.findElementByXPath("//span[@class='icon ion-ios-chatbubble']").click();
//			webWait10.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Start Conversation']")));
//			webDriver.findElementByXPath("//button[@title='Start Conversation']").click();
//			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/input[contains(@class,'namegenTitleReplace')]")));
//			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").click();
//			webDriver.findElementByXPath("//div/input[contains(@class,'namegenTitleReplace')]").sendKeys(groupConversationTitle);		
//			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Config.getInstance().getEUFirefoxDisplayName());	
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);			
//			webWait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']")));			
//			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getEUFirefoxDisplayName()+"']").click();
//			Thread.sleep(1000);
//			webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@item='inviteContacts']")));
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Config.getInstance().getEUChromeDisplayName());	
//			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);	
////			webDriver.findElementByXPath("//input[@type='text' and @class='namegenEmailReplace']/../button").click();
//			Thread.sleep(1000);
////			webDriver.findElementByXPath("//input[@item='inviteContacts']").clear();
////			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Config.getInstance().getEUChromeDisplayName());	
////			webDriver.findElementByXPath("//input[@item='inviteContacts']").sendKeys(Keys.RETURN);
////			Thread.sleep(1000);
//			webWait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getEUChromeDisplayName()+"']")));			
//			webDriver.findElementByXPath("//div[@class='mainDiv']//div[2]//div[@class='scrollbox']/div[@class='table-view']//div[4]/div[@displayname='"+Config.getInstance().getEUChromeDisplayName()+"']/..").click();
//			Thread.sleep(1000);					
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
				System.out.println("Firefoxuser Received the group call from SecondChromeuser");
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
				System.out.println("Chromeuser Received the group call from Webuser");
			}

			if(chromeDriver.findElementByXPath("//button[@title='Accept']") != null) {
				chromeDriver.findElementByXPath("//button[@title='Accept']").click();				
				System.out.println("Chromeuser Accepted the call");								
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
			Thread.sleep(2000);
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
//		if ( NetsfereActivity.destroyConversation(webDriver, groupConversationTitle) ==0 ) {
//			System.out.println("webuser Destroyed conversation :"+groupConversationTitle );			
//		} else {
//			System.out.println("webuser Destroyed conversation Failed :"+conversationTitle );			
//		}	
		//		if ( NetsfereActivity.destroyConversation(webDriver, conversationTitle) ==0 ) {
		//			System.out.println("webuser Destroyed conversation :"+conversationTitle );			
		//		} else {
		//			System.out.println("webuser Destroyed conversation Failed :"+conversationTitle );			
		//		}
		try {
			if ( NetsfereActivity.WebLogout(webDriver) ==0 ) {
				System.out.println("EU Web User logout.");
				webDriver.quit();
			} else {
				System.out.println("EU Web User logout failed.");
				webDriver.quit();
			}	
			if( foxDriver != null ) {
				if ( NetsfereActivity.WebLogout(foxDriver) ==0 ) {
					System.out.println("EU Firefox User logout.");
					foxDriver.quit();
				} else {
					System.out.println("EU Firefox User logout failed.");
					foxDriver.quit();
				}	
				
			}
			if( chromeDriver != null ) {
				if ( NetsfereActivity.WebLogout(chromeDriver) ==0 ) {
					System.out.println("EU Chrome User logout.");
					chromeDriver.quit();
				} else {
					System.out.println("EU Chrome User logout failed.");
					chromeDriver.quit();
				}	
			}
				
			
		} catch ( Exception e) {
			System.out.println("Unable to Logiut and quit");
		}
		
		NetsfereActivity.killAllBrowserSessions();	

		HashMap<String , Integer> EUVault_NewData_map = new HashMap<String, Integer>();	
		EUVault_NewData_map = NetsfereActivity.getVaultData(Config.getInstance().getEUVaultId());
		if(EUVault_NewData_map.get("RC")  == 0  || EUVault_NewData_map.get("RC")  == 2) {
			System.out.println("Conversations Acrchived Count :" + EUVault_NewData_map.get("ConvCount"));
			System.out.println("Message Archive Count :"+EUVault_NewData_map.get("MesgCount"));
			System.out.println("Report Generated Count :"+EUVault_NewData_map.get("ReportCount") );
			//					System.out.println("Used Storage in MB :"+EUVault_NewData_map.get("UsedStorage"));
			if(EUVault_NewData_map.get("RC")  == 2 ) {
				System.out.println("Error in Logging out from Vault...");
			}
		} else if (EUVault_NewData_map.get("RC")  == 1 ) { 
			System.out.println("Error in acccessing Vault....");
		}

		if ( EUVault_NewData_map.get("MesgCount") > EUVault_OldData_map.get("MesgCount") ) {
			System.out.println("EU Archived message count has increased...");
			System.out.println("EU Vault test Case is success.");
		} else {
			System.out.println("No Increase in EU Archived message count ...");
			System.out.println("EU Vault test Case is Failed.");
		}
		if ( EUVault_NewData_map.get("ConvCount") > EUVault_OldData_map.get("ConvCount")) {
			System.out.println("EU Archived COnversations count has increased...");
		} else {
			System.out.println("No Increase in EU Archived COnversations count ...");
		}		
		//			if (EUVault_NewData_map.get("ReportCount") > EUVault_OldData_map.get("ReportCount")) {
		//				System.out.println("EU Archive Reports Generated count has increased...");			
		//			} else {
		//				System.out.println("No Increase in EU Vault Reports count...");
		//			}
		//			if ( EUVault_NewData_map.get("UsedStorage")> EUVault_OldData_map.get("UsedStorage")) {
		//				System.out.println("EU Vault Storage Usage Increased ....");
		//			} else {
		//				System.out.println("NO Increase in EU Vault Storage Usage...");
		//			}

		NetsfereActivity.killAllBrowserSessions();	
		System.out.println("=========================================================================");
		System.out.println("******** EU Region - Basic health checks Ended... ************"); 
		System.out.println("TIme at the END of the Test Execution : " + ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));
		System.out.println("==========================================================================");



	}	
	
	@SuppressWarnings("unchecked")
	@JsonPropertyOrder({"email","password"})
	@Test(priority=6)
	
	public void Netsfere_EU_API() throws FileNotFoundException {
		RestAssured.baseURI ="https://api-eu.netsfere.com";
		RequestSpecification APIRequest = RestAssured.given();
		//JSONObject requestParams = NetsfereActivity.APIObject();
		//JSONObject requestParams = new JSONObject();
		HashMap<String , String> requestParams = new HashMap<String, String>();
		//APIRequest.header("Content-Type", "application/json");
		APIRequest.header("Content-Type","application/x-www-form-urlencoded");
		APIRequest.header("authorization","Bearer {token}");

		String  webUserId = "";
		String  webUserDisplayName = "";
		String  chromeUserId = "";
		String  chromeUserDisplayName = "";
		String  firefoxUserId = "";
		String  firefoxUserDisplayName = "";
		
		if( Config.getInstance().isDebug()) {
			webUserId = Config.getInstance().getEUWebIdTest();
			webUserDisplayName = Config.getInstance().getEUWebDisplayNameTest();
			chromeUserId = Config.getInstance().getEUChromeIdTest();
			chromeUserDisplayName = Config.getInstance().getEUChromeDisplayNameTest();
			firefoxUserId =  Config.getInstance().getEUFirefoxIdTest();
			firefoxUserDisplayName = Config.getInstance().getEUFirefoxDisplayNameTest();
			
		} else {
			webUserId = Config.getInstance().getEUWebId();
			webUserDisplayName = Config.getInstance().getEUWebDisplayName();
			chromeUserId = Config.getInstance().getEUChromeId();
			chromeUserDisplayName = Config.getInstance().getEUChromeDisplayName();
			firefoxUserId =  Config.getInstance().getEUFirefoxId();
			firefoxUserDisplayName = Config.getInstance().getEUFirefoxDisplayName();
		}

		String email = webUserId;
		String udate = new SimpleDateFormat("dd-MM-yy-HHmmss").format(new java.util.Date());
		String convTitle = "API : "+udate;
		udate = new SimpleDateFormat("dd-MM-yy-HHmmss").format(new java.util.Date());
		String msgText = "API Test Message - Sending message in newly created conversation :"+udate;
		String participants = firefoxUserId;
		requestParams.put("email", email);
		requestParams.put("password", "abcdefgh");
		requestParams.put("convTitle", convTitle);
		requestParams.put("msgText", msgText);
		requestParams.put("participants", participants );
		String bodyData ="";

		for( String key : requestParams.keySet()) {
			String value = requestParams.get(key).toString().trim();
			//String value = requestParams.get(key).toString().trim().replaceAll(" ","20%");
			bodyData += key.toString()+"="+value+"&" ;
		}

		System.out.println(bodyData);


		//APIRequest.body(requestParams.toJSONString());
		APIRequest.body(bodyData);
		Response response = APIRequest.post("/send.php");
		//Response response = APIRequest.body(requestParams.toJSONString()).post();
		System.out.println(response.asString());
		int statusCode = response.getStatusCode();
		System.out.println("Return Code :"+statusCode);
		if ( statusCode != 200) {
			String errorMsg = response.getStatusLine();
			System.out.println("error Message  : "+errorMsg);
			Assert.assertEquals(statusCode, 200);
		}
		String RC_convId = response.jsonPath().getJsonObject("convId");
		String RC_msgId = response.jsonPath().getJsonObject("msgId");

		System.out.println("Created COnversation ID :"+RC_convId);
		System.out.println("Message sent Message ID :"+RC_msgId);
		
		Assert.assertNotEquals(RC_convId, null);
		Assert.assertNotEquals(RC_msgId, null);

		///**********
		// Sending message in exisitng conversation
		//***********

		HashMap<String , String> requestParams1 = new HashMap<String, String>();
		requestParams1.put("email", email);
		requestParams1.put("password", "abcdefgh");
		requestParams1.put("convId", RC_convId);
		udate = new SimpleDateFormat("dd-MM-yy-HHmmss").format(new java.util.Date());
		msgText = "API Test Message -Sending message in exisitng conversation :"+udate;
		requestParams1.put("msgText", msgText);

		bodyData ="";

		for( String key : requestParams1.keySet()) {
			String value = requestParams1.get(key).toString().trim();
			//				String value = requestParams.get(key).toString().trim().replaceAll(" ","20%");
			bodyData += key.toString()+"="+value+"&" ;
		}

		System.out.println(bodyData);

		APIRequest.body(bodyData);
		response = APIRequest.post("/send.php");
		//			Response response = APIRequest.body(requestParams.toJSONString()).post();
		System.out.println(response.asString());
		statusCode = response.getStatusCode();
		System.out.println("Return Code :"+statusCode);
		
		if ( statusCode != 200) {
			String errorMsg = response.getStatusLine();
			System.out.println("error Message  : "+errorMsg);
			Assert.assertEquals(statusCode, 200);
		}
		RC_convId = response.jsonPath().getJsonObject("convId");
		RC_msgId = response.jsonPath().getJsonObject("msgId");

		System.out.println("Created COnversation ID :"+RC_convId);
		System.out.println("Message sent Message ID :"+RC_msgId);
		
		Assert.assertNotEquals(RC_convId, null);
		Assert.assertNotEquals(RC_msgId, null);
		Assert.assertEquals(statusCode, 200);
		
	}



}

