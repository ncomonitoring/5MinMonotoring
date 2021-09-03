package Test;

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
import org.openqa.selenium.WebDriver;

	
public class MRI_CleanUp  extends Base {

	
	@SuppressWarnings({ "rawtypes", "unused" })
	
	@Test
	public void CleanConversations() throws InterruptedException, IOException {
		
		List<String> userIds = new ArrayList<String>();

		if(!(Config.getInstance().isDebug())) {
			userIds.add(Config.getInstance().getMRIWebId());
			userIds.add(Config.getInstance().getMRIChromeId());
			userIds.add(Config.getInstance().getMRIFirefoxId());
		} else {
			userIds.add(Config.getInstance().getMRIWebIdTest());
			userIds.add(Config.getInstance().getMRIChromeIdTest());
			userIds.add(Config.getInstance().getMRIFirefoxIdTest());
		}
		for ( String userId: userIds ) {
			int rc = NetsfereActivity.AccountCleanUp(userId);
			if ( rc == 1 ) {
				System.out.println("Account Cleanup for :"+userId+ "   SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ "   FAILED");
			}
		}
		String MRIACPId = "";
		if(!(Config.getInstance().isDebug())) {
			MRIACPId = Config.getInstance().getMRIACPUserId();
		} else {
			MRIACPId = Config.getInstance().getMRIACPUserIdTest();
		}
		userIds.add(MRIACPId);
		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(MRIACPId);
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


