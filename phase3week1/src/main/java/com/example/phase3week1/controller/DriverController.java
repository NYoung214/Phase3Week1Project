package com.example.phase3week1.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.phase3week1.model.Driver;
import com.example.phase3week1.model.DriverDao;
import com.example.phase3week1.service.DriverService;

@Controller
public class DriverController {
	
	Logger logger = LoggerFactory.getLogger(DriverController.class);
	List<String> errors = new ArrayList<>();
	
	@Autowired
	DriverDao driverDao;

	@GetMapping("/driver")
	public String sendDriver(ModelMap map) {
		logger.info("going to driver.jsp");
		return "driver.jsp";
	}
	
	@GetMapping("/edit")
	public String sendUpdate(ModelMap map) {
		logger.info("going to edit.jsp");
		return "edit.jsp";
	}
	
	@GetMapping("/search")
	public String sendSearch(ModelMap map) {
		logger.info("Going to search.jsp");
		return "search.jsp";
	}
	
	@GetMapping("/add")
	public String sendAdd(ModelMap map) {
		logger.info("going to add.jsp");
		return "add.jsp";
	}
	
	@PostMapping("/search")
	public String search(@RequestParam String driverId, @RequestParam String option, ModelMap map) {
		errors.clear();
		if(driverId.length()<1 || driverId == null) {
			map.addAttribute("errors", "Please enter a number");
			return "search.jsp";
		}
		if(driverId.length() != 7) {
			map.addAttribute("errors", "Driver License must be seven numbers");
			return "search.jsp";
		}
		
		try {
			logger.info("Trying to convert driverId ("+driverId+") to an int...");
			int id = Integer.parseInt(driverId);
			logger.info("conversion successful...");
			logger.info("checking for driverID ("+driverId+") in database...");
			boolean check = driverDao.existsById(id);
			if(check) {
				logger.info("driverId found. Displaying info on "+option+".jsp");
				Driver driver = driverDao.findById(id).get();
				//check to see if button was SEARCH or EDIT
				if(option.equals("edit")) {
					map.addAttribute("driverId", driver.getDriverId());
					map.addAttribute("firstName", driver.getFirstName());
					map.addAttribute("middleName", driver.getMiddleName());
					map.addAttribute("lastName", driver.getLastName());
					map.addAttribute("DOB", driver.getDOB());
					map.addAttribute("issueDate", driver.getIssueDate());
					map.addAttribute("expireDate", driver.getExpireDate());
					map.addAttribute("address", driver.getAddress());
					map.addAttribute("city", driver.getCity());
					map.addAttribute("state", driver.getState());
					map.addAttribute("zip", driver.getZip());
					map.addAttribute("sex", driver.getSex());
					map.addAttribute("trueId", driverId);
					return "edit.jsp";
				}
				map.addAttribute("driver",driver);				
				return "driver.jsp";
			}
			logger.info("driverId ("+driverId+") not found, returning to search.jsp");
			map.addAttribute("errors","Driver with ID ("+driverId+") was not found");
			return "search.jsp";			
		}catch(NumberFormatException e) {
			logger.info("could not convert driverId ("+driverId+") to int...");
			errors.add("Not an integer");
			return "search.jsp";
		}catch(IllegalArgumentException e) {
			logger.info("could not find driverId ("+driverId+") in database...");
			errors.add("Not in database");
			return "search.jsp";
		}catch(Exception e) {
			logger.info("unknown error occured...");
			errors.add(e.getMessage());
			return "search.jsp";
		}

	}
	
	@PostMapping("/add")
	public String add(@RequestParam String driverId, @RequestParam String trueId, @RequestParam String firstName, @RequestParam String middleName, 
			@RequestParam String lastName, @RequestParam String DOB, @RequestParam String issueDate, 
			@RequestParam String expireDate, @RequestParam String address, @RequestParam String city, 
			@RequestParam String state, @RequestParam String zip, @RequestParam String sex, @RequestParam String operation, ModelMap map) {
		String[] fields = {driverId, firstName, middleName, lastName, DOB, issueDate, expireDate, address, city, state, zip, sex, operation};
		
		logger.info("Operation: "+operation+"... attempting to validate submitted fields...");
		errors = DriverService.validate(fields);

		if(errors.isEmpty() && operation.equals("edit") && driverDao.existsById(Integer.parseInt(driverId))) {
			errors.add("Driver ID already in use");
		}
		
		if(!errors.isEmpty()){
			logger.info("error found in fields submitted...");
			
			map.addAttribute("firstName", firstName);
			map.addAttribute("middleName", middleName);
			map.addAttribute("lastName", lastName);
			map.addAttribute("DOB", DOB);
			map.addAttribute("issueDate", issueDate);
			map.addAttribute("expireDate", expireDate);
			map.addAttribute("address", address);
			map.addAttribute("city", city);
			map.addAttribute("state", state);
			map.addAttribute("zip", zip);
			map.addAttribute("sex", sex);			
			
			if(operation.equals("edit")) {
				map.addAttribute("driverId", trueId);
				logger.info("returning to edit.jsp");
				errors.add("<span style=\"color:black;\">*RESETTING DRIVER ID*</span>");
				map.addAttribute("errors", errors);
				return "edit.jsp";
			}
			map.addAttribute("driverId", driverId);
			logger.info("returning to add.jsp");
			map.addAttribute("errors", errors);
			return "add.jsp";
		}
		
		if(operation.equals("add") && driverDao.existsById(Integer.parseInt(driverId))) {
			logger.info("found driver ID match in database, returning to add.jsp");
			map.addAttribute("driverId", driverId);
			map.addAttribute("firstName", firstName);
			map.addAttribute("middleName", middleName);
			map.addAttribute("lastName", lastName);
			map.addAttribute("DOB", DOB);
			map.addAttribute("issueDate", issueDate);
			map.addAttribute("expireDate", expireDate);
			map.addAttribute("address", address);
			map.addAttribute("city", city);
			map.addAttribute("state", state);
			map.addAttribute("zip", zip);
			map.addAttribute("sex", sex);		
	
			errors.add("Driver ID already in use.");
			return "add.jsp";
		}
		
		logger.info("no errors found, attempting to convert fields to int (driverId) and date (date of birth, issue date, expire date)");
		Driver driver = DriverService.convert(fields);		
		logger.info("fields converted successfully...");
		
		if(operation.equals("edit")) {
			logger.info("removing previous copy of driver");
			driverDao.deleteById(Integer.parseInt(trueId));
		}
		driverDao.save(driver);
		
		if(operation.equals("edit")) {
			logger.info("driver updated in database succesfully, returning to edit.jsp");
			map.addAttribute("success","Driver Successfully Updated");
			return "edit.jsp";
		}
		logger.info("new driver saved in database succesfully, returning to add.jsp");
		map.addAttribute("success","New Driver Successfully Added");
		return "add.jsp";
	}

	
}
