package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class ResourceLegalStatus extends BrowserActions {

	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// //ResourceLegalStatus
	public void resourceLegalStatus() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("ResourceLegalStatus");
		col = xl.getColumnCount("ResourceLegalStatus");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Resource Legal Status");
		// create col
		xl.addColumn("ResourceLegalStatus", "Message");

		xl.addColumn("ResourceLegalStatus", "Status");
		// Iterator for loop
		for (int ResourceLegalStatus = 2; ResourceLegalStatus <= (row + 1); ResourceLegalStatus++) {
			System.out.println("ResourceLegalStatus count : " + ResourceLegalStatus);

			String nm_legal = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 0);
			String TX_ValidReason = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 1);
			String Id_Ins_User = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 2);
			String id_mod_user = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 3);
			String dt_ins = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 4);
			String dt_mod = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 5);
			String description = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 6);
			String Checkstatus = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 7);
			search = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 8);
			String type = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 9);
			String recordAction = xl.getCellData("ResourceLegalStatus", ResourceLegalStatus, 10);
			// Add new record

			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
			gen.waitFor(1000);
			gen.SetVal(Element, By.xpath("//input[@name='nm_legal']"), nm_legal);
			gen.SetVal(Element, By.xpath("//textarea[@name='description']"), description);
			gen.SetVal(Element, By.name("tx_valid_reason"), TX_ValidReason);
			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			xl.setCellDataOutput(path, "ResourceLegalStatus_output", ResourceLegalStatus, "nm_legal", nm_legal);
			xl.setCellDataOutput(path, "ResourceLegalStatus_output", ResourceLegalStatus, "TX_ValidReason",
					TX_ValidReason);
			xl.setCellDataOutput(path, "ResourceLegalStatus_output", ResourceLegalStatus, "description", description);
			xl.setCellDataOutput(path, "ResourceLegalStatus_output", ResourceLegalStatus, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "ResourceLegalStatus_output", ResourceLegalStatus, "id_mod_user", id_mod_user);

			// Save Changes
			PSMSCommonElements.popElements(recordAction);
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//div[text()=' Resource Legal Status Already Exists']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = Element
						.findElement(By.xpath("//div[text()=' Resource Legal Status Already Exists']")).getText();
				// Assert.assertEquals(errormessage, " Resource Legal Status Already Exists");
				gen.clickElement(Element, By.xpath("//span[text()='Close']"));

				xl.setCellData("ResourceLegalStatus", "Message", ResourceLegalStatus, errormessage);
				xl.setCellData("ResourceLegalStatus", "Status", ResourceLegalStatus, "FAIL");
			} else {
				xl.setCellData("ResourceLegalStatus", "Message", ResourceLegalStatus, "Record inserted successfully");
				xl.setCellData("ResourceLegalStatus", "Status", ResourceLegalStatus, "PASS");
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
