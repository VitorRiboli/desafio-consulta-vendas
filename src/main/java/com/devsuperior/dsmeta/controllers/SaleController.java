package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummarySumDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	
	//JPQL
	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleDTO>> getReport(
		@RequestParam(value = "minDate", defaultValue = "") String minDate, 
		@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
		@RequestParam(value = "name", defaultValue = "") String name,
		Pageable pageable) {
			
		Page<SaleDTO> result = service.report(minDate, maxDate, name, pageable);

		return ResponseEntity.ok(result);
	}
	

	//SQL Raiz
	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SummarySumDTO>> getSummary(
		@RequestParam(value = "minDate", defaultValue = "") String minDate, 
		@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
		Pageable pageable
	) {
		
		Page<SummarySumDTO> result = service.summary(minDate, maxDate, pageable);
		
		return ResponseEntity.ok(result);
	}
}
