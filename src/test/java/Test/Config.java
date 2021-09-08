package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Config {	
	//	private static Logger log = LogManager.getLogger(Config.class.getName());
	static String randomDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	private static final String debug ="debug";
	private static final String GmailUserID = "gmailId";
	private static final String GmailPassword = "gmailPasswd";
	private static final String domain = "netsfere_domain";
	//	private static final String region = "region";
	//	private static final String ChromeDriver = "chromedriverpath";	

	//====================================
	// **** MS1 Cluster ****
	//====================================
	private static final String ms1WebId = "ms1WebId";
	private static final String ms1WebDisplayName = "ms1WebDisplayName";
	private static final String ms1ChromeId = "ms1ChromeId";
	private static final String ms1ChromeDisplayName = "ms1ChromeDisplayName";
	private static final String ms1FirefoxId = "ms1FirefoxId";
	private static final String ms1FirefoxDisplayName = "ms1FirefoxDisplayName";	
	private static final String  ms1ACPUserId ="ms1ACPUserId";
	//====================================
	// **** MS1 Cluster Debug ****
	//====================================
	private static final String ms1WebIdTest = "ms1WebIdTest";
	private static final String ms1WebDisplayNameTest = "ms1WebDisplayNameTest";
	private static final String ms1ChromeIdTest = "ms1ChromeIdTest";
	private static final String ms1ChromeDisplayNameTest = "ms1ChromeDisplayNameTest";
	private static final String ms1FirefoxIdTest = "ms1FirefoxIdTest";
	private static final String ms1FirefoxDisplayNameTest = "ms1FirefoxDisplayNameTest";	
	private static final String  ms1ACPUserIdTest ="ms1ACPUserIdTest";
	//====================================
	// **** MS2 Cluster ****
	//====================================
	private static final String ms2WebId = "ms2WebId";
	private static final String ms2WebDisplayName = "ms2WebDisplayName";
	private static final String ms2ChromeId = "ms2ChromeId";
	private static final String ms2ChromeDisplayName = "ms2ChromeDisplayName";
	private static final String ms2FirefoxId = "ms2FirefoxId";
	private static final String ms2FirefoxDisplayName = "ms2FirefoxDisplayName";
	private static final String ms2VaultId = "ms2VaultId";
	private static final String ms2ACPUserId = "ms2ACPUserId";

	//====================================
	// **** MS2 Cluster Debug ****
	//====================================
	private static final String ms2WebIdTest = "ms2WebIdTest";
	private static final String ms2WebDisplayNameTest = "ms2WebDisplayNameTest";
	private static final String ms2ChromeIdTest = "ms2ChromeIdTest";
	private static final String ms2ChromeDisplayNameTest = "ms2ChromeDisplayNameTest";
	private static final String ms2FirefoxIdTest = "ms2FirefoxId";
	private static final String ms2FirefoxDisplayNameTest = "ms2FirefoxDisplayNameTest";
	private static final String ms2VaultIdTest = "ms2VaultITest";
	private static final String ms2ACPUserIdTest = "ms2ACPUserIdTest";


	//====================================
	// **** EU Cluster ****
	//====================================
	private static final String euWebId = "euWebId";
	private static final String euWebDisplayName = "euWebDisplayName";
	private static final String euChromeId = "euChromeId";
	private static final String euChromeDisplayName = "euChromeDisplayName";
	private static final String euFirefoxId = "euFirefoxId";
	private static final String euFirefoxDisplayName = "euFirefoxDisplayName";
	private static final String euVaultId = "euVaultId";
	private static final String euACPUserId = "euACPUserId";

	//====================================
	// **** EU Cluster Debug ****
	//====================================
	private static final String euWebIdTest = "euWebIdTest";
	private static final String euWebDisplayNameTest = "euWebDisplayNameTest";
	private static final String euChromeIdTest = "euChromeIdTest";
	private static final String euChromeDisplayNameTest = "euChromeDisplayNameTest";
	private static final String euFirefoxIdTest = "euFirefoxIdTest";
	private static final String euFirefoxDisplayNameTest = "euFirefoxDisplayNameTest";
	private static final String euVaultIdTest = "euVaultIdTest";
	private static final String euACPUserIdTest = "euACPUserIdTest";
	//====================================
	// **** APSE Cluster ****
	//====================================
	private static final String apseWebId = "apseWebId";
	private static final String apseWebDisplayName = "apseWebDisplayName";
	private static final String apseChromeId = "apseChromeId";
	private static final String apseChromeDisplayName = "apseChromeDisplayName";
	private static final String apseFirefoxId = "apseFirefoxId";
	private static final String apseFirefoxDisplayName = "apseFirefoxDisplayName";
	private static final String apseVaultId = "apseVaultId";
	private static final String apseACPUserId = "apseACPUserId";

	//====================================
	// **** APSE Cluster Debug ****
	//====================================
	private static final String apseWebIdTest = "apseWebIdTest";
	private static final String apseWebDisplayNameTest = "apseWebDisplayNameTest";
	private static final String apseChromeIdTest = "apseChromeIdTest";
	private static final String apseChromeDisplayNameTest = "apseChromeDisplayNameTest";
	private static final String apseFirefoxIdTest = "apseFirefoxIdTest";
	private static final String apseFirefoxDisplayNameTest = "apseFirefoxDisplayNameTest";
	private static final String apseVaultIdTest = "apseVaultIdTest";
	private static final String apseACPUserIdTest = "apseACPUserIdTest";

	//====================================
	// **** UMR Cluster ****
	//====================================
	private static final String umrWebId = "umrWebId";
	private static final String umrWebDisplayName = "umrWebDisplayName";
	private static final String umrChromeId = "umrChromeId";
	private static final String umrChromeDisplayName = "umrChromeDisplayName";
	private static final String umrFirefoxId = "umrFirefoxId";
	private static final String umrFirefoxDisplayName = "umrFirefoxDisplayName";
	private static final String umrGuestId = "umrGuestId";
	private static final String umrGuestDisplayName = "umrGuestDisplayName";
	private static final String umrACPUserId = "umrACPUserId" ;

	//====================================
	// **** UMR Cluster Debug ****
	//====================================
	private static final String umrWebIdTest = "umrWebIdTest";
	private static final String umrWebDisplayNameTest = "umrWebDisplayNameTest";
	private static final String umrChromeIdTest = "umrChromeIdTest";
	private static final String umrChromeDisplayNameTest = "umrChromeDisplayNameTest";
	private static final String umrFirefoxIdTest = "umrFirefoxIdTest";
	private static final String umrFirefoxDisplayNameTest = "umrFirefoxDisplayNameTest";
	private static final String umrGuestIdTest = "umrGuestIdTest";
	private static final String umrGuestDisplayNameTest = "umrGuestDisplayNameTest";
	private static final String umrACPUserIdTest = "umrACPUserIdTest" ;

	//====================================
	// **** DIMAS Cluster  ****
	//====================================
	private static final String dimasWebId = "dimasWebId";
	private static final String dimasWebDisplayName = "dimasWebDisplayName";
	private static final String dimasChromeId = "dimasChromeId";
	private static final String dimasChromeDisplayName = "dimasChromeDisplayName";
	private static final String dimasFirefoxId = "dimasFirefoxId";
	private static final String dimasFirefoxDisplayName = "dimasFirefoxDisplayName";
	private static final String dimasGuestId = "dimasGuestID";
	private static final String dimasGuestDisplayName = "dimasGuestDisplayName";	
	private static final String dimasACPUserId = "dimasACPUserId" ;

	//====================================
	// **** DIMAS Cluster  ****
	//====================================
	private static final String dimasWebIdTest = "dimasWebIdTest";
	private static final String dimasWebDisplayNameTest = "dimasWebDisplayNameTest";
	private static final String dimasChromeIdTest = "dimasChromeIdTest";
	private static final String dimasChromeDisplayNameTest = "dimasChromeDisplayNameTest";
	private static final String dimasFirefoxIdTest = "dimasFirefoxIdTest";
	private static final String dimasFirefoxDisplayNameTest = "dimasFirefoxDisplayNameTest";
	private static final String dimasGuestIdTest = "dimasGuestIDTest";
	private static final String dimasGuestDisplayNameTest = "dimasGuestDisplayNameTest";	
	private static final String dimasACPUserIdTest = "dimasACPUserIdTest" ;



	//====================================
	// **** AM1 Cluster ****
	//====================================
	private static final String am1WebId = "am1WebId";
	private static final String am1WebDisplayName = "am1WebDisplayName";
	private static final String am1ChromeId = "am1ChromeId";
	private static final String am1ChromeDisplayName = "am1ChromeDisplayName";
	private static final String am1FirefoxId = "am1FirefoxId";
	private static final String am1FirefoxDisplayName = "am1FirefoxDisplayName";
	private static final String am1ACPUserId = "am1ACPUserId";
	private static final String am1VaultId = "am1VaultId";



	//====================================
	// **** AM1 Cluster Debug ****
	//====================================
	private static final String am1WebIdTest = "am1WebIdTest";
	private static final String am1WebDisplayNameTest = "am1WebDisplayNameTest";
	private static final String am1ChromeIdTest = "am1ChromeIdTest";
	private static final String am1ChromeDisplayNameTest = "am1ChromeDisplayNameTest";
	private static final String am1FirefoxIdTest = "am1FirefoxIdTest";
	private static final String am1FirefoxDisplayNameTest = "am1FirefoxDisplayNameTest";
	private static final String am1ACPUserIdTest = "am1ACPUserIdTest";
	private static final String am1VaultIdTest = "am1VaultIdTest";

	//====================================
	// **** MRI Cluster ****
	//====================================
	private static final String mriWebId = "mriWebId";
	private static final String mriWebDisplayName = "mriWebDisplayName";
	private static final String mriChromeId = "mriChromeId";
	private static final String mriChromeDisplayName = "mriChromeDisplayName";
	private static final String mriFirefoxId = "mriFirefoxId";
	private static final String mriFirefoxDisplayName = "mriFirefoxDisplayName";
	private static final String mriACPUserId = "mriACPUserId";

	//====================================
	// **** MRI Cluster Debug****
	//====================================
	private static final String mriWebIdTest = "mriWebIdTest";
	private static final String mriWebDisplayNameTest = "mriWebDisplayNameTest";
	private static final String mriChromeIdTest = "mriChromeIdTest";
	private static final String mriChromeDisplayNameTest = "mriChromeDisplayNameTest";
	private static final String mriFirefoxIdTest = "mriFirefoxIdTest";
	private static final String mriFirefoxDisplayNameTest = "mriFirefoxDisplayNameTest";
	private static final String mriACPUserIdTest = "mriACPUserIdTest";

	//====================================
	// **** UKF Cluster ****
	//====================================
	private static final String ukfWebId = "ukfWebId";
	private static final String ukfWebDisplayName = "ukfWebDisplayName";
	private static final String ukfChromeId = "ukfChromeId";
	private static final String ukfChromeDisplayName = "ukfChromeDisplayName";
	private static final String ukfFirefoxId = "ukfFirefoxId";
	private static final String ukfFirefoxDisplayName = "ukfFirefoxDisplayName";
	private static final String ukfACPUserId = "ukfACPUserId";

	//====================================
	// **** UKF Cluster Debug ****
	//====================================
	private static final String ukfWebIdTest = "ukfWebIdTest";
	private static final String ukfWebDisplayNameTest = "ukfWebDisplayNameTest";
	private static final String ukfChromeIdTest = "ukfChromeIdTest";
	private static final String ukfChromeDisplayNameTest = "ukfChromeDisplayNameTest";
	private static final String ukfFirefoxIdTest = "ukfFirefoxIdTest";
	private static final String ukfFirefoxDisplayNameTest = "ukfFirefoxDisplayNameTest";
	private static final String ukfACPUserIdTest = "ukfACPUserIdTest";

	//====================================
	// **** APSE2 Cluster ****
	//====================================
	private static final String apse2WebId = "apse2WebId";
	private static final String apse2WebDisplayName = "apse2WebDisplayName";
	private static final String apse2ChromeId = "apse2ChromeId";
	private static final String apse2ChromeDisplayName = "apse2ChromeDisplayName";
	private static final String apse2FirefoxId = "apse2FirefoxId";
	private static final String apse2FirefoxDisplayName = "apse2FirefoxDisplayName";
	private static final String apse2VaultId = "apse2VaultId";
	private static final String apse2ACPUserId = "apse2ACPUserId";
	//====================================
	// **** APSE2 Cluster Debug ****
	//====================================
	private static final String apse2WebIdTest = "apse2WebIdTest";
	private static final String apse2WebDisplayNameTest = "apse2WebDisplayNameTest";
	private static final String apse2ChromeIdTest = "apse2ChromeIdTest";
	private static final String apse2ChromeDisplayNameTest = "apse2ChromeDisplayNameTest";
	private static final String apse2FirefoxIdTest = "apse2FirefoxIdTest";
	private static final String apse2FirefoxDisplayNameTest = "apse2FirefoxDisplayNameTest";
	private static final String apse2VaultIdTest = "apse2VaultIdTest";
	private static final String apse2ACPUserIdTest = "apse2ACPUserIdTest";

	//====================================
	// **** ZOLLERNALB(ZK) Cluster ****
	//====================================
	private static final String zkWebId = "zkWebId";
	private static final String zkWebDisplayName = "zkWebDisplayName";
	private static final String zkChromeId = "zkChromeId";
	private static final String zkChromeDisplayName = "zkChromeDisplayName";
	private static final String zkFirefoxId = "zkFirefoxId";
	private static final String zkFirefoxDisplayName = "zkFirefoxDisplayName";
	private static final String zkACPUserId = "zkACPUserId";
	//========================================
	// **** ZOLLERNALB(ZK) Cluster  Debug****
	//========================================
	private static final String zkWebIdTest = "zkWebIdTest";
	private static final String zkWebDisplayNameTest = "zkWebDisplayNameTest";
	private static final String zkChromeIdTest = "zkChromeIdTest";
	private static final String zkChromeDisplayNameTest = "zkChromeDisplayNameTest";
	private static final String zkFirefoxIdTest = "zkFirefoxIdTest";
	private static final String zkFirefoxDisplayNameTest = "zkFirefoxDisplayNameTest";
	private static final String zkACPUserIdTest = "zkACPUserIdTest";

	//====================================
	// **** URL Health Check ****
	//====================================
	private static final String webUrl = "webUrl" ;
	private static final String anchorUrl = "anchorUrl";
	private static final String liveusUrl = "liveusUrl" ;
	private static final String liveeuUrl  = "liveeuUrl" ;
	private static final String ms2VaultUrl  = "ms2VaultUrl" ;
	private static final String eu1VaultUrl  = "eu1VaultUrl" ;
	private static final String apseVaultUrl  = "apseVaultUrl" ;
	private static final String eppdVaultUrl  = "eppdVaultUrl" ;
	private static final String acpUrl = "acpUrl" ;
	private static final String ms2Url = "ms2Url" ;
	private static final String eu1Url = "eu1Url" ;
	private static final String apse1Url = "apse1Url" ;
	private static final String ms7Url = "ms7Url" ;
	private static final String ms1Url = "ms1Url" ;
	private static final String umrUrl = "umrUrl" ;
	private static final String dimasUrl = "dimasUrl" ;
	private static final String mriUrl = "mriUrl" ;
	private static final String NstUrl = "NstUrl";
	private static final String SignupUrl = "SignupUrl";
	private static final String DesktopClientUrl  = "DesktopClientUrl" ;
	private static final String ukfUrl= "ukfUrl";
	private static final String zkUrl= "zkUrl";
	private static final String apse2Url= "apse2Url";
	private static final String am1Url= "am1Url";
	private static final String helpUrl= "helpUrl";
	private static final String apiUrl= "apiUrl";
	private static final String apiusUrl= "apiusUrl";
	private static final String apieuUrl= "apieuUrl";
	private static final String apiapseUrl= "apiapseUrl";



	private static final String userPassword = "userPassword";
	private static final String adminPassword = "adminPassword";

	private static final String ImageAttachment = "imageattachment";


	private static Config instance;
	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	private Properties credentials;
	private Config() {
		loadCredentials();
	}

	private void loadCredentials() {
		credentials = new Properties();
		String OS = System.getProperty("os.name").toLowerCase();
		String project_Dir = System.getProperty("user.dir");
		FileInputStream fis;
		try {
			if( OS.indexOf("mac") >= 0) {
				fis = new FileInputStream(project_Dir+"/credentials.properties");
			} else {
				fis = new FileInputStream(project_Dir+"\\credentials.properties");
			}
			credentials.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isDebug() {
		if ( credentials.getProperty(debug).equals("true")) {
			return true;
		} else {
			return false;
		}

	}

	//	public String getRegion() {
	//		return credentials.getProperty(region);
	//	}

	public String getGmailUserId() {
		return credentials.getProperty(GmailUserID);
	}	

	public String getGmailPassword() {
		return credentials.getProperty(GmailPassword);
	}

	public String getSignupDomain() {
		return credentials.getProperty(domain);
	}



	//====================================
	// **** EU  Cluster ****
	//====================================

	public String getEUWebId() {
		return credentials.getProperty(euWebId);
	}
	public String getEUWebDisplayName() {
		return credentials.getProperty(euWebDisplayName);
	}
	public String getEUChromeId() {
		return credentials.getProperty(euChromeId);
	}
	public String getEUChromeDisplayName() {
		return credentials.getProperty(euChromeDisplayName);
	}

	public String getEUFirefoxId() {
		return credentials.getProperty(euFirefoxId);
	}
	public String getEUFirefoxDisplayName() {
		return credentials.getProperty(euFirefoxDisplayName);
	}
	public String getEUVaultId() {
		return credentials.getProperty(euVaultId);
	}
	public String getEUACPId() {
		return credentials.getProperty(euACPUserId);
	}

	//====================================
	// **** EU  Cluster Debug****
	//====================================

	public String getEUWebIdTest() {
		return credentials.getProperty(euWebIdTest);
	}
	public String getEUWebDisplayNameTest() {
		return credentials.getProperty(euWebDisplayNameTest);
	}
	public String getEUChromeIdTest() {
		return credentials.getProperty(euChromeIdTest);
	}
	public String getEUChromeDisplayNameTest() {
		return credentials.getProperty(euChromeDisplayNameTest);
	}
	public String getEUFirefoxIdTest() {
		return credentials.getProperty(euFirefoxIdTest);
	}
	public String getEUFirefoxDisplayNameTest() {
		return credentials.getProperty(euFirefoxDisplayNameTest);
	}

	public String getEUVaultIdTest() {
		return credentials.getProperty(euVaultIdTest);
	}
	public String getEUACPIdTest() {
		return credentials.getProperty(euACPUserIdTest);
	}



	//====================================
	// **** UMR  Cluster ****
	//====================================
	public String getUMRWebId() {
		return credentials.getProperty(umrWebId);
	}
	public String getUMRWebDisplayName() {
		return credentials.getProperty(umrWebDisplayName);
	}
	public String getUMRChromeId() {
		return credentials.getProperty(umrChromeId);
	}
	public String getUMRChromeDisplayName() {
		return credentials.getProperty(umrChromeDisplayName);
	}

	public String getUMRFirefoxId() {
		return credentials.getProperty(umrFirefoxId);
	}
	public String getUMRFirefoxDisplayName() {
		return credentials.getProperty(umrFirefoxDisplayName);
	}

	public String getUMRGuestId() {
		return credentials.getProperty(umrGuestId);
	}
	public String getUMRGuestDisplayName() {
		return credentials.getProperty(umrGuestDisplayName);
	}
	public String getUMRACPUserId() {
		return credentials.getProperty(umrACPUserId);
	}

	//====================================
	// **** UMR  Cluster Debug ****
	//====================================
	public String getUMRWebIdTest() {
		return credentials.getProperty(umrWebIdTest);
	}
	public String getUMRWebDisplayNameTest() {
		return credentials.getProperty(umrWebDisplayNameTest);
	}
	public String getUMRChromeIdTest() {
		return credentials.getProperty(umrChromeIdTest);
	}
	public String getUMRChromeDisplayNameTest() {
		return credentials.getProperty(umrChromeDisplayNameTest);
	}

	public String getUMRFirefoxIdTest() {
		return credentials.getProperty(umrFirefoxIdTest);
	}
	public String getUMRFirefoxDisplayNameTest() {
		return credentials.getProperty(umrFirefoxDisplayNameTest);
	}

	public String getUMRGuestIdTest() {
		return credentials.getProperty(umrGuestIdTest);
	}
	public String getUMRGuestDisplayNameTest() {
		return credentials.getProperty(umrGuestDisplayNameTest);
	}
	public String getUMRACPUserIdTest() {
		return credentials.getProperty(umrACPUserIdTest);
	}


	//====================================
	// **** DIMAS  Cluster ****
	//====================================

	public String getDimasWebId() {
		return credentials.getProperty(dimasWebId);
	}
	public String getDimasWebDisplayName() {
		return credentials.getProperty(dimasWebDisplayName);
	}
	public String getDimasChromeId() {
		return credentials.getProperty(dimasChromeId);
	}
	public String getDimasChromeDisplayName() {
		return credentials.getProperty(dimasChromeDisplayName);
	}

	public String getDimasFirefoxId() {
		return credentials.getProperty(dimasFirefoxId);
	}
	public String getDimasFirefoxDisplayName() {
		return credentials.getProperty(dimasFirefoxDisplayName);
	}

	public String getDimasGuestId() {
		return credentials.getProperty(dimasGuestId);
	}
	public String getDimasGuestDisplayName() {
		return credentials.getProperty(dimasGuestDisplayName);
	}
	public String getDimasACPUserId() {
		return credentials.getProperty(dimasACPUserId);
	}

	//====================================
	// **** DIMAS  Cluster Debug****
	//====================================
	public String getDimasWebIdTest() {
		return credentials.getProperty(dimasWebIdTest);
	}
	public String getDimasWebDisplayNameTest() {
		return credentials.getProperty(dimasWebDisplayNameTest);
	}
	public String getDimasChromeIdTest() {
		return credentials.getProperty(dimasChromeIdTest);
	}
	public String getDimasChromeDisplayNameTest() {
		return credentials.getProperty(dimasChromeDisplayNameTest);
	}

	public String getDimasFirefoxIdTest() {
		return credentials.getProperty(dimasFirefoxIdTest);
	}
	public String getDimasFirefoxDisplayNameTest() {
		return credentials.getProperty(dimasFirefoxDisplayNameTest);
	}

	public String getDimasGuestIdTest() {
		return credentials.getProperty(dimasGuestIdTest);
	}
	public String getDimasGuestDisplayNameTest() {
		return credentials.getProperty(dimasGuestDisplayNameTest);
	}
	public String getDimasACPUserIdTest() {
		return credentials.getProperty(dimasACPUserIdTest);
	}


	//====================================
	// **** MRI  Cluster ****
	//====================================

	public String getMRIWebId() {
		return credentials.getProperty(mriWebId);
	}
	public String getMRIWebDisplayName() {
		return credentials.getProperty(mriWebDisplayName);
	}
	public String getMRIChromeId() {
		return credentials.getProperty(mriChromeId);
	}
	public String getMRIChromeDisplayName() {
		return credentials.getProperty(mriChromeDisplayName);
	}

	public String getMRIFirefoxId() {
		return credentials.getProperty(mriFirefoxId);
	}
	public String getMRIFirefoxDisplayName() {
		return credentials.getProperty(mriFirefoxDisplayName);
	}
	public String getMRIACPUserId() {
		return credentials.getProperty(mriACPUserId);
	}

	//====================================
	// **** MRI  Cluster Debug ****
	//====================================

	public String getMRIWebIdTest() {
		return credentials.getProperty(mriWebIdTest);
	}
	public String getMRIWebDisplayNameTest() {
		return credentials.getProperty(mriWebDisplayNameTest);
	}
	public String getMRIChromeIdTest() {
		return credentials.getProperty(mriChromeIdTest);
	}
	public String getMRIChromeDisplayNameTest() {
		return credentials.getProperty(mriChromeDisplayNameTest);
	}
	public String getMRIFirefoxIdTest() {
		return credentials.getProperty(mriFirefoxIdTest);
	}
	public String getMRIFirefoxDisplayNameTest() {
		return credentials.getProperty(mriFirefoxDisplayNameTest);
	}
	public String getMRIACPUserIdTest() {
		return credentials.getProperty(mriACPUserIdTest);
	}



	//====================================
	// **** UKF  Cluster ****
	//====================================

	public String getUKFWebId() {
		return credentials.getProperty(ukfWebId);
	}
	public String getUKFWebDisplayName() {
		return credentials.getProperty(ukfWebDisplayName);
	}
	public String getUKFChromeId() {
		return credentials.getProperty(ukfChromeId);
	}
	public String getUKFChromeDisplayName() {
		return credentials.getProperty(ukfChromeDisplayName);
	}

	public String getUKFFirefoxId() {
		return credentials.getProperty(ukfFirefoxId);
	}
	public String getUKFFirefoxDisplayName() {
		return credentials.getProperty(ukfFirefoxDisplayName);
	}
	public String getUKFACPUserId() {
		return credentials.getProperty(ukfACPUserId);
	}

	//====================================
	// **** UKF  Cluster Debug ****
	//====================================

	public String getUKFWebIdTest() {
		return credentials.getProperty(ukfWebIdTest);
	}
	public String getUKFWebDisplayNameTest() {
		return credentials.getProperty(ukfWebDisplayNameTest);
	}
	public String getUKFChromeIdTest() {
		return credentials.getProperty(ukfChromeIdTest);
	}
	public String getUKFChromeDisplayNameTest() {
		return credentials.getProperty(ukfChromeDisplayNameTest);
	}

	public String getUKFFirefoxIdTest() {
		return credentials.getProperty(ukfFirefoxIdTest);
	}
	public String getUKFFirefoxDisplayNameTest() {
		return credentials.getProperty(ukfFirefoxDisplayNameTest);
	}
	public String getUKFACPUserIdTest() {
		return credentials.getProperty(ukfACPUserIdTest);
	}

	// ================================
	// ***  MS1 Cluster *******
	// ================================
	public String getMs1WebId() {
		return credentials.getProperty(ms1WebId);
	}
	public String getMs1WebDisplayName() {
		return credentials.getProperty(ms1WebDisplayName);
	}
	public String getMs1ChromeId() {
		return credentials.getProperty(ms1ChromeId);
	}
	public String getMs1ChromeDisplayName() {
		return credentials.getProperty(ms1ChromeDisplayName);
	}
	public String getMs1FirefoxId() {
		return credentials.getProperty(ms1FirefoxId);
	}
	public String getMs1FirefoxDisplayName() {
		return credentials.getProperty(ms1FirefoxDisplayName);
	}
	public String getMs1ACPUserId() {
		return credentials.getProperty(ms1ACPUserId);
	}

	// ================================
	// ***  MS1 Cluster Debug *******
	// ================================
	public String getMs1WebIdTest() {
		return credentials.getProperty(ms1WebIdTest);
	}
	public String getMs1WebDisplayNameTest() {
		return credentials.getProperty(ms1WebDisplayNameTest);
	}
	public String getMs1ChromeIdTest() {
		return credentials.getProperty(ms1ChromeIdTest);
	}
	public String getMs1ChromeDisplayNameTest() {
		return credentials.getProperty(ms1ChromeDisplayNameTest);
	}
	public String getMs1FirefoxIdTest() {
		return credentials.getProperty(ms1FirefoxIdTest);
	}
	public String getMs1FirefoxDisplayNameTest() {
		return credentials.getProperty(ms1FirefoxDisplayNameTest);
	}
	public String getMs1ACPUserIdTest() {
		return credentials.getProperty(ms1ACPUserIdTest);
	}

	// ================================
	// ***  MS2 Cluster *******
	// ================================
	public String getMs2WebId() {
		return credentials.getProperty(ms2WebId);
	}
	public String getMs2WebDisplayName() {
		return credentials.getProperty(ms2WebDisplayName);
	}
	public String getMs2ChromeId() {
		return credentials.getProperty(ms2ChromeId);
	}
	public String getMs2ChromeDisplayName() {
		return credentials.getProperty(ms2ChromeDisplayName);
	}
	public String getMs2FirefoxId() {
		return credentials.getProperty(ms2FirefoxId);
	}
	public String getMs2FirefoxDisplayName() {
		return credentials.getProperty(ms2FirefoxDisplayName);
	}
	public String getMs2VaultId() {
		return credentials.getProperty(ms2VaultId);
	}
	public String getMs2ACPUserId() {
		return credentials.getProperty(ms2ACPUserId);
	}

	// ================================
	// ***  MS2 Cluster Debug *******
	// ================================
	public String getMs2WebIdTest() {
		return credentials.getProperty(ms2WebIdTest);
	}
	public String getMs2WebDisplayNameTest() {
		return credentials.getProperty(ms2WebDisplayNameTest);
	}
	public String getMs2ChromeIdTest() {
		return credentials.getProperty(ms2ChromeIdTest);
	}
	public String getMs2ChromeDisplayNameTest() {
		return credentials.getProperty(ms2ChromeDisplayNameTest);
	}
	public String getMs2FirefoxIdTest() {
		return credentials.getProperty(ms2FirefoxIdTest);
	}
	public String getMs2FirefoxDisplayNameTest() {
		return credentials.getProperty(ms2FirefoxDisplayNameTest);
	}
	public String getMs2VaultIdTest() {
		return credentials.getProperty(ms2VaultIdTest);
	}
	public String getMs2ACPUserIdTest() {
		return credentials.getProperty(ms2ACPUserIdTest);
	}


	//====================================
	// **** AM1  Cluster  ****
	//====================================
	public String getAM1WebId() {
		return credentials.getProperty(am1WebId);
	}
	public String getAM1WebDisplayName() {
		return credentials.getProperty(am1WebDisplayName);
	}
	public String getAM1ChromeId() {
		return credentials.getProperty(am1ChromeId);
	}
	public String getAM1ChromeDisplayName() {
		return credentials.getProperty(am1ChromeDisplayName);
	}
	public String getAM1FirefoxId() {
		return credentials.getProperty(am1FirefoxId);
	}
	public String getAM1FirefoxDisplayName() {
		return credentials.getProperty(am1FirefoxDisplayName);
	}
	public String getAM1ACPUserId() {
		return credentials.getProperty(am1ACPUserId);
	}
	public String getAM1VaultId() {
		return credentials.getProperty(am1VaultId);
	}


	//====================================
	// **** AM1  Cluster Debug ****
	//====================================
	public String getAM1WebIdTest() {
		return credentials.getProperty(am1WebIdTest);
	}
	public String getAM1WebDisplayNameTest() {
		return credentials.getProperty(am1WebDisplayNameTest);
	}
	public String getAM1ChromeIdTest() {
		return credentials.getProperty(am1ChromeIdTest);
	}
	public String getAM1ChromeDisplayNameTest() {
		return credentials.getProperty(am1ChromeDisplayNameTest);
	}
	public String getAM1FirefoxIdTest() {
		return credentials.getProperty(am1FirefoxIdTest);
	}
	public String getAM1FirefoxDisplayNameTest() {
		return credentials.getProperty(am1FirefoxDisplayNameTest);
	}
	public String getAM1ACPUserIdTest() {
		return credentials.getProperty(am1ACPUserIdTest);
	}
	
	public String getAM1VaultIdTest() {
		return credentials.getProperty(am1VaultIdTest);
	}

	//====================================
	// **** APSE  Cluster ****
	//====================================
	public String getAPSEWebId() {
		return credentials.getProperty(apseWebId);
	}
	public String getAPSEWebDisplayName() {
		return credentials.getProperty(apseWebDisplayName);
	}
	public String getAPSEChromeId() {
		return credentials.getProperty(apseChromeId);
	}
	public String getAPSEChromeDisplayName() {
		return credentials.getProperty(apseChromeDisplayName);
	}
	public String getAPSEFirefoxId() {
		return credentials.getProperty(apseFirefoxId);
	}
	public String getAPSEFirefoxDisplayName() {
		return credentials.getProperty(apseFirefoxDisplayName);
	}
	public String getAPSEVaultId() {
		return credentials.getProperty(apseVaultId);
	}
	public String getAPSEACPId() {
		return credentials.getProperty(apseACPUserIdTest);
	}

	//====================================
	// **** APSE  Cluster Debug ****
	//====================================
	public String getAPSEWebIdTest() {
		return credentials.getProperty(apseWebIdTest);
	}
	public String getAPSEWebDisplayNameTest() {
		return credentials.getProperty(apseWebDisplayNameTest);
	}
	public String getAPSEChromeIdTest() {
		return credentials.getProperty(apseChromeIdTest);
	}
	public String getAPSEChromeDisplayNameTest() {
		return credentials.getProperty(apseChromeDisplayNameTest);
	}

	public String getAPSEFirefoxIdTest() {
		return credentials.getProperty(apseFirefoxIdTest);
	}
	public String getAPSEFirefoxDisplayNameTest() {
		return credentials.getProperty(apseFirefoxDisplayNameTest);
	}
	public String getAPSEVaultIdTest() {
		return credentials.getProperty(apseVaultIdTest);
	}
	public String getAPSEACPIdTest() {
		return credentials.getProperty(apseACPUserIdTest);
	}




	//====================================
	// **** ZOLLERNALB(ZK) Cluster  ****
	//====================================
	public String getZKWebId() {
		return credentials.getProperty(zkWebId);
	}
	public String getZKWebDisplayName() {
		return credentials.getProperty(zkWebDisplayName);
	}
	public String getZKChromeId() {
		return credentials.getProperty(zkChromeId);
	}
	public String getZKChromeDisplayName() {
		return credentials.getProperty(zkChromeDisplayName);
	}
	public String getZKFirefoxId() {
		return credentials.getProperty(zkFirefoxId);
	}
	public String getZKFirefoxDisplayName() {
		return credentials.getProperty(zkFirefoxDisplayName);
	}
	public String getZKACPUserId() {
		return credentials.getProperty(zkACPUserId);
	}


	//====================================
	// **** ZOLLERNALB(ZK) Cluster Debug ****
	//====================================
	public String getZKWebIdTest() {
		return credentials.getProperty(zkWebIdTest);
	}
	public String getZKWebDisplayNameTest() {
		return credentials.getProperty(zkWebDisplayNameTest);
	}
	public String getZKChromeIdTest() {
		return credentials.getProperty(zkChromeIdTest);
	}
	public String getZKChromeDisplayNameTest() {
		return credentials.getProperty(zkChromeDisplayNameTest);
	}
	public String getZKFirefoxIdTest() {
		return credentials.getProperty(zkFirefoxIdTest);
	}
	public String getZKFirefoxDisplayNameTest() {
		return credentials.getProperty(zkFirefoxDisplayNameTest);
	}
	public String getZKACPUserIdTest() {
		return credentials.getProperty(zkACPUserIdTest);
	}

	//====================================
	// **** APSE2  Cluster ****
	//====================================

	public String getAPSE2WebId() {
		return credentials.getProperty(apse2WebId);
	}
	public String getAPSE2WebDisplayName() {
		return credentials.getProperty(apse2WebDisplayName);
	}
	public String getAPSE2ChromeId() {
		return credentials.getProperty(apse2ChromeId);
	}
	public String getAPSE2ChromeDisplayName() {
		return credentials.getProperty(apse2ChromeDisplayName);
	}
	public String getAPSE2FirefoxId() {
		return credentials.getProperty(apse2FirefoxId);
	}
	public String getAPSE2FirefoxDisplayName() {
		return credentials.getProperty(apse2FirefoxDisplayName);
	}
	public String getAPSE2VaultId() {
		return credentials.getProperty(apse2VaultId);
	}
	public String getAPSE2ACPId() {
		return credentials.getProperty(apse2ACPUserId);
	}
	//====================================
	// **** APSE2   Cluster Debug ****
	//====================================
	public String getAPSE2WebIdTest() {
		return credentials.getProperty(apse2WebIdTest);
	}
	public String getAPSE2WebDisplayNameTest() {
		return credentials.getProperty(apse2WebDisplayNameTest);
	}
	public String getAPSE2ChromeIdTest() {
		return credentials.getProperty(apse2ChromeIdTest);
	}
	public String getAPSE2ChromeDisplayNameTest() {
		return credentials.getProperty(apse2ChromeDisplayNameTest);
	}
	public String getAPSE2FirefoxIdTest() {
		return credentials.getProperty(apse2FirefoxIdTest);
	}
	public String getAPSE2FirefoxDisplayNameTest() {
		return credentials.getProperty(apse2FirefoxDisplayNameTest);
	}
	public String getAPSE2VaultIdTest() {
		return credentials.getProperty(apse2VaultIdTest);
	}
	public String getAPSE2ACPIdTest() {
		return credentials.getProperty(apse2ACPUserIdTest);
	}



	//==============================
	// *** Password's
	//==============================
	public String getUserPassword() {
		return credentials.getProperty(userPassword);
	}

	public String getAdminPassword() {
		return credentials.getProperty(adminPassword);
	}

	//==============================
	// *** URL's
	//==============================
	public String getwebUrl() {
		return credentials.getProperty(webUrl);
	}
	public String getAnchorUrl() {
		return credentials.getProperty(anchorUrl);
	}	
	public String getliveusUrl() {
		return credentials.getProperty(liveusUrl);
	}
	public String getliveeuUrl() {
		return credentials.getProperty(liveeuUrl);
	}
	public String getms2VaultUrl() {
		return credentials.getProperty(ms2VaultUrl);
	}
	public String geteu1VaultUrl() {
		return credentials.getProperty(eu1VaultUrl);
	}
	public String getapseVaultUrl() {
		return credentials.getProperty(apseVaultUrl);
	}
	public String geteppdVaultUrl() {
		return credentials.getProperty(eppdVaultUrl);
	}
	
	public String getacpUrl() {
		return credentials.getProperty(acpUrl);
	}
	public String getms2Url() {
		return credentials.getProperty(ms2Url);
	}
	public String geteu1Url() {
		return credentials.getProperty(eu1Url);
	}
	public String getapse1Url() {
		return credentials.getProperty(apse1Url);
	}
	public String getapse2Url() {
		return credentials.getProperty(apse2Url);
	}
	public String getms7Url() {
		return credentials.getProperty(ms7Url);
	}
	public String getms1Url() {
		return credentials.getProperty(ms1Url);
	}
	public String getumrUrl() {
		return credentials.getProperty(umrUrl);
	}
	public String getdimasUrl() {
		return credentials.getProperty(dimasUrl);
	}
	public String getmriUrl() {
		return credentials.getProperty(mriUrl);
	}
	public String getNstUrl() {
		return credentials.getProperty(NstUrl);
	}
	public String getSignupUrl() {
		return credentials.getProperty(SignupUrl);
	}
	public String getDesktopClientUrl() {
		return credentials.getProperty(DesktopClientUrl);
	}
	public String getUKFUrl() {
		return credentials.getProperty(ukfUrl);
	}
	public String getZKUrl() {
		return credentials.getProperty(zkUrl);
	}
	public String getAM1Url() {
		return credentials.getProperty(am1Url);
	}
	public String getHelpUrl() {
		return credentials.getProperty(helpUrl);
	}
	public String getAPIUrl() {
		return credentials.getProperty(apiUrl);
	}
	public String getAPIUSUrl() {
		return credentials.getProperty(apiusUrl);
	}
	public String getAPIEUUrl() {
		return credentials.getProperty(apieuUrl);
	}
	public String getAPIAPSEUrl() {
		return credentials.getProperty(apiapseUrl);
	}
	public String getImageAttachment() {
		return credentials.getProperty(ImageAttachment);
	}


}



