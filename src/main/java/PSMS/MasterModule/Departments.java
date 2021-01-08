package PSMS.MasterModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class Departments {

	public ExcelUtilities xl;
	GenericMethods gen = new GenericMethods();
	// Departments
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	private String searchvalue;
	WebDriver Element = BrowserActions.driver;

	public void DepartmentsMouble() throws Throwable {

		xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		int row = xl.getRowCount("Department");
		int col = xl.getColumnCount("Department");
		System.out.println("no.of rows:" + row + " " + "no.of columns" + col);
		gen.clickElement(Element, By.xpath("//span[text()='Master']"));
		gen.ExplicitWait(Element, 5, By.xpath("//li[text()='Departments']"));
		gen.menuType(Element, "//ul[@role='menu']//a", "Departments");
		gen.waitFor(1000);
		xl.addColumn("Department", "Status");
		for (int Department = 2; Department <= (row + 1); Department++) {

			String Name = xl.getCellData("Department", Department, 0);
			String Description = xl.getCellData("Department", Department, 1);
			String Code = xl.getCellData("Department", Department, 2);
			searchvalue = xl.getCellData("Department", Department, 3);

			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
			gen.waitFor(1000);
			gen.SetVal(Element, By.name("name"), Name);
			gen.SetVal(Element, By.xpath("//textarea[@name='description']"), Description);
			gen.SetVal(Element, By.name("code"), Code);

			xl.setCellDataOutput(path, "Department_Output", Department, "Department", Name);
			xl.setCellDataOutput(path, "Department_Output", Department, "Description text", Description);
			xl.setCellDataOutput(path, "Department_Output", Department, "code", Code);
			xl.setCellData("Department_Output", "Status", Department, "PASS");
			gen.clickElement(Element, By.xpath("//span[text()='Save Changes']"));
		}

		gen.clearText(Element, By.xpath("//input[@placeholder='Search']"));
		gen.SetVal(Element, By.xpath("//input[@placeholder='Search']"), searchvalue);
		CheckBox();
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
