package Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static ChromeDriver chromeInstance;
	public static FirefoxDriver firefoxInstance ;
	public static EdgeDriver  edgeInstance ;
	
	public static ChromeDriver chromeDriverInitialize() {

		WebDriverManager.chromedriver().setup();
		DesiredCapabilities caps = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 1);
		options.setExperimentalOption("prefs", prefs);
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		options.addArguments("use-fake-ui-for-media-stream");
		options.addArguments("use-fake-device-for-media-stream");
		options.addArguments("--enable-notifications");
		chromeInstance = new ChromeDriver(options);
		(chromeInstance).manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		(chromeInstance).manage().window().maximize();
		return chromeInstance;	
	}	

	// close driver
	public static void chromeDriverClose() {
		chromeInstance.quit();
	}
	
	public static  FirefoxDriver FirefoxDriverInitialize()
	{
		WebDriverManager.firefoxdriver().setup();

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile ffprofile = profile.getProfile("default");
		ffprofile.setPreference("enableNativeEvents", true);
		ffprofile.setPreference("dom.webnotifications.enabled", false);
		ffprofile.setPreference("media.navigator.permission.disabled", true);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("marionette", false); 
		capabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);
		FirefoxOptions options = new FirefoxOptions(); 
		options.merge(capabilities);
		firefoxInstance = new FirefoxDriver(options);
		(firefoxInstance).manage().window().maximize();
		return firefoxInstance;
	}
	
	public static void firefoxDriverClose()
	{
		firefoxInstance.quit();
	}
	
	public static  EdgeDriver EdgeDriverInitialize()
	{
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions() ; 
		options.setCapability("dom.webnotifications.enabled", 1);
		options.setCapability("permissions.default.microphone", 1);
		options.setCapability("permissions.default.camera", 1);
		edgeInstance =  new EdgeDriver(options);
		edgeInstance.manage().window().maximize();
		edgeInstance.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		edgeInstance.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return edgeInstance;
		
	}
	
	public static void edgeDriverClose()
	{
		edgeInstance.quit();
	}
}
