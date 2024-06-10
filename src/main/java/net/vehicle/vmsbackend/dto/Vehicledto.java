package net.vehicle.vmsbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.sql.Date;

import lombok.AllArgsConstructor;
//Lombok annotations to generate boilerplate code such as getters, setters, and constructors
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicledto {
	private long id;
	private String reg_no;
	private String model;
	private String company;
	private long purchase_year;
	private Date reg_date;
	
	 // Default constructor
   public Vehicledto() {}

   // Constructor with all fields
   public Vehicledto(long id, String reg_no,Date reg_date, String model, String company, long purchase_year) {
       this.id = id;
       this.reg_no = reg_no;
       this.model = model;
       this.company = company;
       this.purchase_year = purchase_year;
       this.reg_date= reg_date;
   }

   // Getters and setters
   public long getId() {
       return id;
   }

   public void setId(long id) {
       this.id = id;
   }

   public String getReg_no() {
       return reg_no;
   }

   public void setRegNo(String regNo) {
       this.reg_no = regNo;
   }

   public String getModel() {
       return model;
   }

   public void setModel(String model) {
       this.model = model;
   }

   public String getCompany() {
       return company;
   }

   public void setCompany(String company) {
       this.company = company;
   }

   public long getPurchase_year() {
       return purchase_year;
   }

   public void setPurchaseYear(long purchase_year) {
       this.purchase_year = purchase_year;
   }
   public Date getreg_date() {
       return reg_date;
   }
   public void setreg_date(Date reg_date) {
       this.reg_date = reg_date;
   }
}
