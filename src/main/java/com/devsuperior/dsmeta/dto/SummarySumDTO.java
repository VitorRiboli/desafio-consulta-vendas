package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SummaryProjection;

public class SummarySumDTO {

  private String name;
  private Long sum;

  public SummarySumDTO() {
  }

  public SummarySumDTO(String name, Long sum) {
    this.name = name;
    this.sum = sum;
  }

  public SummarySumDTO(SummaryProjection projection) {
    this.name = projection.getName();
    this.sum = projection.getSum();
  }

  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "CategorySumDTO [name=" + name + ", sum=" + sum + "]";
	}

}