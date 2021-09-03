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


public class MS2_CleanUp extends Base  {	
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@Test
	public void CleanConversations() throws InterruptedException, IOException {
	
		List<String> userIds = new ArrayList<String>();
		// MS2 Users
		if(!(Config.getInstance().isDebug())) {
			userIds.add(Config.getInstance().getMs2WebId());
			userIds.add(Config.getInstance().getMs2ChromeId());
			userIds.add(Config.getInstance().getMs2FirefoxId());
		} else {
			userIds.add(Config.getInstance().getMs2WebIdTest());
			userIds.add(Config.getInstance().getMs2ChromeIdTest());
			userIds.add(Config.getInstance().getMs2FirefoxIdTest());
		}
		for ( String userId: userIds ) {
			int rc = NetsfereActivity.AccountCleanUp(userId);
			if ( rc == 1 ) {
				System.out.println("Account Cleanup for :"+userId+ "   SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ "   FAILED");
			}
		}
		String Ms2ACPId = "";
		if(!(Config.getInstance().isDebug())) {
			Ms2ACPId = Config.getInstance().getMs2ACPUserId();
		} else {
			Ms2ACPId = Config.getInstance().getMs2ACPUserIdTest();
		}
		userIds.add(Ms2ACPId);
		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(Ms2ACPId);
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
			
