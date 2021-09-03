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


public class EU_CleanUp extends Base {

	@SuppressWarnings({ "rawtypes", "unused" })
	//public static void main(String[] args) throws InterruptedException, IOException , NoSuchElementException {
	@Test
	public void CleanConversations() throws InterruptedException, IOException {


		List<String> userIds = new ArrayList<String>();
		// EU Users
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
				System.out.println("Account Cleanup for :"+userId+ " SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ " FAILED");
			}
		}
		String EUACPId = "";
		if(!(Config.getInstance().isDebug())) {
			EUACPId = Config.getInstance().getEUACPId();
			
		} else {
			EUACPId = Config.getInstance().getEUACPIdTest();
		}
		userIds.add(EUACPId);
//		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(Config.getInstance().getEUACPId());
		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(EUACPId);
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
