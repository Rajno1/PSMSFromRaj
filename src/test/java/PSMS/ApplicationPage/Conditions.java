package PSMS.ApplicationPage;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import LoginFunctionalitys.loginDetailsPage;
import Utlities.BrowserActions;
import Utlities.GenericMethods;

public class Conditions {
	String username = "AdminPage16";

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- PSMS Application validation
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * @TestNg Annotation --- Pre-Conditions and Post-Conditions
	 * ##############################################################
	 */

	// 191
	@BeforeClass
	public void initialization() throws Exception {
		BrowserActions.launchBrowser();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		loginDetailsPage.logout();
		Thread.sleep(3000);
		BrowserActions.driver.quit();
	}

	@AfterMethod
	public static void screenShotPage(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			GenericMethods.getScreenShot(BrowserActions.driver);
		}
	}

}
