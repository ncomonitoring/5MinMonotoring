package Test;

import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;




public class APAC_CleanUp extends Base {
	
	@SuppressWarnings({ "rawtypes", "unused" })
	//public static void main(String[] args) throws InterruptedException, IOException , NoSuchElementException {	
	@Test
	public void CleanConversations() throws InterruptedException, IOException {
	
		// APSE Users
		List<String> userIds = new ArrayList<String>();
		if(!(Config.getInstance().isDebug())) {
			userIds.add(Config.getInstance().getAPSEWebId());
			userIds.add(Config.getInstance().getAPSEChromeId());
			userIds.add(Config.getInstance().getAPSEFirefoxId());
		} else {
			userIds.add(Config.getInstance().getAPSEWebIdTest());
			userIds.add(Config.getInstance().getAPSEChromeIdTest());
			userIds.add(Config.getInstance().getAPSEFirefoxIdTest());
		}
		
		for ( String userId: userIds ) {
			int rc = NetsfereActivity.AccountCleanUp(userId);
			if ( rc == 1 ) {
				System.out.println("Account Cleanup for :"+userId+ "   SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ "   FAILED");
			}
		}
		
		String APSEACPId = "";
		if(!(Config.getInstance().isDebug())) {
			APSEACPId = Config.getInstance().getAPSEACPId();
			
		} else {
			APSEACPId = Config.getInstance().getAPSEACPIdTest();
		}
		userIds.add(APSEACPId);
//		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(Config.getInstance().getEUACPId());
		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(APSEACPId);
		if ( AcpDriver != null) {
			for ( String userId: userIds ) {
				int rc = NetsfereActivity.ForceLogoutAllSessions(AcpDriver,userId );
				if ( rc == 0 ) {
					System.out.println("Force Logout for :"+userId+ " SUCCESS");
				} else {
					System.out.println("Force Logout for :"+userId+ " FAILED");
				}
				Thread.sleep(1000);
			}
		}
		if (AcpDriver != null) {
			AcpDriver.close();
		}
	}
}

