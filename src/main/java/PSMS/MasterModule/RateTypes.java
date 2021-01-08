package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class RateTypes {
	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// Ratetypes
	public void Ratetypes() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("Rate Type");
		col = xl.getColumnCount("Rate Type");

		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));

		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Rate types");
		xl.addColumn("Rate Type", "Message");
		xl.addColumn("Rate Type", "Status");
		// Iterator for loop
		for (int rateTypedata = 2; rateTypedata <= (row + 1); rateTypedata++) {
			System.out.println("rateTypedata count : " + rateTypedata);

			String rateType = xl.getCellData("Rate Type", rateTypedata, 0);
			String TX_ValidReason = xl.getCellData("Rate Type", rateTypedata, 1);
			String Id_Ins_User = xl.getCellData("Rate Type", rateTypedata, 2);
			String id_mod_user = xl.getCellData("Rate Type", rateTypedata, 3);
			String dt_ins = xl.getCellData("Rate Type", rateTypedata, 4);
			String dt_mod = xl.getCellData("Rate Type", rateTypedata, 5);
			String Description = xl.getCellData("Rate Type", rateTypedata, 6);
			String Checkstatus = xl.getCellData("Rate Type", rateTypedata, 7);
			search = xl.getCellData("Rate Type", rateTypedata, 8);
			String type = xl.getCellData("Rate Type", rateTypedata, 9);
			// Add new record

			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
			// nm_client_type

			gen.SetVal(Element, By.xpath("//input[@name='nm_rate_type']"), rateType);

			// tx_valid_reason
			gen.SetVal(Element, By.xpath("//input[@name='tx_valid_reason']"), TX_ValidReason);

			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			// Checkstatus
			if (Checkstatus.contentEquals("N")) {
				gen.clickElement(Element, By.xpath("//label[text()='Checkstatus']/..//span[text()='No']"));
			} else if (Checkstatus.contentEquals("Y")) {
				gen.clickElement(Element, By.xpath("//label[text()='Checkstatus']/..//span[text()='Yes']"));
			}

			// client_desc
			gen.SetVal(Element, By.xpath("//textarea[@name='Description']"), Description);

			xl.setCellDataOutput(path, "Rate Type_Output", rateTypedata, "RateType", rateType);
			xl.setCellDataOutput(path, "Rate Type_Output", rateTypedata, "TX_ValidReason", TX_ValidReason);
			xl.setCellDataOutput(path, "Rate Type_Output", rateTypedata, "Description", Description);
			xl.setCellDataOutput(path, "Rate Type_Output", rateTypedata, "Checkstatus", Checkstatus);
			xl.setCellDataOutput(path, "Rate Type_Output", rateTypedata, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "Rate Type_Output", rateTypedata, "id_mod_user", id_mod_user);

			// Save Changes
			gen.clickElement(Element, By.xpath("//span[text()='Save Changes']"));

			// handling throw error alert message
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//div[text()='Rate Type already exists']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = Element.findElement(By.xpath("// div[@class='text-danger mt-1']")).getText();
				Assert.assertEquals(errormessage, "Rate Type already exists");

				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				xl.setCellData("Rate Type", "Message", rateTypedata, errormessage);
				xl.setCellData("Rate Type", "Status", rateTypedata, "FAIL");
			} else {
				xl.setCellData("Rate Type", "Message", rateTypedata, "Record inserted successfully");
				xl.setCellData("Rate Type", "Status", rateTypedata, "PASS");
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
