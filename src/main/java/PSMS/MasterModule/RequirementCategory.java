package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class RequirementCategory extends BrowserActions {
	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// RequirementCategory
	public void requirementCategory() throws Exception {
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("Requirement Category");
		col = xl.getColumnCount("Requirement Category");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Requirement Category");
		// create col
		xl.addColumn("Requirement Category", "Message");

		xl.addColumn("Requirement Category", "Status");

		for (int RequirementCategory = 2; RequirementCategory <= (row + 1); RequirementCategory++) {
			System.out.println("RequirementCategory count : " + RequirementCategory);

			String Requirement_Category = xl.getCellData("Requirement Category", RequirementCategory, 0);
			String TX_ValidReason = xl.getCellData("Requirement Category", RequirementCategory, 1);
			String Id_Ins_User = xl.getCellData("Requirement Category", RequirementCategory, 2);
			String id_mod_user = xl.getCellData("Requirement Category", RequirementCategory, 3);
			String dt_ins = xl.getCellData("Requirement Category", RequirementCategory, 4);
			String dt_mod = xl.getCellData("Requirement Category", RequirementCategory, 5);
			String RequirementCategory_Description = xl.getCellData("Requirement Category", RequirementCategory, 6);
			String Checkstatus = xl.getCellData("Requirement Category", RequirementCategory, 7);
			search = xl.getCellData("Requirement Category", RequirementCategory, 8);
			String type = xl.getCellData("Requirement Category", RequirementCategory, 9);
			String recordAction = xl.getCellData("Requirement Category", RequirementCategory, 10);

			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));

			gen.SetVal(Element, By.name("Requirement_Category"), Requirement_Category);

			gen.SetVal(Element, By.name("RequirementCategory_Description"), RequirementCategory_Description);

			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			xl.setCellDataOutput(path, "Requirement Category_output", RequirementCategory, "Requirement_Category",
					Requirement_Category);
			xl.setCellDataOutput(path, "Requirement Category_output", RequirementCategory,
					"RequirementCategory_Description", RequirementCategory_Description);
			xl.setCellDataOutput(path, "Requirement Category_output", RequirementCategory, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "Requirement Category_output", RequirementCategory, "id_mod_user", id_mod_user);

			// Save Changes
			PSMSCommonElements.popElements(recordAction);
			xl.setCellDataOutput(path, "Requirement Category_output", RequirementCategory, "recordAction",
					recordAction);

			// throw error alert message
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//div[text()='Requirement Category already exists']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = Element.findElement(By.xpath("// div[@class='text-danger mt-1']")).getText();
				Assert.assertEquals(errormessage, "Requirement Category already exists");

				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				xl.setCellData("Requirement Category", "Message", RequirementCategory, errormessage);
				xl.setCellData("Requirement Category", "Status", RequirementCategory, "FAIL");
			} else {
				xl.setCellData("Requirement Category", "Message", RequirementCategory, "Record inserted successfully");
				xl.setCellData("Requirement Category", "Status", RequirementCategory, "PASS");
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
