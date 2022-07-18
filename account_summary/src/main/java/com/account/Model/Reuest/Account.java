package com.account.Model.Reuest;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Account {
	@MongoId
	private String account;
	private float balance;
	private String accType;
	private String date;

}
