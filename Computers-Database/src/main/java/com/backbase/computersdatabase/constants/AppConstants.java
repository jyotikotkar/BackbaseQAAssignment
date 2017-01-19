package com.backbase.computersdatabase.constants;
/**
 * Application level constants used 
 * @author jyoti
 * 1. Supported browsers
 * 2. Waiting Time
 * 3. File Paths
 * 4. Page Headers 
 */
public class AppConstants {
	
	//Supported Browser Types
	public static final String FIREFOX_BROWSER = "FIREFOX";
	public static final String CHROME_BROWSER = "CHROME";
	public static final String IE_BROWSER = "IE";
	
	//Default waiting Time
	public static final int DEFAULT_WAIT_TIME = 15;
	
	//APP level Properties info  
	public static final String APPLICATION_PROPERTIES_FILE_PATH = "src\\test\\resources\\application_file.properties";	
	public static final String APPLICATION_LOCATORS_FILE_PATH = "src\\test\\resources\\application_locators.properties";
	
	//APP Level Test Data 
	public static final String TEST_DATA_JSON_FILE_PATH = "src\\test\\resources\\ComputersTestData.json";
	
	//App level Page Headers 
	public static final String HOME_PAGE_HEADER="Play sample application — Computer database";
	public static final String ADD_COMPUTER_HEADER = "Add a computer";
	public static final String HOME_PAGE_DONE_MESSAGE= "Done!";
	public static final String UPDATE_PAGE_HEADER= "Edit computer";
	public static final String DELETE_PAGE_DONE_MESSAGE="Done!";
	public static final String READ_PAGE_NOT_DONE_MESSAGE="No computers found";
	

	
	
	
	

}
