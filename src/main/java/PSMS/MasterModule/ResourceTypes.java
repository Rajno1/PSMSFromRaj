package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class ResourceTypes extends BrowserActions {
	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// ResourceTypes
	public void resourceTypes() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("clientType");
		col = xl.getColumnCount("clientType");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Resource Types");
		// create col
		xl.addColumn("ResourceTypes", "Message");

		xl.addColumn("ResourceTypes", "Status");

		// Iterator for loop
		for (int ResourceTypes = 2; ResourceTypes <= (row + 1); ResourceTypes++) {
			System.out.println("ResourceTypes count : " + ResourceTypes);

			String resource_type = xl.getCellData("ResourceTypes", ResourceTypes, 0);
			String tx_valid_reason = xl.getCellData("ResourceTypes", ResourceTypes, 1);
			String Id_Ins_User = xl.getCellData("ResourceTypes", ResourceTypes, 2);
			String id_mod_user = xl.getCellData("ResourceTypes", ResourceTypes, 3);
			String dt_ins = xl.getCellData("ResourceTypes", ResourceTypes, 4);
			String dt_mod = xl.getCellData("ResourceTypes", ResourceTypes, 5);
			String description = xl.getCellData("ResourceTypes", ResourceTypes, 6);
			String Checkstatus = xl.getCellData("ResourceTypes", ResourceTypes, 7);
			search = xl.getCellData("ResourceTypes", ResourceTypes, 8);
			String type = xl.getCellData("ResourceTypes", ResourceTypes, 9);
			String recordAction = xl.getCellData("ResourceTypes", ResourceTypes, 10);
			// Add new record

			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
			// nm_client_type

			gen.SetVal(Element, By.xpath("//input[@name='resource_type']"), resource_type);
			// tx_valid_reason

			gen.SetVal(Element, By.name("tx_valid_reason"), tx_valid_reason);
			// client_desc

			gen.SetVal(Element, By.name("description"), description);

			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			// Checkstatus
			// gen.clickwebElement(Element, By.id("Checkstatus"));
			xl.setCellDataOutput(path, "ResourceTypes", ResourceTypes, "resource_type", resource_type);
			xl.setCellDataOutput(path, "ResourceTypes", ResourceTypes, "tx_valid_reason", tx_valid_reason);
			xl.setCellDataOutput(path, "ResourceTypes", ResourceTypes, "description", description);
			xl.setCellDataOutput(path, "ResourceTypes", ResourceTypes, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "ResourceTypes", ResourceTypes, "id_mod_user", id_mod_user);

			// Save Changes
			PSMSCommonElements.popElements(recordAction);
			// throw error alert message
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//div[text()=' Resource Type Already Exists']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = Element.findElement(By.xpath("//div[text()=' Resource Type Already Exists']"))
						.getText();
				// Assert.assertEquals(errormessage, " Resource Type Already Exists");

				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				xl.setCellData("ResourceTypes", "Message", ResourceTypes, errormessage);
				xl.setCellData("ResourceTypes", "Status", ResourceTypes, "FAIL");
			} else {
				xl.setCellData("ResourceTypes", "Message", ResourceTypes, "Record inserted successfully");
				xl.setCellData("ResourceTypes", "Status", ResourceTypes, "PASS");
			}
			Thread.sleep(3000);
		}

		PSMSCommonElements.MasterSearch(search);
		PSMSCommonElements.viewRecord();

		PSMSCommonElements.editRecord("Close");

		PSMSCommonElements.printRecord();

		PSMSCommonElements.deleteRecord();

		PSMSCommonElements.gridCount();

	}

	public void CheckBox() {
		Element.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
	}

}
