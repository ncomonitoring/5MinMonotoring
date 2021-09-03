package Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Netsfere_URL_HealthCheck extends Base{

	@BeforeClass
	public void precleanup() throws IOException, InterruptedException {
		NetsfereActivity.killAllBrowserSessions();
		String udate = new SimpleDateFormat("dd-MM-yy-HHmmss").format(new java.util.Date());
		//		String proj_dir = System.getProperty("user.dir");
		System.setOut(new PrintStream(new FileOutputStream("\\X:\\"+"Monitoring2-" + udate +".txt")));
		//		System.setOut(new PrintStream(new FileOutputStream("Monitoring-" + udate +".txt")));
		System.out.println("Output File Created");
	}
	@Test(priority=2)
	public void Netsfere_Url_HealthCheck() throws FileNotFoundException {

		//	RequestSpecification httpRequest = RestAssured.given();


		System.out.println("=========================================================================");
		System.out.println("******** NetSfere URL Check - Basic health checks started... ************"); 
		System.out.println("TIme at the START of the Test Execution : " + ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));
		System.out.println("==========================================================================");


		HashMap<String , String> Server_URL_Map = new HashMap<String, String>();
		HashMap<String, List<String>> URL_RESPONSE = new HashMap<String, List<String>>();

		Server_URL_Map.put("webUrl" , Config.getInstance().getwebUrl());
		Server_URL_Map.put("anchorUrl" , Config.getInstance().getAnchorUrl());
		Server_URL_Map.put("liveusUrl" , Config.getInstance().getliveusUrl());
		Server_URL_Map.put("liveeuUrl" , Config.getInstance().getliveeuUrl());
		Server_URL_Map.put("ms2VaultUrl" , Config.getInstance().getms2VaultUrl());
		Server_URL_Map.put("eu1VaultUrl" , Config.getInstance().geteu1VaultUrl());
		Server_URL_Map.put("apseVaultUrl" , Config.getInstance().getapseVaultUrl());
		Server_URL_Map.put("acpUrl" , Config.getInstance().getacpUrl());
		Server_URL_Map.put("ms2Url" , Config.getInstance().getms2Url());
		Server_URL_Map.put("eu1Url" , Config.getInstance().geteu1Url());
		Server_URL_Map.put("apse1Url" , Config.getInstance().getapse1Url());
		Server_URL_Map.put("ms7Url" , Config.getInstance().getms7Url());
		Server_URL_Map.put("ms1Url" , Config.getInstance().getms1Url());
		Server_URL_Map.put("umrUrl" , Config.getInstance().getumrUrl());
		Server_URL_Map.put("dimasUrl" , Config.getInstance().getdimasUrl());
		Server_URL_Map.put("mriUrl" , Config.getInstance().getmriUrl());
		Server_URL_Map.put("NstUrl" , Config.getInstance().getNstUrl());
		Server_URL_Map.put("SignupUrl" , Config.getInstance().getSignupUrl());	
		Server_URL_Map.put("DesktopClientUrl" , Config.getInstance().getDesktopClientUrl());
		Server_URL_Map.put("UKFUrl",Config.getInstance().getUKFUrl());
		Server_URL_Map.put("HelpUrl",Config.getInstance().getHelpUrl());
		Server_URL_Map.put("AM1Url",Config.getInstance().getAM1Url());
		Server_URL_Map.put("ZKUrl",Config.getInstance().getZKUrl());
		Server_URL_Map.put("APIUrl",Config.getInstance().getAPIUrl());
		Server_URL_Map.put("APIUSUrl",Config.getInstance().getAPIUSUrl());
		Server_URL_Map.put("APIEUUrl",Config.getInstance().getAPIEUUrl());
		Server_URL_Map.put("APIAPSEUrl",Config.getInstance().getAPIAPSEUrl());

		URL_RESPONSE = NetsfereActivity.Netsfere_URL_HealthCheck(Server_URL_Map);
		List<String> response_data = new  ArrayList<String>();
		for( String ServerLabel : URL_RESPONSE.keySet()) {
			response_data = URL_RESPONSE.get(ServerLabel);

			if( ServerLabel == "webUrl") {
				System.out.println("web.netsfere.com url test.");
				if(response_data != null ) {
					System.out.println("The status code of web.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of web.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact netsfere web client, please check..!!");
				}
			}  else if (ServerLabel == "anchorUrl" )  {
				System.out.println("https://anchor.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of anchor.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of anchor.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact anchor.netsfere.com, please check..!!");
				}
			}  else if (ServerLabel == "liveusUrl" )  {
				System.out.println("https://live.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of live.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of live.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact live.netsfere.com, please check..!!");

				}
			} else if (ServerLabel == "liveeuUrl" )  {
				System.out.println("https://live-eu1.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of live-eu1.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of live-eu1.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact live-eu1.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "ms2VaultUrl" )  {
				System.out.println("https://vault.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of vault.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of vault.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact vault.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "eu1VaultUrl" )  {
				System.out.println("https://vault-eu1.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of vault-eu1.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of vault-eu1.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact vault-eu1.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "apseVaultUrl" )  {
				System.out.println("https://vault-apse1.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of vault-apse1.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of vault-apse1.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact vault-apse1.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "acpUrl" )  {
				System.out.println("https://admin.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of admin.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of admin.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact admin.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "ms2Url" )  {
				System.out.println("https://ms2.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of ms2.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of ms2.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact ms2.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "eu1Url" )  {
				System.out.println("https://eu1.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of eu1.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of eu1.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact eu1.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "apse1Url" )  {
				System.out.println("https://apse1.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of apse1.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of apse1.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact apse1.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "ms7Url" )  {
				System.out.println("https://ms7.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of ms7.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of ms7.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact ms7.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "ms1Url" )  {
				System.out.println("https://ms7.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of ms1.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of ms1.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact ms1.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "umrUrl" )  {
				System.out.println("https://umr.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of umr.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of umr.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact umr.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "dimasUrl" )  {
				System.out.println("https://dimas.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of dimas.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of dimas.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact dimas.netsfere.com, please check..!!");
				}
			} else if (ServerLabel == "mriUrl" )  {
				System.out.println("https://mri.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of mri.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of mri.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact mri.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "NstUrl" )  {
				System.out.println("https://www.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of www.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of www.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact www.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "SignupUrl" )  {
				System.out.println("https://signup.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact signup.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "AM1Url" )  {
				System.out.println("https://am1.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact https://am1.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "HelpUrl" )  {
				System.out.println("https://help.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact https://help.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "UKFUrl" )  {
				System.out.println("https://ukf.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact https://ukf.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "ZKUrl" )  {
				System.out.println("https://zollernalb.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact https://zollernalb.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "APIUrl" )  {
				System.out.println("https://api.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact https://api.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "APIUSUrl" )  {
				System.out.println("https://api-us.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact https://api-us.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "APIEUUrl" )  {
				System.out.println("https://api-eu.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact https://api-eu.netsfere.com, please check..!!");
				}
			} else if(ServerLabel == "APIAPSEUrl" )  {
				System.out.println("https://api-apse.netsfere.com  url test.");
				if(response_data != null ) {
					System.out.println("The status code of signup.netsfere.com is=" + " " + response_data.get(1));
					System.out.println("The status line of signup.netsfere.com is=" + " " +response_data.get(2) );
				} else {
					System.out.println("ERROR- Unable to contact https://api-apse.netsfere.com, please check..!!");
				}
			} 

		}

		for( String ServerLabel : URL_RESPONSE.keySet()) {
			response_data = URL_RESPONSE.get(ServerLabel);
			Assert.assertNotNull(response_data);
			Assert.assertEquals(response_data.get(0) , "PASS" , response_data.get(2));
		}

		System.out.println("=========================================================================");
		System.out.println("******** NetSfere URL Check - Basic health checks started... ************"); 
		System.out.println("TIme at the END of the Test Execution : " + ( new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(new java.util.Date())));
		System.out.println("==========================================================================");

	}

}
