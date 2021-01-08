package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class Skills extends BrowserActions {
	GenericMethods gen = new GenericMethods();
	public String search;
	public int row;
	public int col;
	private ExcelUtilities xl;
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;

	// Skills
	public void skills() throws Exception {
		// path of excel sheet
		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		row = xl.getRowCount("Skills");
		col = xl.getColumnCount("Skills");
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));

		gen.menuType(Element, "//ul[@role='menu']//a", "Skills");
		xl.addColumn("Skills", "Message");
		xl.addColumn("Skills", "Status");
		for (int skillsdata = 2; skillsdata <= (row + 1); skillsdata++) {
			System.out.println("skillsdata count :" + skillsdata);
			String Skills = xl.getCellData("Skills", skillsdata, 0);
			String TX_ValidReason = xl.getCellData("Skills", skillsdata, 1);
			String Id_Ins_User = xl.getCellData("Skills", skillsdata, 2);
			String id_mod_user = xl.getCellData("Skills", skillsdata, 3);
			String dt_ins = xl.getCellData("Skills", skillsdata, 4);
			String dt_mod = xl.getCellData("Skills", skillsdata, 5);
			String skill_desc = xl.getCellData("Skills", skillsdata, 6);
			String Checkstatus = xl.getCellData("Skills", skillsdata, 7);
			search = xl.getCellData("Skills", skillsdata, 8);
			String type = xl.getCellData("Skills", skillsdata, 9);
			String recordAction = xl.getCellData("Skills", skillsdata, 10);

			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
			gen.waitFor(1000);
			gen.SetVal(Element, By.xpath("//input[@name='skill_name']"), Skills);
			gen.SetVal(Element, By.xpath("//textarea[@name='skill_desc']"), skill_desc);
			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			xl.setCellDataOutput(path, "Skills", skillsdata, "Skills", Skills);
			xl.setCellDataOutput(path, "Skills", skillsdata, "TX_ValidReason", TX_ValidReason);
			xl.setCellDataOutput(path, "Skills", skillsdata, "skill_desc", skill_desc);
			xl.setCellDataOutput(path, "Skills", skillsdata, "Id_Ins_User", Id_Ins_User);
			xl.setCellDataOutput(path, "Skills", skillsdata, "id_mod_user", id_mod_user);
			// Save Changes

			PSMSCommonElements.popElements(recordAction);
			// throw error alert message
			Element.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (!Element.findElements(By.xpath("//div[@id='ealertbox']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = Element.findElement(By.xpath("//div[@id='ealertbox']")).getText();
				// Assert.assertEquals(errormessage, "Skill Already Exists");
				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				xl.setCellData("Skills", "Message", skillsdata, errormessage);
				xl.setCellData("Skills", "Status", skillsdata, "FAIL");
			} else {
				xl.setCellData("Skills", "Message", skillsdata, "Record inserted successfully");
				xl.setCellData("Skills", "Status", skillsdata, "PASS");
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
