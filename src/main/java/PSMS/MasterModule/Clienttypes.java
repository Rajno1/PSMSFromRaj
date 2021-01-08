package PSMS.MasterModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ExcelData.ExcelUtilities;
import Utlities.BrowserActions;
import Utlities.GenericMethods;
import Utlities.PSMSCommonElements;

public class Clienttypes {
	GenericMethods gen = new GenericMethods();
	public String search;
	
	public String path = "OutputData\\MasterModuleOutput.xlsx";
	WebDriver Element = BrowserActions.driver;
	private String clientType;

	// Client Type
	public void clientPage() throws Throwable {
		// path of excel sheet
		ExcelUtilities xl = new ExcelUtilities("Resource\\MasterModule.xlsx");
		
		
		
		
		
		int row = xl.getRowCount("ClientType_sheet");
		int col = xl.getColumnCount("ClientType_sheet");
		

		// Excel sheet data count
		System.out.println("number of rows:" + row + " " + "number of columns" + col);
		
	//	for (int rowcount = 2; rowcount < row; rowcount++) {

			int rowcount = 2;
			// Click on master module
			gen.clickElement(Element, By.xpath("//span[text()='Master']"));
			
			// sub-module following to client type
			gen.menuType(Element, "//ul[@role='menu']//a", "Client types");
			
			// Add new record
			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
						
			
			
			System.out.println("Clientdata count : " + rowcount);

			String clientTypeText = xl.getCellData("ClientType_sheet", rowcount, 0);
			String TX_ValidReason = xl.getCellData("ClientType_sheet", rowcount, 1);
			String Id_Ins_User = xl.getCellData("ClientType_sheet", rowcount, 2);
			String id_mod_user = xl.getCellData("ClientType_sheet", rowcount, 3);
			String dt_ins = xl.getCellData("ClientType_sheet", rowcount, 4);
			String dt_mod = xl.getCellData("ClientType_sheet", rowcount, 5);
			String client_desc = xl.getCellData("ClientType_sheet", rowcount, 6);
			String Checkstatus = xl.getCellData("ClientType_sheet", rowcount, 7);
			search = xl.getCellData("ClientType_sheet", rowcount, 8);
			String type = xl.getCellData("ClientTYpe", rowcount, 9);
			String recordAction = xl.getCellData("ClientTYpe", rowcount, 10);
			
			
			// nm_client_type
			gen.SetVal(Element, By.name("nm_client_type"), clientTypeText);
			
			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "ClientType", clientTypeText); //set value to output excel
			// tx_valid_reason
			
			// client_desc
			gen.SetVal(Element, By.name("client_desc"), client_desc);
			
			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "client_desc", client_desc);
			
			
			gen.SetVal(Element, By.name("tx_valid_reason"), TX_ValidReason);
			
			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "TX_ValidReason", TX_ValidReason);
			
			
			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Id_Ins_User", Id_Ins_User);
			
			
			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);

			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "id_mod_user", id_mod_user);
			
			// Save Changes
			gen.clickElement(Element, By.xpath("//span[text()='Save Changes']"));
			
			// throw error alert message
			Element.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
			
			if (!Element.findElements(By.xpath("//div[text()='Client Type already exists']")).isEmpty()) {
				Element.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				String errormessage = Element.findElement(By.xpath("// div[@class='text-danger mt-1']")).getText();
				Assert.assertEquals(errormessage, "Client Type already exists");

				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
				
				xl.addColumn("ClientType_sheet", "Message");
				xl.setCellData("ClientType_sheet", "Message", rowcount, errormessage);
				
				xl.addColumnOutput(path, "clientTypeOutput", "Message");
				xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Message", "Record Already Exists");
				
				xl.addColumn("ClientType_sheet", "Status");
				xl.setCellData("ClientType_sheet", "Status", rowcount, "FAIL");
				
				xl.addColumnOutput(path, "clientTypeOutput", "Status");	
				xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Status", "FAIL");
		
			} else if(!Element.findElements(By.xpath("//div[@class='alert alert-success']")).isEmpty()){
				Element.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				//String message = Element.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
				
				xl.addColumn("ClientType_sheet", "Message");
				xl.setCellData("ClientType_sheet", "Message", rowcount, "Record Added");
				
				xl.addColumnOutput(path, "clientTypeOutput", "Message");
				xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Message", "Record Added");
				
				xl.addColumn("ClientType_sheet", "Status");
				xl.setCellData("ClientType_sheet", "Status", rowcount, "PASS");
				
				xl.addColumnOutput(path, "clientTypeOutput", "Status");	
				xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Status", "PASS");
				
			}
			gen.waitFor(3000);
		
			
		//}
		
		// Iterator for loop
//		for (int rowcount = 2; rowcount <= (row + 1); rowcount++) {
//			
//			// Click on master module
//			gen.clickElement(Element, By.xpath("//span[text()='Master']"));
//			
//			// sub-module following to client type
//			gen.menuType(Element, "//ul[@role='menu']//a", "Client types");
//			
//			// Add new record
//			gen.ClickElementJs(Element, By.xpath("//button[@title='Add Record']"));
//						
//			
//			
//			System.out.println("Clientdata count : " + rowcount);
//
//			String clientTypeText = xl.getCellData("ClientType_sheet", rowcount, 0);
//			String TX_ValidReason = xl.getCellData("ClientType_sheet", rowcount, 1);
//			String Id_Ins_User = xl.getCellData("ClientType_sheet", rowcount, 2);
//			String id_mod_user = xl.getCellData("ClientType_sheet", rowcount, 3);
//			String dt_ins = xl.getCellData("ClientType_sheet", rowcount, 4);
//			String dt_mod = xl.getCellData("ClientType_sheet", rowcount, 5);
//			String client_desc = xl.getCellData("ClientType_sheet", rowcount, 6);
//			String Checkstatus = xl.getCellData("ClientType_sheet", rowcount, 7);
//			search = xl.getCellData("ClientType_sheet", rowcount, 8);
//			String type = xl.getCellData("ClientTYpe", rowcount, 9);
//			String recordAction = xl.getCellData("ClientTYpe", rowcount, 10);
//			
//			
//			// nm_client_type
//			gen.SetVal(Element, By.name("nm_client_type"), clientTypeText);
//			
//			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "ClientType", clientTypeText); //set value to output excel
//			// tx_valid_reason
//			
//			// client_desc
//			gen.SetVal(Element, By.name("client_desc"), client_desc);
//			
//			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "client_desc", client_desc);
//			
//			
//			gen.SetVal(Element, By.name("tx_valid_reason"), TX_ValidReason);
//			
//			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "TX_ValidReason", TX_ValidReason);
//			
//			
//			gen.setText(Element, By.xpath("//input[@name='id_ins_user']"), Id_Ins_User);
//			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Id_Ins_User", Id_Ins_User);
//			
//			
//			gen.setText(Element, By.xpath("//input[@name='id_mod_user']"), id_mod_user);
//
//			xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "id_mod_user", id_mod_user);
//			
//			// Save Changes
//			gen.clickElement(Element, By.xpath("//span[text()='Save Changes']"));
//			
//			// throw error alert message
//			Element.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		
//			
//			if (!Element.findElements(By.xpath("//div[text()='Client Type already exists']")).isEmpty()) {
//				Element.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//				String errormessage = Element.findElement(By.xpath("// div[@class='text-danger mt-1']")).getText();
//				Assert.assertEquals(errormessage, "Client Type already exists");
//
//				gen.clickElement(Element, By.xpath("//span[text()='Close']"));
//				
//				xl.addColumn("ClientType_sheet", "Message");
//				xl.setCellData("ClientType_sheet", "Message", rowcount, errormessage);
//				
//				xl.addColumnOutput(path, "clientTypeOutput", "Message");
//				xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Message", "Record Already Exists");
//				
//				xl.addColumn("ClientType_sheet", "Status");
//				xl.setCellData("ClientType_sheet", "Status", rowcount, "FAIL");
//				
//				xl.addColumnOutput(path, "clientTypeOutput", "Status");	
//				xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Status", "FAIL");
//		
//			} else if(!Element.findElements(By.xpath("//div[@class='alert alert-success']")).isEmpty()){
//				Element.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//				String message = Element.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
//				
//				
//				xl.addColumn("ClientType_sheet", "Message");
//				xl.setCellData("ClientType_sheet", "Message", rowcount, message);
//				
//				xl.addColumnOutput(path, "clientTypeOutput", "Message");
//				xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Message", message);
//				
//				xl.addColumn("ClientType_sheet", "Status");
//				xl.setCellData("ClientType_sheet", "Status", rowcount, "PASS");
//				
//				xl.addColumnOutput(path, "clientTypeOutput", "Status");	
//				xl.setCellDataOutput(path, "clientTypeOutput", rowcount, "Status", "PASS");
//			}
//			gen.waitFor(3000);
//		}
		
	}
}
