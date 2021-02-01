package com.example.phase3week1.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.phase3week1.model.Driver;

public class DriverService {
	
	static public List<String> validate(String[] fields) {
		List<String> errors = new ArrayList<>();
		
		//check for empty fields
		for(int i = 0; i < fields.length/2; i++) {
			if(fields[i] == null || fields[i].length() < 1) {
				errors.add("Please fill entire form");
				break;
			}
		}
		
		//if errors are found return them and do not continue validations
		if(!(errors.isEmpty())) {
			return errors;
		}
		
		/******* REGEX ************
		 * ^[0-9]{7}$ 								(Driver License must be seven numbers)
		 * ^[a-zA-Z]{1,25}$							(First Name/Middle Name/Last Name must be between 1 and 25 letters)
		 * ^([0-9]{4}\-[01][0-9]\-[0123][0-9])$ 	(Date must be in the format of YYYY-MM-DD)
		 * ^([0-9]{3,5}\s[a-zA-Z\s]{2,40})$			(Address format Invalid)
		 * ^[a-zA-Z]{2,40}$							(City must be between 2 and 40 letters)
		 * ^([A-Z]{2})$								(State must be 2 capital letters)
		 * ^([0-9]{5})$								(ZIP must be 5 numbers)
		 */
		
		Pattern idPattern = Pattern.compile("^[0-9]{7}$");
		Pattern namePattern = Pattern.compile("^[a-zA-Z]{1,25}$");
		Pattern datePattern = Pattern.compile("^([0-9]{4}\\-[01][0-9]\\-[0123][0-9])$");
		Pattern addressPattern = Pattern.compile("^([0-9]{3,5}\\s[a-zA-Z\\s]{1,40})$");
		Pattern cityPattern = Pattern.compile("^[a-zA-Z\\s]{2,40}$");
		Pattern statePattern = Pattern.compile("^([A-Z]{2})$");
		Pattern zipPattern = Pattern.compile("^([0-9]{5})$");
		Matcher matcher;
		
		final List<String> STATES = new ArrayList<>(Arrays.asList("AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA",
				"HI","ID","IL","IN","IA","KS","KY","LA","ME","MA","MD","MI","MN",
				"MS","MO","MY","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK",
				"OR","PA","PR","RI","SC","SD","TN","TX","UT","VT","VA","VI","WA",
				"WV","WI","WY"));
		
		//compare to regex patterns
		boolean match;
		for(int i=0; i < fields.length-1; i++) {
			switch(i) {
			case 0:	// ID check
				matcher = idPattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("Driver License must be 7 numbers only");
				}
				try {
					Integer.parseInt(fields[i]);
				}catch(Exception e) {
					errors.add("Driver License must be NUMBERS ONLY");
				}
				break;
			case 1:	// First Name check
				matcher = namePattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("First Name cannot be more than 25 letters");
				}
				break;
			case 2:	// Middle Name check
				matcher = namePattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("Midde Name cannot be more than 25 letters");
				}
				break;
			case 3:	// Last Name check
				matcher = namePattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("Last Name cannot be more than 25 letters");
				}
				break;
			case 4:	// Date of Birth check
				matcher = datePattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("Date of Birth must be in the format of YYYY-MM-DD");
				}
				break;
			case 5:	// Issue Date check
				matcher = datePattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("Issue Date must be in the format of YYYY-MM-DD");
				}
				break;
			case 6:	// Expire Date check
				matcher = datePattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("Expire Date must be in the format of YYYY-MM-DD");
				}
				break;
			case 7:	// address check
				matcher = addressPattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("Address cannot be more than 40 characters");
				}
				break;
			case 8:	// city check
				matcher = cityPattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("City cannot be more than 40 characters");
				}
				break;
			case 9: // state check
				matcher = statePattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("State must be 2 capital letters	");
				}
				if(!STATES.contains(fields[i])) {
					errors.add("Invald state abbreviation");
				}
				break;
			case 10: // zip code check
				matcher = zipPattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("ZIP code must be 5 numbers");
				}
				try {
					Integer.parseInt(fields[i]);
				}catch(Exception e) {
					errors.add("ZIP Code must be NUMBERS ONLY");
				}
				break;
			default:
				break;
			}

		}
		
		return errors;
	}
	
	public static Driver convert(String[] fields) {
		Driver driver = new Driver();
		SimpleDateFormat ymd = new SimpleDateFormat("yyy-mm-dd");
		Date date;
		
		driver.setDriverId(Integer.parseInt(fields[0]));
		driver.setFirstName(fields[1]);
		driver.setMiddleName(fields[2]);
		driver.setLastName(fields[3]);
		
		try {
			date = ymd.parse(fields[4]);
			driver.setDOB(date);
			
			date = ymd.parse(fields[5]);
			driver.setIssueDate(date);
			
			date = ymd.parse(fields[6]);
			driver.setExpireDate(date);
		}catch(Exception e) {
			System.out.println("Error in processing dates");
		}
		driver.setAddress(fields[7]);
		driver.setCity(fields[8]);
		driver.setState(fields[9]);
		driver.setZip(fields[10]);
		driver.setSex(fields[11]);

		return driver;
	}
}
