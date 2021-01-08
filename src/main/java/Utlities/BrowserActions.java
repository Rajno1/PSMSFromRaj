package Utlities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import ExcelData.ExcelUtilities;
import LoginFunctionalitys.loginDetailsPage;

import PSMS.MasterModule.MasterModule;


public class BrowserActions {

	public static GenericMethods generic = new GenericMethods();
	public static WebDriver driver;
	public String searchName = "@2process@";

	public static void launchBrowser() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		System.setProperty("webdriver.chrome.driver", "Browsers\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ExcelUtilities xl = new ExcelUtilities("Resource\\LoginData.xlsx");
		driver.get(xl.getCellData("URL", 2, 0));
		driver.manage().deleteAllCookies();
		// --Verify title--
		Assert.assertEquals(driver.getTitle(), "PSMS");
	}

	public static void modulesPage() throws Throwable {

		ExcelUtilities xla = new ExcelUtilities("Resource\\ApplicationModules.xlsx");
		int row = xla.getRowCount("Modules");
		int col = xla.getColumnCount("Modules");
		// Excel sheet data count
		System.out.println("no.of rows :" + row + " " + "no.of columns :" + col);

		// Iterator for loop
		for (int Modules = 2; Modules < row; Modules++) {

			String ModuleName = xla.getCellData("Modules", Modules, 0);
			String IsExcute = xla.getCellData("Modules", Modules, 1);

			if (IsExcute.contentEquals("Y") && ModuleName.contentEquals("loginDetailsPage")) {
				// --Landing page--
				loginDetailsPage.loginPage();
			}

			if (IsExcute.contentEquals("Y") && ModuleName.contentEquals("MasterModule")) {
				// --Master module--
				MasterModule.ElementsPage();
			}
		}
	}
}

