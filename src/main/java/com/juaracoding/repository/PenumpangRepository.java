package com.juaracoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juaracoding.model.PenumpangModel;

public interface PenumpangRepository extends JpaRepository<PenumpangModel, String> {

	PenumpangModel findByUsername(String username);

}
