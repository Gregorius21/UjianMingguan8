package com.juaracoding.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="keberangkatan")
public class KeberangkatanModel {
	
	@Id
	@GeneratedValue
	private long id;
	private int harga;
	private String kelas;
	private String tanggal;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="idJurusan", referencedColumnName = "id")
	private JurusanModel idJurusan;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="noPolisi", referencedColumnName = "noPolisi")
	private BusModel noPolisi;

}
