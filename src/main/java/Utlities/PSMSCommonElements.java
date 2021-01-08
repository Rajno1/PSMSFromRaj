package Utlities;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ExcelData.ExcelUtilities;

public class PSMSCommonElements {
	public static GenericMethods generic = new GenericMethods();
	static WebDriver Element = BrowserActions.driver;
	static String close2 = "//div[@class='modal-content']//following::span[text()='Close']";
	private static String requirement01;

	public static String textArea() {
		ExcelUtilities xl = new ExcelUtilities("Resource\\RequirementsModule.xlsx");
		int row = xl.getRowCount("Requirements");
		int col = xl.getColumnCount("Requirements");
		// Iterator for loop
		requirement01 = xl.getCellData("Requirements", 2, 4);
		return requirement01;
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- View record
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void viewRecord() throws Exception {
		Element.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if (!Element.findElements(By.xpath("(//button[@title='View Record'])[1]")).isEmpty()) {
			Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			generic.clickElement(Element, By.xpath("(//button[@title='View Record'])[1]"));
			generic.clickElement(Element, By.xpath("//span[text()='Close']"));
		} else {
			System.out.println("View Record not visible");
		}
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Edit record
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void editRecord(String close) throws Exception {
		if (!Element.findElements(By.xpath("(//button[@title='Edit Record'])[1]")).isEmpty()) {
			Element.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			if (close.contentEquals("Close")) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				generic.clickElement(Element, By.xpath("(//button[@title='Edit Record'])[1]"));
				generic.waitFor(3000);
				generic.clickElement(Element, By.xpath("//span[text()='Close']"));
			} else if (close.contentEquals("Cancel")) {
				generic.clickElement(Element, By.xpath("(//button[@title='Edit Record'])[1]"));

				generic.clickElement(Element, By.xpath("//span[text()='Cancel']"));
			}
		} else {
			System.out.println("Edit button not visible");
		}
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Print record
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void printRecord() throws Exception {
		Element.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		String print = "(//button[@title='Print Record'])[1]";
		generic.scrollToElement(Element, print);
		if (!Element.findElements(By.xpath(print)).isEmpty()) {
			Element.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			generic.clickElement(Element, By.xpath(print));

			String vendorContact1 = Element.getWindowHandle();
			Set<String> windows = Element.getWindowHandles();
			Iterator<String> it = windows.iterator();
			while (it.hasNext()) {
				String vendorContact2 = it.next();
				if (!vendorContact1.equals(vendorContact2)) {
					Element.switchTo().window(vendorContact2);
				}
			}
			generic.waitFor(3000);
			Element.switchTo().window(vendorContact1);
		} else {
			System.out.println("Print Record not visible");
		}
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Delete Record
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void deleteRecord() throws InterruptedException {
		Element.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if (!Element.findElements(By.xpath("(//button[@title='Delete Record'])[1]")).isEmpty()) {
			Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			generic.clickElement(Element, By.xpath("(//button[@title='Delete Record'])[1]"));
			Element.switchTo().alert().accept();
			Element.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
			generic.scrollToElement(Element, "(//input[@type='checkbox'])[1]");
			Element.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		} else {
			System.out.println("Delete Record not visible");
		}
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Pop-Up Element(Save changes,Close,Reset buttons)
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void popElements(String S_C_Button) throws InterruptedException {
		// Save Changes
		if (S_C_Button.contentEquals("Save Changes")) {
			generic.clickElement(Element, By.xpath("//span[text()='Save Changes']"));
		} else if (S_C_Button.contentEquals("Close")) {
			// Close
			generic.clickElement(Element, By.xpath("//span[text()='Close']"));
		} else if (S_C_Button.contentEquals("reset")) {
			// reset

		}
	}

	public static void MasterSearch(String textValue) throws Exception {
		generic.SetVal(Element, By.xpath("//div[@title='Search']/div/input[@placeholder='Search']"), textValue);
		Element.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (!Element.findElements(By.xpath("(//input[@type='checkbox'])[2]")).isEmpty()) {
			Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Element.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		} else {
			System.out.println("No check box");
		}
	}

	public static void masterDisplay(String S_C_Button, String text) throws Exception {
		popElements(S_C_Button);
		MasterSearch(text);
	}

	public static void adminElement(String menu) throws InterruptedException {
		generic.clickElement(Element, By.xpath("//span[text()='Admin']"));
		generic.menuType(Element, "//li[@role='menuitem']", menu);
		generic.clickElement(Element, By.xpath("//button[@type='button' and @aria-label='Add']"));
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Notes tab
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void notesCloseButton() {
		Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (!Element.findElements(By.xpath(close2)).equals(Element.findElements(By.xpath("//span[text()='Close']")))) {
			Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			generic.ClickElementJs(Element, By.xpath(close2));
		} else {
			generic.ClickElementJs(Element, By.xpath("//span[text()='Close']"));
		}
		generic.waitFor(3000);
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Bulk Print
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void bulkPrinrt() throws InterruptedException {
		generic.clickElement(Element, By.xpath("//button[@title='Print']"));
		String BulkPrint = Element.getWindowHandle();
		Set<String> BulkPrint1 = Element.getWindowHandles();
		Iterator<String> it = BulkPrint1.iterator();
		while (it.hasNext()) {
			String BulkPrint2 = it.next();
			if (!BulkPrint.equals(BulkPrint2)) {
				Element.switchTo().window(BulkPrint2);
			}
		}
		generic.waitFor(3000);
		Element.switchTo().window(BulkPrint);
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Bulk Delete
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void bulkDelete() {
		BrowserActions.driver.findElement(By.xpath("//button[@aria-label='delete' or @title='Delete']")).click();
		generic.waitFor(3000);
		Element.switchTo().alert().accept();
		generic.waitFor(3000);
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Grid Count
	 * 
	 * @param driver --- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static void gridCount() throws InterruptedException {
		Element.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		generic.waitFor(3000);

		// Requirements table count
		Element.findElement(By.xpath("//div[@aria-haspopup='listbox']")).click();
		Element.findElement(By.xpath("(//ul[@tabindex='-1']/li)[8]")).click();
		generic.waitFor(3000);

		Element.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		generic.waitFor(3000);

		// Bulk Print
		PSMSCommonElements.bulkPrinrt();
		generic.waitFor(3000);

		List<WebElement> table = Element.findElements(By.xpath("//tbody[@class='MuiTableBody-root']/tr"));
		int TableCount = table.size();
		System.out.println("Grid Count :" + TableCount);
		generic.waitFor(3000);
	}
}
