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


public class APSE2_CleanUp extends Base {
	
	@SuppressWarnings({ "rawtypes", "unused" })
	//public static void main(String[] args) throws InterruptedException, IOException , NoSuchElementException {	
	@Test
	public void CleanConversations() throws InterruptedException, IOException {
	
		// APSE Users
		List<String> userIds = new ArrayList<String>();
		if(!(Config.getInstance().isDebug())) {
			userIds.add(Config.getInstance().getAPSE2WebId());
			userIds.add(Config.getInstance().getAPSE2ChromeId());
			userIds.add(Config.getInstance().getAPSE2FirefoxId());
		} else {
			userIds.add(Config.getInstance().getAPSE2WebIdTest());
			userIds.add(Config.getInstance().getAPSE2ChromeIdTest());
			userIds.add(Config.getInstance().getAPSE2FirefoxIdTest());
		}
		
		for ( String userId: userIds ) {
			int rc = NetsfereActivity.AccountCleanUp(userId);
			if ( rc == 1 ) {
				System.out.println("Account Cleanup for :"+userId+ "   SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ "   FAILED");
			}
		}
		
		String APSE2ACPId = "";
		if(!(Config.getInstance().isDebug())) {
			APSE2ACPId = Config.getInstance().getAPSE2ACPId();
			
		} else {
			APSE2ACPId = Config.getInstance().getAPSE2ACPIdTest();
		}
		userIds.add(APSE2ACPId);
//		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(Config.getInstance().getEUACPId());
		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(APSE2ACPId);
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


