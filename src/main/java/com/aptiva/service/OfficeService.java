package com.aptiva.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptiva.model.Office;
import com.aptiva.repository.OfficeRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class OfficeService {
	@Autowired
	private OfficeRepo officeRepo;
	
	public boolean saveOfficeData(CSVReader csvReader) {
		try {
			csvReader.skip(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] officeData;
		List<Office> offLists = new ArrayList<>();
		//offficeId,mobileNum,area,officeNum,officeWorkingHrs

		try {
			while((officeData= csvReader.readNext())!=null) {
				Office ofObject = null;
					try {
						ofObject = Office.builder().officeId(Integer.valueOf(officeData[0]))
						.mobileNum(officeData[1])
						.area(officeData[2])
						.ofNum(officeData[3])
						.officeHrs(officeData[4])
						.build();
						
						offLists.add(ofObject);
						
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
				
				
			}
			if(officeRepo.saveAll(offLists)!=null) {
				return true;
			}else {
				return false;
			}
		} catch (CsvValidationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}
}
