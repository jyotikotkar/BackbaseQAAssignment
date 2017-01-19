package com.backbase.computersdatabase.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.backbase.computersdatabase.constants.AppConstants;
import com.backbase.computersdatabase.util.EventsUtil;
import com.backbase.computersdatabase.util.TestDataUtil;
import com.backbase.computersdatabase.util.CommonsUtil;
import com.backbase.computersdb.model.ComputerInformation;

/**
 * Main Test Invoked By Test NG
 * CRUD Operations Test
 * @author jyoti
 *
 */
public class ComputersDatabaseCRUDTest extends CommonsUtil {

	String xpathForHomePageTitle = readLocatorsProperties("home_page_title_Xpath");
	
	String doneMessageXPath = readLocatorsProperties("home_page_done_Message_Xpath");

	@BeforeTest
	public void setUp() {
		System.out.print("Inside setUp....... ");
		String browserName = readApplicationProperties("browserForTestRun");
		initializeDriver(browserName);
		loadApplicationByURL();

		if (EventsUtil.isNavigatedToCorrectPage(xpathForHomePageTitle, AppConstants.HOME_PAGE_HEADER)) {
			driver.manage().window().maximize();
			waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		} else {
			System.out.println("Application URL Not acessible....!");

		}

	}

	@Test(priority = 1)
	public void createNewComputerRecord() {
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		System.out.print("Inside createNewComputerRecord....... ");
		EventsUtil.clickWebElementr(By.id(readLocatorsProperties("home_page_add_button_id")));
		String xpathForPageTitle = readLocatorsProperties("create_page_header_Xpath");
		Assert.assertTrue(EventsUtil.isNavigatedToCorrectPage(xpathForPageTitle, AppConstants.ADD_COMPUTER_HEADER));
		// Navigated to Add a computer page
		System.out.println("Navigated to Correct Page- Add a computer");
		ComputerInformation compInfo = TestDataUtil.getComputerRecordForCreation();
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		EventsUtil.enterTextInTextbox(By.id(readLocatorsProperties("name_Text_Id")), compInfo.getComputerName());
		EventsUtil.enterTextInTextbox(By.id(readLocatorsProperties("introducedDate_Text_Id")),
				compInfo.getIntroducedDate());
		EventsUtil.enterTextInTextbox(By.id(readLocatorsProperties("discontinuedDate_Text_Id")),
				compInfo.getDiscontinuedDate());
		EventsUtil.selectDropDownByText(By.id(readLocatorsProperties("company_Select_Id")), compInfo.getCompany());
		EventsUtil.clickWebElementr(By.xpath(readLocatorsProperties("create_button_Xpath")));
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);

		// after click navigated to Home page & done message shown

		Assert.assertTrue(EventsUtil.isNavigatedToCorrectPage(doneMessageXPath, AppConstants.HOME_PAGE_DONE_MESSAGE));

	}

	@Test(priority = 2)
	public void readComputerRecord() {
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		ComputerInformation compInfo = TestDataUtil.getComputerRecordForCreation();
		System.out.print("Inside readComputerRecord....... ");
		EventsUtil.enterTextInTextbox(By.id(readLocatorsProperties("home_page_search_text_id")),
				compInfo.getComputerName());
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		EventsUtil.clickWebElementr(By.id(readLocatorsProperties("home_page_search_button_id")));
		WebElement deleteMsg = EventsUtil.getWebElement(By.xpath(readLocatorsProperties("read_message_Xpath")));
		Assert.assertNotEquals(AppConstants.READ_PAGE_NOT_DONE_MESSAGE, deleteMsg.getText());

	}

	@Test(priority = 3)
	public void updateComputerRecord() {
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		System.out.print("Inside updateComputerRecord....... ");
		String xpathForHomePageTitle = readLocatorsProperties("home_page_title_Xpath");
		Assert.assertTrue((EventsUtil.isNavigatedToCorrectPage(xpathForHomePageTitle, AppConstants.HOME_PAGE_HEADER)));
		ComputerInformation compInfo = TestDataUtil.getComputerRecordForUpdate();
		// update start
		EventsUtil.clickWebElementr(By.xpath(readLocatorsProperties("home_page_table_first_row")));
		
		Assert.assertTrue((EventsUtil.isNavigatedToCorrectPage(readLocatorsProperties("update_page_title_Xpath"),
				AppConstants.UPDATE_PAGE_HEADER)));
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		System.out.println("Navigated to Correct Page - Edit Computer");
		WebElement elementToUpdate = EventsUtil.getWebElement((By.id("name")));
		String beforeUpdateName = elementToUpdate.getText();
		EventsUtil.clearWebElement(elementToUpdate);
		EventsUtil.enterTextInTextbox(By.id("name"), compInfo.getComputerName());
		EventsUtil.clickWebElementr(By.xpath(readLocatorsProperties("update_Page_Save_button_Xpath")));
		// update end
		// message check : updated contains
		// updated content with old and new value

		Assert.assertTrue(EventsUtil.isNavigatedToCorrectPage(doneMessageXPath, AppConstants.HOME_PAGE_DONE_MESSAGE));
		EventsUtil.enterTextInTextbox(By.id(readLocatorsProperties("home_page_search_text_id")),
				compInfo.getComputerName());
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		EventsUtil.clickWebElementr(By.id(readLocatorsProperties("home_page_search_button_id")));
		Assert.assertNotNull(EventsUtil.getWebElement(By.xpath(readLocatorsProperties("home_page_table_Xpath"))));

	}

	@Test(priority = 4)
	public void deleteComputerRecord() {
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		System.out.print("Inside deleteComputerRecord....... ");
		ComputerInformation compInfo = TestDataUtil.getComputerRecordForUpdate();
		// For delete view  start
		EventsUtil.clickWebElementr(By.xpath(readLocatorsProperties("home_page_table_first_row")));
		Assert.assertTrue((EventsUtil.isNavigatedToCorrectPage(readLocatorsProperties("update_page_title_Xpath"),
				AppConstants.UPDATE_PAGE_HEADER)));
		System.out.println("Navigated to Correct Page - Delete Computer");
		EventsUtil.clickWebElementr(By.xpath(readLocatorsProperties("delete_button_Xpath")));
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		WebElement deleteMsg = EventsUtil.getWebElement(By.xpath(readLocatorsProperties("delete_message_Xpath")));
		Assert.assertEquals(AppConstants.DELETE_PAGE_DONE_MESSAGE, deleteMsg.getText());
	}

	@AfterTest
	public void tearDown() {
		waitForUserInterfaceToLoad(AppConstants.DEFAULT_WAIT_TIME);
		System.out.print("Inside tearDown....... ");
		driver.close();
	}

}
