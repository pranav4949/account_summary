package com.account.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.account.Model.Reuest.Account;

public interface AccountRepository extends MongoRepository<Account,String>{

	

}
