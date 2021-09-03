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

public class DIMAS_CleanUp  extends Base{
	
	@SuppressWarnings({ "rawtypes", "unused" })
	//public static void main(String[] args) throws InterruptedException, IOException , NoSuchElementException {	
	@Test
	public void CleanConversations() throws InterruptedException, IOException {
	
		
		
		List<String> userIds = new ArrayList<String>();
		// Dimas Users
		if(!(Config.getInstance().isDebug())) {
			userIds.add(Config.getInstance().getDimasWebId());
			userIds.add(Config.getInstance().getDimasChromeId());
			userIds.add(Config.getInstance().getDimasFirefoxId());
		} else {
			userIds.add(Config.getInstance().getDimasWebIdTest());
			userIds.add(Config.getInstance().getDimasChromeIdTest());
			userIds.add(Config.getInstance().getDimasFirefoxIdTest());
		}
		for ( String userId: userIds ) {
			int rc = NetsfereActivity.AccountCleanUp(userId);
			if ( rc == 1 ) {
				System.out.println("Account Cleanup for :"+userId+ "   SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ "   FAILED");
			}
		}
		
		
		
		for ( String userId: userIds ) {
			int rc = NetsfereActivity.AccountCleanUp(userId);
			if ( rc == 1 ) {
				System.out.println("Account Cleanup for :"+userId+ "   SUCCESS");
			} else {
				System.out.println("Account Cleanup for :"+userId+ "   FAILED");
			}
		}
		String DimasACPId = "";
		if(!(Config.getInstance().isDebug())) {
			DimasACPId = Config.getInstance().getDimasACPUserId();
		} else {
			DimasACPId = Config.getInstance().getDimasACPUserIdTest();
		}
		userIds.add(DimasACPId);
		ChromeDriver AcpDriver = NetsfereActivity.ACPLogin(DimasACPId);
		if(AcpDriver != null ) {
			for(String userId :userIds) {
				int rc = NetsfereActivity.ForceLogoutAllSessions(AcpDriver, userId);
				if ( rc == 0) {
					System.out.println("Force Logout for the User :"+userId +" is Successful");
				} else {
					System.out.println("Force Logout for the user :"+userId +"is Failed");
				}
			}
//			NetsfereActivity.ACPLogout(acpDriver);
		}
		if (AcpDriver != null) {
			AcpDriver.close();
		}
	}
}

