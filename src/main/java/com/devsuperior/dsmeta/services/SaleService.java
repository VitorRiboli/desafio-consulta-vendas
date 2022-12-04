package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummarySumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;


@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}


	//SQL report
	public Page<SaleDTO> report(String minDate, String maxDate, String name, Pageable pageable) {
		//Data atual do sistema
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

		Page<Sale> list = repository.report(min, max, name, pageable);
		
		repository.reportSalesWithSellers(list.getContent());

		return list.map(x -> new SaleDTO(x));
	}

	//SQL
	public Page<SummarySumDTO> summary (String minDate, String maxDate, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate min = minDate.equals("") ? today.minusYears(1L) : LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

		Page<SummaryProjection> list = repository.summary(min, max, pageable);

		Page<SummarySumDTO> result = list.map(x -> new SummarySumDTO(x));

		return result;
	}
	
	




}
