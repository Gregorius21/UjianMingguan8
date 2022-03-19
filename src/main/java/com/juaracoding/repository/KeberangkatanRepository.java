package com.juaracoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.model.KeberangkatanCustomModel;
import com.juaracoding.model.KeberangkatanModel;

public interface KeberangkatanRepository extends JpaRepository<KeberangkatanModel, Long> {
	
	@Query(value="SELECT keberangkatan.id, bus.`nama_perusahaan`, `kelas`,`harga`,`tanggal`, "
			+ "jurusan.`deskripsi` FROM keberangkatan INNER JOIN bus ON bus.no_polisi = keberangkatan.no_polisi "
			+ "INNER JOIN jurusan ON jurusan.id = keberangkatan.id_jurusan WHERE jurusan.terminal_awal = ?1 "
			+ "OR tanggal like ?2%",nativeQuery = true)
	List<KeberangkatanCustomModel>getAllDataTerminalAwalOrTanggal(String terminalawal, String tanggal);
}