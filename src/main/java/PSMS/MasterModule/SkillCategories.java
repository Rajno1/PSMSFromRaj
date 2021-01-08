package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class SkillCategories {
	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// SkillCategories
	public void skillCategories() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("SkillCategories");
		col = xl.getColumnCount("SkillCategories");
		// Excel sheet data count
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		// Click on master module
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		// sub-module following to client type
		gen.menuType(Element, "//ul[@role='menu']//a", "Skill Categories");
		// create col
		xl.addColumn("SkillCategories", "Message");

		xl.addColumn("SkillCategories", "Status");

		// Iterator for loop
		for (int SkillCategories = 2; SkillCategories <= (row + 1); SkillCategories++) {
			System.out.println("SkillCategories count : " + SkillCategories);

			String skill_cat_name = xl.getCellData("SkillCategories", SkillCategories, 0);
			String tx_valid_reason = xl.getCellData("SkillCategories", SkillCategories, 1);
			String Id_Ins_User = xl.getCellData("SkillCategories", SkillCategories, 2);
			String id_mod_user = xl.getCellData("SkillCategories", SkillCategories, 3);
			String dt_ins = xl.getCellData("SkillCategories", SkillCategories, 4);
			String dt_mod = xl.getCellData("SkillCategories", SkillCategories, 5);
			String skill_cat_desc = xl.getCellData("SkillCategories", SkillCategories, 6);
			String Checkstatus = xl.getCellData("SkillCategories", SkillCategories, 7);
			search = xl.getCellData("SkillCategories", SkillCategories, 8);
			String type = xl.getCellData("SkillCategories", SkillCategories, 9);
			String recordAction = xl.getCellData("SkillCategories", SkillCategories, 10);
			// Add new record

			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
			// nm_client_type

			gen.SetVal(Element, By.name("skill_cat_name"), skill_cat_name);
			// tx_valid_reason

			gen.SetVal(Element, By.name("tx_valid_reason"), tx_valid_reason);
			// client_desc

			gen.SetVal(Element, By.name("skill_cat_desc"), skill_cat_desc);

			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			// Checkstatus
			// gen.clickwebElement(Element, By.id("Checkstatus"));
			xl.setCellDataOutput(path, "SkillCategories", SkillCategories, "skill_cat_name", skill_cat_name);
			xl.setCellDataOutput(path, "SkillCategories", SkillCategories, "tx_valid_reason", tx_valid_reason);
			xl.setCellDataOutput(path, "SkillCategories", SkillCategories, "skill_cat_desc", skill_cat_desc);
			xl.setCellDataOutput(path, "SkillCategories", SkillCategories, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "SkillCategories", SkillCategories, "id_mod_user", id_mod_user);

			// Save Changes
			PSMSCommonElements.popElements(recordAction);
			// throw error alert message
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//div[text()='Skill Category already exists']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = Element.findElement(By.xpath("//div[text()='Skill Category already exists']"))
						.getText();
				Assert.assertEquals(errormessage, "Skill Category already exists");

				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				xl.setCellData("SkillCategories", "Message", SkillCategories, errormessage);
				xl.setCellData("SkillCategories", "Status", SkillCategories, "FAIL");
			} else {
				xl.setCellData("SkillCategories", "Message", SkillCategories, "Record inserted successfully");
				xl.setCellData("SkillCategories", "Status", SkillCategories, "PASS");
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
