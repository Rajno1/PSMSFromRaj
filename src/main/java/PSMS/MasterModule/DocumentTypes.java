package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class DocumentTypes {
	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	private String document_type;
	WebDriver Element = BrowserActions.driver;

	public void DocumentTypePage() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("DocumentTypes");
		col = xl.getColumnCount("DocumentTypes");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Document Types");
		// create col
		xl.addColumn("DocumentTypes", "Message");

		xl.addColumn("DocumentTypes", "Status");
		for (int DocumentTypes = 2; DocumentTypes <= (row + 1); DocumentTypes++) {
			System.out.println("Clientdata count : " + DocumentTypes);

			document_type = xl.getCellData("DocumentTypes", DocumentTypes, 0);
			String recordAction = xl.getCellData("DocumentTypes", DocumentTypes, 1);

			// Add Record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
			gen.waitFor(3000);

			// Type
			gen.SetVal(Element, By.name("document_type"), document_type);
			gen.waitFor(3000);

			// Record save,Reset and close
			PSMSCommonElements.popElements(recordAction);
			gen.waitFor(3000);

			// throw error alert message
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//div[text()='Type already exists']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = Element.findElement(By.xpath("// div[@class='text-danger mt-1']")).getText();
				Assert.assertEquals(errormessage, "Type already exists");
				gen.waitFor(3000);
				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				xl.setCellData("DocumentTypes", "Message", DocumentTypes, errormessage);
				xl.setCellData("DocumentTypes", "Status", DocumentTypes, "FAIL");
			} else {
				xl.setCellData("DocumentTypes", "Message", DocumentTypes, "Record inserted successfully");
				xl.setCellData("DocumentTypes", "Status", DocumentTypes, "PASS");
			}
			gen.waitFor(3000);
		}

		gen.waitFor(3000);
		PSMSCommonElements.MasterSearch(document_type);
		PSMSCommonElements.viewRecord();
		gen.waitFor(3000);
		PSMSCommonElements.editRecord("Close");
		gen.waitFor(3000);
		PSMSCommonElements.printRecord();

		PSMSCommonElements.deleteRecord();

		PSMSCommonElements.gridCount();
	}

	public void CheckBox() {
		Element.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
	}

}
