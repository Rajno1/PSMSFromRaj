package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class ResourceJobStatus extends BrowserActions {
	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// JobStatus
	public void JobStatus() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("Resource Job Status");
		col = xl.getColumnCount("Resource Job Status");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Resource Job Status");
		// create col
		xl.addColumn("Resource Job Status", "Message");

		xl.addColumn("Resource Job Status", "Status");

		// Iterator for loop
		for (int ResourceJobStatus = 2; ResourceJobStatus <= (row + 1); ResourceJobStatus++) {
			System.out.println("ResourceJobStatus count : " + ResourceJobStatus);

			String nm_emp_status = xl.getCellData("Resource Job Status", ResourceJobStatus, 0);
			String TX_ValidReason = xl.getCellData("Resource Job Status", ResourceJobStatus, 1);
			String Id_Ins_User = xl.getCellData("Resource Job Status", ResourceJobStatus, 2);
			String id_mod_user = xl.getCellData("Resource Job Status", ResourceJobStatus, 3);
			String dt_ins = xl.getCellData("Resource Job Status", ResourceJobStatus, 4);
			String dt_mod = xl.getCellData("Resource Job Status", ResourceJobStatus, 5);
			String desc_emp_status = xl.getCellData("Resource Job Status", ResourceJobStatus, 6);
			String Checkstatus = xl.getCellData("Resource Job Status", ResourceJobStatus, 7);
			search = xl.getCellData("Resource Job Status", ResourceJobStatus, 8);
			String type = xl.getCellData("Resource Job Status", ResourceJobStatus, 9);
			String recordAction = xl.getCellData("Resource Job Status", ResourceJobStatus, 10);
			// Add new record

			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));

			// nm_client_type
			gen.SetVal(Element, By.xpath("//input[@name='nm_emp_status']"), nm_emp_status);

			// tx_valid_reason
			gen.SetVal(Element, By.name("tx_valid_reason"), TX_ValidReason);
			// client_desc

			gen.SetVal(Element, By.name("desc_emp_status"), desc_emp_status);

			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			// Checkstatus
			// gen.clickwebElement(Element, By.id("Checkstatus"));
			xl.setCellDataOutput(path, "Resource Job Status_output", ResourceJobStatus, "nm_emp_status", nm_emp_status);
			xl.setCellDataOutput(path, "Resource Job Status_output", ResourceJobStatus, "TX_ValidReason",
					TX_ValidReason);
			xl.setCellDataOutput(path, "Resource Job Status_output", ResourceJobStatus, "desc_emp_status",
					desc_emp_status);
			xl.setCellDataOutput(path, "Resource Job Status_output", ResourceJobStatus, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "Resource Job Status_output", ResourceJobStatus, "id_mod_user", id_mod_user);

			// Save Changes
			PSMSCommonElements.popElements(recordAction);
			// throw error alert message
			// alertbox
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!(Element.findElements(By.xpath("//div[@class='alert alert-danger']")).size() < 1)) {

				gen.ClickElementJs(Element, By.xpath("//span[text()='Close']"));
				String errormessage = Element.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();

				xl.setCellData("Resource Job Status", "Message", ResourceJobStatus, errormessage);
				xl.setCellData("Resource Job Status", "Status", ResourceJobStatus, "FAIL");
			} else {
				xl.setCellData("Resource Job Status", "Message", ResourceJobStatus, "Record inserted successfully");
				xl.setCellData("Resource Job Status", "Status", ResourceJobStatus, "PASS");
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
