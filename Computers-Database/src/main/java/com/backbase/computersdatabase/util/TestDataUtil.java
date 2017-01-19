package com.backbase.computersdatabase.util;

import com.backbase.computersdb.model.ComputerInformation;
/**
 * API for generating test data objects for testing
 * @author jyoti
 * 1. getComputerRecordForCreation
 */
public class TestDataUtil extends CommonsUtil {
	
	public static ComputerInformation getComputerRecordForCreation(){
		ComputerInformation computerInformation = new ComputerInformation();
		try{
			computerInformation.setComputerName(readDataFromJsonFile("Create", "computerName"));
			computerInformation.setIntroducedDate(readDataFromJsonFile("Create", "introducedDate"));
			computerInformation.setDiscontinuedDate(readDataFromJsonFile("Create", "discontinuedDate"));
			computerInformation.setCompany(readDataFromJsonFile("Create", "company"));
		}catch(Exception ex ){
			ex.printStackTrace();
		}		
		return computerInformation;
	}
	
	
	public static ComputerInformation getComputerRecordForUpdate(){
		ComputerInformation computerInformation = new ComputerInformation();
		try{
			computerInformation.setComputerName(readDataFromJsonFile("Update", "computerName"));			
		}catch(Exception ex ){
			ex.printStackTrace();
		}		
		return computerInformation;
	}
	
	

}
