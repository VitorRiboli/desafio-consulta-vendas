package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;


public interface SaleRepository extends JpaRepository<Sale, Long> {

  
  //JPQL
  @Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
  Page<Sale> report(LocalDate min, LocalDate max, String name, Pageable pageable);
  //JPQL
  @Query("SELECT obj FROM Sale obj JOIN FETCH obj.seller WHERE obj in :sales")
  List<Sale> reportSalesWithSellers(List<Sale> sales);

  //SQL Raiz
  @Query(nativeQuery = true, 
  value = "SELECT tb_seller.name, SUM(tb_sales.amount) AS sum " +
        "FROM tb_sales " +
        "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
        "WHERE tb_sales.date BETWEEN :min AND :max " +
        "GROUP BY tb_seller.name " +
        "ORDER BY tb_seller.name " )
  Page<SummaryProjection> summary(LocalDate min, LocalDate max, Pageable pageable);
  
}
