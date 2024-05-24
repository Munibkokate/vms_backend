package net.vehicle.vmsbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "reg_no",nullable=false, unique = true)
	private String reg_no;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "purchase_year")
	private long purchase_year;
	
    // Default constructor
    public Vehicle() {}

    // Constructor with all fields
    public Vehicle(long id, String reg_no, String model, String company, long purchase_year) {
        this.id = id;
        this.reg_no = reg_no;
        this.model = model;
        this.company = company;
        this.purchase_year = purchase_year;
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public long getPurchase_year() {
		return purchase_year;
	}
	public void setPurchase_year(long purchase_year) {
		this.purchase_year = purchase_year;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

}

