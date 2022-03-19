package com.juaracoding.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="booking")
public class BookingModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="idKeberangkatan",referencedColumnName = "id")
	private KeberangkatanModel idKeberangkatan;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="nik", referencedColumnName = "nik")
	private PenumpangModel nik;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="idJurusan", referencedColumnName = "id")
	private JurusanModel idJurusan;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="noPolisi",referencedColumnName = "noPolisi")
	private BusModel noPolisi;
	
	private String tanggal;
}
