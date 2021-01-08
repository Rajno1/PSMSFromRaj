package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class RequirementStatus {
	GenericMethods gen = new GenericMethods();

	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";

	private String requirement_status;
	WebDriver Element = BrowserActions.driver;

	// RequirementStatus
	public void RequirementStatus() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("Requirement Status");
		col = xl.getColumnCount("Requirement Status");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Requirement Status");
		// create col
		xl.addColumn("Requirement Status", "Message");

		xl.addColumn("Requirement Status", "Status");

		// Iterator for loop
		for (int RequirementStatus = 2; RequirementStatus <= (row + 1); RequirementStatus++) {
			System.out.println("RequirementStatus count : " + RequirementStatus);

			requirement_status = xl.getCellData("Requirement Status", RequirementStatus, 0);
			String TX_ValidReason = xl.getCellData("Requirement Status", RequirementStatus, 1);
			String Id_Ins_User = xl.getCellData("Requirement Status", RequirementStatus, 2);
			String id_mod_user = xl.getCellData("Requirement Status", RequirementStatus, 3);
			String dt_ins = xl.getCellData("Requirement Status", RequirementStatus, 4);
			String dt_mod = xl.getCellData("Requirement Status", RequirementStatus, 5);
			String status_description = xl.getCellData("Requirement Status", RequirementStatus, 6);
			String Checkstatus = xl.getCellData("Requirement Status", RequirementStatus, 7);
			String type = xl.getCellData("Requirement Status", RequirementStatus, 8);
			String recordAction = xl.getCellData("Requirement Status", RequirementStatus, 9);
			// Add new record

			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));

			// nm_client_type
			gen.SetVal(Element, By.name("requirement_status"), requirement_status);

			// tx_valid_reason
			gen.SetVal(Element, By.name("tx_valid_reason"), TX_ValidReason);

			// client_desc
			gen.SetVal(Element, By.name("status_description"), status_description);

			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			// Checkstatus
			// gen.clickwebElement(Element, By.id("Checkstatus"));

			xl.setCellDataOutput(path, "Requirement Status_output", RequirementStatus, "requirement_status",
					requirement_status);
			xl.setCellDataOutput(path, "Requirement Status_output", RequirementStatus, "TX_ValidReason",
					TX_ValidReason);
			xl.setCellDataOutput(path, "Requirement Status_output", RequirementStatus, "status_description",
					status_description);
			xl.setCellDataOutput(path, "Requirement Status_output", RequirementStatus, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "Requirement Status_output", RequirementStatus, "id_mod_user", id_mod_user);

			// Save Changes
			PSMSCommonElements.popElements(recordAction);
			xl.setCellDataOutput(path, "Requirement Status_output", RequirementStatus, "recordAction", recordAction);

			// throw error alert message
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//div[text()='Requirement Status already exists']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = Element.findElement(By.xpath("// div[@class='text-danger mt-1']")).getText();
				Assert.assertEquals(errormessage, "Requirement Status already exists");

				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				xl.setCellData("Requirement Status", "Message", RequirementStatus, errormessage);
				xl.setCellData("Requirement Status", "Status", RequirementStatus, "FAIL");
			} else {
				xl.setCellData("Requirement Status", "Message", RequirementStatus, "Record inserted successfully");
				xl.setCellData("Requirement Status", "Status", RequirementStatus, "PASS");
			}
			Thread.sleep(3000);
		}

		PSMSCommonElements.MasterSearch(requirement_status);
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
