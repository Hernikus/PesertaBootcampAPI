package com.springbootpeserta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootpeserta.config.JwtTokenUtil;
import com.springbootpeserta.model.Bootcamp;
import com.springbootpeserta.model.Alamat;
import com.springbootpeserta.model.JenisKelamin;
import com.springbootpeserta.model.Nama;
import com.springbootpeserta.model.Peserta;
import com.springbootpeserta.model.Id;
import com.springbootpeserta.model.NamadanAlamat.NamadanAlamat;
import com.springbootpeserta.repository.KelaminRepository;
import com.springbootpeserta.repository.PesertaRepository;
import com.springbootpeserta.service.JWTUserDetailsService;

@RestController
@RequestMapping("/peserta")
public class PesertaController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private JWTUserDetailsService userDetailsService;
	
	@Autowired
	PesertaRepository pesertaRepo;
	
	@PostMapping("/")
	public String postData(@RequestBody Peserta peserta)	{
		pesertaRepo.save(peserta);
		return "Berhasil ditambahkan";
	}
	@GetMapping("/")
	public List<Peserta> getAllData()	{
		return pesertaRepo.findAll();
	}
	@GetMapping("/nama/{nama}")
    public List<Peserta> getAllDataByNama(@PathVariable(value="nama") String nama) {
		return pesertaRepo.findByNama(nama);
     }
	@GetMapping("/update/{temp}")
	public String updateId(@RequestBody Peserta peserta) 	{
		pesertaRepo.save(peserta);
		return "Update Selesai";
	}
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") long id)	{
		pesertaRepo.deleteById(id);
		return "Delete Berhasil";
	}
	@GetMapping("/telpon")
	public List<Peserta> getNoTelpNULL()	{
		return pesertaRepo.findTidakPunyaTelpon();
	}
	@GetMapping("/countPeserta")
	public String getTotalPeserta()	{
		return "Total peserta ada "+pesertaRepo.countPeserta();
	}
	@GetMapping("/groupByAlamat")
	public List<Alamat> groupByAlamat()	{
		return pesertaRepo.groupByAlamat();
	}
	@GetMapping("/AlamatPria")
	public List<Alamat> AlamatPria()	{
		return pesertaRepo.alamatPria();
	}
	@GetMapping("/NamadanAlamatPria")
	public List<NamadanAlamat> NamadanAlamatPria()	{
		return pesertaRepo.findNamaAlamatPria();
	}
	@GetMapping("/NoTelpNull")
	public List<Id> Id()	{
		return pesertaRepo.FindNotelpNull();
	}
	@GetMapping("/NamabyJK")
	public List<Nama> NamabyJk(@RequestParam (value="idjk") int jk)	{
		return pesertaRepo.FindNamabyidjk(jk);
	}
	
	@Autowired
	KelaminRepository kelaminRepo;
	
	@PostMapping("/kelamin")
	public String postData(@RequestBody JenisKelamin kelamin)	{
		kelaminRepo.save(kelamin);
		return "Berhasil ditambahkan";
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Peserta authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(token);
	}
	
	private void authenticate(String username, String password) throws Exception	{
		try	{
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e)	{
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e)	{
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}	
}
