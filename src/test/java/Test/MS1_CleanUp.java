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

public class MS1_CleanUp  extends Base {

	
	@SuppressWarnings({ "rawtypes", "unused" })
	//public static void main(String[] args) throws InterruptedException, IOException , NoSuchElementException {	
	@Test
	public void CleanConversations() throws InterruptedException, IOException {
	
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
		String Ms1ACPId = "";
		if(!(Config.getInstance().isDebug())) {
			Ms1ACPId = Config.getInstance().getMs1ACPUserId();
		} else {
			Ms1ACPId = Config.getInstance().getMs1ACPUserIdTest();
		}
		userIds.add(Ms1ACPId);
		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(Ms1ACPId);
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
