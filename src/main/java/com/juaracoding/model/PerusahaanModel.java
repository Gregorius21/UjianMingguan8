package com.juaracoding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="perusahaan")
public class PerusahaanModel {
	
	@Id
	@Column(length = 30)
	private String nama;
	private String alamat;

}
