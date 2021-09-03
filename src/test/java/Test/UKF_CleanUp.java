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


public class UKF_CleanUp  extends Base {


	@SuppressWarnings({ "rawtypes", "unused" })
	//public static void main(String[] args) throws InterruptedException, IOException , NoSuchElementException {	
	@Test
	public void CleanConversations() throws InterruptedException, IOException {

		List<String> userIds = new ArrayList<String>();
		// UKF Users
		if(!(Config.getInstance().isDebug())) {
			userIds.add(Config.getInstance().getUKFWebId());
			userIds.add(Config.getInstance().getUKFChromeId());
			userIds.add(Config.getInstance().getUKFFirefoxId());
		} else {
			userIds.add(Config.getInstance().getUKFWebIdTest());
			userIds.add(Config.getInstance().getUKFChromeIdTest());
			userIds.add(Config.getInstance().getUKFFirefoxIdTest());
		}
		for ( String userId: userIds ) {
			int rc = NetsfereActivity.AccountCleanUp(userId);
			if ( rc == 1 ) {
				System.out.println("Account Cleanup for :"+userId+ "   SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ "   FAILED");
			}
		}
		String UKFACPId = "";
		if(!(Config.getInstance().isDebug())) {
			UKFACPId = Config.getInstance().getUKFACPUserId();
		} else {
			UKFACPId = Config.getInstance().getUKFACPUserIdTest();
		}
		userIds.add(UKFACPId);
		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(UKFACPId);
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




