package com.springbootpeserta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootpeserta.model.Bootcamp;
import com.springbootpeserta.repository.BootcampRepository;

@RestController
@RequestMapping("/bootcamp")
public class BootcampController {
	
	@Autowired
	BootcampRepository bootcampRepo;
	
	@GetMapping("/")
	public List<Bootcamp> getAllData()	{
		return bootcampRepo.findAll();
	}
	
	@GetMapping("/native")
	public List<Bootcamp> getAllDataNative()	{
		return bootcampRepo.findAllBootcampNative();
	}
	
	@GetMapping("/perempuanother")
	public List<Bootcamp> getPerempuanOther()	{
		return bootcampRepo.findPerempuandanOther();
	}
	
	@GetMapping("/lastdate")
	public List<Bootcamp> getLastDate()	{
		return bootcampRepo.findLastDate();
	}
}
