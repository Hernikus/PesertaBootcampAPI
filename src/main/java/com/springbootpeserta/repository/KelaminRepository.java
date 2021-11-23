package com.springbootpeserta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootpeserta.model.JenisKelamin;

public interface KelaminRepository extends JpaRepository <JenisKelamin, Long>{

}
