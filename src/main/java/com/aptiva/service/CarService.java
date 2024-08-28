package com.aptiva.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptiva.model.Car;
import com.aptiva.repository.CarRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
@Service
public class CarService {

	@Autowired
	private CarRepo carRepo;
	
	public boolean saveCarData(CSVReader csvReader) {
		try {
			csvReader.skip(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] carData;
		List<Car> carLists = new ArrayList<>();
		try {
			while((carData= csvReader.readNext())!=null) {
				Car carObject = null;
					try {
						carObject = Car.builder().carId(Integer.valueOf(carData[0]))
						.carMaker(carData[1])
						.carModel(carData[2])
						.carPlateNum(carData[3])
						.carRegisDate(new SimpleDateFormat("yyyy-MM-dd").parse(carData [4]))
						.carExpiryDate(new SimpleDateFormat("yyyy-MM-dd").parse(carData [5]))
						.build();
						
						carLists.add(carObject);
						
					} catch (NumberFormatException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
				
				
			}
			if(carRepo.saveAll(carLists)!=null) {
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
