package com.example.detective.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Freedoms {
	@Id
	private Integer uniqueId;
	private String username;
	private float amount;
}
