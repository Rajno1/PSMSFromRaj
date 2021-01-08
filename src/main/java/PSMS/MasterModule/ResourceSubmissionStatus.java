package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class ResourceSubmissionStatus extends BrowserActions {

	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// ResourceSubmissionStatus
	public void resourceSubmissionStatus() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("ResourceSubmissionStatus");
		col = xl.getColumnCount("ResourceSubmissionStatus");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Resource Submission Status");
		// create col
		xl.addColumn("ResourceSubmissionStatus", "Message");

		xl.addColumn("ResourceSubmissionStatus", "Status");

		// Iterator for loop
		for (int ResourceSubmissionStatus = 2; ResourceSubmissionStatus <= (row + 1); ResourceSubmissionStatus++) {
			System.out.println("ResourceSubmissionStatus count : " + ResourceSubmissionStatus);

			String submission_status = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 0);
			String tx_valid_reason = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 1);
			String Id_Ins_User = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 2);
			String id_mod_user = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 3);
			String dt_ins = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 4);
			String dt_mod = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 5);
			String description = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 6);
			String Checkstatus = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 7);
			search = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 8);
			String type = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 9);
			String recordAction = xl.getCellData("ResourceSubmissionStatus", ResourceSubmissionStatus, 10);
			// Add new record

			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
			// nm_client_type

			gen.SetVal(Element, By.name("submission_status"), submission_status);
			// tx_valid_reason

			gen.SetVal(Element, By.name("tx_valid_reason"), tx_valid_reason);
			// client_desc

			gen.SetVal(Element, By.name("description"), description);

			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			// Checkstatus
			// gen.clickwebElement(Element, By.id("Checkstatus"));
			xl.setCellDataOutput(path, "ResourceSubmissionStatus", ResourceSubmissionStatus, "submission_status",
					submission_status);
			xl.setCellDataOutput(path, "ResourceSubmissionStatus", ResourceSubmissionStatus, "TX_ValidReason",
					tx_valid_reason);
			xl.setCellDataOutput(path, "ResourceSubmissionStatus", ResourceSubmissionStatus, "description",
					description);
			xl.setCellDataOutput(path, "ResourceSubmissionStatus", ResourceSubmissionStatus, "Id_Ins_User",
					Id_Ins_User);
			xl.setCellDataOutput(path, "ResourceSubmissionStatus", ResourceSubmissionStatus, "id_mod_user",
					id_mod_user);

			// Save Changes
			PSMSCommonElements.popElements(recordAction);
			// throw error alert message
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!(Element.findElements(By.xpath("//div[text()='Resource Submission Status already exists']"))
					.size() < 1)) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				String errormessage = Element
						.findElement(By.xpath("//div[text()='Resource Submission Status already exists']")).getText();

				xl.setCellData("ResourceSubmissionStatus", "Message", ResourceSubmissionStatus, errormessage);
				xl.setCellData("ResourceSubmissionStatus", "Status", ResourceSubmissionStatus, "FAIL");
			} else {
				xl.setCellData("ResourceSubmissionStatus", "Message", ResourceSubmissionStatus,
						"Record inserted successfully");
				xl.setCellData("ResourceSubmissionStatus", "Status", ResourceSubmissionStatus, "PASS");
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
