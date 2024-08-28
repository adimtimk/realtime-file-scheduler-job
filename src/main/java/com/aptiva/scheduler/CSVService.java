package com.aptiva.scheduler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aptiva.service.AgentService;
import com.aptiva.service.CarService;
import com.aptiva.service.OfficeService;
import com.opencsv.CSVReader;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class CSVService {
	@Autowired
	private CarService carService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private OfficeService officeService;
	
	public  boolean checkIfFileAvailable(String filepath ) {
		Path path = Paths.get(filepath);
		if(Files.exists(path)) {
			log.info("File is present on path - {}",path.getFileName());
			return true;
		}else {
			log.info("File is not present on path - {}",path.getFileName());
			return false;
		}
	}
	
	
	public  boolean readCsvFromFilePathAndSave(String filepath,String type) {
		CSVReader csvReader = null;
		boolean result = false;
		try {
			csvReader = new CSVReader(new FileReader(filepath));
			if(csvReader !=null) {
				if(type.equals("CAR")) {
					 result = carService.saveCarData(csvReader);
				}
				if(type.equals("OFFICE")) {
					 result = officeService.saveOfficeData(csvReader);
				}
				if(type.equals("AGENT")) {
					 result = agentService.saveAgentData(csvReader);
				}
			}
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(clearPath(filepath)) {
		log.info("Filepath  {} was cleaned ",filepath); 
	}else {
		log.info("Filepath  {} was not cleaned ",filepath); 
	}
		
		return result;
		
	}


	public boolean clearPath(String filePath) {
		Path path = Paths.get(filePath);
		if(Files.exists(path)) {
			try {
				return Files.deleteIfExists(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
		// TODO Auto-generated method stub
		
	}
	
	
	

}
