package com.aptiva.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.aptiva.model.Agent;

public interface AgentRepo extends CassandraRepository<Agent, Integer>{

}
