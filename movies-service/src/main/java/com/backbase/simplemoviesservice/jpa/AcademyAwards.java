package com.backbase.simplemoviesservice.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class AcademyAwards {
	@Id
	@CsvBindByName
	private String nominee;
	@CsvBindByName
	private String category;
	@CsvBindByName
	private String winner;
	
	public AcademyAwards(String nominee, String category, String winner) {
		super();
		this.nominee = nominee;
		this.category = category;
		this.winner = winner;
	}
	
	public AcademyAwards() {
	}
	public String getNominee() {
		return nominee;
	}
	public void setNominee(String nominee) {
		this.nominee = nominee;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
}
