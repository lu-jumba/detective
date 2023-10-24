package com.example.detective.dto;

import lombok.Getter;


@Getter

public class InfoDto {
    private Long infoId;
    private String type;
    private String name;
    private float price;
    private String description;
    private String serialNo;
    
     public Long getInfoId() {
        return infoId;
    }
   
   public void setInfoId(Long infoId){
       this.infoId = infoId;
   }

    public String getType() {
        return type;
	}
    
    public void setType(String type){
       this.type = type;
   }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name){
       this.name = name;
   }

	public float getPrice() {
        return price;
	}
        
        public void setPrice(float price){
       this.price = price;
   }

    public String getDescription() {
        return description;
	}
    public void setDescription(String description){
       this.description = description;
   }

    public String getSerialNo(){
		return serialNo;
	}
    
    public void setSerialNo(String serialNo){
       this.serialNo = serialNo;
   }
    
	
	public InfoDto (Long infoId, String type, String name, float price, String description, String serialNo) {
		this.infoId = infoId;
		this.type = type;
		this.name = name;
		this.price = price;
		this.description = description;
		this.serialNo = serialNo;
	}

   
    
}
