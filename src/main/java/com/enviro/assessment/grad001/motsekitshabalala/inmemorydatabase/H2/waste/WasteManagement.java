


package com.enviro.assessment.grad001.motsekitshabalala.inmemorydatabase.H2.waste;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity(name = "waste_management")
public class WasteManagement {

	protected WasteManagement() {}

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message ="Waste Category: enter atleast 2 characters")

	private String wasteCategory;
	@Size(min=2, message ="Disposal Guidelines: enter atleast 2 characters")
	private String disposalGuidelines;
	@Size(min=2, message ="Recycling Tips: enter atleast 2 characters")
    private String recyclingTips;

	public WasteManagement(Integer id, String wasteCategory, String disposalGuidelines, String recyclingTips) {
		super();
		this.id = id;
		this.wasteCategory = wasteCategory;
		this.disposalGuidelines = disposalGuidelines;
		this.recyclingTips = recyclingTips;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWasteCategory() {
		return wasteCategory;
	}

	public void setWasteCategory(String wasteCategory) {
		this.wasteCategory = wasteCategory;
	}

	public String getDisposalGuidelines() {
		return disposalGuidelines;
	}

	public void setDisposalGuidelines(String disposalGuidelines) {
		this.disposalGuidelines = disposalGuidelines;
	}

	public String getRecyclingTips() {
		return recyclingTips;
	}

	public void setRecyclingTips(String recyclingTips) {
		this.recyclingTips = recyclingTips;
	}

	@Override
	public String toString() {
		return "Waste [id=" + id + ", wasteCategory=" + wasteCategory + ", disposalGuidelines=" + disposalGuidelines
				+ ", recyclingTips=" + recyclingTips + "]";
	}


}
