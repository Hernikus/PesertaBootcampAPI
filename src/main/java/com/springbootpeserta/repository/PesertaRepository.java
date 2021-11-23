package com.springbootpeserta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootpeserta.model.Bootcamp;
import com.springbootpeserta.model.Id;
import com.springbootpeserta.model.Nama;
import com.springbootpeserta.model.Alamat;
import com.springbootpeserta.model.Peserta;
import com.springbootpeserta.model.NamadanAlamat.NamadanAlamat;

public interface PesertaRepository extends JpaRepository <Peserta, Long>{
	List<Peserta> findByNama(String nama);
	
	@Query(value="SELECT * From peserta WHERE notelp is NULL",nativeQuery = true)
	List<Peserta> findTidakPunyaTelpon();
	
	@Query(value="SELECT COUNT(nama) as Jumlah FROM peserta",nativeQuery = true)
	int countPeserta();
	
	@Query(value="SELECT DISTINCT alamat FROM peserta",nativeQuery = true)
	List<Alamat> groupByAlamat();
	
	@Query(value="SELECT DISTINCT alamat FROM peserta where idjk = 1 ",nativeQuery = true)
	List<Alamat> alamatPria();
	
	@Query(value="SELECT DISTINCT nama, alamat FROM peserta where idjk = 1 ",nativeQuery = true)
	List<NamadanAlamat> findNamaAlamatPria();
	
	@Query(value="SELECT DISTINCT id FROM peserta where notelp is NULL ",nativeQuery = true)
	List<Id> FindNotelpNull();
	
	@Query(value="SELECT DISTINCT nama FROM peserta where idjk=?1",nativeQuery = true)
	List<Nama> FindNamabyidjk(int idjk);
	
	Peserta findByUsername(String username);
}
