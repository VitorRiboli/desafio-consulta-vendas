package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleDTO {
  
  private Long id;
  private LocalDate date;
  private Double amount;
  private String sellerName;

  public SaleDTO() {
  }
  public SaleDTO(Long id, LocalDate date, Double amount, String sellerName) {
    this.id = id;
    this.date = date;
    this.amount = amount;
    this.sellerName = sellerName;
  }

  public SaleDTO(Sale saleEntity) {
    this.id = saleEntity.getId();
    date = saleEntity.getDate();
    amount = saleEntity.getAmount();
    sellerName = saleEntity.getSeller().getName();
}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  public LocalDate getDate() {
    return date;
  }
  public void setDate(LocalDate date) {
    this.date = date;
  }
  public Double getAmount() {
    return amount;
  }
  public void setAmount(Double amount) {
    this.amount = amount;
  }
  public String getSellerName() {
    return sellerName;
  }
  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }

  

  

}
