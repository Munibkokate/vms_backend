package net.vehicle.vmsbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
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
	
	
	 // Default constructor
   public Vehicledto() {}

   // Constructor with all fields
   public Vehicledto(long id, String reg_no, String model, String company, long purchase_year) {
       this.id = id;
       this.reg_no = reg_no;
       this.model = model;
       this.company = company;
       this.purchase_year = purchase_year;
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
}
