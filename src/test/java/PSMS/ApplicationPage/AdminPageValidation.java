package PSMS.ApplicationPage;

import org.testng.annotations.Test;

import Utlities.BrowserActions;

public class AdminPageValidation extends Conditions {

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- PSMS Application validation
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * @Framework --- TestNg and Data Driven Framework(Hybrid Framework)
	 * 
	 * ##############################################################
	 */

	@Test
	public void PSMSApplicationValidation() throws Throwable {
		BrowserActions.modulesPage();
	}
}
