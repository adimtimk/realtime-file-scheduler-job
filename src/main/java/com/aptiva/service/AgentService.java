package com.aptiva.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptiva.model.Agent;
import com.aptiva.repository.AgentRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class AgentService {
	@Autowired
	private AgentRepo agentRepo;
	
	public boolean saveAgentData(CSVReader csvReader) {
		try {
			csvReader.skip(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] agentData;
		List<Agent> agentLists = new ArrayList<>();
		//agentId,mobileNum,name,gender,age,nationality,officeId

		try {
			while((agentData= csvReader.readNext())!=null) {
				Agent agObject = null;
					try {
						agObject = Agent.builder().agentId(Integer.valueOf(agentData[0]))
						.mobileNum(agentData[1])
						.name(agentData[2])
						.gender(agentData[3])
						.age(Integer.valueOf(agentData[4]))
						.nationality(agentData[5])
						.officeId(agentData[6])
						.build();
						
						agentLists.add(agObject);
						
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
				
				
			}
			if(agentRepo.saveAll(agentLists)!=null) {
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
