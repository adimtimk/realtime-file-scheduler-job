package com.aptiva.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SchedulerService {
	
	@Value("${car.inbox.path}")
	private String cfilePath;
	@Value("${agent.inbox.path}")
	private String afilePath;
	@Value("${office.inbox.path}")
	private String ofilePath;
	@Autowired
	private CSVService csvService;
	
	@Scheduled(fixedRate = 10000)
	public void scheduleCarReferenceJob() {
		if(csvService.checkIfFileAvailable(cfilePath)) {
			 String type="CAR";
			if(csvService.readCsvFromFilePathAndSave(cfilePath,type)) {
				log.info("Read the Car data from {} and save to Cassandra successfully ",cfilePath); 

			 }else {
				 log.info("Read the Car data from {} and save to Cassandra failed ",cfilePath); 
			 }
		}
		
	}
	
	@Scheduled(fixedRate = 5000)
	public void scheduleOfficeReferenceJob() {
		if(csvService.checkIfFileAvailable(ofilePath)) {
			 String type="OFFICE";
			if(csvService.readCsvFromFilePathAndSave(ofilePath,type)) {
				log.info("Read the Car data from {} and save to Cassandra successfully ",cfilePath); 

			 }else {
				 log.info("Read the Car data from {} and save to Cassandra failed ",cfilePath); 
			 }
		}
	}
	
	@Scheduled(fixedRate = 3000)
	public void scheduleAgentReferenceJob() {
		if(csvService.checkIfFileAvailable(afilePath)) {
			 String type="AGENT";
			if(csvService.readCsvFromFilePathAndSave(afilePath,type)) {
				log.info("Read the Car data from {} and save to Cassandra successfully ",cfilePath); 

			 }else {
				 log.info("Read the Car data from {} and save to Cassandra failed ",cfilePath); 
			 }
		}
	}
	
}
