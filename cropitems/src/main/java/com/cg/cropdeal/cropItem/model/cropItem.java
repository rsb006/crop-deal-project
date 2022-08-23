package com.cg.cropdeal.cropItem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Crop")
public class cropItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String cropid;
	private String cropname;
	private String croptype;
	private String cropqnt;
	private double cropprice;
	
	
	private cropItem() { //outside cannot instantiate

	}
	
	//setter and getter method is used to access variable outside the class
	public String getCropid() {
		return cropid;
	}
	public void setCropid(String cropid) {
		this.cropid = cropid;
	}
	public String getCropname() {
		return cropname;
	}
	public void setCropname(String cropname) {
		this.cropname = cropname;
	}
	public String getCroptype() {
		return croptype;
	}
	public void setCroptype(String croptype) {
		this.croptype = croptype;
	}
	public String getCropqnt() {
		return cropqnt;
	}
	public void setCropqnt(String cropqnt) {
		this.cropqnt = cropqnt;
	}
	public double getCropprice() {
		return cropprice;
	}
	public void setCropprice(double cropprice) {
		this.cropprice = cropprice;
	}
		
}
