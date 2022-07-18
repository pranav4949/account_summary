 package com.account.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.Model.Response.AccountSummary;
import com.account.Model.Reuest.Account;
import com.account.Service.AccountServiceImpl;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountServiceImpl customerServiceImpl;

	@GetMapping("/all")
	public ResponseEntity<List<Account>> allCustomer() {
		List<Account> customerList = customerServiceImpl.allCustomer();
		return new ResponseEntity<List<Account>>(customerList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountSummary> allCustomer(@PathVariable("id") String account_id) {
		AccountSummary account = customerServiceImpl.findAccount(account_id);
		return new ResponseEntity<AccountSummary>(account, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Account> saveCustomer(@RequestBody Account customer) throws ParseException {
		Account customer1 = customerServiceImpl.saveCustomer(customer);
		return new ResponseEntity<Account>(customer1, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id) {
		String message = customerServiceImpl.deleteById(id);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Account> updateCustomer(@PathVariable String id,@RequestBody Account customer){
		Account cust=customerServiceImpl.updateCustomer(id,customer);
		return new ResponseEntity<Account>(cust,HttpStatus.OK);
	}
	


}
