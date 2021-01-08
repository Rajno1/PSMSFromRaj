package LoginFunctionalitys;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;

public class loginDetailsPage {
	private static GenericMethods generic;

	public static String path = "OutputData\\data.xlsx";
	static WebDriver Element = BrowserActions.driver;

	public static void loginPage() throws Throwable {
		generic = new GenericMethods();

		ExcelUtilities xl = new ExcelUtilities("Resource\\LoginData.xlsx");
		int row = xl.getRowCount("LoginDetails");
		int col = xl.getColumnCount("LoginDetails");

		System.out.println("no.of rows :" + row + " " + "no.of columns :" + col);
		for (int i = 2; i <= (row + 1); i++) {
			System.out.println(i);
			String username = xl.getCellData("LoginDetails", i, 0);
			String password = xl.getCellData("LoginDetails", i, 1);

			if (!username.isEmpty() && !password.isEmpty()) {
				generic.SetVal(Element, By.id("id_uname"), username);
				generic.SetVal(Element, By.id("id_password"), password);
				System.out.println("username:" + username + "--" + "password:" + password);
				xl.setCellDataOutput(path, "output", i, "username", username);
				xl.setCellDataOutput(path, "output", i, "password", password);
				xl.setCellDataOutput(path, "output", i, "Status", "PASS");
				generic.clickwebElement(Element, By.id("id_btnsubmit"));
			} else {
				xl.setCellDataOutput(path, "output", i, "username", username);
				xl.setCellDataOutput(path, "output", i, "password", password);
				xl.setCellDataOutput(path, "output", i, "Status", "FAIL");
			}
		}
	}

	// logout
	public static void logout() throws InterruptedException, IOException {

		if (!Element.findElements(By.xpath("(//button[@title='User Actions'])[2]")).isEmpty()) {
			generic.waitFor(3000);
			generic.clickwebElement(Element, By.xpath("(//button[@title='User Actions'])[2]"));
			generic.waitFor(3000);
			generic.clickwebElement(Element, By.xpath("//li[text()='Logout']"));
		}

	}
}
