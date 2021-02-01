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
	
	
	
	
	
	@GetMapping("/search")
	public String sendSearch(ModelMap map) {
		logger.info("Going to search.jsp");
		return "search.jsp";
	}
	
	@PostMapping("/search")
	public String search(String driverId , ModelMap map) {
		errors.clear();
		if(driverId.length()<1 || driverId == null) {
			map.addAttribute("errors", "Please enter a number");
			return "search.jsp";
		}
		if(driverId.length()<7) {
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
				logger.info("driverId found. Displaying info on driver.jsp.");
				map.addAttribute("driver",driverDao.findById(id).get());
				return "driver.jsp";
			}
			logger.info("driverId ("+driverId+") not found, returning to search.jsp");
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
	
	@GetMapping("/add")
	public String sendAdd(ModelMap map) {
		logger.info("going to add.jsp");
		return "add.jsp";
	}
	
	@PostMapping("/add")
	public String add(@RequestParam String driverId,  @RequestParam String firstName, @RequestParam String middleName, 
			@RequestParam String lastName, @RequestParam String DOB, @RequestParam String issueDate, 
			@RequestParam String expireDate, @RequestParam String address, @RequestParam String city, 
			@RequestParam String state, @RequestParam String zip, @RequestParam String sex, ModelMap map) {
		String[] fields = {driverId, firstName, middleName, lastName, DOB, issueDate, expireDate, address, city, state, zip, sex};
		
		logger.info("attempting to validate submitted fields...");
		errors = DriverService.validate(fields);

		if(!errors.isEmpty()){
			logger.info("error found in fields submitted, returning to add.jsp");
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
			
			map.addAttribute("errors", errors);
			return "add.jsp";
		}
		logger.info("no errors found, attempting to convert fields to int (driverId) and date (date of birth, issue date, expire date)");
		Driver driver = DriverService.convert(fields);		
		logger.info("fields converted successfully...");
		driverDao.save(driver);
		logger.info("new driver saved in database succesfully, returning to add.jsp");
		map.addAttribute("success","New Driver Successfully Added");
		
		return "add.jsp";
	}
}
