package com.aptiva.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.aptiva.model.Car;
import com.aptiva.model.Office;

public interface OfficeRepo extends CassandraRepository<Office, Integer> {

}
