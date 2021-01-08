package Utlities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class GenericMethods {
	public Boolean blnRunStatus;
	public SoftAssert softassert = new SoftAssert();
	public String executionMessage = "";
	public String strPropertyValue = "";
	public String ClickElementMessage = "";

	/*
	 * ##############################################################
	 * 
	 * @param driver - This is driver object
	 * 
	 * @param previoswindowId - previous window id
	 * 
	 * @return - it will return current window id as well
	 * ##############################################################
	 */
	public String switchToWindow(WebDriver driver, String previoswindowId) {

		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!previoswindowId.equals(windowHandle)) {
				driver.switchTo().window(windowHandle);
			}
		}
		String currenWindow = driver.getWindowHandle();
		return currenWindow;
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Click Element Javascript
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ##############################################################
	 */
	public void ClickElementJs(WebDriver driver, By sObjectType) {
		try {

			WebElement Object = driver.findElement(sObjectType);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", Object);

		} catch (Exception e) {
			e.getMessage();
			executionMessage = "WebDriver " + sObjectType.getClass() + " does not exist";
		}
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- Takes Screenshot
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * ##############################################################
	 */
	public static String getScreenShot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenShot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (Exception e) {
			System.out.println("Capture failed" + e.getMessage());
		}
		return path;
	}

	/*
	 * #####################################################
	 * 
	 * @Descriptions ---verify the text
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ####################################################
	 */

	public String verifytext(WebDriver driver, By sObjectType) throws Exception {
		boolean flag = false;
		try {
			WebElement Obj = driver.findElement(sObjectType);
			String tempValue = Obj.getText();
			if (tempValue.isEmpty()) {
				tempValue = Obj.getAttribute("value");
				flag = true;
			} else {
				flag = false;
			}
			return tempValue;
			// System.out.println("Xpath :" + sObjectType + "<==>" + "inputValue :" +
			// tempValue);
		} catch (Exception e) {
			e.getMessage();
			executionMessage = "Webelement " + sObjectType.getClass() + " does not exist";
		}
		return null;
	}

	/*
	 * #####################################################
	 * 
	 * @Descriptions ---verify the click
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ####################################################
	 */
	public boolean verifyClick(WebDriver driver, By sObjectType) throws Exception {
		boolean flag = false;
		try {
			WebElement Obj = driver.findElement(sObjectType);
			ExplicitWait(driver, 30, sObjectType);
			Obj.click();
			// executionMessage = "Webelement " + sObjectType + " in exist";
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			executionMessage = "Webelement " + sObjectType + " does not exist";

		}
		return false;
	}

	/*
	 * #####################################################
	 * 
	 * @Descriptions --- Clear the text
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ####################################################
	 */
	public void clearText(WebDriver driver, By sObjectType) {
		try {
			driver.findElement(sObjectType).sendKeys(Keys.CONTROL, "a", Keys.DELETE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			executionMessage = "Webelement " + sObjectType.getClass() + " does not exist";
		}
	}

	/*
	 * #####################################################
	 * 
	 * @Descriptions --- Select the value from the Dropdown
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ####################################################
	 */
	public void selectElementValFromDropDown(WebDriver driver, By xpathExpress, String Val2Select) {
		try {
			Select dropdown = new Select(driver.findElement(xpathExpress));
			dropdown.selectByVisibleText(Val2Select);
		} catch (Exception e) {
			e.getMessage();
			executionMessage = "Webelement " + xpathExpress + " does not exist";
		}
	}

	/*
	 * ##################################################### function to pause the
	 * 
	 * @Descriptions --- execution for the specified time period
	 * 
	 * @param millisecond the wait time in millisecond
	 * #####################################################
	 */
	public void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			softassert.assertAll();
			e.printStackTrace();
		}
	}

	/*
	 * ################################################
	 * 
	 * @Descriptions ---Enters the text in a field and clear the text
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ################################################
	 */
	public void setText(WebDriver driver, By sObjectType, String Val2Set) {
		try {
			driver.findElement(sObjectType).sendKeys(Keys.CONTROL, "a", Keys.DELETE);
			Thread.sleep(2000);
			driver.findElement(sObjectType).sendKeys(Val2Set);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.getMessage();
			softassert.assertAll();
			executionMessage = "Webelement " + sObjectType + " does not exist";
		}
	}

	/*
	 * ################################################
	 * 
	 * @Descriptions --- adds day to the current date
	 * 
	 * @param days--- no of days to add
	 * ################################################
	 */
	public String addDaysToCurrentDate(int days) {
		String date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, days);
			date = sdf.format(c.getTime());
		} catch (Exception e) {
			e.getMessage();
			softassert.assertAll();
		}
		return date;
	}

	/*
	 * ################################################
	 * 
	 * @Descriptions ---Enters the text in a field
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ################################################
	 */
	public String SetVal(WebDriver driver, By sObjectType, String sInputVal) {
		boolean blnResult = false;
		String pagetitle = null;
		int iTimer = 0;
		try {
			do {
				waitFor(3000);
				List<WebElement> sList = driver.findElements(sObjectType);
				if (sList.size() > 0) {
					for (int i = 0; i <= sList.size(); i++) {
						if (sList.get(i).isDisplayed() && sList.get(i) != null && sList.get(i).isEnabled()) {
							pagetitle = driver.getTitle();
							WebDriverWait wait = new WebDriverWait(driver, 60);
							wait.until(ExpectedConditions.elementToBeClickable(sList.get(i)));
							sList.get(i).sendKeys(Keys.CONTROL, "a", Keys.DELETE);
							sList.get(i).sendKeys(sInputVal);
							blnResult = true;
							break;
						}
					}
				}
				if (!blnResult) {
					Thread.sleep(1000);
					iTimer = iTimer + 1;
				}
			} while ((blnResult != true) && (iTimer != 60));

			// Flag returns false
			if (blnResult != true) {
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return sInputVal;
	}
	/*
	 * ################################################
	 * 
	 * @Descriptions ---Click at the webElement
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ################################################
	 */

	public void clickwebElement(WebDriver driver, By sObjectType) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(sObjectType);
			if (element.isDisplayed()) {
				if (element.isEnabled()) {
					waitFor(3000);
					element.click();
					blnRunStatus = true;
					flag = true;
				} else {
					flag = false;
				}
			}

			// System.out.println(executionMessage = "Webelement " + sObjectType + "
			// exist");
		} catch (Exception e) {
			blnRunStatus = false;
			e.getMessage();
			System.out.println(executionMessage = "Webelement " + sObjectType.getClass() + " does not exist");
		}
	}

	public boolean clickElement(WebDriver driver, By sObjectType) throws InterruptedException {
		try {
			WebElement element = driver.findElement(sObjectType);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(sObjectType));
			waitFor(3000);
			element.click();
			return true;
		} catch (Exception e) {
			e.getMessage();
			executionMessage = "Webelement " + sObjectType.getClass() + " does not exist";
			return false;
		}
	}
	/*
	 * ################################################
	 * 
	 * @Descriptions ---Iterating the Drop Down values
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param xpathExpress -- xpath of the Web Element
	 * ################################################
	 */

	public String menuType(WebDriver driver, String path, String menu) {
		try {
			List<WebElement> menuItem = driver.findElements(By.xpath(path));
			for (int i = 0; i < menuItem.size(); i++) {
				String menuList = menuItem.get(i).getText();
				if (menuList.equals(menu)) {
					menuItem.get(i).click();
					break;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			executionMessage = "Webelement " + path.getClass() + " does not exist";
		}
		return menu;
	}

	/*
	 * ################################################
	 * 
	 * @Descriptions ---Conditional Synchronization:Explicit Wait
	 * 
	 * @param driver -- WebDriver parameter
	 * 
	 * @param millisecond the wait time in millisecond
	 * ################################################
	 */

	public void ExplicitWait(WebDriver driver, int time, By sObjectType) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(sObjectType));
	}
	/*
	 * ################################################
	 * 
	 * @Descriptions ---Random String generate
	 * 
	 * ################################################
	 */

	public String randomstring(int count) {
		String generateString2 = RandomStringUtils.randomAlphabetic(count);
		return (generateString2);
	}

	/*
	 * ################################################
	 * 
	 * @Descriptions ---Random integer generate
	 * 
	 * ################################################
	 */
	public String randomint() {
		String generateint = RandomStringUtils.randomNumeric(0);
		return (generateint);
	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- scroll to Element
	 * 
	 * @param driver--WebDriver
	 * 
	 * @param xpathExpress- xpath expression of the element
	 * ##############################################################
	 */
	public void scrollToElement(WebDriver driver, String xpathExpress) throws InterruptedException {

		try {

			ExplicitWait(driver, 30, By.xpath(xpathExpress));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathExpress)));
			ExplicitWait(driver, 30, By.xpath(xpathExpress));

		} catch (Exception e) {
			e.getMessage();
			executionMessage = "WebDriver " + xpathExpress.getClass() + " does not exist";
		}
	}
	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- MoveToElement
	 * 
	 * @param driver--WebDriver
	 * 
	 * @param xpathExpress- xpath expression of the element
	 * ##############################################################
	 */

	public void navigateMouseToElement(WebDriver driver, By sObjectType) throws Exception {
		try {
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(sObjectType));
			WebElement webElement = driver.findElement(sObjectType);
			Actions builder = new Actions(driver);
			builder.moveToElement(webElement).perform();
			Thread.sleep(5000);
		} catch (Exception e) {
			e.getMessage();
			executionMessage = "WebDriver " + sObjectType + " does not exist";
		}
	}
	/*
	 * ##############################################################
	 * 
	 * @Descriptions --- is Alert Present
	 * 
	 * @param driver--WebDriver
	 * 
	 * ##############################################################
	 */

	public boolean isAlertPresent(WebDriver driver) {

		boolean presentFlag = false;

		try {
			// Check the presence of alert
			Alert alert = driver.switchTo().alert();
			// Alert present; set the flag
			presentFlag = true;
			// if present consume the alert
			alert.accept();
			String Error = alert.getText();
			System.out.println(Error);
		} catch (NoAlertPresentException ex) {
			// Alert not present
			// ex.printStackTrace();
			System.out.println("Alert not found");
		}

		return presentFlag;
	}
}
