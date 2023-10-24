package com.example.detective.entities;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
        scope = Info.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "info")
@Indexed


public class Info implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "info_Id", index = Index.NO, store = Store.YES)
    private Long infoId;
    
    @NotNull
    @Field(store = Store.YES)
    private String type;
    
    
    @NotNull
    @Field(store = Store.YES)
    private String name;

    @NotNull
    @Field(store = Store.YES)
    private float price;

    @NotNull
    @Field(store = Store.YES)
    @Column(columnDefinition="text")
    private String description;

    @NotNull
    @Field(store = Store.YES)
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
    
}

