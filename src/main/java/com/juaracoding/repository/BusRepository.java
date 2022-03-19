package com.juaracoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juaracoding.model.BusModel;

public interface BusRepository extends JpaRepository<BusModel, String> {
	

}
