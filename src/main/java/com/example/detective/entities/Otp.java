package com.example.detective.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Otp {
	@Id
	private Integer id;
	private Integer code;
	private Date expires;
}
