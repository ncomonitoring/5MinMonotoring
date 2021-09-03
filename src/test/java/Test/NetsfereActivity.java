package Test;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.SikuliException;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

public class NetsfereActivity  extends Base{

	public static int AccountCleanUp(String account) throws InterruptedException, IOException {

		
		ChromeDriver  clenupDriver = chromeDriverInitialize();
		WebDriverWait webWait = new WebDriverWait(clenupDriver, 60);
		WebDriverWait webWait2 = new WebDriverWait(clenupDriver, 5);
		WebDriverWait webWait3 = new WebDriverWait(clenupDriver, 120);
		JavascriptExecutor js = (JavascriptExecutor) clenupDriver;
		int refresh_count = 0;
		Thread.sleep(3000);

		int rc = NetsfereActivity.webClientLogin(clenupDriver, account);
		if( rc != 0 ) {
			System.out.println("ERROR -netsfere Login page Not Loaded. Pls Check Internet Connection...");	        
			System.out.println("exiting the programe...");			
			clenupDriver.close();
			return 0;
		}	


		try {
			webWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']/div[2]/div/div")));
			List<WebElement> netcelems = clenupDriver.findElementsByXPath("//div[@class='scrollbox']/div[2]/div/div");
			if(netcelems.size() <= 1) {
				System.out.println("Only Net-c conversation exists in " + account);
				Thread.sleep(2000);
				if( WebLogout(clenupDriver) == 0) {
					System.out.println("Logout of user is successful");					
				} else {
					System.out.println("Logout of user is  NOT successful");	
					clenupDriver.quit();
				}
				clenupDriver.quit();
				return 1;
			} else if(netcelems.size() > 1) {
				while(netcelems.size() > 1) {
					netcelems.get(1).click();
					clenupDriver.findElementByXPath("//span[@class='fa fa-sign-out']").click();
					Thread.sleep(1000);
					try {
						if (clenupDriver.findElementByXPath("//button//span[contains(text(),'Destroy')]").isDisplayed() ) {								
							clenupDriver.findElementByXPath("//button//span[contains(text(),'Destroy')]").click();
							Thread.sleep(1000);
							clenupDriver.findElementByXPath("//button//span[contains(text(),'Destroy')]").click();
						}
					} catch (NoSuchElementException e10) {
						clenupDriver.findElementByXPath("//div[contains(text(),'Leave Conversation')]/..//span[contains(text(),'Leave')]").click();	
						Thread.sleep(3000);
					}
					Thread.sleep(3000);

					netcelems =  clenupDriver.findElementsByXPath("//div[@class='scrollbox']/div[2]/div/div");
					refresh_count = refresh_count + 1 ;
					if (refresh_count > 19 ) {
						Thread.sleep(2000);
						refresh_count = 0;
					}
				}
				System.out.println("Only Net-c conversation exists in " + account);
				if( WebLogout(clenupDriver) == 0) {
					System.out.println("Logout of user is successful");					
				} else {
					System.out.println("Logout of user is  NOT successful");
					clenupDriver.quit();
				}

				clenupDriver.quit();
				return 1;				
			}
		} catch ( NoSuchElementException e2) {
			clenupDriver.quit();
			return 0;
		}
		clenupDriver.quit();
		return 1;

	}

	public static int webClientLogin(ChromeDriver Driver , String accountId) throws InterruptedException {

		WebDriverWait webWait = new WebDriverWait(Driver, 60);
		WebDriverWait webWait2 = new WebDriverWait(Driver, 5);
		WebDriverWait webWait3 = new WebDriverWait(Driver, 120);
		WebDriverWait webWait4 = new WebDriverWait(Driver, 3);
		WebDriverWait webWait5 = new WebDriverWait(Driver, 15);

		JavascriptExecutor js = (JavascriptExecutor) Driver;

		try {

			Driver.get(Config.getInstance().getwebUrl());
			webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='loginPageContent']//div[contains(text(),'Login to your account')]")));
			if (Driver.findElementByXPath("//input[@type='email' and @placeholder='Enter email address']") != null ) {
				Driver.findElementByXPath("//input[@type='email' and @placeholder='Enter email address']").sendKeys(accountId);
				Driver.findElementByXPath("//input[@type='password']").sendKeys("abcdefgh");
				webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
				Driver.findElementByXPath("//button[@type='submit']").click();
				//				Thread.sleep(4000);
				try {
					webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feature-intro']")));
				} catch ( Exception e3 ) {
					try {
						webWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='screenOverlaySpinner']/following-sibling::div[contains(text(),'Loading...')]")));
						Driver.navigate().refresh();
						webWait3.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='screenOverlaySpinner']/following-sibling::div[contains(text(),'Loading...')]")));
						//						Driver.navigate().refresh();
					}catch (NoSuchElementException e2) {
						try {
							Driver.navigate().refresh();
							webWait3.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='screenOverlaySpinner']/following-sibling::div[contains(text(),'Loading...')]")));
							webWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feature-intro']")));
						} catch (Exception e10) {
							System.out.println("Taking too long time to Login");
							System.out.println("netsfere Login Failure ..."); 	
							Driver.close();
							return 1;
						}

					}	
				}
				try {
					System.out.println("Login Succeeded.  continue....");
					webWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feature-intro']")));
					try {
						webWait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feature-intro']")));
						if (Driver.findElementByXPath("//div[@id='feature-intro']").isDisplayed()) {
							WebElement newFeatureIntroCloseButton = Driver.findElementByXPath("//div[@id='feature-intro']/following-sibling::button");
							js.executeScript("arguments[0].click();", newFeatureIntroCloseButton);
						}
					} catch (NoSuchElementException e1) {
						System.out.println("New feature intro page is not displayed");
					}						
					webWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Welcome to NetSfere')]")));
					return 0;	

				} catch (NoSuchElementException e2) {
					webWait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invalid email address and/or password')]")));
					System.out.println("issue while logging in., try again.");
					Driver.findElementByXPath("//button[@type='submit']").click();
					try {
						webWait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invalid email address and/or password')]")));
						System.out.println("netsfere Login Failure ..."); 	
						Driver.close();
						return 1;
					}catch (NoSuchElementException e3) {
						System.out.println("Login Succeeded at 2nd attempt. continue....");
						try {
							webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feature-intro']")));
							if (Driver.findElementByXPath("//div[@id='feature-intro']").isDisplayed()) {
								WebElement newFeatureIntroCloseButton = Driver.findElementByXPath("//div[@id='feature-intro']/following-sibling::button");
								js.executeScript("arguments[0].click();", newFeatureIntroCloseButton);
							}
						} catch (NoSuchElementException e1) {
							System.out.println("New feature intro page is not displayed");
						}						
						webWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Welcome to NetSfere')]")));
						return 0;
					}

				} 
			}
		}catch ( NoSuchElementException e) {
			System.out.println("netsfere Login Failure ..."); 	
			Driver.close();
			return 1;		       
		}
		return 1;

	}
	public static ChromeDriver ACPLogin(String accountId) throws InterruptedException, IOException {

		killAllBrowserSessions();
		ChromeDriver Driver = chromeDriverInitialize();

		WebDriverWait webWait = new WebDriverWait(Driver, 60);
		WebDriverWait webWait2 = new WebDriverWait(Driver, 5);
		WebDriverWait webWait3 = new WebDriverWait(Driver, 120);
		WebDriverWait webWait4 = new WebDriverWait(Driver, 3);
		WebDriverWait webWait5 = new WebDriverWait(Driver, 15);

		JavascriptExecutor js = (JavascriptExecutor) Driver;

		try {

			Driver.get(Config.getInstance().getacpUrl());
			webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Administrator Account')]")));
			if (Driver.findElementByXPath("//input[@type='email']") != null ) {
				Driver.findElementByXPath("//input[@type='email']").clear();
				Driver.findElementByXPath("//input[@type='email']").sendKeys(accountId);
				Driver.findElementByXPath("//input[@type='password']").clear();
				Driver.findElementByXPath("//input[@type='password']").sendKeys("abcdefgh");
				webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
				Driver.findElementByXPath("//button[@type='submit']").click();
				// Thread.sleep(4000);
				try {
					webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Admin Control Panel')]")));
					webWait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://netsfere.com/support']/button/span[contains(text(),'Contact Us')]")));
				} catch ( Exception e3 ) {
					System.out.println("Taking too long time to Login");
					System.out.println("netsfere ACP Login Failure ...");
					Driver.close();
					return null;
				}
				return Driver;
			}
		} catch ( Exception e4 ) {
			System.out.println("netsfere ACP Login Failure ...");
			Driver.close();
			return null;
		}
		return null;

	}

	public static  void ACPLogout(ChromeDriver Driver) throws InterruptedException {
		WebDriverWait webWait = new WebDriverWait(Driver, 20);
		Driver.navigate().refresh();
		try {
			webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Administrator Account')]")));
		} catch ( Exception e) {
			System.out.println("Not in ACP Page. ACP  Logout Failure ...");
			Driver.close();
		}
		try {
			webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[./i[@class='fas fa-sign-out-alt fa-fw']/following-sibling::div[contains(text(),'Logout')]]")));
			Driver.findElementByXPath("//div[./i[@class='fas fa-sign-out-alt fa-fw']/following-sibling::div[contains(text(),'Logout')]]").click();
			Driver.close();
		}catch ( Exception e1) {
			System.out.println("Not in ACP Page. ACP  Logout Failure ...");
			Driver.close();
		}
	}
	//div[./i[@class='fas fa-sign-out-alt fa-fw']/following-sibling::div[contains(text(),'Logout')]]

	public static int ForceLogoutAllSessions(ChromeDriver Driver , String accountId) throws InterruptedException {

		WebDriverWait webWait = new WebDriverWait(Driver, 60);
		WebDriverWait webWait2 = new WebDriverWait(Driver, 5);
		WebDriverWait webWait3 = new WebDriverWait(Driver, 120);
		WebDriverWait webWait4 = new WebDriverWait(Driver, 3);
		WebDriverWait webWait5 = new WebDriverWait(Driver, 15);
		Driver.navigate().refresh();

		//i[@class='fas fa-users fa-fw']/following-sibling::div/span[@class='icon ion-android-arrow-dropdown']

		//i[@class='fas fa-users fa-fw']/following-sibling::div/span[@class='icon ion-android-arrow-dropright']
		Thread.sleep(2000);
		try {
			webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[./i[@class='fas fa-users fa-fw']]")));
			if (Driver.findElementByXPath("//div[./i[@class='fas fa-users fa-fw']]").isDisplayed()) {
				Driver.findElementByXPath("//div[./i[@class='fas fa-users fa-fw']]").click();
			}
		} catch (NoSuchElementException e1) {
			System.out.println("Not in ACP Page");
			Driver.close();
			return 1;
		}
		Thread.sleep(2000);
		try {
			webWait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fas fa-users fa-fw']/following-sibling::div/span[@class='icon ion-android-arrow-dropdown']")));
			if( Driver.findElementByXPath("//div[./i[@class='fas fa-users fa-fw']]/following-sibling::ul//a[@href='#Active Users']//div[contains(text(),'Active Users')]").isDisplayed() ){
				Driver.findElementByXPath("//div[./i[@class='fas fa-users fa-fw']]/following-sibling::ul//a[@href='#Active Users']//div[contains(text(),'Active Users')]").click();
			}
		} catch (Exception e2) {
			webWait5.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//i[@class='fas fa-users fa-fw']/following-sibling::div/span[@class='icon ion-android-arrow-dropright']")));
			Driver.findElementByXPath("//i[@class='fas fa-users fa-fw']/following-sibling::div/span[@class='icon ion-android-arrow-dropright']").click();
			// if (Driver.findElementByXPath("//div[./i[@class='fas fa-users fa-fw']]").isDisplayed()) {
			// Driver.findElementByXPath("//div[./i[@class='fas fa-users fa-fw']]").click();
			// }
			if( Driver.findElementByXPath("//div[./i[@class='fas fa-users fa-fw']]/following-sibling::ul//a[@href='#Active Users']//div[contains(text(),'Active Users')]").isDisplayed() ){
				Driver.findElementByXPath("//div[./i[@class='fas fa-users fa-fw']]/following-sibling::ul//a[@href='#Active Users']//div[contains(text(),'Active Users')]").click();
			}
		}
		Thread.sleep(2000);
		try {
			Driver.findElementByXPath("//select[.//option[@value='chatSpongeUname']]").click();
			Driver.findElementByXPath("//select[@title]//option[@value='chatSpongeUname']").click();
			Driver.findElementByXPath("//select[@title]//option[@value='=']").click();
			Driver.findElementByXPath("//div[./input[@type]]/input").clear();
			Driver.findElementByXPath("//div[./input[@type]]/input").sendKeys(accountId);
		} catch ( Exception e6) {
			System.out.println("Error while searching the user");
			Driver.close();
			return 1;
		}
		Thread.sleep(1000);
		try {
			webWait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+accountId+"')]/preceding-sibling::td/i[@class='far fa-square fa-fw']")));
			if( Driver.findElementByXPath("//td[contains(text(),'"+accountId+"')]/preceding-sibling::td/i[@class='far fa-square fa-fw']").isDisplayed()) {
				Driver.findElementByXPath("//td[contains(text(),'"+accountId+"')]/preceding-sibling::td/i[@class='far fa-square fa-fw']").click();
			}
			Thread.sleep(1000);
			webWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[./i[@class='fa fa-sign-out-alt pr-2']]")));
			if(Driver.findElementByXPath("//div[./i[@class='fa fa-sign-out-alt pr-2']]").isDisplayed()) {
				Driver.findElementByXPath("//div[./i[@class='fa fa-sign-out-alt pr-2']]").click();
			}
			Thread.sleep(1000);
			webWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'NetSfere')]/following-sibling::div[contains(text(),'Successfully forced logout for')]/following-sibling::div/button/span[contains(text(),'Ok')]")));
			if(Driver.findElementByXPath("//div[contains(text(),'NetSfere')]/following-sibling::div[contains(text(),'Successfully forced logout for')]/following-sibling::div/button/span[contains(text(),'Ok')]").isDisplayed()) {
				Driver.findElementByXPath("//div[contains(text(),'NetSfere')]/following-sibling::div[contains(text(),'Successfully forced logout for')]/following-sibling::div/button/span[contains(text(),'Ok')]").click();
				//				Driver.close();
				Thread.sleep(1000);
				return 0;
			}

		} catch (Exception e5) {
			if ( Driver.findElementByXPath("//div[contains(text(),'No data available in table')]").isDisplayed() ) {
				System.out.println("User :"+accountId+ "  Not Found");
			} else {
				System.out.println("Error while force Logging out a user");
				Driver.close();
				return 1;
			}
		}
		return 1;
	}

	public static HashMap<String , Integer> getVaultData(String vaultId) throws InterruptedException {		

		HashMap<String , Integer> Vault_data_map = new HashMap<String, Integer>();
		ChromeDriver vaultDriver = chromeDriverInitialize();
		WebDriverWait vaultWait = new WebDriverWait(vaultDriver, 60);	
		WebDriverWait vaultWait3 = new WebDriverWait(vaultDriver, 120);

		JavascriptExecutor js = (JavascriptExecutor) vaultDriver;

		String MsgCount = "";
		String ConvCount = "";
		String ReportGeneratedCount = "";
		String usedStorage = "";

		try {
			if (vaultId.contains("eu")) {
				vaultDriver.get(Config.getInstance().geteu1VaultUrl());
			} else if(vaultId.contains("apse")) {
				vaultDriver.get(Config.getInstance().getapseVaultUrl());				
			} else {
				vaultDriver.get(Config.getInstance().getms2VaultUrl());
			}
			vaultWait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/img[@src='../../images/vaultLogo.svg']")));
			System.out.println("vault.netsfere.com has loaded...");
			if (vaultDriver.findElementByXPath("//input[@type='email' and @placeholder='Enter email address']") != null ) {
				vaultDriver.findElementByXPath("//input[@type='email' and @placeholder='Enter email address']").sendKeys(vaultId);
				vaultDriver.findElementByXPath("//input[@type='password']").sendKeys("abcdefgh");
				vaultWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
				vaultDriver.findElementByXPath("//button[@type='submit']").click();
				try {
					vaultWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-dashboard pr-3']/following-sibling::span[contains(text(),'Dashboard')]")));
					System.out.println("Login to Vault successfull...");
					Vault_data_map.put("RC", 0);
				} catch( NoSuchElementException e) {
					System.out.println("ERROR- Login to Vault failed...");
					Vault_data_map.put("RC", 1);
					vaultDriver.quit();
					return Vault_data_map;
				}
			}
		} catch( NoSuchElementException e) {
			System.out.println("ERROR- vault.netsfere.com has not loaded...");
			Vault_data_map.put("RC", 1);
			vaultDriver.quit();
			return Vault_data_map;				
		}

		Thread.sleep(5000);		

		//	vaultWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dashboard']//div[@class='card-block d-block']//h1[@id='msgArchived']")));
		vaultWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id='msgArchived']")));
		//	MsgCount = vaultDriver.findElement(By.xpath("//div[@id='dashboard']//div[@class='card-block d-block']//h1[@id='msgArchived']")).getText();
		MsgCount = vaultDriver.findElement(By.xpath("//h1[@id='msgArchived']")).getText();
		Vault_data_map.put("MesgCount", Integer.parseInt(MsgCount));
		//	ConvCount = vaultDriver.findElement(By.xpath("//div[@id='dashboard']//div[@class='card-block d-block']//h1[@id='consvArchived']")).getText();
		ConvCount = vaultDriver.findElement(By.xpath("//h1[@id='consvArchived']")).getText();
		Vault_data_map.put("ConvCount", Integer.parseInt(ConvCount));
		//	ReportGeneratedCount = vaultDriver.findElement(By.xpath("//div[@id='dashboard']//div[@class='card-block d-block']//h1[@id='reportsGenerated']")).getText();
		ReportGeneratedCount = vaultDriver.findElement(By.xpath("//h1[@id='reportsGenerated']")).getText();
		Vault_data_map.put("ReportCount", Integer.parseInt(ReportGeneratedCount));
		//	usedStorage =  vaultDriver.findElement(By.xpath("//div[@id='dashboard']//div[@class='card-block d-block']//h1[@id='usedStorage']")).getText();
		//	usedStorage =  vaultDriver.findElement(By.xpath("//h1[@id='usedStorage']")).getText();
		//	System.out.println("Murali - Used Storage "+usedStorage);
		//	Vault_data_map.put("UsedStorage", Integer.parseInt(usedStorage.replace("Mb", "")));

		try {
			vaultDriver.findElementByXPath("//ul[@class='nav nav-pills flex-column py-3']/li[7]//span").click();
			vaultWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and text()='Sign Out']"))).click();
			System.out.println("Logged out of vault...");
			vaultDriver.quit();
			return Vault_data_map;
		} catch( NoSuchElementException e1) {
			System.out.println("Error while Logging iut from Vault...");
			Vault_data_map.put("RC", 2);
			vaultDriver.quit();
			return Vault_data_map;		

		}
	}	


	public static  HashMap<String , List<String>> Netsfere_URL_HealthCheck(HashMap<String, String> Server_URL_MAP) throws FileNotFoundException {

		RequestSpecification httpRequest = RestAssured.given();
		HashMap<String, List<String>> URL_RESPONSE = new HashMap<String, List<String>>();
		int statusCode = 0;
		String statusText = "";
		for( String ServerLabel : Server_URL_MAP.keySet() ) {
			try {
				System.out.println(" Checking URL  -> "+Server_URL_MAP.get(ServerLabel));
				Response response = httpRequest.get(Server_URL_MAP.get(ServerLabel));
				List<String> response_data = new  ArrayList<String>();
				statusCode = response.getStatusCode();
				//	    		response_data.add(Integer.toString(statusCode));	
				if ( statusCode == 200) {
					statusText = response.getStatusLine();
					response_data.add("PASS");
					response_data.add(Integer.toString(statusCode));
					response_data.add(statusText);
				} else if (  statusCode == 204) {
					statusText = response.getStatusLine();
					response_data.add("PASS");
					response_data.add(Integer.toString(statusCode));
					response_data.add(statusText);
				} else if ( statusCode == 400) {
					response_data.add("FAIL");
					response_data.add("ERROR- Bad Request, something went worng please check.");
				} else if (statusCode == 404) {
					response_data.add("FAIL");
					response_data.add(Integer.toString(statusCode));
					response_data.add("ERROR- Not Found, something went worng please check.");
				} else if (statusCode == 408 ) {
					response_data.add("FAIL");
					response_data.add(Integer.toString(statusCode));
					response_data.add("ERROR- Request Timed Out, something went worng please check.");
				} else if ( statusCode == 500) {

					response_data.add("FAIL");
					response_data.add(Integer.toString(statusCode));
					response_data.add("ERROR- Internal Server Error, something went worng please check.");
				} else if (statusCode == 502 ) {

					response_data.add("FAIL");
					response_data.add(Integer.toString(statusCode));
					response_data.add("ERROR- Gateway Timeout, something went worng please check.");
				} else if (statusCode == 504 ) {

					response_data.add("FAIL");
					response_data.add(Integer.toString(statusCode));
					response_data.add("ERROR- Gateway Timeout, something went worng please check.");
				}
				//	    		if ( statusCode == 200) {
				//	    			statusText = response.getStatusLine();
				//	    			response_data.add(statusText);
				//	    		} else if (  statusCode == 204) {
				//	    			statusText = response.getStatusLine();
				//	    			response_data.add(statusText);
				//	    		} else if ( statusCode == 400) {
				//	    			response_data.add("ERROR- Bad Request, something went worng please check.");
				//	    		} else if (statusCode == 404) {
				//	    			response_data.add("ERROR- Not Found, something went worng please check.");
				//	    		} else if (statusCode == 408 ) {
				//	    			response_data.add("ERROR- Request Timed Out, something went worng please check.");
				//	    		} else if ( statusCode == 500) {
				//	    			response_data.add("ERROR- Internal Server Error, something went worng please check.");
				//	    		} else if (statusCode == 502 ) {
				//	    			response_data.add("ERROR- Gateway Timeout, something went worng please check.");
				//	    		} else if (statusCode == 504 ) {
				//	    			response_data.add("ERROR- Gateway Timeout, something went worng please check.");
				//	    		}
				URL_RESPONSE.put(ServerLabel.split("_URL")[0] ,response_data );
			} catch ( Exception  e) {
				System.out.println("Error while checking the URL : "+Server_URL_MAP.get(ServerLabel));
				URL_RESPONSE.put(ServerLabel.split("_URL")[0], null);	    		
			} 	

		}

		return URL_RESPONSE;
	}


	public static int WebLogout(ChromeDriver logoutDdriver) {
		WebDriverWait logoutWait = new WebDriverWait(logoutDdriver, 60);	
		JavascriptExecutor js = (JavascriptExecutor) logoutDdriver;
		try {
			//			logoutWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Settings']")));
			logoutWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Settings']/div/img")));
			logoutDdriver.findElementByXPath("//div[@title='Settings']").click();			
			logoutWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mainDiv']//button/span[contains(text(),'Logout')]")));
			WebElement LogoutButton = logoutDdriver.findElementByXPath("//div[@class='mainDiv']//button/span[contains(text(),'Logout')]");
			js.executeScript("arguments[0].scrollIntoView();", LogoutButton);
			js.executeScript("arguments[0].click();", LogoutButton);
			//			logoutDdriver.findElementByXPath("//div[@class='mainDiv']//button/span[contains(text(),'Logout')]").click();
			Thread.sleep(3000);
			logoutWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Login')]")));
			if ( logoutDdriver.findElementByXPath("//div[contains(text(),'Login')]") != null ) {
				System.out.println("WebClient LOGOUT Successful..");
				return 0;
			} else {
				System.out.println("WebClient LOGOUT Failed..");
				return 1;
			}	
		} catch(Exception e) {
			System.out.println("WebClient LOGOUT Failed..");
			return 1;			
		}
	}


	public static int leaveConversation(ChromeDriver leaveConvDdriver, String convTitle) {
		WebDriverWait leaveConvWait = new WebDriverWait(leaveConvDdriver, 60);	
		WebDriverWait leaveConvWait1 = new WebDriverWait(leaveConvDdriver, 5);

		try {
			System.out.println("Web CLient Leaving Conversation :"+convTitle);			
			leaveConvDdriver.findElementByXPath("//span[@class='icon ion-ios-chatbubble']").click();
			leaveConvDdriver.findElementByXPath("//button[@title='Start Conversation']/../div/input").sendKeys(convTitle);			
			leaveConvWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+convTitle+"')]")));
			leaveConvDdriver.findElementByXPath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'\"+convTitle+\"')]").click();
			leaveConvDdriver.findElementByXPath("//span[@class='fa fa-sign-out']//div[contains(text(),'Leave')]").click();
			try {
				leaveConvDdriver.findElementByXPath("//div[contains(text(),'Leave Conversation')]/following-sibling::div[2]/button/span[contains(text(),'Leave')]").click();
				return 0;
			} catch (NoSuchElementException e1) {
				leaveConvDdriver.findElementByXPath("//div[contains(text(),'Transfer Ownership')]/following-sibling::div[3]/div[2]").click();
				leaveConvDdriver.findElementByXPath("//button//span[contains(text(),'transfer')]").click();
				leaveConvDdriver.findElementByXPath("//div[contains(text(),'Leave Conversation')]/following-sibling::div[2]/button/span[contains(text(),'Leave')]").click();
				return 0;
			}
		} catch  (NoSuchElementException e) {
			System.out.println("Leave COnversation Failed.");
			return 0;
		}
	}

	public static int destroyConversation(ChromeDriver leaveConvDdriver, String convTitle) {
		WebDriverWait leaveConvWait = new WebDriverWait(leaveConvDdriver, 60);	
		WebDriverWait leaveConvWait1 = new WebDriverWait(leaveConvDdriver, 5);

		try {
			System.out.println("Web CLient Leaving Conversation :"+convTitle);			
			leaveConvDdriver.findElementByXPath("//span[@class='icon ion-ios-chatbubble']").click();
			leaveConvDdriver.findElementByXPath("//button[@title='Start Conversation']/../div/input").sendKeys(convTitle);			
			leaveConvWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+convTitle+"')]")));
			leaveConvDdriver.findElementByXPath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+convTitle+"')]").click();
			//			leaveConvWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+convTitle+"')]")));
			//			leaveConvDdriver.findElementByXPath("//div[@class='scrollbox']//div[@class='table-view']/div[2]//div[contains(text(),'"+convTitle+"')]").click();
			leaveConvWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='fa fa-sign-out']/following-sibling::div/div[contains(text(),'Leave')]")));
			leaveConvDdriver.findElementByXPath("//span[@class='fa fa-sign-out']/following-sibling::div/div[contains(text(),'Leave')]").click();
			try {
				if (leaveConvDdriver.findElementByXPath("//button//span[contains(text(),'Destroy')]") != null) {
					leaveConvDdriver.findElementByXPath("//button//span[contains(text(),'Destroy')]").click();
					leaveConvDdriver.findElementByXPath("//div[contains(text(),'Destroy Conversation')]/following-sibling::div/button[./span[contains(text(),'Destroy')]]").click();
					return 0;
				} else {
					return 1;
				}
			} catch (NoSuchElementException e1) {
				return 1;

			}
		} catch  (NoSuchElementException e) {
			System.out.println("Destroy Conversation Failed.");
			return 1;
		}
	}		

	public static void killAllBrowserSessions() throws IOException, InterruptedException {
		String command = "taskkill /IM chrome.exe /F ";
		String command1 = "taskkill /IM chromedriver.exe /F ";
		Runtime r = Runtime.getRuntime();
		r.exec(command);
		Runtime r1 = Runtime.getRuntime();
		r1.exec(command1);
		Thread.sleep(3000);
	}

	public static void  attachImage_win_Robot() throws  AWTException, InterruptedException {

		try {
			StringSelection sel = new StringSelection(Config.getInstance().getImageAttachment());		
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
			//			System.out.println("selection" +sel);		
			Robot robot = new Robot();
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);		 
			// Release Enter
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			// Press CTRL+V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);		 
			// Release CTRL+V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch ( Exception e) {
			System.out.println("ERROR - Attachment Upload Failed.");
		}
	}

	public static void  attachImage_win() throws SikuliException {

		Screen s = new Screen();
		String dir = System.getProperty("user.dir");
		//		System.out.println(dir);
		Pattern DirSearchImage = new Pattern(dir+"\\Resources\\Win\\FileSearchTextBoxImage.PNG");
		if(s.exists(DirSearchImage) != null) {
			try { 
				s.wait(DirSearchImage, 2);
				//				System.out.println(s.getFindFailedResponse());
				Pattern FileInputTextBoxImage = new Pattern(dir+"\\Resources\\Win\\FileSearchInputTextBoxImage.PNG");
				s.wait(FileInputTextBoxImage, 2);
				if(s.exists(FileInputTextBoxImage) != null) {
					s.type(FileInputTextBoxImage, Config.getInstance().getImageAttachment());
					Pattern OpenBtnImage = new Pattern(dir+"\\Resources\\Win\\OpenBtnImage.PNG");
					s.wait(OpenBtnImage, 2);
					if(s.exists(OpenBtnImage) != null) {
						s.click(OpenBtnImage);
					}

				}
			} catch (SikuliException e) {
				System.out.println("ERROR - unable to attach File in COnversation.");
			}
		}
	}

	public static boolean isMac() {
		String OS = System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT);
		return OS.contains("mac");
	}
	public static boolean isWin() {
		String OS = System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT);
		return OS.contains("win");
	}



}



