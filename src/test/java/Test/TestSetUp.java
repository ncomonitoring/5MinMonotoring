package Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class TestSetUp extends Base {
	
	@BeforeSuite
	public void CreateLogFile() throws IOException, InterruptedException {
		NetsfereActivity.killAllBrowserSessions();
		String udate = new SimpleDateFormat("dd-MM-yy-HHmmss").format(new java.util.Date());
		if ( Config.getInstance().getExecutionRegion().contains("EU")) {
			System.setOut(new PrintStream(new FileOutputStream("\\Y:\\NS_Automon_logs\\"+"Monitoring1-" + udate +".txt")));
//			System.setOut(new PrintStream(new FileOutputStream("C:\\Murali\\NCO-Monit-Logs\\"+"Monitoring1-" + udate +".txt")));
		} else if ( Config.getInstance().getExecutionRegion().contains("MS2")){
			System.setOut(new PrintStream(new FileOutputStream("\\Y:\\NS_Automon_logs\\"+"Monitoring2-" + udate +".txt")));
//			System.setOut(new PrintStream(new FileOutputStream("C:\\Murali\\NCO-Monit-Logs\\"+"Monitoring2-" + udate +".txt")));
		}
		System.out.println("Output File Created");
	}

	//	@BeforeTest
	public void outputfile() throws IOException, InterruptedException {
		NetsfereActivity.killAllBrowserSessions();
	}

}
