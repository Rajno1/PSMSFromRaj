package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class ResourceJobTitles {
	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// ResourceJobTitles
	public void resourceJobTitles() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("ResourceJobTitles");
		col = xl.getColumnCount("ResourceJobTitles");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Resource Job Titles");
		// create col
		xl.addColumn("ResourceJobTitles", "Message");

		xl.addColumn("ResourceJobTitles", "Status");
		// Iterator for loop
		for (int ResourceJobTitles = 2; ResourceJobTitles <= (row + 1); ResourceJobTitles++) {
			System.out.println("ResourceJobTitles count : " + ResourceJobTitles);

			String NM_JOBTITLE = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 0);
			String Id_Ins_User = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 1);
			String id_mod_user = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 2);
			String dt_ins = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 3);
			String dt_mod = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 4);
			String TXT_DESCRIPTION = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 5);
			String Checkstatus = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 6);
			search = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 7);
			String type = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 8);
			String recordAction = xl.getCellData("ResourceJobTitles", ResourceJobTitles, 9);
			// Add new record

			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));

			gen.SetVal(Element, By.xpath("//input[@name='NM_JOBTITLE']"), NM_JOBTITLE);

			gen.SetVal(Element, By.xpath("//textarea[@name='TXT_DESCRIPTION']"), TXT_DESCRIPTION);
			gen.setText(Element, By.xpath("//input[@name='ID_INS_USER']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='ID_MOD_USER']"), id_mod_user);

			xl.setCellDataOutput(path, "ResourceJobTitles_output", ResourceJobTitles, "NM_JOBTITLE", NM_JOBTITLE);
			xl.setCellDataOutput(path, "ResourceJobTitles_output", ResourceJobTitles, "TXT_DESCRIPTION",
					TXT_DESCRIPTION);
			xl.setCellDataOutput(path, "ResourceJobTitles_output", ResourceJobTitles, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "ResourceJobTitles_output", ResourceJobTitles, "id_mod_user", id_mod_user);

			// Save Changes
			PSMSCommonElements.popElements(recordAction);
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//*[text()='Job Title already exists!']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				String errormessage = Element.findElement(By.xpath("//*[text()='Job Title already exists!']"))
						.getText();
				Assert.assertEquals(errormessage, "Job Title already exists!");

				xl.setCellData("ResourceJobTitles", "Message", ResourceJobTitles, errormessage);
				xl.setCellData("ResourceJobTitles", "Status", ResourceJobTitles, "FAIL");
			} else {
				xl.setCellData("ResourceJobTitles", "Message", ResourceJobTitles, "Record inserted successfully");
				xl.setCellData("ResourceJobTitles", "Status", ResourceJobTitles, "PASS");
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
